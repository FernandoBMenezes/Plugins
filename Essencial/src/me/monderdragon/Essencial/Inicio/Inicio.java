package me.monderdragon.Essencial.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.monderdragon.Essencial.Base.Voltar;
import me.monderdragon.Essencial.Comandos.Comandos;
import me.monderdragon.Essencial.Eventos.Eventos;

public class Inicio extends JavaPlugin implements Listener{
    
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Voltar(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Comandos(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Eventos(), this);
		
		//getCommand("voltar").setExecutor(new Comandos());
		//getCommand("tempo").setExecutor(new Comandos());
		//getCommand("iteminfo").setExecutor(new Comandos());
		//getCommand("chapeu").setExecutor(new Comandos());
		//getCommand("mutar").setExecutor(new Comandos());
		//getCommand("inventario").setExecutor(new Comandos());
		//getCommand("msg").setExecutor(new Comandos());
		//getCommand("spawn").setExecutor(new Comandos());
		//getCommand("limpar").setExecutor(new Comandos());
		getCommand("holograma").setExecutor(new Comandos());
		getCommand("aleatorio").setExecutor(new Comandos());
		//Desbugar
		for(Player debugp : Bukkit.getOnlinePlayers()){
			Voltar.nova(debugp);
		}
		for(ArmorStand arm : Bukkit.getWorld("world").getEntitiesByClass(ArmorStand.class)){
			if(arm.getCustomName().contains("❤") && arm.getCustomName().contains("%")){
				arm.remove();
			}
		}
	}
	public void onDisable(){
		
	}
}
