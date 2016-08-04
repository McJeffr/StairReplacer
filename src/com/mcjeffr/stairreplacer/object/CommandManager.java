package com.mcjeffr.stairreplacer.object;

import java.util.HashMap;
import java.util.List;
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
            for (List<String> s : COMMANDS.keySet()) {
                if (s.contains(args[0])) {
                    COMMANDS.get(s).executeCommand(sender, cmd, label, args);
                    return true;
                }
            }
            Messenger.getInstance().sendMessage(sender, "err-unknown_subcommand", args[0]);
            sendInfoMessage(sender);
            return false;
        } else {
            sendInfoMessage(sender);
            return false;
        }
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
        Messenger messenger = Messenger.getInstance();
        if (sender.hasPermission(PERMISSION_ADMIN)) {
            messenger.sendMessage(sender, "misc-available_commands");
            messenger.sendMessage(sender, "misc-replace_command");
            messenger.sendMessage(sender, "misc-undo_command");
            messenger.sendMessage(sender, "misc-info_command");
            messenger.sendMessage(sender, "misc-modify_command");
            messenger.sendMessage(sender, "misc-reload_command");
        } else {
            messenger.sendMessage(sender, "misc-available_commands");
            messenger.sendMessage(sender, "misc-replace_command");
            messenger.sendMessage(sender, "misc-undo_command");
            messenger.sendMessage(sender, "misc-info_command");
        }
    }

}
