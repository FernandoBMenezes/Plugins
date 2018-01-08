package me.monderdragon.BancoCentral.Inicio;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import me.monderdragon.BancoCentral.Arquivos.Arquivo;
import me.monderdragon.BancoCentral.Cliente.Cliente;
import me.monderdragon.BancoCentral.Utilidades.Utilidade;

public class Banco extends JavaPlugin implements Listener {

	public void onEnable() {
		if(Arquivo.Existe(new File(Banco.getLocal().toString())) == false){
			Arquivo.CriarPasta(new File(Banco.getLocal().toString()));
		}
		
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getPluginManager().registerEvents(new Eventos(), this);
		getServer().getPluginManager().registerEvents(new Comandos(), this);
		getCommand("conta").setExecutor(new Comandos());
		getCommand("pagar").setExecutor(new Comandos());
		getCommand("saldo").setExecutor(new Comandos());
		getCommand("top").setExecutor(new Comandos());
	}

	public void onDisable() {
		
	}

	public static void criarConta(Player p) {
		if (ExisteConta(p) == false) {
			new Cliente(0, p);
		}
	}

	public static Cliente getCliente(Player p) {
		if (ExisteConta(p) == true) {
			return Utilidade.bc.get(p.getUniqueId());
		} else {
			criarConta(p);
			return Utilidade.bc.get(p.getUniqueId());
		}
	}
	public static boolean ExisteConta(Player p){
		return Utilidade.bc.containsKey(p.getUniqueId());
	}
	public static File getLocal(){
	return Bukkit.getPluginManager().getPlugin("BancoCentral").getDataFolder();
	//return Bukkit.getWorld("world").getWorldFolder();
	}
}
