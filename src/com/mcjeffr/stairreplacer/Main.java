package com.mcjeffr.stairreplacer;

import com.mcjeffr.stairreplacer.command.CmdInfo;
import com.mcjeffr.stairreplacer.command.CmdModify;
import com.mcjeffr.stairreplacer.command.CmdReload;
import com.mcjeffr.stairreplacer.command.CmdReplace;
import com.mcjeffr.stairreplacer.command.CmdUndo;
import com.mcjeffr.stairreplacer.object.CommandManager;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * This is the main class of the plugin. This class handles the plugin enable
 * and disable.
 *
 * @author McJeffr
 */
public class Main extends JavaPlugin {

    /* Attributes */
    private static JavaPlugin javaPlugin;

    @Override
    public void onEnable() {
        javaPlugin = this;
        saveDefaultConfig();
        Session.initializeConfig(this.getConfig());
        Session.initializeSnapshots();
        registerCommands();
        PluginManager pm = Bukkit.getPluginManager();
        for (Plugin plugin : pm.getPlugins()) {
            if (plugin instanceof WorldEditPlugin) {
                Session.setWorldEditPlugin((WorldEditPlugin) plugin);
                break;
            }
        }
    }

    @Override
    public void onDisable() {

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
        return javaPlugin;
    }

}
