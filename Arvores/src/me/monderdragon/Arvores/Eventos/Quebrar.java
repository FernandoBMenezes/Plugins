package me.monderdragon.Arvores.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import me.monderdragon.Arvores.Utils.Utilidades;

public class Quebrar {
	@SuppressWarnings("deprecation")
	@EventHandler 
	public void onBlockBreakEvent(BlockBreakEvent e) {
		 FileConfiguration SB =  Bukkit.getPluginManager().getPlugin("Arvores").getConfig();
		 Player p = (Player)e.getPlayer();
		 Location lp = Utilidades.localp(p);
		 int BX = e.getBlock().getX() - lp.getBlockX(); int BY = e.getBlock().getY() - lp.getBlockY(); 
		 int BZ = e.getBlock().getZ() - lp.getBlockZ(); SB.set("Arvores." + BX + "." + BY + "."+ BZ,"Colocar.ColocarB( p, Material." + e.getBlock().getType().toString() + ","+ e.getBlock().getData() + "," + BX + ", " + BY+ ", " + BZ + ");");
		 p.sendMessage("[BLOCO] Material: " + e.getBlock().getType() + ", Cor: " + e.getBlock().getData() + ", Local: X: " + BX + " Y: " + BY + " Z: " + BZ);
		 Bukkit.getPluginManager().getPlugin("Arvores").saveConfig(); 
		}
}
