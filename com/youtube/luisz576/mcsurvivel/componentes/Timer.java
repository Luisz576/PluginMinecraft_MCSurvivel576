package com.youtube.luisz576.mcsurvivel.componentes;

import org.bukkit.Bukkit;

import com.youtube.luisz576.mcsurvivel.Main;

public class Timer {
	
	private int timeWater, timeBanheiro;
	
	public Timer() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
			@Override
			public void run() {
				if(Main.necessidadesData.isAtivoNecessidades()) {
					if(timeWater >= 30) {
						Main.necessidadesData.UpdateWater();
						timeWater = 0;
					}
					if(timeBanheiro >= 50) {
						Main.necessidadesData.UpdateBanheiro();
						timeBanheiro = 0;
					}
					timeWater++;
					timeBanheiro++;
				}
			}
		}, 1, 20);
	}

}