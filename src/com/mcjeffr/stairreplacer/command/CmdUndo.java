package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.object.Snapshot;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.object.Messenger;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr undo" command.
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
        Messenger messenger = Messenger.getInstance();
        if (!(sender instanceof Player)) {
            messenger.sendMessage(sender, "err-not_a_player");
            return;
        }
        Player player = (Player) sender;
        Session session = Session.getInstance();
        if (args.length > 1) {
            if (TypeCheck.isInteger(args[1])) {
                int undos = Integer.parseInt(args[1]);
                int rollbackedBlocks = 0;
                for (int i = 0; i < undos; i++) {
                    Snapshot snapshot = session.getLastSnapshot(player);
                    if (snapshot != null) {
                        rollbackedBlocks += snapshot.undo();
                        session.removeSnapshot(player, snapshot);
                    } else {
                        break;
                    }
                }
                if (rollbackedBlocks != 0) {
                    messenger.sendMessage(player, "cmd-undo-rollback", Integer.toString(rollbackedBlocks));
                } else {
                    messenger.sendMessage(player, "cmd-undo-nothing_left");
                }
            } else {
                messenger.sendMessage(player, "err-not_a_number", args[1]);
            }
        } else {
            Snapshot snapshot = session.getLastSnapshot(player);
            if (snapshot != null) {
                int rollbackedBlocks = snapshot.undo();
                session.removeSnapshot(player, snapshot);
                messenger.sendMessage(player, "cmd-undo-rollback", Integer.toString(rollbackedBlocks));
            } else {
                messenger.sendMessage(player, "cmd-undo-nothing_left");
            }
        }
    }

}
