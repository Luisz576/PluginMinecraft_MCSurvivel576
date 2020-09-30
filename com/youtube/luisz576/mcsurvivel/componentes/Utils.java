package com.youtube.luisz576.mcsurvivel.componentes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utils {
	
	private ConsoleCommandSender cmd;
	
	public Utils() {
		cmd = Bukkit.getConsoleSender();
	}
	
	public void sendMessageAll(String msg) {
		for(Player p : Bukkit.getOnlinePlayers())
			p.sendMessage(msg);
	}
	
	public void sendMessageConsole(String msg) { cmd.sendMessage(msg); }
	
	//Madeira
	public boolean isWood(Block b) {
		return (
			b.getType() == Material.ACACIA_LOG ||
			b.getType() == Material.BIRCH_LOG ||
			b.getType() == Material.DARK_OAK_LOG ||
			b.getType() == Material.JUNGLE_LOG ||
			b.getType() == Material.OAK_LOG ||
			b.getType() == Material.SPRUCE_LOG
		);
	}
	
	//Ferramentas especiais
	public ItemStack getMachadoArvores() {
		ItemStack item = new ItemStack(Material.GOLDEN_AXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2MACHADO UPER");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack getIceBoots() {
		ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2ICE BOOTS");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack getTeleporter() {
		ItemStack item = new ItemStack(Material.CLOCK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2TELEPORTER");
		item.setItemMeta(meta);
		return item;
	}
	public ItemStack getSeterTeleport() {
		ItemStack item = new ItemStack(Material.ENDER_EYE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2SETER");
		item.setItemMeta(meta);
		return item;
	}

}