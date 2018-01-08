package me.monderdragon.Arvores.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.monderdragon.Arvores.Comandos.Comandos;
import me.monderdragon.Arvores.Eventos.Farinha;
import me.monderdragon.Arvores.Eventos.FolhaCair;
import me.monderdragon.Arvores.Eventos.Selecionar;

public class Main extends JavaPlugin implements Listener {

	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new FolhaCair(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Selecionar(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Farinha(), this);
		getCommand("arvore").setExecutor(new Comandos());
		getLogger().info("Iniciado");
		saveConfig();
	}

	public void onDisable() {
		getLogger().info("Finalizado");
		saveConfig();
	}
}
