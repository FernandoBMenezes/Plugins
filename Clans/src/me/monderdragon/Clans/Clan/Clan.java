package me.monderdragon.Clans.Clan;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.monderdragon.Clans.Arquivos.Arquivo;
import me.monderdragon.Clans.Arquivos.Criar;
import me.monderdragon.Clans.Arquivos.Deletar;
import me.monderdragon.Clans.Arquivos.Escrever;
import me.monderdragon.Clans.Arquivos.Leitura;
import me.monderdragon.Clans.Utilidades.Utilidades;

public class Clan implements Grupos {

	// DADOS
	private UUID Criador;

	private String Nome;
	private String Sigla;

	private Location Base;
	private String Vinculo = "";

	private HashMap<UUID, Cargos> Encarregado = new HashMap<>();

	public Clan(UUID u, String sigla, String nome) {
		this.setarSigla(sigla.toUpperCase());
		this.setarNome(nome.toUpperCase());
		this.setarCriador(u);
	}
	//FUNÇOES
	@Override
	public void criar() {
		File f = new File(Arquivo.pegarLocal() + "/" + pegarNome());
		if(Arquivo.Existe(f) == false){
			Criar.Pasta(f);
			//DADOS
			String dados = f + "/Dados.yml";
			Criar.Bloco(dados);
			Escrever.Escrita(dados, "Sigla", this.pegarSigla() + ";");
			Escrever.Escrita(dados, Leitura.Ler(dados) + "Nome", this.pegarNome() + ";");
			Escrever.Escrita(dados, Leitura.Ler(dados) + "Criador", this.pegarCriador() + ";");
			Escrever.Escrita(dados, Leitura.Ler(dados) + "Vinculada", Vinculo + ";");
			//BASE
			String base = f + "/Base.yml";
			Criar.Bloco(base);
			if(Base != null){
				Escrever.Escrita(base, "Mundo", Base.getWorld().getName() + ";");
				Escrever.Escrita(base, Leitura.Ler(base) + "X", (int)Base.getX() + ";");
				Escrever.Escrita(base, Leitura.Ler(base) + "Y", (int)Base.getY() + ";");
				Escrever.Escrita(base, Leitura.Ler(base) + "Z", (int)Base.getZ() + ";");
			} else {
				Escrever.Escrita(base, "Mundo", null + ";");
			}
			// MEMBROS
			String lmembros = f + "/Membros.yml";
			Criar.Bloco(lmembros);
		}
	}
	
	@Override
	public void deletar() {
		File f = new File(Arquivo.pegarLocal() + "/" + pegarNome());
		Utilidades.clans.remove(this);
		Deletar.Del(f);
	}
	
	@Override
	public void importar() {
		Utilidades.clans.add(this);
	}
	
	@Override
	public void avisar(String s) {
		for(UUID ps : this.pegarEncarregados().keySet()){
			OfflinePlayer pe = Bukkit.getOfflinePlayer(ps);
			if(pe.isOnline() == true){
				Player pon = pe.getPlayer();
				pon.sendMessage(s);
			}
		}
	}
	
	// CRIADOR
	@Override
	public void setarCriador(UUID u) {
		this.Criador = u;
		this.Encarregado.put(u, Cargos.Criador);
	}

	@Override
	public UUID pegarCriador() {
		return this.Criador;
	}

	// NOME
	@Override
	public void setarNome(String nome) {
		this.Nome = nome;
	}

	@Override
	public String pegarNome() {
		return this.Nome;
	}

	// SIGLA
	@Override
	public void setarSigla(String sigla) {
		this.Sigla = sigla;
	}

	@Override
	public String pegarSigla() {
		return this.Sigla;
	}

	@Override
	public void setarBase(Location l) {
		this.Base = l;
		File f = new File(Arquivo.pegarLocal() + "/" + pegarNome());
		String base = f + "/Base.yml";
		Escrever.Escrita(base, "Mundo", Base.getWorld().getName() + ";");
		Escrever.Escrita(base, Leitura.Ler(base) + "X", (int)Base.getX() + ";");
		Escrever.Escrita(base, Leitura.Ler(base) + "Y", (int)Base.getY() + ";");
		Escrever.Escrita(base, Leitura.Ler(base) + "Z", (int)Base.getZ() + ";");
	}

	// BASE
	@Override
	public Location pegarBase() {
		return this.Base;
	}

	// ENCARREGADOS
	@Override
	public void setarEncarregado(Cargos c, UUID u) {
		File f = new File(Arquivo.pegarLocal() + "/" + pegarNome());
		this.Encarregado.put(u, c);
		String dados = u + ":" + c + ";";
		String local = f + "/Membros.yml";
		String lida = Leitura.Ler(local);
		if(lida.contains(u.toString())){
		String llinha = Leitura.Linha(local, u.toString());
		if(llinha != null && llinha.length() > 2){
			Escrever.Escrita(local, lida.replaceAll(llinha, dados));
		}
		} else {
			Escrever.Escrita(local, lida + "\n" + dados);
		}
	}

	@Override
	public Cargos pegarEncarregado(UUID u) {
		if(this.pegarCriador().equals(u)){
			return Cargos.Criador;
		}
		if (this.Encarregado.containsKey(u)) {
			return this.Encarregado.get(u);
		}
		return Cargos.Normal;
	}

	@Override
	public HashMap<UUID, Cargos> pegarEncarregados() {
		return this.Encarregado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Criador == null) ? 0 : Criador.hashCode());
		result = prime * result + ((Nome == null) ? 0 : Nome.hashCode());
		result = prime * result + ((Sigla == null) ? 0 : Sigla.hashCode());
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
		if (!(obj instanceof Clan)) {
			return false;
		}
		Clan other = (Clan) obj;
		if (Criador == null) {
			if (other.Criador != null) {
				return false;
			}
		} else if (!Criador.equals(other.Criador)) {
			return false;
		}
		if (Nome == null) {
			if (other.Nome != null) {
				return false;
			}
		} else if (!Nome.equals(other.Nome)) {
			return false;
		}
		if (Sigla == null) {
			if (other.Sigla != null) {
				return false;
			}
		} else if (!Sigla.equals(other.Sigla)) {
			return false;
		}
		return true;
	}
}
