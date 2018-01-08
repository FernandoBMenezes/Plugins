package me.monderdragon.TabControle.Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.monderdragon.ChatControle.Utilidades.Utilidades;

public class Entrou implements Listener {
	
	@EventHandler
	  public void onPlayerJoin(PlayerJoinEvent e){
	    Player p = e.getPlayer();
	    String tag = Utilidades.pegarTag(Utilidades.pegarGrupo(p)).replace("[", "*");
		tag = tag.replace("]", "*");
	    me.monderdragon.TabControle.Utilidades.Utilidades.colocarTab(p, tag);
	  }
}