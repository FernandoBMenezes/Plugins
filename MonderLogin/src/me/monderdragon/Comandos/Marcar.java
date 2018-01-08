package me.monderdragon.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Marcar implements Listener, CommandExecutor {
	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		FileConfiguration MCC = Bukkit.getPluginManager().getPlugin("MonderLogin").getConfig();
	    Player p = (Player)sender;
		   if (cmd.getName().equalsIgnoreCase("sentrada")) {
			   if(p.hasPermission("ML.setentrada")){
                   MCC.set("Inicio.mundo", p.getLocation().getWorld().getName());
                   MCC.set("Inicio.x", p.getLocation().getX());
                   MCC.set("Inicio.y", p.getLocation().getY());
                   MCC.set("Inicio.z", p.getLocation().getZ());
                   Bukkit.getPluginManager().getPlugin("MonderLogin").saveConfig();
                   p.sendMessage(args[0]);
                   p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Entrada marcada!");
                   return true;
			   } else {
				   p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Sem direitos!");
			   }
	      }
		   if(cmd.getName().equalsIgnoreCase("entrada")){
		        World w = Bukkit.getServer().getWorld(MCC.getString("Inicio.mundo"));
		        double x = MCC.getDouble("Inicio.x");
		        double y = MCC.getDouble("Inicio.y");
		        double z = MCC.getDouble("Inicio.z");
		        p.teleport(new Location(w, x, y, z));
		        p.sendMessage(ChatColor.RED + "[Nayd] " + ChatColor.WHITE + "Foi para a entrada!");
		   }
		return false;
}
}