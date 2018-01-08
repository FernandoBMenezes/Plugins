package me.monderdragon.Mercantes.Arquivos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;

import org.bukkit.Bukkit;

public class Arquivo {

	public static File getLocal(){
	return Bukkit.getPluginManager().getPlugin("Mercantes").getDataFolder();
	//return Bukkit.getWorld("world").getWorldFolder();
	}
	
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
	public static void Escrever(String Local, String Escrita){
		try {
		BufferedWriter Escrever = new BufferedWriter(new FileWriter(Local));
		Escrever.write(Escrita);
		Escrever.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static boolean Existe(File f) {
		return f.exists();
	}
	}