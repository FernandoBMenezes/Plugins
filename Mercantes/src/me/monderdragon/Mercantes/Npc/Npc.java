package me.monderdragon.Mercantes.Npc;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.PathEntity;

public class Npc implements Loja {
	private Villager Npc;
	private Location local;
	private String comando = "";

	public Npc(Location l) {
		this.setarLocal(l);
		this.nascer();
	}

	public Npc(Profession p, Location l) {
		this.setarLocal(l);
		this.nascer();
		this.setarTipo(p);
		Npc.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20*60*60*60*24*3, 1));
		Npc.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 20*60*60*60*24*3, 2));
		Npc.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60*60*60*24*3, 2));
		voltar();
	}

	@Override
	public void setarLocal(Location l) {
		this.local = l;
	}

	@Override
	public Location pegarLocal() {
		return this.local;
	}

	@Override
	public void setarTipo(Profession p) {
		this.pegar().setProfession(p);
	}

	@Override
	public void crescer(boolean b) {
		this.pegar().setAgeLock(b);
	}

	@Override
	public void idade(int i) {
		this.pegar().setAge(i);
	}

	@Override
	public void nomear(String s) {
		this.pegar().setCustomName(s);
	}

	@Override
	public void nomear(String s, boolean b) {
		this.nomear(s);
		this.pegar().setCustomNameVisible(b);
	}

	@Override
	public String pegarNome() {
		return this.pegar().getCustomName();
	}

	@Override
	public void nascer() {
		Npc = (Villager) this.pegarLocal().getWorld().spawnEntity(this.pegarLocal(), EntityType.VILLAGER);
	}

	@Override
	public void matar() {
		Utilidades.mercantes.remove(this.pegarInstancia());
		Utilidades.lojas = null;
		this.pegar().remove();
	}

	@Override
	public Villager pegar() {
		return this.Npc;
	}

	@Override
	public Npc pegarInstancia() {
		return this;
	}

	@Override
	public void executar(Player p) {
		if (comando != null && comando.isEmpty() == false) {
			Bukkit.dispatchCommand(p, comando);
		}
	}

	@Override
	public void setarComando() {
		this.comando = "loja " + this.pegarNome() + " 0";
	}

	@Override
	public void importar() {
		Utilidades.mercantes.add(this);
		Utilidades.lojas.add(this.pegarNome());
	}

	// Override de outros
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Villager) {
			if (this.pegar().getUniqueId().equals(((Villager) obj).getUniqueId()) == true) {
				return true;
			}
		}
		return false;
	}

	// OUTROS
	public void voltar() {
		Bukkit.getPluginManager().getPlugin("Mercantes").getServer().getScheduler()
		.scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("Mercantes"), new Runnable() {
			public void run() {
				EntityLiving nmsEntity = ((CraftLivingEntity) pegar()).getHandle();
				PathEntity pasta = ((EntityInsentient) nmsEntity).getNavigation().a(pegarLocal().getX(), pegarLocal().getY(), pegarLocal().getZ());
				((EntityInsentient) nmsEntity).getNavigation().a(pasta, 0.5);
				voltar();
			}
		}, 60L);
	}
}