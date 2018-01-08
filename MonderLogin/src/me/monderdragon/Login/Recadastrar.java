package me.monderdragon.Login;

import me.monderdragon.Inicio.Utilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Recadastrar implements Listener{
	
	public static boolean PRecadastrar(Player p){
		if(Utilidades.login.contains(p.getName())){
			FileConfiguration pnu = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
			pnu.set("Jogadores." + p.getUniqueId() + ".Senha", null);
			Utilidades.login.remove(p.getName());
		} else {
			p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Esteja logado pra mudar sua senha: /entrar [senha]");
			return true;
		}
		return false;
	}
}