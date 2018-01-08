package me.monderdragon.Arvores.Arquivos;

import java.io.File;
import java.util.Formatter;

public class Criar {
	public static void Pasta(File Arq) {
		try {
			if (Arquivo.Existe(Arq) == false) {
				Arq.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Bloco(String Local) {
		Formatter b;
		try {
			b = new Formatter(Local);
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
