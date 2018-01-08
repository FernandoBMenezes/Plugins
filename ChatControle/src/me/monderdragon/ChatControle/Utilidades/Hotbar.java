package me.monderdragon.ChatControle.Utilidades;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class Hotbar {
    public static void mostrarBarra(Player p, String msg){
      final String msg1 = msg.replace("_", " ").replaceAll("&", "§");
      String s = ChatColor.translateAlternateColorCodes('§', msg1);
      IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s +
        "\"}");
      PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
      
      ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
    }
}
