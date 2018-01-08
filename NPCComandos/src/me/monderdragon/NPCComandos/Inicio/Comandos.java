package me.monderdragon.NPCComandos.Inicio;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.monderdragon.NPCComandos.Entidades.Npc;
import me.monderdragon.NPCComandos.Utilidades.Utilidades;

public class Comandos implements CommandExecutor, Listener {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("npc")) {
			if(args[0].equals("criar")){
				
			}
			if(args[0].equals("deletar")){
				String nome = args[1];
				Npc np = Utilidades.pegarNpc(nome);
				np.deletar();
			}
			return true;
		}
		return false;
	}
}
