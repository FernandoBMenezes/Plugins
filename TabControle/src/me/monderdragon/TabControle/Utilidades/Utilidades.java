package me.monderdragon.TabControle.Utilidades;

import org.bukkit.entity.Player;

public class Utilidades {

	public static void colocarTab(Player p, String tagGrupo) {

		String nome = p.getName();

		if (nome.length() >= 16) {
			nome = nome.substring(0, 16);
		}
		if (p.getCustomName() != null && p.getCustomName() != p.getName()) {
			p.setPlayerListName(tagGrupo + " " + p.getCustomName());
		} else {
			p.setPlayerListName(tagGrupo + " " + p.getName());
		}
	}
}