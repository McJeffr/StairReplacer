package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Main;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.object.Messenger;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr info" command.
 *
 * @author McJeffr
 */
public class CmdInfo extends Subcommand {

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
        messenger.sendMessage(player, "cmd-info-version", Main.getPlugin().getDescription().getVersion());
        player.spigot().sendMessage(new ComponentBuilder("Developed by ").color(net.md_5.bungee.api.ChatColor.GOLD)
                .append("McJeffr").color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/McJeffr"))
                .append(", member of ").color(net.md_5.bungee.api.ChatColor.GOLD)
                .append("Qubion").color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/Qubion"))
                .append(".").color(net.md_5.bungee.api.ChatColor.GOLD)
                .create());
    }

}
