package me.monderdragon.Mercantes.Npc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.material.MaterialData;

import me.monderdragon.Mercantes.Arquivos.Arquivo;
import me.monderdragon.Mercantes.Menu.Itens;
import me.monderdragon.Mercantes.Mercadoria.Negociavel;
import me.monderdragon.Mercantes.Mercadoria.Produto;

public class Utilidades {
	public static ArrayList<Loja> mercantes = new ArrayList<>();
	public static ArrayList<String> lojas = new ArrayList<>();
	public static ArrayList<Player> negociando = new ArrayList<>();
	public static ArrayList<String> vlojas = new ArrayList<>();
	public static HashMap<String, ArrayList<Negociavel>> Hm = new HashMap<>();
	public static HashMap<MaterialData, String[]> Hv = new HashMap<>();

	public static Npc verificar(Villager v) {
		for (Loja m : Utilidades.mercantes) {
			if (m.equals(v) == true) {
				return m.pegarInstancia();
			}
		}
		return null;
	}
	public static Negociavel pesquisar(Object obj){
		for(ArrayList<Negociavel> neg : Hm.values()){
			for(Negociavel negocia : neg){
				if(negocia.equals(obj)){
					return negocia;
				}
			}
		}
		return null;
	}

	public static ArrayList<String> lojas() {
		if (lojas == null || lojas.isEmpty() == true) {
			lojas = new ArrayList<String>();
			for (Loja l : mercantes) {
				lojas.add(l.pegarNome());
			}
		} else {
			return lojas;
		}
		return lojas;
	}

	public static void proteger() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.closeInventory();
		}
	}

	@SuppressWarnings("deprecation")
	public static void Importar() {
		Npc();
		String l = Arquivo.getLocal().toString();
		File f = new File(l);
		String[] lojas = f.list();
		for (int i0 = 0; i0 < lojas.length; i0++) {
			if (!lojas[i0].contains(".yml")) {
				vlojas.add(lojas[i0].toUpperCase());
				String item = Arquivo.getLocal().toString() + "/" + lojas[i0];
				File iyml = new File(item);
				String[] itens = iyml.list();
				
				for (int i1 = 0; i1 < itens.length; i1++) {
					//MATERIAL E PRODUTO
					MaterialData material = Itens.pegarItem(lojas[i0], i1);
					Produto p0 = new Produto(lojas[i0], material.getItemTypeId(), material.getData());
					//LER VALORES
					String pcl = Arquivo.Ler(l + "/" + lojas[i0] + "/" + i1 + ".yml", "Compra: ");
					int pc = Integer.parseInt(pcl);
					String pvl = Arquivo.Ler(l + "/" + lojas[i0] + "/" + i1 + ".yml", "Venda: ");
					int pv = Integer.parseInt(pvl);
					p0.setarValor(pc, pv);//SETAR VALOR
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void Npc() {
		String ler = Arquivo.getLocal().toString() + "/Npc.yml";
		String lido = Arquivo.Ler(ler);
		for (String lido0 : lido.split(";")) {
			if (lido0.isEmpty() == false && lido0.length() > 3 && lido0 != null) {
				String[] lido1 = lido0.split(":");
				Profession prof = Profession.getProfession(Integer.parseInt("0" + lido1[1]));
				World w = Bukkit.getWorld(lido1[2]);
				int x = Integer.parseInt(lido1[3]);
				int y = Integer.parseInt(lido1[4]);
				int z = Integer.parseInt(lido1[5]);
				Location l = new Location(w, x, y, z);
				Npc n0 = new Npc(prof, l);
				n0.nomear(lido1[0], true);
				n0.setarComando();
				n0.importar();
			}
		}
	}

	public static void Voltar() {
		for (Loja lm : mercantes) {
			if (lm.pegarLocal().distanceSquared(lm.pegar().getLocation()) > 5) {
				lm.pegar().teleport(lm.pegarLocal());
			} else {
				lm.pegarInstancia().voltar();
			}
		}
	}
}
