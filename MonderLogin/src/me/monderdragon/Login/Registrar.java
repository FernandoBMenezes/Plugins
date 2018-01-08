package me.monderdragon.Login;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Registrar implements Listener{
		
	public static boolean PRegistrar(Player p, String psenha){
		FileConfiguration fb = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
		   if(psenha.length() < 4){
			   p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Senha Muito Curta!");
			   return true;
		        }
		   if(psenha.equals("123")||psenha.equals("123456")||psenha.equals("123456789")||psenha.equals("senha")||psenha.equals(p.getName())){
			   p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Senha Invalida Tente Outra!");
		       return true;
		        }
		   fb.set("Jogadores." + p.getUniqueId() + ".Senha", psenha.toString());
		   Bukkit.getPluginManager().getPlugin("MonderLogin").saveConfig();
		   if(psenha.length() < 4){
               p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + p.getName() + ": " + psenha + ", Resistencia: Fraca!");
               return true;
                }
		   if(psenha.length() == 6 || psenha.length() > 6 && psenha.length() < 8 ){
               p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + p.getName() + ": " + psenha + ", Resistencia: Media!");
               return true;
	   		    }
		   if(psenha.length() == 8 || psenha.length() > 8){
               p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + p.getName() + ": " + psenha + ", Resistencia: Forte!");
               return true;
	   		    }
		return false;
		}
}