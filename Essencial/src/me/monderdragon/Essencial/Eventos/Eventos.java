package me.monderdragon.Essencial.Eventos;

import java.lang.reflect.Field;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import me.monderdragon.Essencial.Utilidades.Holograma;
import me.monderdragon.Essencial.Utilidades.Utilidades;
import me.monderdragon.TerrenoSeguro.Terreno.Terreno;
import me.monderdragon.TerrenoSeguro.Utils.Hotbar;
import net.minecraft.server.v1_8_R3.TileEntityChest;

@SuppressWarnings("deprecation")
public class Eventos implements Listener {

	@EventHandler
	public static void Mutado(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (Utilidades.mutado.contains(e.getPlayer())) {
			p.sendMessage("§4[*]§f Esta Mutado");
			e.setCancelled(true);
		}
	}

	@EventHandler
	public static void Apanhou(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof LivingEntity && e.getDamager() instanceof Player) {
			LivingEntity l = (LivingEntity) e.getEntity();
			Player d = (Player) e.getDamager();
			if (Utilidades.vida.contains(d)) {
			} else {
				int vida = (int) ((l.getHealth() - e.getDamage()) * 5);
				if (vida < 0) {
					vida = 0;
				}
				if (vida > 70) {
					Hotbar.mostrarBarra(d, "§2❤ " + vida + "%  ❤");
				}
				if (vida <= 70 && vida > 30) {
					Hotbar.mostrarBarra(d, "§6❤ " + vida + "% ❤");
				}
				if (vida <= 30) {
					Hotbar.mostrarBarra(d, "§4❤ " + vida + "% ❤");
				}
				Holograma.atualizar(d, l, 10 * 5);
			}
		}
	}

	@EventHandler
	public static void Morreu(PlayerDeathEvent e) {
		Terreno t0 = me.monderdragon.TerrenoSeguro.Utils.Utilidades.temTerreno(e.getEntity().getLocation());
		if (t0 == null) {
			List<ItemStack> itens = e.getDrops();
			if(itens.isEmpty() == false){
			Player p = (Player) e.getEntity().getPlayer();
			Location l = p.getLocation();
			if (l.getBlock().getType().equals(Material.AIR) || l.getBlock().getType().equals(Material.WATER)
					|| l.getBlock().getType().equals(Material.LAVA)) {
				l.getBlock().setType(Material.CHEST);
				Chest c = (Chest) l.getBlock().getState();
				try {
					Field invf = c.getClass().getDeclaredField("chest");
					invf.setAccessible(true);
					TileEntityChest tc = ((TileEntityChest) invf.get(c));
					tc.a("§4[" + p.getName() + "]");
					itens.forEach(x -> c.getBlockInventory().addItem(x));
					e.getDrops().clear();
				} catch (Exception excep) {
					System.out.println(excep.getMessage());
				}
			}
			}
		}
	}

	/*
	 * @EventHandler public static void Entrou(PlayerJoinEvent e) { Player p =
	 * e.getPlayer(); e.setJoinMessage(null);
	 * Bukkit.getServer().dispatchCommand(p, "skin set " + p.getName()); /*if
	 * (p.getCustomName() == null) {
	 * Bukkit.broadcastMessage(ChatColor.BOLD.toString() +
	 * ChatColor.GREEN.toString() + "[+] " + p.getName()); } else {
	 * Bukkit.broadcastMessage(ChatColor.BOLD.toString() +
	 * ChatColor.GREEN.toString() + "[+] " + p.getCustomName()); } }
	 * 
	 * @EventHandler public static void Saiu(PlayerQuitEvent e) { Player p =
	 * e.getPlayer(); e.setQuitMessage(null); /*if (p.getCustomName() == null) {
	 * Bukkit.broadcastMessage(ChatColor.BOLD.toString() +
	 * ChatColor.RED.toString() + "[-] " + p.getName()); } else {
	 * Bukkit.broadcastMessage(ChatColor.BOLD.toString() +
	 * ChatColor.RED.toString() + "[-] " + p.getCustomName()); } }
	 */
}
