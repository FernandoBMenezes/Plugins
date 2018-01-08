package me.monderdragon.BancoCentral.Utilidades;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import me.monderdragon.BancoCentral.Cliente.Cliente;

public class Utilidade {
	public static Plugin pl = Bukkit.getPluginManager().getPlugin("BancoCentral");
	public static HashMap<UUID, Cliente> bc = new HashMap<>();

}
