package com.mcjeffr.stairreplacer.object;

import com.mcjeffr.stairreplacer.Main;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * This class contains the Messenger object. This object is used for storing and
 * sending messages throughout the plugin. The stored messages are loaded in
 * from the messages.yml file. There can only be one Messenger during runtime,
 * and therefore this class is in the Singleton Design Pattern format.
 *
 * @author McJeffr
 */
public class Messenger {

    /* Attributes */
    private final Map<String, String> MESSAGES;

    /* Instance */
    private static Messenger instance;

    /**
     * Private constructor used by the getInstance() method.
     */
    private Messenger() {
        MESSAGES = new HashMap<>();
        try {
            File file = new File(Main.getPlugin().getDataFolder(), "messages.yml");
            if (file.exists()) {
                YamlConfiguration config = new YamlConfiguration();
                config.load(file);
                ConfigurationSection section = config.getConfigurationSection("");
                for (String key : section.getKeys(false)) {
                    MESSAGES.put(key, config.getString(key));
                }
            }
        } catch (InvalidConfigurationException | IOException ex) {
            Main.getPlugin().getLogger().log(Level.SEVERE,
                    "The messages.yml could not be loaded.", ex);
        }
    }

    /**
     * This method returns the instance of the Messenger object. If there is no
     * instance yet, this method will create one and send that one back.
     *
     * @return The instance of the Messenger object.
     */
    public static Messenger getInstance() {
        if (instance == null) {
            instance = new Messenger();
        }
        return instance;
    }

    /**
     * This method will return the message that is attached to the provided key.
     *
     * @param key The key of the message.
     * @return The message that is attached to the provided key, or null if the
     * key doesn't exist.
     */
    public String getMessage(String key) {
        return MESSAGES.get(key);
    }

    /**
     * This method sends a message to a CommandSender. This message can contain
     * color codes, indicated with the '&' character. This message is unmapped,
     * meaning that it's not registered on the map of messages. Use the
     * Messenger#sendMessageWithArgs() method if you want to send a mapped
     * message to a player using the key of the message.
     *
     * @param sender The CommandSender that the message needs to be sent to.
     * @param message The message that needs to be sent to the CommandSender.
     * @param args The arguments that need to be put into the message.
     */
    public void sendUnmappedMessage(CommandSender sender, String message, String... args) {
        String s = String.format(message, (Object[]) args);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Config.getInstance().getPrefix() + s));
    }

    /**
     * This method will send a message from the map of registered messages with
     * the provided key and arguments.
     *
     * @param sender The CommandSender that the message needs to be sent to.
     * @param key The key of the message that needs to be fechted from the map
     * of messages.
     * @param args The arguments that need to be put into the message.
     */
    public void sendMessage(CommandSender sender, String key, String... args) {
        String s = String.format(getMessage(key), (Object[]) args);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                Config.getInstance().getPrefix() + s));
    }

    /**
     * This method will reload all the messages registered. It will clear the
     * Map containing all messages and then attempt to load them in again from
     * the configuration file.
     */
    public void reload() {
        MESSAGES.clear();
        try {
            File file = new File(Main.getPlugin().getDataFolder(), "messages.yml");
            if (file.exists()) {
                YamlConfiguration config = new YamlConfiguration();
                config.load(file);
                ConfigurationSection section = config.getConfigurationSection("");
                for (String key : section.getKeys(false)) {
                    MESSAGES.put(key, config.getString(key));
                }
            }
        } catch (InvalidConfigurationException | IOException ex) {
            Main.getPlugin().getLogger().log(Level.SEVERE,
                    "The messages.yml could not be loaded.", ex);
        }
    }

}
