package me.monderdragon.ChatControle.Utilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utilidades {
	public static HashMap<UUID, String> mensagem = new HashMap<>();
	public static ArrayList<Player> silenciar = new ArrayList<>();
	
	public static int pegarGrupo(Player p){
		if(p.hasPermission("ChatControle.Dono")){
			return 6;
		}
		if(p.hasPermission("ChatControle.Adm")){
			return 5;
		}
		if(p.hasPermission("ChatControle.Mod")){
			return 4;
		}
		if(p.hasPermission("ChatControle.Vip+")){
			return 3;
		}
		if(p.hasPermission("ChatControle.Vip")){
			return 2;
		}
		if(p.hasPermission("ChatControle.Ajudante")){
			return 1;
		}
		return 0;
	}
	public static String pegarTag(Player p){
		String prefix = PermissionsEx.getUser(p).getGroups()[0].getPrefix().replaceAll("&", "§");
		return prefix;
	}
}
