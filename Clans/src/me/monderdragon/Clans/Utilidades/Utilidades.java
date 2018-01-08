package me.monderdragon.Clans.Utilidades;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import me.monderdragon.Clans.Arquivos.Arquivo;
import me.monderdragon.Clans.Arquivos.Leitura;
import me.monderdragon.Clans.Clan.Cargos;
import me.monderdragon.Clans.Clan.Clan;

public class Utilidades {

	public static ArrayList<Clan> clans = new ArrayList<>();
	public static HashMap<UUID, Clan> convite = new HashMap<>();

	public static Clan pegarClan(UUID u) {
		for (Clan gd : clans) {
			
			if (gd.pegarCriador().equals(u) || gd.pegarEncarregado(u) != Cargos.Normal) {
				return gd;
			}
		}
		return null;
	}

	public static void convidar(UUID u, Clan gd) {
		convite.put(u, gd);
	}

	public static Clan Existe(Clan gdo){
		for (Clan gd : clans) {
			if(gd.equals(gdo) || gd.pegarNome().equals(gdo.pegarNome()) || gd.pegarSigla().equals(gdo.pegarSigla())){
				return gd;
			}
		}
		return null;
	}
	
	public static void Importar() {
		File local = Arquivo.pegarLocal();
		for (File f : local.listFiles()) {
			Clan gd = null;
			// DADOS
			String[] Dados = Leitura.Linhas(f + "/Dados.yml");
			if (Dados != null) {
				String Sigla = (Dados[0].split(":"))[1];
				String Nome = (Dados[1].split(":"))[1];
				String Criador = (Dados[2].split(":"))[1];
				// TODO String Vinculada = (Dados[3].split(":"))[1];
				// FORMATADOS
				UUID u = UUID.fromString(Criador);
				// CRIAR
				gd = new Clan(u, Sigla, Nome);
				gd.setarCriador(u);
				gd.importar();
				gd.setarCriador(u);
			} else {
				return;
			}
			// BASE
			String[] DBase = Leitura.Linhas(f + "/Base.yml");
			if (DBase != null && gd != null) {
				String dMundo = (DBase[0].split(":"))[1];
				if(!dMundo.equals("null")){
				String dX = (DBase[1].split(":"))[1];
				String dY = (DBase[2].split(":"))[1];
				String dZ = (DBase[3].split(":"))[1];
				// FORMATADOS
				World Mundo = Bukkit.getWorld(dMundo);
				int X = Integer.parseInt(dX);
				int Y = Integer.parseInt(dY);
				int Z = Integer.parseInt(dZ);
				Location lbase = new Location(Mundo, X, Y, Z);
				// SETAR
				gd.setarBase(lbase);

				// MEMBROS
				}
				Membros(f, gd);

			}
		}
	}

	public static void Membros(File f, Clan gd) {
		// MEMBROS
		String[] lMembro = Leitura.Linhas(f + "/Membros.yml");
		if (lMembro != null && gd != null) {
			for (String Sm : lMembro) {
				String[] Membro = Sm.split(":");
				if (Sm.length() == 2) {
					String ul = Membro[0];
					String cl = Membro[1];
					// FORMATADOS
					UUID u = UUID.fromString(ul);
					Cargos c = Cargos.paraCargo(cl);
					gd.setarEncarregado(c, u);
				}
			}
		}
	}

	public static Location LocalSeguro(Location l) {
		Location lmais = new Location(l.getWorld(), l.getX(), l.getY() + 1, l.getZ());
		Location lmenos = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());
		if (l.getBlock().getType().equals(Material.AIR) == true
				&& lmenos.getBlock().getType().equals(Material.AIR) == false) {
			return l;
		} else {
			if (l.getY() < 300 && l.getY() > 0 && l.getBlock().getType() != Material.LAVA && l.getBlock().getType() != Material.STATIONARY_LAVA && l.getBlock().getType() != Material.CACTUS) {
				if (l.getBlock().getType().equals(Material.AIR) == true
						&& lmenos.getBlock().getType().equals(Material.AIR) == true) {
					return LocalSeguro(lmenos);
				}
				if (l.getBlock().getType().equals(Material.AIR) == false
						&& lmenos.getBlock().getType().equals(Material.AIR) == false) {
					return LocalSeguro(lmais);
				}
			} else {
				return null;
			}
		}
		return null;
	}
}
