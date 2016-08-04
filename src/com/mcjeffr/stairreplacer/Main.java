package com.mcjeffr.stairreplacer;

import com.mcjeffr.stairreplacer.command.CmdInfo;
import com.mcjeffr.stairreplacer.command.CmdModify;
import com.mcjeffr.stairreplacer.command.CmdReload;
import com.mcjeffr.stairreplacer.command.CmdReplace;
import com.mcjeffr.stairreplacer.command.CmdUndo;
import com.mcjeffr.stairreplacer.object.CommandManager;
import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin. This class handles the plugin enable
 * and disable.
 *
 * @author McJeffr
 */
public class Main extends JavaPlugin {

    /* Attributes */
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        if (Session.getInstance().getWorldEditPlugin() == null) {
            this.getLogger().log(Level.SEVERE, "WorldEdit could not be found. Please make sure that WorldEdit is installed. Switching plugin off.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        plugin = this;
        saveDefaultConfigs();
        registerCommands();
    }

    @Override
    public void onDisable() {

    }

    /**
     * This method checks if the default configs for the plugin are present. If
     * they are not present, it will move the default configuration files to the
     * plugin folder.
     */
    private void saveDefaultConfigs() {
        saveDefaultConfig();
        File messageConfigFile = new File(this.getDataFolder(), "messages.yml");
        if (!messageConfigFile.exists()) {
            this.saveResource("messages.yml", false);
        }
    }

    /**
     * This method registers the commands of the plugin.
     */
    private void registerCommands() {
        getCommand("stairreplacer").setExecutor(new CommandManager());
        CommandManager.addComand(Arrays.asList("replace", "r"), new CmdReplace());
        CommandManager.addComand(Arrays.asList("undo", "u"), new CmdUndo());
        CommandManager.addComand(Arrays.asList("info"), new CmdInfo());
        CommandManager.addComand(Arrays.asList("reload"), new CmdReload());
        CommandManager.addComand(Arrays.asList("modify"), new CmdModify());
    }

    /**
     * This method gets the main plugin instance.
     *
     * @return The main plugin instance.
     */
    public static JavaPlugin getPlugin() {
        return plugin;
    }

}
