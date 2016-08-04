package com.mcjeffr.stairreplacer.object;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 * This class executes Subcommands based off abstract methods.
 *
 * @author McJeffr
 */
public abstract class Subcommand {

    /**
     * This method gets the permission node from each Subcommand which is being
     * forwarded to check if a user has permissions to execute the Subcommand.
     *
     * @return The permission node of a Subcommand.
     */
    public abstract String getPermission();

    /**
     * This method executes a command that is defined in a class that extends
     * this one.
     *
     * @param sender The CommandSender of the command.
     * @param cmd The Command that has been executed.
     * @param string The String of the command that has been executed.
     * @param args The arguments of the command that has been executed.
     */
    public abstract void onCommand(CommandSender sender, Command cmd, String string, String[] args);

    /**
     * This method executes the command that has been defined in the onCommand()
     * method.
     *
     * @param sender The CommandSender of the command.
     * @param cmd The Command that has been executed.
     * @param string The String of the command that has been executed.
     * @param args The arguments of the command that has been executed.
     * @return True if the command has been executed successfully, false
     * otherwise.
     */
    public boolean executeCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (!sender.hasPermission(getPermission())) {
            Messenger.getInstance().sendMessage(sender, "err-not_enough_permissions", getPermission());
            return false;
        } else {
            onCommand(sender, cmd, string, args);
            return true;
        }
    }

}
