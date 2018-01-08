package me.monderdragon.Clans.Arquivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escrever {
	public static void Escrita(String Local, String escrita){
		try {
		BufferedWriter escrever = new BufferedWriter(new FileWriter(Local));
		escrever.write(escrita.trim());
		escrever.flush();
		escrever.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void Escrita(String Local, String chave, String valor){
		try {
			FileWriter escrever = new FileWriter(Local);
			escrever.write((chave + ":" + valor).trim());
			escrever.flush();
			escrever.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
