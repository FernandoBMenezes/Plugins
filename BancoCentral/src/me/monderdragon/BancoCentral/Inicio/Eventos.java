package me.monderdragon.BancoCentral.Inicio;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Eventos implements Listener{
	@EventHandler
	public void Entrou(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		Banco.criarConta(p);
	}
}
