package me.monderdragon.BancoCentral.Inicio;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import me.monderdragon.BancoCentral.Cliente.Cliente;
import me.monderdragon.BancoCentral.Utilidades.Top;

public class Comandos implements CommandExecutor, Listener{
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(p.hasPermission("BancoCentral.Usar") == true){
		if (cmd.getName().equalsIgnoreCase("pagar")) {
			if(args.length == 2){
			int valor = Integer.parseInt("0"+args[1].replaceAll("\\D+",""));
			if(Bukkit.getPlayer(args[0]) == null){
				p.sendMessage("[BancoCentral] Impossivel achar este jogador!");
				return true;
			} else {
			Player p2 = (Player) Bukkit.getPlayer(args[0]);
			Cliente c = Banco.getCliente(p);
			Cliente c2 = Banco.getCliente(p2);
			if(valor <= 0 || c.Verificar(valor) == false){
				p.sendMessage("[BancoCentral] Sem dinheiro o suficiente ou invalido!");
				return true;
			} else {
			//FUNÇÃO
			c.Pagar(valor);
			c2.Receber(valor);
			//MSG
			p.sendMessage("[BancoCentral] Acabou de pagar: " + Double.parseDouble(args[1]) + ", Para " + p2.getName() + "!");
			p2.sendMessage("[BancoCentral] O " + p.getName() + " Lhe pagou: " + Double.parseDouble(args[1]) + "!");
			return true;
			}
			}
			} else {
				p.sendMessage("[BancoCentral] Comando: /PAGAR [NOME DO PLAYER] [VALOR]");
			}
		}
		if (cmd.getName().equalsIgnoreCase("saldo")) {
			Cliente pf = Banco.getCliente(p);
			p.sendMessage("[BancoCentral] Saldo: " + pf.getSaldo());
			return true;
		}
		if (cmd.getName().equalsIgnoreCase("top")) {
			Cliente c0 = Top.getTop();
			if(c0 != null){
			p.sendMessage("[BancoCentral] Mais Rico Online: " + c0.pegarDono().getName());
			} else {
			p.sendMessage("[BancoCentral] Mais Rico Online: Ninguem Detectado!");
			}
			return true;
		}
		} else {
			p.sendMessage("[BancoCentral] Sem direitos para isto!");
		}
		return false;
	}
}
