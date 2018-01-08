package me.monderdragon.Clans.Clan;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Location;

public interface Grupos {

	//DADOS
	void setarCriador(UUID u);
	UUID pegarCriador();
	
	//NOME/SIGLA
	void setarNome(String nome);
	String pegarNome();
	void setarSigla(String nome);
	String pegarSigla();
	
	//BASE
	void setarBase(Location l);
	Location pegarBase();
	
	//CARGOS
	void setarEncarregado(Cargos c, UUID u);
	Cargos pegarEncarregado(UUID u);
	HashMap<UUID, Cargos> pegarEncarregados();
	
	//FUNÇOES
	void importar();
	void deletar();
	void criar();
	void avisar(String s);
}
