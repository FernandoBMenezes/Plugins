package me.monderdragon.BancoCentral.Arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import org.bukkit.configuration.file.YamlConfiguration;

public class Arquivo {

	public static void CriarPasta(File Arquivo) {
		try {
			if (Existe(Arquivo) == false) {
				Arquivo.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void CriarBloco(String Local) {
		Formatter b;
		try {
			b = new Formatter(Local);
			b.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void Deletar(File dir) {
		if (Existe(dir) == true) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					Deletar(new File(dir, children[i]));
					System.out.println("Deletado: " + dir.getName());
				}
			}
			dir.delete();
		}
	}

	public static YamlConfiguration Novo(String local){
		return YamlConfiguration.loadConfiguration(new File(local));
	}
	
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
			return Ler.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String Ler(String Local, String Prefixo) {
		YamlConfiguration yml = Novo(Local);
		return ""+yml.get(Prefixo);
	}
	
	public static void Escrever(String Local, String chave, String valor){
		try {
			FileWriter escrever = new FileWriter(Local);
			escrever.write(chave + ": " + valor);
			escrever.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean Existe(File f) {
		return f.exists();
	}
	}