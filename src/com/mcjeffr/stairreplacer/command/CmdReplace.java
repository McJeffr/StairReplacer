package com.mcjeffr.stairreplacer.command;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.enumeration.Stair;
import com.mcjeffr.stairreplacer.enumeration.Step;
import com.mcjeffr.stairreplacer.object.Config;
import com.mcjeffr.stairreplacer.object.Subcommand;
import com.mcjeffr.stairreplacer.object.Messenger;
import com.mcjeffr.stairreplacer.util.Replacer;
import com.mcjeffr.stairreplacer.util.TypeCheck;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This class contains the command executor for the "/sr replace" command.
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
        Messenger messenger = Messenger.getInstance();
        if (!(sender instanceof Player)) {
            messenger.sendMessage(sender, "err-not_a_player");
            return;
        }
        Player player = (Player) sender;
        Selection selection = Session.getInstance().getWorldEditSelection(player);
        Config config = Config.getInstance();
        if (selection == null) {
            messenger.sendMessage(player, "cmd-replace-no_selection");
            return;
        }
        if (args.length == 1) {
            ItemStack inHand = player.getInventory().getItemInMainHand();
            if (inHand != null) {
                int id = inHand.getTypeId();
                byte data = inHand.getData().getData();
                if (Stair.isStair(id)) {
                    Stair stair = Stair.getById(id);
                    int amountReplaced = Replacer.replaceStairs(player, selection, null, stair);
                    if (amountReplaced == 0) {
                        messenger.sendMessage(player, "cmd-replace-nothing_found");
                    } else if (amountReplaced == config.getMaxBlockChange()) {
                        messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                    } else {
                        messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                    }
                } else if (Step.isStep(id, data)) {
                    Step step = Step.getById(id, data);
                    int amountReplaced = Replacer.replaceSteps(player, selection, null, step);
                    if (amountReplaced == 0) {
                        messenger.sendMessage(player, "cmd-replace-nothing_found");
                    } else if (amountReplaced == config.getMaxBlockChange()) {
                        messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                    } else {
                        messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                    }
                } else {
                    messenger.sendMessage(player, "cmd-replace-hold_block");
                }
            } else {
                messenger.sendMessage(player, "cmd-replace-hold_block");
            }
        } else if (args.length == 2) {
            String idAndData = args[1];
            int id = getId(idAndData);
            byte data = getData(idAndData);
            if (id == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_id", idAndData);
                return;
            } else if (data == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_data", idAndData);
                return;
            }
            if (Stair.isStair(id)) {
                Stair stair = Stair.getById(id);
                int amountReplaced = Replacer.replaceStairs(player, selection, null, stair);
                if (amountReplaced == 0) {
                    messenger.sendMessage(player, "cmd-replace-nothing_found");
                } else if (amountReplaced == config.getMaxBlockChange()) {
                    messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                } else {
                    messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                }
            } else if (Step.isStep(id, data)) {
                Step step = Step.getById(id, data);
                int amountReplaced = Replacer.replaceSteps(player, selection, null, step);
                if (amountReplaced == 0) {
                    messenger.sendMessage(player, "cmd-replace-nothing_found");
                } else if (amountReplaced == config.getMaxBlockChange()) {
                    messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                } else {
                    messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                }
            } else {
                messenger.sendMessage(player, "cmd-replace-invalid_block", idAndData);
            }
        } else {
            String fromIdAndData = args[1];
            String toIdAndData = args[2];
            int fromId = getId(fromIdAndData);
            int toId = getId(toIdAndData);
            byte fromData = getData(fromIdAndData);
            byte toData = getData(toIdAndData);
            if (toId == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_id", toIdAndData);
                return;
            } else if (fromId == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_id", fromIdAndData);
                return;
            } else if (toData == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_data", toIdAndData);
                return;
            } else if (fromData == -1) {
                messenger.sendMessage(player, "cmd-replace-invalid_data", fromIdAndData);
                return;
            }
            if (Stair.isStair(fromId) && Stair.isStair(toId)) {
                Stair fromStair = Stair.getById(fromId);
                Stair toStair = Stair.getById(toId);
                int amountReplaced = Replacer.replaceStairs(player, selection, fromStair, toStair);
                if (amountReplaced == 0) {
                    messenger.sendMessage(player, "cmd-replace-nothing_found");
                } else if (amountReplaced == config.getMaxBlockChange()) {
                    messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                } else {
                    messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                }
            } else if (Step.isStep(fromId, fromData) && Step.isStep(toId, toData)) {
                Step fromStep = Step.getById(fromId, fromData);
                Step toStep = Step.getById(toId, toData);
                int amountReplaced = Replacer.replaceSteps(player, selection, fromStep, toStep);
                if (amountReplaced == 0) {
                    messenger.sendMessage(player, "cmd-replace-nothing_found");
                } else if (amountReplaced == config.getMaxBlockChange()) {
                    messenger.sendMessage(player, "cmd-replace-limit_reached", Integer.toString(config.getMaxBlockChange()));
                } else {
                    messenger.sendMessage(player, "cmd-replace-replaced_blocks", Integer.toString(amountReplaced));
                }
            } else {
                messenger.sendMessage(player, "cmd-replace-invalid-blocks", new String[]{toIdAndData, fromIdAndData});
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

}
