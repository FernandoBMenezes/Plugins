package me.monderdragon.Clans.Arquivos;

import java.io.File;

import org.bukkit.Bukkit;

public class Arquivo {
	
	public static File pegarLocal(){
	return Bukkit.getPluginManager().getPlugin("Clans").getDataFolder();
	}
	
	public static boolean Existe(File f) {
		return f.exists();
	}
}
