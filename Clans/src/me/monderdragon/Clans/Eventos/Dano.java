package me.monderdragon.Clans.Eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.monderdragon.Clans.Clan.Clan;
import me.monderdragon.Clans.Utilidades.Utilidades;

public class Dano implements Listener {
	@EventHandler
	public void Causou(EntityDamageByEntityEvent e){
		if(e.getDamager() instanceof Player && e.getEntity() instanceof Player){
			Player bateu = (Player)e.getDamager();
			Player apanhou = (Player)e.getEntity();
			Clan gb = Utilidades.pegarClan(bateu.getUniqueId());
			if(gb != null){
				Clan ga = Utilidades.pegarClan(apanhou.getUniqueId());
				if(ga != null){
					if(gb.equals(ga) == true){
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
