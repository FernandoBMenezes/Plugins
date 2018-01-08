package me.monderdragon.Essencial.Base;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class ModoJogo {
	public static void usar(Player p, GameMode g){
		p.setGameMode(g);
	}
}
