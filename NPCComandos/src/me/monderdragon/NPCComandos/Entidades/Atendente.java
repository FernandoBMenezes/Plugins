package me.monderdragon.NPCComandos.Entidades;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;

import me.monderdragon.NPCComandos.Utilidades.Utilidades;

public class Atendente implements Npc {

	// DADOS
	Profession Trabalho = Profession.LIBRARIAN;
	String Nome = null;
	Location Local;

	Villager Npc;
	String Comando = null;
	int Tipo;

	@Override
	public void criar() {
		// TODO Auto-generated method stu
		
	}
	
	@Override
	public void deletar() {
		// TODO Auto-generated method stub
		
	}
	
	public String pegarUUID(){
		return Trabalho.getId()+"-"+Nome;
	}
	
	@Override
	public void nascer() {
		Npc = (Villager) this.pegarLocal().getWorld().spawnEntity(this.pegarLocal(), EntityType.VILLAGER);
	}

	@Override
	public void matar() {
		Utilidades.Atendentes.remove(this);
		this.pegar().remove();
	}
	
	@Override
	public void setarLocal(Location l) {
		this.Local = l;
	}

	@Override
	public Location pegarLocal() {
		return Local;
	}

	@Override
	public String pegarNome() {
		return Nome;
	}

	@Override
	public void nomear(String s, boolean b) {
		this.Nome = s;
		this.pegar().setCustomName(s);
		this.pegar().setCustomNameVisible(b);
	}

	@Override
	public void setarTipo(Profession p) {
		this.pegar().setProfession(p);
	}

	@Override
	public void crescer(boolean b) {
		this.pegar().setAgeLock(b);
	}

	@Override
	public void idade(int i) {
		this.pegar().setAge(i);
	}

	@Override
	public Villager pegar() {
		return this.Npc;
	}

	@Override
	public Npc pegarInstancia() {
		return this;
	}

	@Override
	public void setarComando(String s) {
		this.Comando = s;
	}

	@Override
	public void executar(Player p) {
		if (Comando != null) {
			if (Tipo == 0) { // EXECUTADO PELO JOGADOR

			}
			if (Tipo == 1) {// EXECUTADO PELO SERVIDOR

			}
		}
	}

	@Override
	public void importar() {
		if (Utilidades.Atendentes.contains(this) == false) {
			Utilidades.Atendentes.add(this);
		}
	}

	// OUTROS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Comando == null) ? 0 : Comando.hashCode());
		result = prime * result + ((Npc == null) ? 0 : Npc.hashCode());
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((Trabalho == null) ? 0 : Trabalho.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Atendente)) {
			return false;
		}
		Atendente other = (Atendente) obj;
		if (Comando == null) {
			if (other.Comando != null) {
				return false;
			}
		} else if (!Comando.equals(other.Comando)) {
			return false;
		}
		if (Npc == null) {
			if (other.Npc != null) {
				return false;
			}
		} else if (!Npc.equals(other.Npc)) {
			return false;
		}
		if (Nome == null) {
			if (other.Nome != null) {
				return false;
			}
		} else if (!Nome.equals(other.Nome)) {
			return false;
		}
		if (Trabalho != other.Trabalho) {
			return false;
		}
		return true;
	}

}
