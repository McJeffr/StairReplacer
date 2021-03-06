package com.mcjeffr.stairreplacer.object;

import com.mcjeffr.stairreplacer.Main;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * This method contains the object of Config. This object contains all the
 * values from the config file and it holds functions to reload this file or
 * fetch and store any information from the config file. This class contains a
 * Singleton Design Pattern, meaning that the method Config#getInstance() needs
 * to be used at all times to retrieve the config data.
 *
 * @author McJeffr
 */
public class Config {

    /* Attributes */
    private FileConfiguration config;
    private int maxBlockChange, maxSnapshots;
    private String prefix;

    /* Instance */
    private static Config instance;

    /**
     * Constructor for the Config object. This constructor accepts the config
     * file of the StairReplacer plugin. No checks are in place to make sure the
     * correct configuration file is served, so check if it is.
     */
    private Config() {
        this.config = Main.getPlugin().getConfig();
        this.maxBlockChange = config.getInt("limits.max-block-change");
        this.maxSnapshots = config.getInt("limits.max-undo-amount");
        this.prefix = config.getString("misc.prefix");
    }

    /**
     * This method returns the instance of Config. If there is no instance yet
     * initialized, it will return a new instance.
     *
     * @return The instance of Config.
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    /**
     * This method reloads all the stored values from the configuration file.
     */
    public void reload() {
        this.config = Main.getPlugin().getConfig();
        this.maxBlockChange = config.getInt("limits.max-block-change");
        this.maxSnapshots = config.getInt("limits.max-undo-amount");
        this.prefix = config.getString("misc.prefix");
    }

    /**
     * Getter for the maximum amount of blocks that can be changed.
     *
     * @return The maximum amount of blocks that can be changed.
     */
    public int getMaxBlockChange() {
        return maxBlockChange;
    }

    /**
     * Setter for the maximum amount of blocks that can be changed. This method
     * automatically updates the config.yml file as well.
     *
     * @param maxBlockChange The maximum amount of blocks that can be changed.
     */
    public void setMaxBlockChange(int maxBlockChange) {
        this.maxBlockChange = maxBlockChange;
        config.set("limits.max-block-change", maxBlockChange);
        Main.getPlugin().saveConfig();
    }

    /**
     * Getter for the maximum amount of snapshots that can be stored per player.
     *
     * @return The maximum amount of snapshots that can be stored per player.
     */
    public int getMaxSnapshots() {
        return maxSnapshots;
    }

    /**
     * Setter for the maximum amount of snapshots that can be stored per player.
     * This method automatically updates the config.yml file as well.
     *
     * @param maxSnapshots The maximum amount of snapshots that can be stored
     * per player.
     */
    public void setMaxSnapshots(int maxSnapshots) {
        this.maxSnapshots = maxSnapshots;
        config.set("limits.max-undo-amount", maxSnapshots);
        Main.getPlugin().saveConfig();
    }

    /**
     * Getter for the prefix in chat of the plugin.
     *
     * @return The prefix in chat of the plugin.
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Setter for the prefix of the plugin. This method automatically updates
     * the config.yml file as well.
     *
     * @param prefix The prefix of the plugin.
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
        config.set("misc.prefix", prefix);
        Main.getPlugin().saveConfig();
    }

}
