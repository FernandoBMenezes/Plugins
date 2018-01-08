package me.monderdragon.Mercantes.Menu;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import me.monderdragon.Mercantes.Mercadoria.Negociavel;
import me.monderdragon.Mercantes.Npc.Utilidades;

public class Menu implements Listener {
	private static Inventory Menu;

	public static Inventory Loja(String Tipo, Player p, int pag){
	    Menu = Bukkit.createInventory(p, 27, "[L] " + Tipo);
	    for(int i = 0 ; i <= 26; i++){
		Menu.setItem(i, Itens.Vidro(DyeColor.BLACK));
	    }
	    ArrayList<Negociavel> mi = MInventarios.Pegar(Tipo, pag);
	    for(int i1= 0; i1 <= 6; i1++){
	    	if(mi.size() >= i1 && mi.get(i1) != null && mi.get(i1).equals(MInventarios.Vazio) == false){
	    	Negociavel n0 = mi.get(i1);
		    Menu.setItem(i1+10, n0.pegar().toItemStack(1));
	    	} else {
	    	Menu.setItem(i1+10, Itens.Voltar());
	    	}
	    }
	    Menu.setItem(26, Itens.Proximo(pag));
	    Menu.setItem(18, Itens.Voltar());
	    return Menu;
	  }
	
	public static Inventory Negocios(Player p, String Tipo, int Numero, int pag){
	    Menu = Bukkit.createInventory(p, 27, "[C] " + Tipo);
	    for(int i = 0 ; i <= 26; i++){
		Menu.setItem(i, Itens.Vidro(DyeColor.BLACK));
	    }
	    int pb = Numero+pag*6;
	    MaterialData m = Itens.pegarItem(Tipo, pb);
	    //ITEM
	    ArrayList<String> Itemlore = new ArrayList<>();
	    Itemlore.add("Preço de Comprar 1: " + Utilidades.pesquisar(m).pegarValor()[0]);
	    Itemlore.add("Preço de Vender 1: " + Utilidades.pesquisar(m).pegarValor()[1]);
	    
		ItemMeta itemmeta = m.toItemStack().getItemMeta();
		itemmeta.setDisplayName("§lVALORES");
		itemmeta.setLore(Itemlore);
		ItemStack m1 = m.toItemStack(1);
		m1.setItemMeta(itemmeta);
	    Menu.setItem(13, m1);
	    //BRANCO
	    Menu.setItem(9, Itens.Vidro(DyeColor.WHITE));
	    Menu.setItem(12, Itens.Vidro(DyeColor.WHITE));
	    Menu.setItem(14, Itens.Vidro(DyeColor.WHITE));
	    Menu.setItem(17, Itens.Vidro(DyeColor.WHITE));
	    //VERDE
	    Menu.setItem(1, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    Menu.setItem(2, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    Menu.setItem(10, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    Menu.setItem(11, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    Menu.setItem(19, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    Menu.setItem(20, Itens.Vidro("COMPRAR", DyeColor.GREEN));
	    //AMARELO
	    Menu.setItem(6, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    Menu.setItem(7, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    Menu.setItem(15, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    Menu.setItem(16, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    Menu.setItem(24, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    Menu.setItem(25, Itens.Vidro("VENDER", DyeColor.YELLOW));
	    //INFO
	    ItemStack placa = new ItemStack(Material.SIGN);
	    placa.setItemMeta(Itens.ItemCV("63", m.toItemStack(1)));
	    //PLACA
	    ArrayList<String> placalore = new ArrayList<>();
	    placalore.add("Clique Abaixo para §lEditar a Quantidade!");
	    placalore.add("Clique Com Esquedo para §l+1");
	    placalore.add("Clique Com Direito para §l-1");
	    placalore.add("Clique Com Qualquer um Segurando o Shift para §l+64");
		ItemMeta placameta = placa.getItemMeta();
		placameta.setDisplayName("§lINFO");
		placameta.setLore(placalore);
		placa.setItemMeta(placameta);
	    Menu.setItem(4, placa);
	    
	    return Menu;
	  }
}
