package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.enumeration.Stair;
import com.mcjeffr.stairreplacer.enumeration.Step;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.util.Replacer;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * This class contains the command executor for the "/sr r" command.
 *
 * @author McJeffr
 */
public class CmdReplace extends Subcommand {

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
        Selection selection = Session.getWorldEditSelection(player);
        if (selection == null) {
            sendMessage(player, "&cMake a region selection first.");
            return;
        }
        if (args.length == 1) {
            if (Stair.isStair(player.getItemInHand().getTypeId())
                    || Step.isStep(player.getItemInHand().getTypeId(), player.getItemInHand().getData().getData())) {
                int id = player.getItemInHand().getTypeId();
                if (Stair.isStair(id)) {
                    Stair stair = Stair.getById(id);
                    int amountReplaced = Replacer.replaceStairs(player, selection, null, stair);
                    if (amountReplaced == 0) {
                        sendMessage(player, "&cNothing found to replace.");
                    } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                        sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                    } else {
                        sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                    }
                } else if (Step.isStep(id, player.getItemInHand().getData().getData())) {
                    Step step = Step.getById(id, player.getItemInHand().getData().getData());
                    int amountReplaced = Replacer.replaceSteps(player, selection, null, step);
                    if (amountReplaced == 0) {
                        sendMessage(player, "&cNothing found to replace.");
                    } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                        sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                    } else {
                        sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                    }
                } else {
                    sendMessage(player, "&cHold the stair or slab you want to replace the stairs or slabs to in your hand.");
                }
            }
        } else if (args.length == 2) {
            String idAndData = args[1];
            int id = getId(idAndData);
            byte data = getData(idAndData);
            if (id == -1) {
                sendMessage(player, "&cThe provided ID in &f" + idAndData + " &cis not a number "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            } else if (data == -1) {
                sendMessage(player, "&cThe provided metadata in &f" + idAndData + " &cis not a number between 0-16 "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            }
            if (Stair.isStair(id)) {
                Stair stair = Stair.getById(id);
                int amountReplaced = Replacer.replaceStairs(player, selection, null, stair);
                if (amountReplaced == 0) {
                    sendMessage(player, "&cNothing found to replace.");
                } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                    sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                } else {
                    sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                }
            } else if (Step.isStep(id, data)) {
                Step step = Step.getById(id, data);
                int amountReplaced = Replacer.replaceSteps(player, selection, null, step);
                if (amountReplaced == 0) {
                    sendMessage(player, "&cNothing found to replace.");
                } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                    sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                } else {
                    sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                }
            } else {
                sendMessage(player, "&cProvided ID and data &f" + idAndData + " &cis not a stair or a slab.");
            }
        } else {
            String fromIdAndData = args[1];
            String toIdAndData = args[2];
            int fromId = getId(fromIdAndData);
            int toId = getId(toIdAndData);
            byte fromData = getData(fromIdAndData);
            byte toData = getData(toIdAndData);
            if (toId == -1) {
                sendMessage(player, "&cThe provided ID in &f" + toIdAndData + " &cis not a number "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            } else if (fromId == -1) {
                sendMessage(player, "&cThe provided ID in &f" + fromIdAndData + " &cis not a number "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            } else if (toData == -1) {
                sendMessage(player, "&cThe provided metadata in &f" + toIdAndData + " &cis not a number between 0-16 "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            } else if (fromData == -1) {
                sendMessage(player, "&cThe provided metadata in &f" + toIdAndData + " &cis not a number between 0-16 "
                        + "or is an invalid format. Supported formats: \"id\", \"id:data\" or \"id;data\".");
                return;
            }
            if (Stair.isStair(fromId) && Stair.isStair(toId)) {
                Stair fromStair = Stair.getById(fromId);
                Stair toStair = Stair.getById(toId);
                int amountReplaced = Replacer.replaceStairs(player, selection, fromStair, toStair);
                if (amountReplaced == 0) {
                    sendMessage(player, "&cNothing found to replace.");
                } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                    sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                } else {
                    sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                }
            } else if (Step.isStep(fromId, fromData) && Step.isStep(toId, toData)) {
                Step fromStep = Step.getById(fromId, fromData);
                Step toStep = Step.getById(toId, toData);
                int amountReplaced = Replacer.replaceSteps(player, selection, fromStep, toStep);
                if (amountReplaced == 0) {
                    sendMessage(player, "&cNothing found to replace.");
                } else if (amountReplaced == Session.getConfig().getMaxBlockChange()) {
                    sendMessage(player, "&cMaximum block change limit of &f" + Session.getConfig().getMaxBlockChange() + " &cblocks reached.");
                } else {
                    sendMessage(player, "&6Replaced &e" + amountReplaced + " &6blocks.");
                }
            } else {
                sendMessage(player, "&cProvided ID and data &f" + fromIdAndData + "&c or &f"
                        + toIdAndData + " &cis not a stair or a slab.");
            }
        }
    }

    /**
     * This method fetches the id from a provided String that contains the
     * format of "id", "id:data" or "id;data".
     *
     * @param idAndData The String that contains the format of "id", "id:data"
     * or "id;data".
     * @return The ID that was fetched from the String, or -1 if no ID could be
     * found.
     */
    private int getId(String idAndData) {
        if (idAndData.contains(":")) {
            String[] splitResult = idAndData.split(":");
            if (TypeCheck.isInteger(splitResult[0])) {
                return Integer.parseInt(splitResult[0]);
            } else {
                return -1;
            }
        } else if (idAndData.contains(";")) {
            String[] splitResult = idAndData.split(";");
            if (TypeCheck.isInteger(splitResult[0])) {
                return Integer.parseInt(splitResult[0]);
            } else {
                return -1;
            }
        } else {
            if (TypeCheck.isInteger(idAndData)) {
                return Integer.parseInt(idAndData);
            } else {
                return -1;
            }
        }
    }

    /**
     * This method fetches the data from a provided String that contains the
     * format of "id", "id:data" or "id;data".
     *
     * @param idAndData The String that contains the format of "id", "id:data"
     * or "id;data".
     * @return The data that was fetched from the String, or -1 if no data could
     * be found.
     */
    private byte getData(String idAndData) {
        if (idAndData.contains(":")) {
            String[] splitResult = idAndData.split(":");
            if (TypeCheck.isMetaData(splitResult[1])) {
                return Byte.parseByte(splitResult[1]);
            } else {
                return -1;
            }
        } else if (idAndData.contains(";")) {
            String[] splitResult = idAndData.split(";");
            if (TypeCheck.isMetaData(splitResult[1])) {
                return Byte.parseByte(splitResult[1]);
            } else {
                return -1;
            }
        } else {
            return 0;
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
