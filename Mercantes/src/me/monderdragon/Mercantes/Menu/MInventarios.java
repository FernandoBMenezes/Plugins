package me.monderdragon.Mercantes.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import me.monderdragon.Mercantes.Mercadoria.Negociavel;
import me.monderdragon.Mercantes.Mercadoria.Produto;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class MInventarios {
	static HashMap<String, ArrayList<Negociavel>> Hm = Utilidades.Hm;
	static Negociavel Vazio = new Produto("Nada", 166, 0);

	public static ArrayList<Negociavel> Pegar(String Tipo, int pag) {
		if (Hm.containsKey(Tipo.toUpperCase())) {
			ArrayList<Negociavel> HpA = Hm.get(Tipo.toUpperCase());
			ArrayList<Negociavel> RHpA = new ArrayList<>();
			for (int i = 0; i <= 6; i++) { // 0 a 6
				int ci = 6*pag+i+1;
				if(HpA.size() >= ci){
				if (HpA.get(6*pag+i) != null) {
					
					RHpA.add(i, HpA.get(6*pag+i));
					
				} else {
					RHpA.add(i, Vazio);
				}
				} else {
					RHpA.add(i, Vazio);
				}
			}
			return RHpA;
		} else {
			return RetornoVazio();
		}
	}

	public static ArrayList<Negociavel> RetornoVazio() {
		ArrayList<Negociavel> ArrayNull = new ArrayList<>();
		for (int i = 0; i <= 6; i++) {
			ArrayNull.add(i, Vazio);
		}
		return ArrayNull;
	}
}
