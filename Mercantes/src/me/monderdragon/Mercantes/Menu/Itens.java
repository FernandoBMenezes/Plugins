package me.monderdragon.Mercantes.Menu;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import me.monderdragon.Mercantes.Arquivos.Arquivo;
import me.monderdragon.Mercantes.Mercadoria.Negociavel;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class Itens {
	public static ItemStack Vidro(DyeColor c) {
		@SuppressWarnings("deprecation")
		ItemStack v = new ItemStack(Material.STAINED_GLASS_PANE, 1, c.getData());
		ItemMeta v1 = v.getItemMeta();
		v1.setDisplayName(" ");
		v.setItemMeta(v1);
		return v;
	}

	public static ItemStack Vidro(String nome, DyeColor c) {
		@SuppressWarnings("deprecation")
		ItemStack v = new ItemStack(Material.STAINED_GLASS_PANE, 1, c.getData());
		ItemMeta v1 = v.getItemMeta();
		v1.setDisplayName(nome);
		v.setItemMeta(v1);
		return v;
	}

	public static ItemStack Proximo(int pag) {
		ItemStack p = new ItemStack(Material.PAPER);
		ItemMeta p1 = p.getItemMeta();
		p1.setDisplayName("" + pag);
		p.setItemMeta(p1);
		return p;
	}

	public static ItemStack Voltar() {
		ItemStack p = new ItemStack(Material.BARRIER);
		ItemMeta p1 = p.getItemMeta();
		p1.setDisplayName("Voltar");
		p.setItemMeta(p1);
		return p;
	}

	public static ItemStack getItem(String s) {
		// String leitura = Arquivo.Ler(s);
		@SuppressWarnings("deprecation")
		ItemStack OItem = new ItemStack(Material.getMaterial(Integer.parseInt(s.replace(" ", ""))));
		return OItem;
	}

	public static ItemMeta ItemCV(String item, ItemStack itm) {
		ItemMeta i = Itens.getItem(item).getItemMeta();
		//String[] cv = Utilidades.Hv.get(itm.getData());
		
		/*if (cv.length == 2) {
			i.setDisplayName("COMPRAR: " + cv[0] + " | VENDER: " + cv[1]);
			List<String> slore = new ArrayList<>();
			slore.add("Clique Abaixo para Editar a Quantidade!");
			slore.add("Clique Com Esquedo para +1");
			slore.add("Clique Com Direito para -1");
			slore.add("Clique Com Qualquer um Segurando o Shift para +64");
			i.setLore(slore);
			return i;
		} else {
			i.setDisplayName("[EM BREVE]");
			return i;
		}*/
		return i;
	}

	public static MaterialData pegarItem(String tipo, int numero) {
		if(Utilidades.Hm.containsKey(tipo) && Utilidades.Hm.get(tipo).contains(numero)){
		Negociavel m = Utilidades.Hm.get(tipo).get(numero);
		return m.pegar();
		} else {
		int itemI = Integer.parseInt(Arquivo.Ler(Arquivo.getLocal().toString() + "/" + tipo.toUpperCase() + "/" + numero + ".yml", "Item: "));
	    int itemT = Integer.parseInt(Arquivo.Ler(Arquivo.getLocal().toString() + "/" + tipo.toUpperCase() + "/" + numero + ".yml", "Tipo: "));
	    @SuppressWarnings("deprecation")
		MaterialData m = new MaterialData(itemI, (byte)itemT);
		return m;
		}
	}
}
