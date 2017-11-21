package by.derovi.questbroadcast;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.Collection;

/**
 * Created by User on 19.11.2017.
 */
public class Broadcast {
    public static void broadcastMessage(String server, String message) {
        try {
            Collection<ProxiedPlayer> proxiedPlayers = QuestBroadcast.instance.getProxy().getServerInfo(server).getPlayers();
            for (ProxiedPlayer proxiedPlayer : proxiedPlayers) {
                sendMessage(proxiedPlayer, message);
            }
        } catch (Exception ex) {
            InfoManager.serverRE(server);
        }
    }

    public static void broadcastTitle(String server, String tit, String subtit) {
        try {
            Collection<ProxiedPlayer> proxiedPlayers = QuestBroadcast.instance.getProxy().getServerInfo(server).getPlayers();
            for (ProxiedPlayer proxiedPlayer : proxiedPlayers) {
                sendTitle(proxiedPlayer, tit, subtit);
            }
        } catch (Exception ex) {
            InfoManager.serverRE(server);
        }
    }

    public static void sendMessage(ProxiedPlayer player, String message) {
        player.sendMessage(new TextComponent(message));
    }

    public static void sendTitle(ProxiedPlayer player, String tit, String subtit) {
        Title title = ProxyServer.getInstance().createTitle();
        title.title(new TextComponent(tit));
        title.subTitle(new TextComponent(subtit));
        player.sendTitle(title);
    }
}
