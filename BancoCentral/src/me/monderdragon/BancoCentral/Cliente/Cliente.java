package me.monderdragon.BancoCentral.Cliente;

import java.io.File;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.monderdragon.BancoCentral.Arquivos.Arquivo;
import me.monderdragon.BancoCentral.Inicio.Banco;
import me.monderdragon.BancoCentral.Utilidades.Utilidade;

public class Cliente implements Conta {
	private UUID u;
	private int SaldoAtual;

	public Cliente(int valor, Player pc) {
		this.u = pc.getUniqueId();
		String l = Banco.getLocal().toString() + "/" + u.toString();
		String b = l + "/Dados.yml";
		File f = new File(l);
		if (Arquivo.Existe(f) == false) {
			Arquivo.CriarPasta(f);
			Arquivo.CriarBloco(l + "/Dados.yml");
			Arquivo.Escrever(b, "Saldo", "" + 0);
			this.setSaldo(Integer.parseInt(Arquivo.Ler(b, "Saldo")));
		} else {
			this.setSaldo(Integer.parseInt(Arquivo.Ler(b, "Saldo")));
		}
		Utilidade.bc.put(u, this);
	}

	@Override
	public Player pegarDono() {
		return Bukkit.getPlayer(u);
	}

	@Override
	public void setSaldo(int valor) {
		this.SaldoAtual = valor;
		Atualizar();
	}

	@Override
	public void Negociar(Negocio tipo, int valor) {
		if (tipo == Negocio.Receber) {
			this.setSaldo(SaldoAtual + valor);
		}
		if (tipo == Negocio.Pagar) {
			this.setSaldo(SaldoAtual - valor);
		}
	}

	@Override
	public int getSaldo() {
		return this.SaldoAtual;
	}

	@Override
	public void Pagar(int valor) {
		this.Negociar(Negocio.Pagar, valor);
	}

	@Override
	public void Receber(int valor) {
		this.Negociar(Negocio.Receber, valor);
	}

	@Override
	public boolean Verificar(int valor) {
		return this.SaldoAtual - valor >= 0;
	}

	@Override
	public void Atualizar() {
		String l = Banco.getLocal().toString() + "/" + u.toString();
		String b = l + "/Dados.yml";
		Arquivo.Escrever(b, "Saldo", "" + this.SaldoAtual);
	}

}
