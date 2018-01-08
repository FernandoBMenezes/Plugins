package me.monderdragon.Arvores.Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Leitura {
	public static String Ler(String Local) {
		try {
			File Arquivo = new File(Local);
			FileReader ALido = new FileReader(Arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				Ler.append(line);
				Ler.append("\n");
			}
			ALido.close();
			// System.out.println(Ler);
			return Ler.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Ler(String Local, String Prefixo) {
		try {
			File Arquivo = new File(Local);
			FileReader ALido = new FileReader(Arquivo);
			BufferedReader Br = new BufferedReader(ALido);
			StringBuffer Ler = new StringBuffer();
			String line;
			while ((line = Br.readLine()) != null) {
				if (line.startsWith(Prefixo)) {
					Ler.append(line);
				}
			}
			ALido.close();
			return Ler.toString().replace(Prefixo, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
}
}
