package me.monderdragon.NPCComandos.Entidades;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

public interface Npc {
	//LOCAL
	void setarLocal(Location l);
	Location pegarLocal();
	//DADOS
	String pegarNome();
	void nomear(String s, boolean b);
	void setarTipo(Profession p);
	
	//MODIFICAR
	void crescer(boolean b);
	void idade(int i);
	
	//FUNÇÕES
	void nascer();
	void matar();
	Villager pegar();
	Npc pegarInstancia();
	void setarComando(String s);
	void executar(Player p);
	void importar();
	void deletar();
	void criar();
}