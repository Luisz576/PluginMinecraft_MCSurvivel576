package com.youtube.luisz576.mcsurvivel;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import com.youtube.luisz576.mcsurvivel.componentes.Mechanics;
import com.youtube.luisz576.mcsurvivel.componentes.Timer;
import com.youtube.luisz576.mcsurvivel.componentes.Utils;
import com.youtube.luisz576.mcsurvivel.data.NecessidadesData;
import com.youtube.luisz576.mcsurvivel.data.Teleports;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	
	public static Main plugin;
	public static Utils utils;
	public static Mechanics mechanics;
	public static NecessidadesData necessidadesData;
	public static Timer timer;
	public static Teleports teleports;
	
	public void onEnable() {
		//Sets
		plugin = this;
		utils = new Utils();
		mechanics = new Mechanics();
		necessidadesData = new NecessidadesData();
		teleports = new Teleports();
		//Commands
		getCommand("status").setExecutor(new Commands());
		getCommand("banheiro").setExecutor(new Commands());
		getCommand("cleartp").setExecutor(new Commands());
		//Events
		Bukkit.getPluginManager().registerEvents(new Events(), plugin);
		//Recipes
		for(ShapedRecipe recipe : mechanics.getRecipes())
			getServer().addRecipe(recipe);
		//Timer
		timer = new Timer();
		//Message
		utils.sendMessageConsole(ChatColor.GREEN + "[MCSurvivel576]" + ChatColor.YELLOW + " Plugin iniciado!");
	}

}