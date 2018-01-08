package me.monderdragon.Clans.Clan;

public enum Cargos {
	Normal, Membro, Dono, Criador;

	public static Cargos paraCargo(String s) {
		if (s.equalsIgnoreCase("Normal")) {
			return Cargos.Normal;
		}
		if (s.equalsIgnoreCase("Membro")) {
			return Cargos.Membro;
		}
		if (s.equalsIgnoreCase("Dono")) {
			return Cargos.Dono;
		}
		if (s.equalsIgnoreCase("Criador")) {
			return Cargos.Criador;
		}
		return Normal;
	}
}
