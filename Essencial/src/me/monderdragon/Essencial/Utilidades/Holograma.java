package me.monderdragon.Essencial.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.monderdragon.TerrenoSeguro.Utils.Hotbar;

public class Holograma {
	static Plugin pl = Bukkit.getPluginManager().getPlugin("Essencial");

	public static void atualizar(Player p, LivingEntity l, int qnt) {
		pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
			public void run() {
				if(qnt > 0){
				if(l.isDead() == true || l.getHealth() == 0){
					Utilidades.vida.remove(p);
				}
				int vida = (int)((l.getHealth()) * 5);
				if (vida > 70) {
					Hotbar.mostrarBarra(p, "§2❤ " + vida + "% ❤");
				}
				if (vida <= 70 && vida > 30) {
					Hotbar.mostrarBarra(p, "§6❤ " + vida + "% ❤");
				}
				if (vida <= 30) {
					Hotbar.mostrarBarra(p, "§4❤ " + vida + "% ❤");
				}
				Holograma.atualizar(p, l, qnt-1);
				} else {
					Utilidades.vida.remove(p);
				}
			}
		}, 2L);
	}
}
