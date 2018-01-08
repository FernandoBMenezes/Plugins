package me.monderdragon.Clans.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.monderdragon.Clans.Comandos.Comandos;
import me.monderdragon.Clans.Eventos.Dano;
import me.monderdragon.Clans.Utilidades.Utilidades;

public class Iniciar extends JavaPlugin implements Listener {
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Comandos(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Dano(), this);
		
		getCommand("Clans").setExecutor(new Comandos());
		getCommand("Clan").setExecutor(new Comandos());
		getCommand("C").setExecutor(new Comandos());
		Utilidades.Importar();
	}

	public void onDisable() {

	}
}
