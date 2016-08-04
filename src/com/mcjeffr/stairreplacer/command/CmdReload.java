package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Main;
import com.mcjeffr.stairreplacer.object.Config;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.object.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
        Main.getPlugin().reloadConfig();
        Config.getInstance().reload();
        Messenger.getInstance().reload();
        Messenger.getInstance().sendMessage(sender, "cmd-reload-reloaded");
    }

}
