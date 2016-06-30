package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Main;
import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.object.Subcommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr reload" command.
 *
 * @author McJeffr
 */
public class CmdReload extends Subcommand {

    /* Attributes */
    private static final String PERMISSION = "stairreplacer.admin";

    @Override
    public String getPermission() {
        return PERMISSION;
    }

    @Override
    public void onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sendMessage(sender, "&cOnly players can execute this command.");
            return;
        }
        Player player = (Player) sender;
        Main.getPlugin().reloadConfig();
        Session.getConfig().reload(Main.getPlugin().getConfig());
        sendMessage(player, "&6Reloaded the configuration file.");
    }

    /**
     * This method sends a (colored) message to a CommandSender.
     *
     * @param sender The CommandSender.
     * @param message The message, with optional color codes indicated by the
     * character '&'.
     */
    private void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Session.getConfig().getPrefix() + message));
    }

}
