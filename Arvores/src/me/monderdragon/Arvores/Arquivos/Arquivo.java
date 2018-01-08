package me.monderdragon.Arvores.Arquivos;

import java.io.File;

public class Arquivo {
	
	public static File pegarLocal(){
	return new File("").getAbsoluteFile();
	}
	
	public static boolean Existe(File f) {
		return f.exists();
	}
}
