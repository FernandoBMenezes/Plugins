package me.monderdragon.Inicio;

import me.monderdragon.Comandos.Entrada;
import me.monderdragon.Comandos.Marcar;
import me.monderdragon.Evento.Eventos;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Inicio extends JavaPlugin implements Listener{
	
	   public void onEnable(){
		   Bukkit.getServer().getPluginManager().registerEvents(this, this);
		   getLogger().info(ChatColor.RED + "Feito Por: Monderdragon!");
	       Bukkit.getServer().getPluginManager().registerEvents(new Eventos(), this);
		   getCommand("entrar").setExecutor(new Entrada());
		   getCommand("cadastrar").setExecutor(new Entrada());
		   getCommand("sentrada").setExecutor(new Marcar());
		   getCommand("entrada").setExecutor(new Marcar());
	       saveConfig();
	   }
	 
	   public void onDisable(){
	       getLogger().info(ChatColor.RED + "[Monderdragon] Feito Por: Monderdragon!");
	       saveConfig();
	   } 
}