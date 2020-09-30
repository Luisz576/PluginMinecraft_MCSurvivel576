package com.youtube.luisz576.mcsurvivel.componentes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

import com.youtube.luisz576.mcsurvivel.Main;

public class Mechanics {
	
	private List<Location> getLocationsNextToLocation(Location l){
		List<Location> locations = new ArrayList<Location>();
		//LessXL
		Location lessXLTemp = new Location(l.getWorld(), l.getX() - 1, l.getY(), l.getZ());
		locations.add(lessXLTemp);
		//LessYL
		Location lessYLTemp = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());
		locations.add(lessYLTemp);
		//LessZL
		Location lessZLTemp = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() - 1);
		locations.add(lessZLTemp);
		//MoreXL
		Location moreXLTemp = new Location(l.getWorld(), l.getX() + 1, l.getY(), l.getZ());
		locations.add(moreXLTemp);
		//MoreYL
		Location moreYLTemp = new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ());
		locations.add(moreYLTemp);
		//MoreZL
		Location moreZLTemp = new Location(l.getWorld(), l.getX(), l.getY(), l.getZ() + 1);
		locations.add(moreZLTemp);
		return locations;
	}
	
	//Quebra madeira
	public void destroyMadeiraFrom(Location lStart) {
		for(Location l : getLocationsNextToLocation(lStart))
			if(Main.utils.isWood(l.getBlock())) {
				l.getBlock().breakNaturally();
				destroyMadeiraFrom(l);
			}
	}
	
	//RECEITAS
	public List<ShapedRecipe> getRecipes() {
		List<ShapedRecipe> recipes = new ArrayList<>();
		//Machado
		@SuppressWarnings("deprecation")
		ShapedRecipe machado = new ShapedRecipe(Main.utils.getMachadoArvores()).shape("ccc", "dbd", "ccc");
		machado.setIngredient('b', Material.GOLDEN_AXE);
		machado.setIngredient('c', Material.GOLD_INGOT);
		machado.setIngredient('d', Material.DIAMOND);
		recipes.add(machado);
		//Bota gelo
		@SuppressWarnings("deprecation")
		ShapedRecipe iceboots = new ShapedRecipe(Main.utils.getIceBoots()).shape("dce", "cbc", "ecd");
		iceboots.setIngredient('b', Material.GOLDEN_BOOTS);
		iceboots.setIngredient('c', Material.ICE);
		iceboots.setIngredient('d', Material.DIAMOND);
		iceboots.setIngredient('e', Material.GOLD_INGOT);
		recipes.add(iceboots);
		//Teleporter
		@SuppressWarnings("deprecation")
		ShapedRecipe teleporter = new ShapedRecipe(Main.utils.getTeleporter()).shape("ced", "ebe", "dec");
		teleporter.setIngredient('b', Material.CLOCK);
		teleporter.setIngredient('c', Material.COMPASS);
		teleporter.setIngredient('d', Material.ENDER_EYE);
		teleporter.setIngredient('e', Material.ENDER_PEARL);
		recipes.add(teleporter);
		//Seter
		@SuppressWarnings("deprecation")
		ShapedRecipe seter = new ShapedRecipe(Main.utils.getSeterTeleport()).shape("ccc", "dbd", "ccc");
		seter.setIngredient('b', Material.ENDER_EYE);
		seter.setIngredient('c', Material.REDSTONE_BLOCK);
		seter.setIngredient('d', Material.EMERALD);
		recipes.add(seter);
		return recipes;
	}

}