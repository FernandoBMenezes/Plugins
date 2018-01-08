package me.monderdragon.Arvores.Arquivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Escrever {
	public static void Escrita(String Local, String Escrita){
		try {
		BufferedWriter Escrever = new BufferedWriter(new FileWriter(Local));
		Escrever.write(Escrita);
		Escrever.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
