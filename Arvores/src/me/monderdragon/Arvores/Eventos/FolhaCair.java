package me.monderdragon.Arvores.Eventos;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

public class FolhaCair implements Listener{
	@EventHandler(priority = EventPriority.HIGH)
	public void onLeavesDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}
}
