package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.object.Snapshot;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr u" command.
 *
 * @author McJeffr
 */
public class CmdUndo extends Subcommand {

    /* Attributes */
    private static final String PERMISSION = "stairreplacer.use";

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
        if (args.length > 1) {
            if (TypeCheck.isInteger(args[1])) {
                int undos = Integer.parseInt(args[1]);
                int rollbackedBlocks = 0;
                for (int i = 0; i < undos; i++) {
                    Snapshot snapshot = Session.getLastSnapshot(player);
                    if (snapshot != null) {
                        rollbackedBlocks += snapshot.undo();
                        Session.removeSnapshot(player, snapshot);
                    } else {
                        break;
                    }
                }
                if (rollbackedBlocks != 0) {
                    sendMessage(player, "&6Rollbacked &e" + rollbackedBlocks + " &6blocks.");
                } else {
                    sendMessage(player, "&cNothing left to undo.");
                }
            } else {
                sendMessage(player, "&cNot a number &f" + args[1] + "&c. Usage: &f/sr u [number]");
            }
        } else {
            Snapshot snapshot = Session.getLastSnapshot(player);
            if (snapshot != null) {
                int rollbackedBlocks = snapshot.undo();
                Session.removeSnapshot(player, snapshot);
                sendMessage(player, "&6Rollbacked &e" + rollbackedBlocks + " &6blocks.");
            } else {
                sendMessage(player, "&cNothing left to undo.");
            }
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
