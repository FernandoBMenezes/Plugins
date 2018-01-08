package me.monderdragon.Evento;

import me.monderdragon.Inicio.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class Eventos implements Listener{
	
	@EventHandler(priority = EventPriority.HIGH)
	public void Entrar(PlayerJoinEvent e){
		Player p = (Player)e.getPlayer();
		FileConfiguration MCC = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
        World w = Bukkit.getServer().getWorld(MCC.getString("Inicio.mundo"));
        double x = MCC.getDouble("Inicio.x");
        double y = MCC.getDouble("Inicio.y");
        double z = MCC.getDouble("Inicio.z");
        p.teleport(new Location(w, x, y, z));
		e.setJoinMessage("" + ChatColor.WHITE + ChatColor.BOLD + "[Nayd] " + ChatColor.GREEN + ChatColor.BOLD + "+ " + p.getName());
		Utilidades.login.remove(p.getName());
		p.sendMessage(" \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n" +
				      "\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n" +
				      "\n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n \n");
		p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Seja Bem Vindo Ao Servidor!");
		Avisar.PAvisar(p);
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void Sair(PlayerQuitEvent e){
		Player p = (Player)e.getPlayer();
		e.setQuitMessage("" + ChatColor.WHITE + ChatColor.BOLD + "[Nayd] " + ChatColor.RED + ChatColor.BOLD + "- " + p.getName());
		Utilidades.login.remove(p.getName());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void PMover(PlayerMoveEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*3, 9));
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*3, 1));
			e.setCancelled(true);
			p.teleport(p.getLocation());
			Avisar.PAvisar(p);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public boolean CMLogin(PlayerCommandPreprocessEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			if(e.getMessage().contains("/entrar") || e.getMessage().contains("/cadastrar")){
				return true;
			}
			Avisar.PAvisar(p);
			e.setCancelled(true);
		}
		return false;
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void CLogin(AsyncPlayerChatEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			Avisar.PAvisar(p);
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void C2Login(PlayerChatEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){;
			Avisar.PAvisar(p);
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void Quebrar(BlockBreakEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			Avisar.PAvisar(p);
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void Dropar(PlayerDropItemEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			Avisar.PAvisar(p);
			e.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void PegarItem(PlayerPickupItemEvent e){
		Player p = (Player)e.getPlayer();
		if(!Utilidades.login.contains(p.getName())){
			e.setCancelled(true);
		}
	}
}
