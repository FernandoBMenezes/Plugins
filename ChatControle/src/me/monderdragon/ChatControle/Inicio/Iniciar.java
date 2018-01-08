package me.monderdragon.ChatControle.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.monderdragon.ChatControle.Comandos.Comandos;
import me.monderdragon.ChatControle.Eventos.Chat;

public class Iniciar extends JavaPlugin implements Listener{
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Chat(), this);
		getCommand("silencio").setExecutor(new Comandos());
	}
	public void onDisable(){
		
	}
}
