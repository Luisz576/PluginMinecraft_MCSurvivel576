package com.youtube.luisz576.mcsurvivel.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Config {

	private File file;
	private YamlConfiguration config;
	
	public Config(@Nonnull String arq, @Nonnull Plugin plugin) 
	{
		file = new File(plugin.getDataFolder(), arq + ".yml");
		config = YamlConfiguration.loadConfiguration(file);
		if(!file.exists()) {
			this.save();
		}
	}
	
	public YamlConfiguration getConfig() { return config; }
	public File getFile() { return file; }
	
	public void save() 
	{
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getString(String key) {
		return config.getString(key);
	}
	public int getInt(String key) {
		return config.getInt(key);
	}
	public boolean getBool(String key) {
		return config.getBoolean(key);
	}
	public List<?> getList(String key) {
		return config.getList(key);
	}
	public ItemStack getItem(String key){
		return config.getItemStack(key);
	}
	public List<Map<?, ?>> getMapList(String key){
		return config.getMapList(key);
	}
	public Map<?, ?> getMap(String key){
		return config.getMapList(key).get(0);
	}
	public Location getLocation(String key){
		String name = config.getString(key + ".world");
		World w = null;
		for(World wt : Bukkit.getWorlds()){
			if(wt.getName().equalsIgnoreCase(name)){
				w = wt;
			}
		}
		if(w == null){
			w = Bukkit.createWorld(new WorldCreator(name));
			Bukkit.getWorlds().add(w);
		}
		return new Location(w,
		config.getInt(key + ".x"),
		config.getInt(key + ".y"),
		config.getInt(key + ".z"));
	}
	public Location getLocationWithYaw(String key){
		String name = config.getString(key + ".world");
		World w = null;
		for(World wt : Bukkit.getWorlds()){
			if(wt.getName().equalsIgnoreCase(name)){
				w = wt;
			}
		}
		if(w == null){
			w = Bukkit.createWorld(new WorldCreator(name));
			Bukkit.getWorlds().add(w);
		}
		return new Location(w,
		config.getInt(key + ".x"),
		config.getInt(key + ".y"),
		config.getInt(key + ".z"),
		(float)config.getDouble(key + ".yaw"),
		(float)config.getDouble(key + ".pitch"));
	}
	
	public Boolean hasKey(String key) {
		return config.contains(key);
	}
	
	public void setValue(String key, String value) {
		config.set(key, value);
	}
	public void setValue(String key, int value) {
		config.set(key, value);
	}
	public void setValue(String key, boolean value) {
		config.set(key, value);
	}
	public void setValue(String key, List<?> value) {
		config.set(key, value);
	}
	public void setValue(String key, ItemStack value) {
		config.set(key, value);
	}
	public void setValue(String key, Map<?, ?> value) {
		config.set(key, value);
	}
	public void setValue(String key, HashMap<?, ?> value) {
		config.set(key, value);
	}
	public void setValue(String key, Location value){
		config.set(key + ".world", value.getWorld().getName() + "");
		config.set(key + ".x", value.getBlockX());
		config.set(key + ".y", value.getBlockY());
		config.set(key + ".z", value.getBlockZ());
	}
	public void setValue(String key, Location value, double yaw, double pitch){
		config.set(key + ".world", value.getWorld().getName() + "");
		config.set(key + ".x", value.getBlockX());
		config.set(key + ".y", value.getBlockY());
		config.set(key + ".z", value.getBlockZ());
		config.set(key + ".yaw", yaw);
		config.set(key + ".pitch", pitch);
	}
	
	public void remove(String key) {
		config.set(key, null);
	}
	
	public boolean containsKey(String key) {
		return config.contains(key);
	}
	
}