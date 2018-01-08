package me.monderdragon.Mercantes.Mercadoria;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import me.monderdragon.Mercantes.Npc.Utilidades;

public class Produto implements Negociavel {

	// DADOS
	int Id;
	int Tid;
	String Tipo;
	MaterialData Item;

	// VALORES
	int Compra;
	int Venda;

	public Produto(String tipo, int id, int tid) {
		this.setarIdTid(id, tid);
		this.setarTipo(tipo);
		this.importar();
	}

	@Override
	public void setarId(int id) {
		this.Id = id;
	}

	@Override
	public void setarTid(int tid) {
		this.Tid = tid;
	}

	@Override
	public void setarIdTid(int id, int tid) {
		this.setarId(id);
		this.setarTid(tid);
	}

	public void setarTipo(String tipo) {
		this.Tipo = tipo;
	}

	@Override
	public void setarValor(int c, int v) {
		this.Compra = c;
		this.Venda = v;
	}

	@Override
	public int[] pegarValor() {
		int[] ii = new int[2];
		ii[0] = Compra;
		ii[1] = Venda;
		return ii;
	}

	@Override
	public MaterialData pegar() {
		return Item;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void importar() {
		Material m = Material.getMaterial(this.Id);
		MaterialData md = new MaterialData(m, (byte) Tid);
		this.Item = md;

		if (Utilidades.Hm.containsKey(Tipo)) {
			Utilidades.Hm.get(Tipo).add(this);
		} else {
			Utilidades.Hm.put(Tipo, new ArrayList<Negociavel>());
			Utilidades.Hm.get(Tipo).add(this);
		}
	}

	// OUTROS OVERRIDE
	@SuppressWarnings("deprecation")
	public boolean equals(Object obj) {

		// VERIFICAR
		if (obj instanceof ItemStack) {
			ItemStack item = (ItemStack) obj;
			if (item.getType() == Item.getItemType() && item.getData().getData() == Item.getData()) {
				return true;
			} else {
				return false;
			}
		}

		// OUTROS
		if (obj instanceof Negociavel) {
			return this.equals(((Negociavel) obj).pegar());
		}
		if (obj instanceof Material) {
			return this.equals(new ItemStack(((Material) obj)));
		}
		if (obj instanceof MaterialData) {
			return this.equals(((MaterialData) obj).toItemStack());
		}
		return false;
	}
}
