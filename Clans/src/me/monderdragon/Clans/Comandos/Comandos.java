package me.monderdragon.Clans.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.monderdragon.Clans.Clan.Cargos;
import me.monderdragon.Clans.Clan.Clan;
import me.monderdragon.Clans.Utilidades.Utilidades;

public class Comandos implements CommandExecutor, Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("Clans") || cmd.getName().equalsIgnoreCase("Clan") || cmd.getName().equalsIgnoreCase("C")) {
			Player p = (Player) sender;
			if (args.length > 0 && args[0].equalsIgnoreCase(null) == false) {
				// INFO
				if (args[0].equalsIgnoreCase("info")) {
					Clan gd = Utilidades.pegarClan(p.getUniqueId());
					if (gd != null) {
						p.sendMessage("§4[Guildas]§f INFO");
						p.sendMessage("§4Criador: §f " + Bukkit.getPlayer(gd.pegarCriador()).getName());
						p.sendMessage("§4Sigla: §f " + "§4[§f" + gd.pegarSigla() + "§4]§f");
						p.sendMessage("§4Nome: §f " + gd.pegarNome());
						p.sendMessage("§4Possui Base? §f " + (gd.pegarBase() != null ? "Sim" : "Não"));
						return true;
					} else {
						p.sendMessage("§4[Guildas]§f Sem Clan!");
						return true;
					}
				}

				// CONVITE
				if (args[0].equalsIgnoreCase("convite")) {
					if (Utilidades.convite.containsKey(p.getUniqueId())) {
						Clan gd = Utilidades.convite.get(p.getUniqueId());
						if (args.length > 1 && args[1].equalsIgnoreCase(null) == false) {
							if (args[1].equalsIgnoreCase("aceitar")) {
								gd.setarEncarregado(Cargos.Membro, p.getUniqueId());
								p.sendMessage("§4[Clans]§f Convite de " + "§4[§f" + gd.pegarSigla() + "§4]§f" + " Aceito!");
								gd.avisar("§4[Clans]§f " + p.getName() + " Aceitou o convite!");
								Utilidades.convite.remove(p.getUniqueId());
								return true;
							}
							if (args[1].equalsIgnoreCase("recusar")) {
								p.sendMessage("§4[Clans]§f Convite de " + "§4[§f" + gd.pegarSigla() + "§4]§f" + " Recusado!");
								gd.avisar("§4[Clans]§f " + p.getName() + " Recusou o convite!");
								return true;
							}
							return true;
						} else {
							p.sendMessage("§4[Clans]§f");
							p.sendMessage("§4Criador: §f " + Bukkit.getPlayer(gd.pegarCriador()).getName());
							p.sendMessage("§4Sigla: §f " + "§4[§f" + gd.pegarSigla() + "§4]§f");
							p.sendMessage("§4Nome: §f " + gd.pegarNome());
							p.sendMessage("§4Possui Base? §f " + (gd.pegarBase() != null ? "Sim" : "Não"));
							p.sendMessage("");
							p.sendMessage("§fUse: §4/c convite [aceitar ou recusar]§f " + "'Para aceitar ou recusar o Convite'");
						}
					} else {
						p.sendMessage("§4[Clans]§f Nenhum convite!");
						return true;
					}
				}

				// CONVIDAR
				if (args[0].equalsIgnoreCase("convidar")) {
					if (args.length > 1 && args[1].equalsIgnoreCase(null) == false) {
						@SuppressWarnings("deprecation")
						Player p2 = Bukkit.getPlayer(args[1]);
						if (p2 != null) {
							Clan gd1 = Utilidades.pegarClan(p.getUniqueId());

							if (gd1 != null) {
								if (gd1.pegarEncarregado(p.getUniqueId()) == Cargos.Criador) {
									Clan gd2 = Utilidades
											.pegarClan(p2.getUniqueId());
									if (gd2 == null) {
										Utilidades.convidar(p2.getUniqueId(), gd1);
										p.sendMessage("§4[Clans]§f " + p2.getName() + " Convidado para Clan!");
										p2.sendMessage("[Clans] Foi convidado para " + "§4[§f" + gd1.pegarSigla() + "§4]§f" + " Use: §4/c convite§f Para saber mais!");
										gd1.avisar("§4[Clans]§f " + p.getName() + " Convidou " + p2.getName()
												+ " Para Clan!");
										return true;
									} else {
										p.sendMessage("§4[Clans]§f " + p2.getName() + " Ja esta em uma Clan!");
										return true;
									}

								} else {
									p.sendMessage("§4[Clans]§f Apenas os Superiores da Clan pode fazer isto!");
									return true;
								}
							} else {
								p.sendMessage("§4[Clans]§f Sem Clan!");
								return true;
							}
						} else {
							p.sendMessage("§4[Clans]§f Impossivel achar o Jogador!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Comando: /c convidar [nome]");
						return true;
					}
				}
				// EXPULSAR
				if (args[0].equalsIgnoreCase("expulsar")) {
					if (args.length > 1 && args[1].equalsIgnoreCase(null) == false) {
						@SuppressWarnings("deprecation")
						Player p2 = Bukkit.getPlayer(args[1]);
						if (p2 != null) {
							if (p.getUniqueId() == p2.getUniqueId()) {
								p.sendMessage("§4[Clans]§f Impossivel se expulsar!");
								p.sendMessage("§4Use: §f /c sair");
								return true;
							}
							Clan gd1 = Utilidades.pegarClan(p.getUniqueId());

							if (gd1 != null) {
								if (gd1.pegarEncarregado(p.getUniqueId()) == Cargos.Criador) {
									Clan gd2 = Utilidades
											.pegarClan(p.getUniqueId());
									if (gd2 != null) {
										if (gd1.equals(gd2)) {
											if (gd1.pegarEncarregado(p2.getUniqueId()) != Cargos.Criador) {
												gd1.setarEncarregado(Cargos.Normal, p2.getUniqueId());
												p.sendMessage(
														"§4[Clans]§f " + p2.getName() + " Foi expulso da Clan!");
												gd1.avisar("§4[Clans]§f " + p.getName() + " Expulsou o "
														+ p2.getName() + "!");
												return true;
											} else {
												p.sendMessage("§4[Clans]§f Criador só pode sair deletando!");
												return true;
											}
										} else {
											p.sendMessage("§4[Clans]§f " + p2.getName() + " Esta em outra Clan!");
											return true;
										}
									} else {
										p.sendMessage("§4[Clans]§f " + p2.getName() + " Não esta em uma Clan!");
										return true;
									}

								} else {
									p.sendMessage("§4[Clans]§f Apenas os Superiores da Clan pode fazer isto!");
									return true;
								}
							} else {
								p.sendMessage("§4[Clans]§f Sem Clan!");
								return true;
							}
						} else {
							p.sendMessage("§4[Clans]§f Impossivel achar o Jogador!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Comando: /c expulsar [nome]");
						return true;
					}
				}
				// BASE
				if (args[0].equalsIgnoreCase("base")) {
					Clan gd = Utilidades.pegarClan(p.getUniqueId());
					if (gd != null) {
						if (gd.pegarBase() != null) {
							p.teleport(gd.pegarBase());
							p.sendMessage("§4[Clans]§f Teleportado para base!");
							return true;
						} else {
							p.sendMessage("§4[Clans]§f Sem Base!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Sem Clan!");
						return true;
					}
				}
				// SAIR
				if (args[0].equalsIgnoreCase("sair")) {
					Clan gd = Utilidades.pegarClan(p.getUniqueId());
					if (gd != null) {
						if (gd.pegarEncarregado(p.getUniqueId()) != Cargos.Criador) {
							gd.setarEncarregado(Cargos.Normal, p.getUniqueId());
							gd.avisar("§4[Clans]§f " + p.getName() + " Saiu da Clan!");
							return true;
						} else {
							p.sendMessage("§4[Clans]§f Criador só pode sair deletando!");
							p.sendMessage("§4Use: §f /c deletar");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Sem Clan!");
						return true;
					}
				}
				// CRIAR CLAN
				if (args[0].equalsIgnoreCase("criar")) {
					if (args.length > 2 && args[1].equalsIgnoreCase(null) == false
							&& args[2].equalsIgnoreCase(null) == false) {
						if (!args[1].contains("&") && args[1].toString().length() >= 2
								&& args[1].toString().length() <= 5) {
							if (!args[2].contains("&") && args[2].toString().length() >= 5
									&& args[1].toString().length() <= 14) {
								Clan gdt = new Clan(p.getUniqueId(), args[1].toString(), args[2].toString());
								if(Utilidades.Existe(gdt) == null){
								Clan gdm = Utilidades.pegarClan(p.getUniqueId());
								if (!Utilidades.clans.contains(gdt)) {
									if (gdm == null) {
										gdt.importar();
										gdt.criar();
										p.sendMessage("§4[Clans]§f Clan §4[" + gdt.pegarSigla() + "] "
												+ gdt.pegarNome() + " §fCriada com Sucesso!");
										p.sendMessage("§4Para Ajuda Use: §f /c Ajuda");
										return true;
									} else {
										p.sendMessage("§4[Clans]§f Ja esta em um Clan!");
										p.sendMessage("§4Para Ajuda Use: §f /c Ajuda");
										return true;
									}
								} else {
									p.sendMessage("§4[Clans]§f Ja existe!");
									return true;
								}
								} else {
									p.sendMessage("§4[Clans]§f Ja existe!");
									return true;
								}
							} else {
								p.sendMessage("§4[Clans]§f Nome invalido precisa ter entre 5 e 14 Letras!");
								return true;
							}
						} else {
							p.sendMessage("§4[Clans]§f Sigla invalida precisa ter entre 2 e 5 Letras!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Comando: /c criar [sigla] [nome]");
						return true;
					}
				}

				// SETAR BASE
				if (args[0].equalsIgnoreCase("setarbase")) {
					Clan gd = Utilidades.pegarClan(p.getUniqueId());
					if (gd != null) {
						if (gd.pegarEncarregado(p.getUniqueId()) == Cargos.Criador) {
							Location sl = Utilidades.LocalSeguro(p.getLocation());
							if (sl != null) {
								gd.setarBase(p.getLocation());
								p.sendMessage("§4[Clans]§f Base Criada!");
								gd.avisar("§4[Clans]§f " + p.getName() + " Criou uma base para Clan!");
								return true;
							} else {
								p.sendMessage("§4[Clans]§f Lugar inseguro para base!");
								return true;
							}
						} else {
							p.sendMessage("§4[Clans]§f Apenas o Criador da Clan pode fazer isto!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Sem Clan!");
						return true;
					}
				}

				// DELETAR
				if (args[0].equalsIgnoreCase("deletar")) {
					Clan gd = Utilidades.pegarClan(p.getUniqueId());
					if (gd != null) {
						if (gd.pegarEncarregado(p.getUniqueId()) == Cargos.Criador) {
							gd.avisar("§4[Clans]§f " + p.getName() + " Deletou a Clan!");
							gd.deletar();
							p.sendMessage("§4[Clans]§f Deletada com Sucesso!");
							return true;
						} else {
							p.sendMessage("§4[Clans]§f Apenas o Criador da Clan pode fazer isto!");
							return true;
						}
					} else {
						p.sendMessage("§4[Clans]§f Sem Clan!");
						return true;
					}
				}

				// AJUDA
				if (args[0].equalsIgnoreCase("ajuda")) {
					p.sendMessage("");
					p.sendMessage("§4[Clans]§f Comandos: ");
					p.sendMessage("§4/c §fou§4 /clan");
					p.sendMessage("");
					p.sendMessage("§4COMANDOS NORMAIS");
					p.sendMessage("§4/c info " + "'Para ver info da sua Clan'");
					p.sendMessage("§4/c base§f " + "'Para ir até a base da Clan'");
					p.sendMessage("§4/c sair§f " + "'Para sair da Clan'");
					p.sendMessage("§4/c convite§f " + "'Para ver info da Clan que te convidou'");
					p.sendMessage("§4/c convite aceitar/recusar§f " + "'Para aceitar ou recusar o Convite'");
					p.sendMessage("");
					p.sendMessage("§4COMANDOS PARA GERENCIAR");
					p.sendMessage("§4/c criar [sigla] [nome]§f " + "'Para criar uma Clan'");
					p.sendMessage("§4/c convidar [nome]§f " + "'Para convidar alguem da Clan'");
					p.sendMessage("§4/c expulsar [nome]§f " + "'Para expulsar alguem da Clan'");
					p.sendMessage("§4/c setarbase§f " + "'Para setar base da Clan'");
					p.sendMessage("§4/c deletar§f " + "'Para deletar a Clan'");
					p.sendMessage("");
				}
			} else {
				p.sendMessage("§4[Clans]§f Comando Invalido!");
				p.sendMessage("§4Para Ajuda Use: §f /c Ajuda");
				return true;
			}
		}
		return false;
	}
}
