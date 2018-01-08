package me.monderdragon.Login;

import me.monderdragon.Inicio.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Login implements Listener{
	public static void PLogin(Player p, String psenha){
		String pnu = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig().getString("Jogadores." + p.getUniqueId() + ".Senha");
		if(psenha.equals(pnu)){
		Utilidades.login.add(p.getName());
		p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Entrou!");
		} else {
			p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Senha errada");
		}
	}
}
