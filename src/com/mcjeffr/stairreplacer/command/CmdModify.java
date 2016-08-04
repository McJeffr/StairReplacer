package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.object.Config;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.object.Messenger;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr modify" command.
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
        Messenger messenger = Messenger.getInstance();
        if (!(sender instanceof Player)) {
            messenger.sendMessage(sender, "err-not_a_player");
            return;
        }
        Player player = (Player) sender;
        if (args.length > 2) {
            switch (args[1].toLowerCase()) {
                case ("prefix"): {
                    String prefix = args[2];
                    Config.getInstance().setPrefix(prefix);
                    messenger.sendMessage(player, "cmd-modify-modified_prefix", prefix);
                    return;
                }
                case ("max-undo-limit"): {
                    if (TypeCheck.isInteger(args[2])) {
                        int maxUndoAmount = Integer.parseInt(args[2]);
                        Config.getInstance().setMaxSnapshots(maxUndoAmount);
                        messenger.sendMessage(player, "cmd-modify-modified_max_snapshots", Integer.toString(maxUndoAmount));
                        return;
                    } else {
                        messenger.sendMessage(player, "err-not_a_number", args[2]);
                        return;
                    }
                }
                case ("max-block-change"): {
                    if (TypeCheck.isInteger(args[2])) {
                        int maxBlockChange = Integer.parseInt(args[2]);
                        Config.getInstance().setMaxBlockChange(maxBlockChange);
                        messenger.sendMessage(player, "cmd-modify-modified_max_limit", Integer.toString(maxBlockChange));
                        return;
                    } else {
                        messenger.sendMessage(player, "err-not_a_number", args[2]);
                        return;
                    }
                }
                default: {
                    messenger.sendMessage(player, "cmd-modify-unknown_modifier");
                    messenger.sendMessage(player, "cmd-modify-available_modifier");
                }
            }
        } else {
            messenger.sendMessage(player, "err-not_enough_arguments");
            messenger.sendMessage(player, "cmd-modify-usage");
        }
    }

}
