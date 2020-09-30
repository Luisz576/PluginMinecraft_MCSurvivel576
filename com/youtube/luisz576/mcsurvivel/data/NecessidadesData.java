package com.youtube.luisz576.mcsurvivel.data;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.youtube.luisz576.mcsurvivel.Main;

public class NecessidadesData {

	private Config config;
	private boolean isAtivo;
	
	public boolean isAtivoNecessidades() {
		return isAtivo;
	}
	
	public NecessidadesData() {
		config = new Config("Necessidades_data", Main.plugin);
		if(!config.containsKey("isAtivo"))
			config.setValue("isAtivo", true);
		isAtivo = config.getBool("isAtivo");
	}
	
	public void UpdateWater() {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getGameMode() != GameMode.CREATIVE && p.getWorld().getDifficulty() != Difficulty.PEACEFUL) {
				if(getNivelWater(p.getUniqueId().toString()) <= 0)
					p.setHealth(0);
				setNivelAgua(p.getUniqueId().toString(), getNivelWater(p.getUniqueId().toString()) - 1);
			}
		}
	}
	
	public boolean isEmptyNivelWater(String id) {
		return (getNivelWater(id) <= 0);
	}
	
	public boolean isEmptyNivelBanheiro(String id) {
		return (getNivelBanheiro(id) <= 0);
	}
	
	public void playerDie(String id) {
		setNivelAgua(id, 20);
		setNivelBanheiro(id, 20);
	}
	
	public void UpdateBanheiro() {
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.getGameMode() != GameMode.CREATIVE && p.getWorld().getDifficulty() != Difficulty.PEACEFUL) {
				if(getNivelBanheiro(p.getUniqueId().toString()) <= 0)
					p.setHealth(0);
				setNivelBanheiro(p.getUniqueId().toString(), getNivelBanheiro(p.getUniqueId().toString()) - 1);
			}
		}
	}
	
	private Integer getNivelWater(String id) {
		if(config.containsKey(id + "_water"))
			return config.getInt(id + "_water");
		setNivelAgua(id, 20);
		return 20;
	}
	
	private void setNivelAgua(String id, Integer value) {
		config.setValue(id + "_water", value);
		config.save();
	}
	
	private Integer getNivelBanheiro(String id) {
		if(config.containsKey(id + "_banheiro"))
			return config.getInt(id + "_banheiro");
		setNivelAgua(id, 20);
		return 20;
	}
	
	private void setNivelBanheiro(String id, Integer value) {
		config.setValue(id + "_banheiro", value);
		config.save();
	}
	
	public List<String> getInfoPlayer(Player p){
		List<String> lista = new ArrayList<String>();
		lista.add(ChatColor.YELLOW + "Nível de água: " + ChatColor.GREEN + getNivelWater(p.getUniqueId().toString()));
		lista.add(ChatColor.YELLOW + "Nível de banheiro: " + ChatColor.GREEN + getNivelBanheiro(p.getUniqueId().toString()));
		lista.add(ChatColor.YELLOW + "Nível de comida: " + ChatColor.GREEN + p.getFoodLevel());
		return lista;
	}
	
	public void PlayerDrink(String id) {
		int setNivel = getNivelWater(id) + 6;
		if(setNivel > 20)
			setNivel = 20;
		setNivelAgua(id, setNivel);
	}
	
	public boolean PlayerUseBanheiro(String id) {
		if(getNivelBanheiro(id) < 18) {
			setNivelBanheiro(id, 20);
			return true;
		}
		return false;
	}
	
	public ItemStack getItemDropWhenUseBanheiro() {
		ItemStack item = new ItemStack(Material.DARK_OAK_BUTTON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "POOR");
		item.setItemMeta(meta);
		return item;
	}
	
}