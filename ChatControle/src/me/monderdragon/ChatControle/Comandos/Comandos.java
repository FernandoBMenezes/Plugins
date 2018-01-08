package me.monderdragon.ChatControle.Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.monderdragon.ChatControle.Utilidades.Utilidades;

public class Comandos implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("silencio")) {
			if(Utilidades.silenciar.contains(p)){
				p.sendMessage("§e[*] Desativou o Modo Silencio!");
				Utilidades.silenciar.remove(p);
			} else {
			p.sendMessage("§e[*] Ativou o Modo Silencio!");
			Utilidades.silenciar.add(p);
			}
		}
		return false;
	}
}
