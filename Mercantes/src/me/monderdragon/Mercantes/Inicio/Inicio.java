package me.monderdragon.Mercantes.Inicio;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import me.monderdragon.Mercantes.Arquivos.Arquivo;
import me.monderdragon.Mercantes.Eventos.INpc;
import me.monderdragon.Mercantes.Eventos.Inventarios;
import me.monderdragon.Mercantes.Npc.Loja;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class Inicio extends JavaPlugin implements Listener{
	
	public void onEnable(){
		if(Arquivo.Existe(new File(Arquivo.getLocal().toString())) == false){
			Arquivo.CriarPasta(new File(Arquivo.getLocal().toString()));
			Arquivo.CriarBloco(Arquivo.getLocal().toString());
		}
		
		Utilidades.proteger();
		Utilidades.Importar();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Comandos(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new INpc(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new Inventarios(), this);
		getCommand("loja").setExecutor(new Comandos());
	}
	public void onDisable(){
		for(Loja nv : Utilidades.mercantes){
			nv.matar();
		}
	}
}