package me.monderdragon.NPCComandos.Arquivos;

import java.io.File;

import org.bukkit.Bukkit;

public class Arquivo {
	
	public static File pegarLocal(){
	return Bukkit.getPluginManager().getPlugin("NPCComandos").getDataFolder();
	//return Bukkit.getWorld("world").getWorldFolder();
	}
	
	public static boolean Existe(File f) {
		return f.exists();
	}
}
