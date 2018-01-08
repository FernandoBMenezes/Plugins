package me.monderdragon.Evento;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Avisar {
	public static boolean PAvisar(Player p){
		FileConfiguration pnu = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
		if(pnu.contains("Jogadores." + p.getUniqueId())){
			p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Para entrar: /entrar [senha]");
			} else {
		p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Para cadastrar: /cadastrar [senha]");
			}
		return false;
	}
}
