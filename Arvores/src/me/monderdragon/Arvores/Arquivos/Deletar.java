package me.monderdragon.Arvores.Arquivos;

import java.io.File;

public class Deletar {
	public static void Del(File dir) {
		if (Arquivo.Existe(dir) == true) {
			if (dir.isDirectory()) {
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++) {
					Del(new File(dir, children[i]));
					System.out.println("Deletado: " + dir.getName());
				}
			}
			dir.delete();
		}
	}
}
