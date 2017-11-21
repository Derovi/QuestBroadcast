package by.derovi.questbroadcast.commands;

import by.derovi.questbroadcast.Broadcast;
import by.derovi.questbroadcast.QuestBroadcast;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.command.ConsoleCommandSender;

import java.util.Collection;

/**
 * Created by User on 13.11.2017.
 */
public class COMMAND_queb extends Command{
    public COMMAND_queb(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            sender.sendMessage("§4You can send this command only from console!");
            return;
        }
        if(args == null || args.length == 0) {
            sender.sendMessage("§4[QUEB] §c/queb reload - reload config, reload tasks");
            sender.sendMessage("§4[QUEB] §c/queb refresh - reload tasks");
            return;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            if(args.length != 1) {
                sender.sendMessage("§cUsage: /queb reload");
                return;
            }
            commandReload(sender, args);
            return;
        }
        if(args[0].equalsIgnoreCase("refresh")) {
            if(args.length != 1) {
                sender.sendMessage("§cUsage: /queb refresh");
                return;
            }
            commandRefresh(sender, args);
            return;
        }
        sender.sendMessage("§4[QUEB] §cCommand not found, use /queb");
    }

    public void commandReload(CommandSender sender, String[] args) {
        try {
            QuestBroadcast.reload();
            sender.sendMessage("§a[QUEB] Plugin has been reloaded!");
        } catch (Exception ex) {
            sender.sendMessage("§4[QUEB]§c Error when reloading plugin!");
        }
    }

    public void commandRefresh(CommandSender sender, String[] args) {
        try {
            QuestBroadcast.refresh();
            sender.sendMessage("§a[QUEB] Plugin has been refreshed!");
        } catch (Exception ex) {
            sender.sendMessage("§4[QUEB]§c Error while refreshing plugin!");
        }
    }
}
