package me.monderdragon.Essencial.Base;

import org.bukkit.Location;
import org.bukkit.Material;

public class LocalSeguro {

	public static Location verificar(Location l) {
		Location lmais = new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ());
		Location lmenos = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());
		if (l.getBlock().getType().equals(Material.AIR) == true
				&& lmenos.getBlock().getType().equals(Material.AIR) == false) {
			return l;
		} else {
			if (l.getY() < 300 && l.getY() > 0 && l.getBlock().getType() != Material.LAVA && l.getBlock().getType() != Material.STATIONARY_LAVA && l.getBlock().getType() != Material.CACTUS) {
				if (l.getBlock().getType().equals(Material.AIR) == true
						&& lmenos.getBlock().getType().equals(Material.AIR) == true) {
					return verificar(lmenos);
				}
				if (l.getBlock().getType().equals(Material.AIR) == false
						&& lmenos.getBlock().getType().equals(Material.AIR) == false) {
					return verificar(lmais);
				}
			} else {
				return null;
			}
		}
		return null;
	}
}
