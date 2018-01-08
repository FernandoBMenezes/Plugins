package me.monderdragon.TabControle.Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.monderdragon.ChatControle.Utilidades.Utilidades;

public class Falou implements Listener{
	
	@EventHandler
	public void falar(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String tag = Utilidades.pegarTag(Utilidades.pegarGrupo(p)).replace("[", "*");
		tag = tag.replace("]", "*");
		me.monderdragon.TabControle.Utilidades.Utilidades.colocarTab(p, tag);
	}
}