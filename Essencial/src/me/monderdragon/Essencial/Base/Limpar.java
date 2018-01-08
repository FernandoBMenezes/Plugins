package me.monderdragon.Essencial.Base;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class Limpar {
	public static void limpo(){
		int Total = 0;
		for(World w : Bukkit.getWorlds()){
			for(Entity ew : w.getEntities()){
				if(ew instanceof Player == false && ew instanceof Villager == false){
					Total += 1;
					ew.remove();
				}
			}
		}
		System.out.println("Total de Entidades Mortas: " + Total);
	}
}
