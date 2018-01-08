package me.monderdragon.Arvores.Comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.monderdragon.Arvores.Base.Arvores;
import me.monderdragon.Arvores.Utils.Utilidades;

public class Comandos implements Listener, CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("arvore")) {
			if(p.hasPermission("Arvores.Comandos") == true){
			if (args[0].equalsIgnoreCase("retirar")) {
				if (Utilidades.aColocadas.containsKey(p) == true) {
					Arvores.TArvore(p);
				} else {
					p.sendMessage("[Arvores] Coloque uma arvore antes de retirar!");
				}
			} else {
				if (Utilidades.lSelecionado.containsKey(p) == true) {
					Location lp = Utilidades.localp(p);
					Arvores.CArvore(p, args[0]);
					String[] pcb = new String[2];
					pcb[0] = "" + args[0];
					pcb[1] = "" + lp;
					Utilidades.aColocadas.put(p, pcb);
				} else {
					p.sendMessage("[Arvores] Selecione um local antes de tentar colocar uma arvore!");
				}
			}
		} else {
			p.sendMessage("[Arvores] Sem direitos para isto!");
		}
		}
		return false;
	}
}
