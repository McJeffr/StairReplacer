package com.mcjeffr.stairreplacer.object;

import com.mcjeffr.stairreplacer.Session;
import java.util.HashMap;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * This class contains the manager of subcommands. Subcommands are executed via
 * this manager.
 *
 * @author McJeffr
 */
public class CommandManager implements CommandExecutor {

    /* Attributes */
    private static final String PERMISSION_ADMIN = "stairreplacer.admin";
    private static final HashMap<List<String>, Subcommand> COMMANDS = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            boolean commandExists = false;
            for (List<String> s : COMMANDS.keySet()) {
                if (s.contains(args[0])) {
                    COMMANDS.get(s).executeCommand(sender, cmd, label, args);
                    commandExists = true;
                }
            }
            if (!commandExists) {
                sendMessage(sender, "&cUnknown subcommand &f" + args[0] + "&c.");
                sendInfoMessage(sender);
            }
        } else {
            sendInfoMessage(sender);
        }
        return true;
    }

    /**
     * This method adds a command to the list of commands containing a
     * subcommand.
     *
     * @param cmds The commands.
     * @param subCommand The subcommand of the command.
     */
    public static void addComand(List<String> cmds, Subcommand subCommand) {
        COMMANDS.put(cmds, subCommand);
    }

    /**
     * This method sends a message containing all commands to a CommandSender.
     *
     * @param sender The CommandSender to send the message to.
     */
    private void sendInfoMessage(CommandSender sender) {
        if (sender.hasPermission(PERMISSION_ADMIN)) {
            sendMessage(sender, "&eAvailable subcommands:");
            sendMessage(sender, "&e/sr &8[&ereplace &8OR &er&8] &e<from-id> <to-id>");
            sendMessage(sender, "&e/sr &8[&eundo &8OR &eu&8] &e<undo-amount>");
            sendMessage(sender, "&e/sr info");
            sendMessage(sender, "&e/sr reload");
        } else {
            sendMessage(sender, "&eAvailable subcommands:");
            sendMessage(sender, "&e/sr &8[&ereplace &8OR &er&8] &e<from-id> <to-id>");
            sendMessage(sender, "&e/sr &8[&eundo &8OR &eu&8] &e<undo-amount>");
            sendMessage(sender, "&e/sr info");
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
