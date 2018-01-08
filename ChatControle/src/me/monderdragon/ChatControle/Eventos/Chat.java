package me.monderdragon.ChatControle.Eventos;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import me.monderdragon.ChatControle.Utilidades.Hotbar;
import me.monderdragon.ChatControle.Utilidades.Utilidades;
import me.monderdragon.Clans.Clan.Clan;

@SuppressWarnings("deprecation")
public class Chat implements Listener {

	@EventHandler
	public static void Falou(PlayerChatEvent e) {
		e.setCancelled(true);
		Player p = e.getPlayer();
		//EMOTICON
		//CORAÇÃO
		e.setMessage(e.getMessage().toLowerCase().replaceAll("<3", "§4<3§f"));
		e.setMessage(e.getMessage().replaceAll("s2", "§4S2§f"));
		//ROSTO
		e.setMessage(e.getMessage().toLowerCase().replaceAll(":p", "§9:P§f"));
		e.setMessage(e.getMessage().replaceAll(":d", "§6:D§f"));
		e.setMessage(e.getMessage().replaceAll(":v", "§6:V§f"));
		if(p.hasPermission("ChatControle.Cor")){
		e.setMessage(e.getMessage().replaceAll("&", "§"));
		} else {
		e.setMessage(e.getMessage().replaceAll("&", ""));
		}
		
		if(Utilidades.silenciar.contains(p)){
			p.sendMessage("§e[*] Esta com Modo Silencio Ativado!");
			return;
		}
		if(Utilidades.mensagem.containsKey(p.getUniqueId()) && Utilidades.mensagem.get(p.getUniqueId()).equals(e.getMessage())){
			p.sendMessage("§e[*] Mensagem Repetida!");
			return;
		} else {
			Utilidades.mensagem.put(p.getUniqueId(), e.getMessage());
		}
		if (e.getMessage().trim().length() < 2) {
			p.sendMessage("§e[*] Mensagem Muito Pequena!");
			return;
		}
		Clan gd = me.monderdragon.Clans.Utilidades.Utilidades.pegarClan(p.getUniqueId());
		String gdtag = "";
		if(gd != null){
			gdtag = " §4[§f" +gd.pegarSigla() + "§4]§f ";
		}
		String ptag = Utilidades.pegarTag(p);
		if (e.getMessage().startsWith("!")) {
			for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
				if(Utilidades.silenciar.contains(ps) && p.hasPermission("ChatControle.Obrigar.Ouvir") == false){
					return;
				}
				ps.sendMessage("§e[!]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage().replaceFirst("!", ""));
			}
			return;
		}
		if (e.getMessage().contains("?")) {
			for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
				if(Utilidades.silenciar.contains(ps) && p.hasPermission("ChatControle.Obrigar.Ouvir") == false){
					return;
				}
				ps.sendMessage("§9[?]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage());
			}
			return;
		}
		if(e.getMessage().startsWith("@")){
			for (Player ps : Bukkit.getServer().getOnlinePlayers()) {
				if(Utilidades.silenciar.contains(ps) && p.hasPermission("ChatControle.Obrigar.Ouvir") == false){
					return;
				}
				if(e.getMessage().toUpperCase().contains(ps.getName().toUpperCase())){
					Hotbar.mostrarBarra(ps, "§a[@]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage().replaceFirst("@", ""));
					ps.playSound(ps.getLocation(), Sound.ANVIL_BREAK, 0.5f, 0.5f);
				}
			}
			Hotbar.mostrarBarra(p, "§a[@]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage().replaceFirst("@", ""));
			return;
		}
		p.sendMessage("§f[L]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage());
		for (Entity ps : p.getNearbyEntities(500, 500, 500)) {
			if (ps instanceof Player) {
				if(Utilidades.silenciar.contains(ps) && p.isOp() == false){
					return;
				}
				Player pps = (Player) ps;
				pps.sendMessage("§f[L]" + gdtag + ptag + " " + p.getName() + ":§f " + e.getMessage());
			}
		}
		return;
	}
}
