package me.monderdragon.TabControle.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.monderdragon.TabControle.Eventos.Entrou;
import me.monderdragon.TabControle.Eventos.Falou;

public class Iniciar extends JavaPlugin implements Listener{
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Entrou(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Falou(), this);
	}
	public void onDisable(){
		
	}
}