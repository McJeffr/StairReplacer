package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Main;
import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.object.Subcommand;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
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
        if (!(sender instanceof Player)) {
            sendMessage(sender, "&cOnly players can execute this command.");
            return;
        }
        Player player = (Player) sender;
        sendMessage(player, "&6Version &ev" + Main.getPlugin().getDescription().getVersion());
        player.spigot().sendMessage(new ComponentBuilder("Developed by ").color(net.md_5.bungee.api.ChatColor.GOLD)
                .append("McJeffr").color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/McJeffr"))
                .append(", member of ").color(net.md_5.bungee.api.ChatColor.GOLD)
                .append("Qubion").color(net.md_5.bungee.api.ChatColor.YELLOW)
                .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/Qubion"))
                .append(".").color(net.md_5.bungee.api.ChatColor.GOLD)
                .create());
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
