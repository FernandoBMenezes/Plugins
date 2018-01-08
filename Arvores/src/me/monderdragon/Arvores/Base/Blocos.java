package me.monderdragon.Arvores.Base;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import me.monderdragon.Arvores.Utils.Utilidades;

public class Blocos {
	
	@SuppressWarnings("deprecation")
	public static void Colocar(Player player, Material material, int color, int X, int Y, int Z){
		Player p = (Player)player;
		Location lp = Utilidades.localp(p);
		Location nlp = new Location(p.getWorld(), lp.getX() + X, lp.getY() + Y, lp.getZ() + Z);
		nlp.getBlock().setTypeIdAndData(material.getId(), (byte) color, true);
	}
	public static void Colocar(Player player, Material material, int color, int X, int Y, int Z, boolean b){
		Player p = (Player)player;
		Location lp = Utilidades.localp(p);
		Location nlp = new Location(p.getWorld(), lp.getX() + X, lp.getY() + Y, lp.getZ() + Z);
		nlp.getBlock().setType(Material.AIR);
	}
}
