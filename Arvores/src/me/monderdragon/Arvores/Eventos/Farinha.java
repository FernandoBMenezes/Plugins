package me.monderdragon.Arvores.Eventos;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import me.monderdragon.Essencial.Base.LocalSeguro;

public class Farinha implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGH)
	public void Clicou(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getClickedBlock() != null) {
			MaterialData mfarinha = new MaterialData(Material.INK_SACK, (byte) 15);
			ItemStack ph = p.getInventory().getItemInHand();
			if (ph.getType() == Material.INK_SACK && ph.getData().equals(mfarinha) && p.getGameMode() == GameMode.CREATIVE) {
				if(p.hasPermission("Arvores.Farinha") == true){
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					Block b = e.getClickedBlock();
					int radius = 5;
					final int x = b.getX();
					final int z = b.getZ();
					final int minX = x - radius;
					final int minZ = z - radius;
					final int maxX = x + radius;
					final int maxZ = z + radius;
					for (int counterX = minX; counterX <= maxX; counterX++) {
						for (int counterZ = minZ; counterZ <= maxZ; counterZ++) {
							Block b2 = b.getWorld().getBlockAt(counterX, b.getY(), counterZ);
							Location lsb = LocalSeguro.verificar(b2.getLocation());
							if (lsb != null && lsb.clone().add(0, -1, 0).getBlock().getType().equals(Material.GRASS)) {
								Random rg = new Random();
								int ri = rg.nextInt(100);
								if (ri < 40) {
									lsb.getBlock().setTypeIdAndData(31, (byte) 1, true);
								}
							}
						}
					}
					p.sendMessage("[Arvores] Grama!");
				}
				}
			}
		}
	}
}
