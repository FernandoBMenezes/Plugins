package me.monderdragon.Essencial.Comandos;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.monderdragon.Essencial.Base.Limpar;
import me.monderdragon.Essencial.Base.LocalSeguro;
import me.monderdragon.Essencial.Base.Voltar;
import me.monderdragon.Essencial.Utilidades.Utilidades;

public class Comandos implements Listener, CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		Player p = (Player) sender;
		World w = p.getWorld();
		// /spawn
		if (cmd.getName().equalsIgnoreCase("spawn")) {
			p.teleport(w.getSpawnLocation());
		}

		if (cmd.getName().equalsIgnoreCase("aleatorio")) {
			Random aleatorio = new Random();
			p.sendMessage("§4[Nayd]§f Teleportado para uma area aleatoria!");
			if (arg.length != 0) {
				if (arg[0].equalsIgnoreCase("Medio") || arg[0].equalsIgnoreCase("M")) {
					int x = aleatorio.nextInt(1000) + 100;
					int z = aleatorio.nextInt(1000) + 100;
					Location l = LocalSeguro.verificar(
							new Location(p.getWorld(), p.getLocation().getX() + x, 100, p.getLocation().getZ() + z));
					if (l != null) {
						p.teleport(l);
					} else {
						Bukkit.dispatchCommand(p, "aleatorio " + arg[0]);
					}
					return true;
				}
				if (arg[0].equalsIgnoreCase("Longe") || arg[0].equalsIgnoreCase("L")) {
					int x = aleatorio.nextInt(10000) + 10000;
					x = -x;
					int z = aleatorio.nextInt(10000) + 10000;
					z = -z;
					Location l = LocalSeguro.verificar(
							new Location(p.getWorld(), p.getLocation().getX() + x, 100, p.getLocation().getZ() + z));
					if (l != null) {
						p.teleport(l);
					} else {
						Bukkit.dispatchCommand(p, "aleatorio " + arg[0]);
					}
					return true;
				}
			}
			int x = aleatorio.nextInt(100) + 500;
			int z = aleatorio.nextInt(100) + 500;
			Location l = LocalSeguro
					.verificar(new Location(p.getWorld(), p.getLocation().getX() + x, 100, p.getLocation().getZ() + z));
			if (l != null) {
				p.teleport(l);
			} else {
				Bukkit.dispatchCommand(p, "aleatorio");
			}
			return true;
		}

		// /tempo dia /tempo noite /tempo chuva /tempo sol
		if (cmd.getName().equalsIgnoreCase("tempo")) {
			if (arg.length >= 1) {
				if (arg[0].equalsIgnoreCase("dia")) {
					w.setTime(1000);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Tempo: Dia");
					return true;
				}
				if (arg[0].equalsIgnoreCase("noite")) {
					w.setTime(1000000);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Tempo: Noite");
					return true;
				}
				if (arg[0].equalsIgnoreCase("chuva")) {
					w.setStorm(true);
					w.setThundering(true);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Tempo: Chuva");
					return true;
				}
				if (arg[0].equalsIgnoreCase("sol")) {
					w.setStorm(false);
					w.setThundering(false);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Tempo: Sol");
					return true;
				}

			} else {
				p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Comando: /TEMPO [DIA/NOITE/CHUVA/SOL]");
			}
		}
		// /voltar
		if (cmd.getName().equalsIgnoreCase("voltar")) {
			p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Tempo: Voltando para o antigo local.");
			Voltar.usar(p);
		}
		// /iteminfo
		if (cmd.getName().equalsIgnoreCase("iteminfo")) {
			ItemStack i = p.getItemInHand();
			p.sendMessage(ChatColor.BOLD + "[" + i.getType() + "]" + "\nID: " + i.getTypeId() + "\nDATA: " + i.getData()
					+ "\nMETA: " + i.getItemMeta());
		}
		// /inventario [player nome]
		if (cmd.getName().equalsIgnoreCase("inventario")) {
			if (arg.length >= 1) {
				if (Bukkit.getPlayer(arg[0]) != null) {
					Player p2 = Bukkit.getPlayer(arg[0]);
					p.openInventory(p2.getInventory());
				}
			}
		}
		// /mutar [player nome]
		if (cmd.getName().equalsIgnoreCase("mutar")) {
			if (arg.length >= 1) {
				if (Bukkit.getPlayer(arg[0]) != null) {
					Player p2 = Bukkit.getPlayer(arg[0]);
					Utilidades.mutado.add(p2);
				}
			}
		}
		// /limpar
		if (cmd.getName().equalsIgnoreCase("limpar")) {
			Limpar.limpo();
		}
		// /msg [player nome]
		if (cmd.getName().equalsIgnoreCase("msg")) {
			if (arg.length >= 1) {
				if (Bukkit.getPlayer(arg[0]) != null) {
					Player p2 = Bukkit.getPlayer(arg[0]);
					String msg = StringUtils.join(arg, ' ', 1, arg.length);
					p2.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "De: " + p.getName() + " " + msg);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Para: " + p2.getName() + " " + msg);
				} else {
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Comando: /MSG [PLAYER] [MSG]");
				}
			}
		}
		// /chapeu
		if (cmd.getName().equalsIgnoreCase("chapeu")) {
			if (p.getItemInHand().getType() != Material.AIR) {
				if (p.getInventory().getHelmet() == null) {
					ItemStack im = p.getItemInHand();
					p.getInventory().setHelmet(im);
					p.getInventory().removeItem(im);
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Chapeu: Colocado.");
				} else {
					p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE
							+ "Precisa tirar antes oq esta usando como chapeu!!");
				}
			} else {
				p.sendMessage(ChatColor.RED + "§4[Nayd]§f " + ChatColor.WHITE + "Precisa segurar algo para usar!");
			}

		}
		if (cmd.getName().equalsIgnoreCase("holograma") && p.hasPermission("Essencial.Holograma")) {
			if (arg[0].equalsIgnoreCase("matar")) {
				for (org.bukkit.entity.Entity e : p.getWorld().getNearbyEntities(p.getLocation(), 1, 2, 1)) {
					if (e instanceof ArmorStand) {
						p.sendMessage("[Essencial] Removido: " + e.getCustomName());
						e.remove();
					}
				}
				return true;
			}
			ArmorStand as = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
			as.setRemainingAir(0);
			as.setRemoveWhenFarAway(false);
			as.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*60*60*60*24*3, 2));
			as.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60*60*60*24*3, 2));
			as.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*60*60*24*3, 2));
			as.setVisible(false);
			String nome = StringUtils.join(arg, ' ', 0, arg.length);
			as.setCustomName(nome.replaceAll("&", "§"));
			as.setCustomNameVisible(true);
			as.setGravity(false);
			p.sendMessage("[Essencial] Holograma: " + nome.replaceAll("&", "§"));
		}
		return false;
	}

}
