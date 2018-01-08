package me.monderdragon.NPCComandos.Utilidades;

import java.util.ArrayList;

import me.monderdragon.NPCComandos.Entidades.Npc;

public class Utilidades {
	public static ArrayList<Npc> Atendentes = new ArrayList<>();
	
	public static Npc pegarNpc(Npc n0){
		if(Atendentes.isEmpty() == false){
			for(Npc nl : Atendentes){
				if(nl.equals(n0) == true){
					return nl;
				}
			}
		}
		return null;
	}
	   /** @deprecated Pois pode ter mais de um com mesmo nome!
	    ** Troca do por: Utilidades.pegarNpc(Npc n0);
	    ** Info: retorna null ou um npc **/
	public static Npc pegarNpc(String n0){
		if(Atendentes.isEmpty() == false){
			for(Npc nl : Atendentes){
				if(nl.pegarNome().equals(n0) == true){
					return nl;
				}
			}
		}
		return null;
	}
	
	public static void Importar(){
		
	}
}
