package me.monderdragon.BancoCentral.Utilidades;

import java.util.ArrayList;
import java.util.Comparator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.monderdragon.BancoCentral.Cliente.Cliente;
import me.monderdragon.BancoCentral.Cliente.Conta;

public class Top {
	public static Cliente getTop() {
		ArrayList<Cliente> tops = new ArrayList<>(Utilidade.bc.values());
		for (Player p : Bukkit.getOnlinePlayers()) {
			Cliente c0 = new Cliente(0, p);
			c0.getSaldo();
		}
		tops.sort(new Comparator<Conta>() {
			@Override
			public int compare(Conta c0, Conta c1) {
				String rc0 = c0.getSaldo() + "";
				String rc1 = c1.getSaldo() + "";
				return rc0.compareToIgnoreCase(rc1);
			}
		});
		if (tops.isEmpty() == true) {
			return null;
		} else {
			return tops.get(tops.size() - 1);
		}
	}
}
