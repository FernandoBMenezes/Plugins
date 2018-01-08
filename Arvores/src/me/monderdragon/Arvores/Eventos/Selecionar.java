package me.monderdragon.Arvores.Eventos;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import me.monderdragon.Arvores.Base.Arvores;
import me.monderdragon.Arvores.Utils.Utilidades;

public class Selecionar implements Listener {
	@EventHandler(priority = EventPriority.HIGH)
	public void Clicou(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getClickedBlock() != null && p.hasPermission("Arvores.Usar")) {
			if (p.getInventory().getItemInHand().getType() == Material.STICK) {
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					Block b = e.getClickedBlock();
					Utilidades.lSelecionado.put(p, b.getLocation().add(0, 1, 0));
					p.sendMessage("[Arvores] Selecionado!");
				}
				if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (Utilidades.lSelecionado.containsKey(p) == true) {
						Block b = e.getClickedBlock();
						Utilidades.lSelecionado.put(p, b.getLocation().add(0, 1, 0));
						// Colocar
						String tipo = Utilidades.aColocadas.get(p)[0];
						Arvores.CArvore(p, tipo);
						String[] snr = new String[2];
						snr[0] = "" + tipo;
						snr[1] = "" + b.getLocation().add(0, 1, 0);
						Utilidades.aColocadas.put(p, snr);

						p.sendMessage("[Arvores] Selecionado e Colocado!");
					} else {
						p.sendMessage("[Arvores] Para usar este sistema precisa ja ter colocado uma arvore antes!");
					}
				}
			}

		}
	}
}
