package me.monderdragon.Mercantes.Npc;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public interface Loja {
	void setarLocal(Location l);
	Location pegarLocal();
	void setarTipo(Profession p);
	void crescer(boolean b);
	void idade(int i);
	void nomear(String s);
	void nomear(String s, boolean b);
	String pegarNome();
	//Invocar
	void nascer();
	void matar();
	Villager pegar();
	Npc pegarInstancia();
	void executar(Player p);
	void setarComando();
	void importar();
}
