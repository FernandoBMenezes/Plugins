package me.monderdragon.Arvores.Utils;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utilidades {
	public static int base = 0;
	
	public static HashMap<Player, Location> lSelecionado = new HashMap<>();
	public static HashMap<Player, String[]> aColocadas = new HashMap<>();
	public static Location localp(Player p){
		return Utilidades.lSelecionado.get(p);
	}
	public static Location toLocation(String ls){
		String ls1 = ls.replace("Location{world=CraftWorld{name=", "");
		String ls2 = ls1.replace("}", "");
		String ls3 = ls2.replace("x=", "");
		String ls4 = ls3.replace("y=", "");
		String ls5 = ls4.replace("z=", "");
		String ls6 = ls5.replace("pitch=", "");
		String ls7 = ls6.replace("yaw=", "");
		String[] lls = ls7.split(",");
		System.out.print("Mundo: " + lls[0]);
		World w = Bukkit.getWorld(lls[0]);
		System.out.print("Mundo: " + w);
		double x = Double.parseDouble(lls[1]);
		double y = Double.parseDouble(lls[2]);
		double z = Double.parseDouble(lls[3]);
		float p = Float.parseFloat(lls[4]);
		float yaw = Float.parseFloat(lls[5]);
		Location l = new Location(w, x, y, z, p, yaw);
		return l;//w,x,y,z,p,yaw
	}
}
