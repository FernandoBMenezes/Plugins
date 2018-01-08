package me.monderdragon.Mercantes.Inicio;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.Listener;
import me.monderdragon.Mercantes.Menu.Menu;
import me.monderdragon.Mercantes.Npc.Npc;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class Comandos implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("loja")) {
			if (args[0].equalsIgnoreCase("criar") && p.hasPermission("Mercantes.Criar")) {
				if (args.length > 2 && args[1] != null && args[2] != null
						&& Integer.parseInt("0" + args[2].replaceAll("\\D+", "")) >= 0) {
					int pv = Integer.parseInt("0" + args[2].replaceAll("\\D+", ""));
					Profession prof = Profession.getProfession(pv);
					Npc n0 = new Npc(prof, p.getLocation());
					n0.nomear(args[1].toUpperCase(), true);
					n0.setarComando();
					n0.importar();
				} else {
					p.sendMessage("[Mercantes]Comando: /loja criar [nome] [numero]");
					p.sendMessage("Tipos:");
					for (Profession profv : Profession.values()) {
						p.sendMessage(profv.getId() + "- " + profv.name());
					}
				}
			return true;
			}
			Utilidades.lojas();
			if(Utilidades.lojas().contains(args[0].toUpperCase())){
				p.openInventory(Menu.Loja(args[0], p, 0));
			}
		}
		return false;
	}
}
