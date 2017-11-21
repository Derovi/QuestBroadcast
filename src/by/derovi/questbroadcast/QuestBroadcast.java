package by.derovi.questbroadcast;

import by.derovi.questbroadcast.commands.COMMAND_queb;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * Created by derovi on 21.11.2017.
 */
public class QuestBroadcast extends Plugin {
    public static File configFile;
    public static Configuration config;
    public static QuestBroadcast instance;

    @Override
    public void onEnable() {
        instance = this;
        loadConfigs();
        registerCommands();
        Data.loadData();
        startTasks();
        InfoManager.log("Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        stopTasks();
        InfoManager.log("Plugin has been disabled!");
    }

    public static void loadConfigs() {
        try {
            if (!instance.getDataFolder().exists()) {
                instance.getDataFolder().mkdir();
            }
            configFile = new File(instance.getDataFolder().getPath(), "config.yml");
            if (!configFile.exists()) {
                InputStream link = (instance.getClass().getResourceAsStream("/config.yml"));
                Files.copy(link, configFile.getAbsoluteFile().toPath());
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
            saveConfig();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void reload() {
        loadConfigs();
        stopTasks();
        Data.loadData();
        startTasks();
    }

    public static void refresh() {
        stopTasks();
        startTasks();
    }

    public static void startTasks() {
        try {
            for (MessageSet messageSet : Data.messages) {
                messageSet.start();
            }
        } catch (Exception ex) {
            InfoManager.taskSE("starting tasks!");
            ex.printStackTrace();
        }
    }

    public static void stopTasks() {
        try {
            for (MessageSet messageSet : Data.messages) {
                messageSet.stop();
            }
        } catch (Exception ex) {
            InfoManager.taskSE("stopping tasks!");
            ex.printStackTrace();
        }
    }

    public static void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void registerCommands() {
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new COMMAND_queb("queb"));
    }
}
