package com.youtube.luisz576.mcsurvivel;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {
		if(!(sender instanceof Player))
			return false;
		Player p = (Player)sender;
		if(cmd.getName().equalsIgnoreCase("status")) {
			if(Main.necessidadesData.isAtivoNecessidades()) {
				p.sendMessage(ChatColor.YELLOW + "======= STATUS =======");
				for(String line : Main.necessidadesData.getInfoPlayer(p))
					p.sendMessage(line);
				p.sendMessage(ChatColor.YELLOW + "======================");
			}else {
				p.sendMessage(ChatColor.RED + "Necessidades não estão ativas!");
			}
		}
		if(cmd.getName().equalsIgnoreCase("banheiro")) {
			if(Main.necessidadesData.isAtivoNecessidades()) {
				if(Main.necessidadesData.PlayerUseBanheiro(p.getUniqueId().toString()))
					p.getWorld().dropItem(p.getLocation(), Main.necessidadesData.getItemDropWhenUseBanheiro());
				else
					p.sendMessage(ChatColor.YELLOW + "Você ainda não precisa usar o banheiro!");
			}else {
				p.sendMessage(ChatColor.RED + "Necessidades não estão ativas!");
			}
		}
		if(cmd.getName().equalsIgnoreCase("cleartp")) {
			if(args.length == 1) {
				int idT = 0;
				try {
					idT = Integer.parseInt(args[0]);
					Main.teleports.removeTp(p, idT);
					p.sendMessage(ChatColor.GREEN + "Tp removido!");
				}catch (Exception e) {
					p.sendMessage(ChatColor.RED + "Formatação incorreta!");
				}
			}
		}
		return false;
	}
	
}