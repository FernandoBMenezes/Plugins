package me.monderdragon.Mercantes.Eventos;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import me.monderdragon.Mercantes.Npc.Npc;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class INpc implements Listener {
	@EventHandler
	public void clicou(PlayerInteractEntityEvent e) {
		Entity ent = e.getRightClicked();
		Player p = e.getPlayer();
		if (ent instanceof Villager) {
			Villager v = (Villager) ent;
			Npc v0 = Utilidades.verificar(v);
			if (Utilidades.verificar(v) != null) {
				e.setCancelled(true);
				v0.voltar();
				v0.executar(p);
			}
		}
	}

	@EventHandler
	public void bateu(EntityDeathEvent e) {
		if (e.getEntity() instanceof Villager) {
			Npc nv = Utilidades.verificar((Villager) e.getEntity());
			if (nv != null) {
				nv.matar();
			}
		}
	}
}
