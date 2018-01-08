package me.monderdragon.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.monderdragon.Evento.Avisar;
import me.monderdragon.Login.Login;
import me.monderdragon.Login.Registrar;

public class Entrada implements CommandExecutor {
	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
	    Player p = (Player)sender;
		FileConfiguration pnu = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
				   if (cmd.getName().equalsIgnoreCase("cadastrar")) {
					   if(args.length == 0){
						Avisar.PAvisar(p);
					   return true;
					   } else {
					if(pnu.contains("Jogadores." + p.getUniqueId())){
						Avisar.PAvisar(p);
						return true;
						} else {
					   Registrar.PRegistrar(p, args[0]);
					   return true;
					   }
					   }
				   }
				   if (cmd.getName().equalsIgnoreCase("entrar")) {
					   if(args.length == 0){
						Avisar.PAvisar(p);
					   return true;
					   } else {
					   Login.PLogin(p, args[0]);
					   return true;
					   }
				   }
				return false;
		 }
}
