package me.monderdragon.Essencial.Base;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Voltar implements Listener {
	static HashMap<Player , Location> hm = new HashMap<>();
	static Plugin plug = Bukkit.getPluginManager().getPlugin("Essencial");
	public static void usar(Player p){
		if(hm.containsKey(p)){
		if(LocalSeguro.verificar(hm.get(p)) != null){
		p.teleport(LocalSeguro.verificar(hm.get(p)));
		} else {
			p.sendMessage("LOCAL COM ALTO PERIGO");
		}
		} else {
			nova(p);
			p.teleport(LocalSeguro.verificar(hm.get(p)));
		}
	}
	
	public static void nova(Player p){
		hm.put(p, p.getLocation());
		plug.getServer().getScheduler().scheduleSyncDelayedTask(plug, new Runnable() {
			public void run() {
			nova(p);
			}
			}, 20L*60);
	}
	
	@EventHandler
	public void Entrou(PlayerJoinEvent e){
		Player p = (Player)e.getPlayer();
		nova(p);
	}
}
