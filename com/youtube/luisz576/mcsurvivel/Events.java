package com.youtube.luisz576.mcsurvivel;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.md_5.bungee.api.ChatColor;

public class Events implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(ChatColor.GREEN + "[MCSurvivel576]" + ChatColor.YELLOW + e.getPlayer().getDisplayName() + ChatColor.GREEN + " entrou!");
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(ChatColor.GREEN + "[MCSurvivel576]" + ChatColor.YELLOW + e.getPlayer().getDisplayName() + ChatColor.GREEN + " saiu!");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		if(Main.utils.isWood(e.getBlock()) &&
			e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(Main.utils.getMachadoArvores().getItemMeta().getDisplayName()))
			Main.mechanics.destroyMadeiraFrom(e.getBlock().getLocation());
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
		if(e.getItem().getType() == Material.POTION)
			Main.necessidadesData.PlayerDrink(e.getPlayer().getUniqueId().toString());
	}
	
	@EventHandler
	public void onDeathPlayer(PlayerDeathEvent e) {
		if(Main.necessidadesData.isEmptyNivelWater(e.getEntity().getUniqueId().toString()))
			e.setDeathMessage(ChatColor.RED + e.getEntity().getDisplayName() + " morreu por cede!");
		if(Main.necessidadesData.isEmptyNivelBanheiro(e.getEntity().getUniqueId().toString()))
			e.setDeathMessage(ChatColor.RED + e.getEntity().getDisplayName() + " morreu por não ir ao banheiro!");
		Main.necessidadesData.playerDie(e.getEntity().getUniqueId().toString());
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getItem() != null && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.utils.getTeleporter().getItemMeta().getDisplayName()))
				Main.teleports.openInv(e.getPlayer());
			if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(Main.utils.getSeterTeleport().getItemMeta().getDisplayName())) 
				if(Main.teleports.saveNewTeleport(e.getPlayer()))
					e.getPlayer().sendMessage(ChatColor.GREEN + "Tp setado!");
				else
					e.getPlayer().sendMessage(ChatColor.RED + "Não foi possível setar o Tp!");
		}
	}
	
	@EventHandler
	public void onInteractInventory(InventoryClickEvent e) {
		if(e.getView().getTitle().equalsIgnoreCase("§2MyTeleports")) {
			e.setCancelled(true);
			if(e.getCurrentItem() != null)
				if(Main.teleports.isCorrectTp((Player)e.getWhoClicked(), e.getCurrentItem().getItemMeta().getDisplayName()))
					Main.teleports.teleport((Player)e.getWhoClicked(), Integer.parseInt(e.getCurrentItem().getItemMeta().getDisplayName().replace("§2", "")));
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Location blockL = e.getPlayer().getLocation();
		blockL.setY(e.getPlayer().getLocation().getY() - 1);
		if(blockL.getBlock().getType() == Material.WATER) 
			if(e.getPlayer().getInventory().getBoots().getItemMeta().getDisplayName().equalsIgnoreCase(Main.utils.getIceBoots().getItemMeta().getDisplayName()))
				blockL.getBlock().setType(Material.ICE);
	}

}