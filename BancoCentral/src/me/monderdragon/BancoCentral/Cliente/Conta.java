package me.monderdragon.BancoCentral.Cliente;

import org.bukkit.entity.Player;

public interface Conta {
	void setSaldo(int valor);
	int getSaldo();
	void Pagar(int valor);
	void Receber(int valor);
	boolean Verificar(int valor);
	void Atualizar();
	void Negociar(Negocio tipo, int valor);
	Player pegarDono();
}
