package me.monderdragon.Mercantes.Mercadoria;

import org.bukkit.material.MaterialData;

public interface Negociavel {
	//Dados
	void setarId(int id);
	void setarTid(int tid);
	void setarIdTid(int id, int tid);
	
	//Valor
	void setarValor(int v, int c);
	int[] pegarValor();
	
	//Pegar
	MaterialData pegar();
	
	//Utilidades
	void importar();
}
