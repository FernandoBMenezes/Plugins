package me.monderdragon.Mercantes.Eventos;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.monderdragon.BancoCentral.Cliente.Cliente;
import me.monderdragon.BancoCentral.Inicio.Banco;
import me.monderdragon.Mercantes.Menu.Itens;
import me.monderdragon.Mercantes.Menu.Menu;
import me.monderdragon.Mercantes.Mercadoria.Negociavel;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class Inventarios implements Listener {
	@EventHandler
	public void fechou(InventoryCloseEvent e) {
		if (Utilidades.negociando.contains(e.getPlayer())) {
			Utilidades.negociando.remove(e.getPlayer());
		} else {

		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void clicou(InventoryClickEvent e) {
		if (e.getWhoClicked() != null && e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR
					&& e.getInventory().getName() != "container.inventory") {
				if (e.getInventory().getName().contains("[L]")) {
					e.setCancelled(true);// CANCELAR EVENTO

					Inventory inv = e.getInventory();
					String tipo = inv.getName().replace("[L] ", "");
					int pag = Integer.parseInt(inv.getItem(26).getItemMeta().getDisplayName());
					if (e.getSlot() == 26) {
						p.openInventory(Menu.Loja(tipo, p, pag + 1));
					}
					if (e.getSlot() == 18) {
						if (pag != 0) {
							p.openInventory(Menu.Loja(tipo, p, pag - 1));
						}
					}
					if (e.getSlot() >= 10 && e.getSlot() <= 16) {
						if (e.getCurrentItem().getData().getData() != 0) {
							p.sendMessage("§4[EM BREVE]§f");
						} else {
							e.getWhoClicked().openInventory(Menu.Negocios(p, tipo, e.getSlot() - 10, pag));
						}
					}
				}
				if (e.getInventory().getName().contains("[C]")) {
					e.setCancelled(true);// CANCELAR EVENTO
					Inventory inv = e.getInventory();
					//String tipo = inv.getName().replace("[C] ", "");
					Negociavel n0 = Utilidades.pesquisar(inv.getItem(13));
					int[] valor = n0.pegarValor();
					int qnt = inv.getItem(13).getAmount();
					Cliente c0 = Banco.getCliente(p);

					if (n0 != null && c0 != null) {

						if (e.getSlot() == 13) {
							if (e.getClick().isRightClick()) {
								if (e.getCurrentItem().getAmount() > 1) {
									e.getInventory().removeItem(new ItemStack(n0.pegar().toItemStack(1)));
								}
							}
							if (e.getClick().isLeftClick()) {
								e.getInventory().addItem(new ItemStack(n0.pegar().toItemStack(1)));
							}
							if (e.getClick().isShiftClick()) {
								e.getInventory().addItem(new ItemStack(n0.pegar().toItemStack(64)));
							}
						}
						if (e.getCurrentItem().equals(Itens.Vidro("COMPRAR", DyeColor.GREEN)) && valor[0] != 0) {// COMPRAR
							if (c0.Verificar(valor[0] * qnt) == true) {
								p.sendMessage("§4[Mercantes]§f Pagou: " + n0.pegarValor()[0] * qnt);
								p.getInventory().addItem(n0.pegar().toItemStack(qnt));
								c0.Pagar(valor[0] * qnt);
							} else {
								p.sendMessage("§4[Mercantes]§f Sem Dinheiro Suficiente!");
							}
						}
						if (e.getCurrentItem().equals(Itens.Vidro("VENDER", DyeColor.YELLOW)) && valor[1] != 0) {// VENDER
							if (p.getInventory().containsAtLeast(n0.pegar().toItemStack(), qnt)) {
								p.sendMessage("§4[Mercantes]§f Recebeu: " + n0.pegarValor()[1] * qnt);
								p.getInventory().removeItem(n0.pegar().toItemStack(qnt));
								c0.Receber(valor[1] * qnt);
							} else {
								p.sendMessage("§4[Mercantes]§f Nenhum item deste vc possui!");
							}
						}
					} else {
						p.closeInventory();
					}
				}
			}
		}
	}
}