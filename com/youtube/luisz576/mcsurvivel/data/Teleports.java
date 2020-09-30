package com.youtube.luisz576.mcsurvivel.data;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.youtube.luisz576.mcsurvivel.Main;

import net.md_5.bungee.api.ChatColor;

public class Teleports {
	
	private Config config;
	
	public Teleports() {
		config = new Config("teleports_data", Main.plugin);
	}
	
	public boolean saveNewTeleport(Player p) {
		int index = 0;
		while(index < 9) {
			if(!config.containsKey(p.getDisplayName() + "_" + index)){
				config.setValue(p.getDisplayName() + "_" + index, p.getLocation());
				index = 99;
				config.save();
				return true;
			}else 
				index++;
		}
		return false;
	}
	
	public boolean isCorrectTp(Player p, int tpId) {
		return (getLocationsPlayer(p).containsKey(tpId));
	}
	
	public boolean isCorrectTp(Player p, String tpId) {
		return (getLocationsPlayer(p).containsKey(Integer.parseInt(tpId.replace("§2", ""))));
	}
	
	public Inventory getInventory() {
		return Bukkit.createInventory(null, 9, "§2MyTeleports");
	}
	
	public void openInv(Player p) {
		Inventory inv = getInventory();
		ItemStack noTp = new ItemStack(Material.RED_WOOL);
		ItemMeta noTpMeta = noTp.getItemMeta();
		noTpMeta.setDisplayName(ChatColor.RED + "Tp não setado!");
		noTp.setItemMeta(noTpMeta);
		for(int i = 0; i < 9; i++)
			inv.setItem(i, noTp);
		for(Entry<Integer, Location> entry : getLocationsPlayer(p).entrySet()) {
			ItemStack tp = new ItemStack(Material.GREEN_WOOL);
			ItemMeta metaTp = tp.getItemMeta();
			metaTp.setDisplayName("§2" + entry.getKey());
			tp.setItemMeta(metaTp);
			inv.setItem(entry.getKey(), tp);
		}
		p.openInventory(inv);
	}
	
	public void teleport(Player p, int id) {
		p.teleport(getLocationsPlayer(p).get(id));
	}
	
	public void removeTp(Player p, int id) {
		config.remove(p.getDisplayName() + "_" + id);
		config.save();
	}
	
	private HashMap<Integer, Location> getLocationsPlayer(Player p){
		int index = 0;
		HashMap<Integer, Location> locals = new HashMap<>();
		while(index < 9) {
			if(config.containsKey(p.getDisplayName() + "_" + index))
				locals.put(index, config.getLocation(p.getDisplayName() + "_" + index));
			index++;
		}
		return locals;
	}

}