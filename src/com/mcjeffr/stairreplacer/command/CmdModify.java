package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr reload" command.
 *
 * @author McJeffr
 */
public class CmdModify extends Subcommand {

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
        if (args.length > 2) {
            switch (args[1].toLowerCase()) {
                case ("prefix"): {
                    String prefix = args[2];
                    Session.getConfig().setPrefix(prefix);
                    sendMessage(player, "&6Prefix has been changed to: &r" + prefix);
                    return;
                }
                case ("max-undo-limit"): {
                    if (TypeCheck.isInteger(args[2])) {
                        int maxUndoAmount = Integer.parseInt(args[2]);
                        Session.getConfig().setMaxSnapshots(maxUndoAmount);
                        sendMessage(player, "&6Max undo limit has been changed to: &e" + maxUndoAmount);
                        return;
                    } else {
                        sendMessage(player, "&cNot a number &f" + args[2] + "&c.");
                        return;
                    }
                }
                case ("max-block-change"): {
                    if (TypeCheck.isInteger(args[2])) {
                        int maxBlockChange = Integer.parseInt(args[2]);
                        Session.getConfig().setMaxBlockChange(maxBlockChange);
                        sendMessage(player, "&6Max block change has been changed to: &e" + maxBlockChange);
                        return;
                    } else {
                        sendMessage(player, "&cNot a number &f" + args[2] + "&c.");
                        return;
                    }
                }
                default: {
                    sendMessage(player, "&cUnknown modifier. Available modifiers:");
                    sendMessage(player, "&fprefix&e, &fmax-undo-limit &eor &fmax-block-change");
                }
            }
        } else {
            sendMessage(player, "&cNot enough arguments. Usage: &f/sr modify [type] [value]");
        }
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
