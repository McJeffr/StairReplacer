package com.mcjeffr.stairreplacer.util;

import com.mcjeffr.stairreplacer.Session;
import com.mcjeffr.stairreplacer.enumeration.Stair;
import com.mcjeffr.stairreplacer.enumeration.Step;
import com.mcjeffr.stairreplacer.object.Snapshot;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 * This utility is used to replace the material of stairs and steps without
 * changing the BlockFace of the provided block.
 *
 * @author McJeffr
 */
public class Replacer {

    /**
     * This method replaces all stairs in a specific WorldEdit Selection.
     *
     * @param player The player who ran the method. Used for storing a snapshot.
     * @param sel The WorldEdit Selection in which the stairs need to be
     * replaced.
     * @param fromStair The type of stair that needs to be replaced from. This
     * can be null when all stairs need to be replaced, and not just one
     * specific type of stair.
     * @param toStair The stair that needs to be replaced to.
     * @return The amount of stairs that have been replaced.
     */
    public static int replaceStairs(Player player, Selection sel, Stair fromStair, Stair toStair) {
        int amountReplaced = 0;
        if (sel != null) {
            Location min = sel.getMinimumPoint();
            Location max = sel.getMaximumPoint();
            World world = sel.getWorld();
            Map<Location, com.mcjeffr.stairreplacer.object.Block> changedBlocks = new HashMap<>();
            if (world != null) {
                for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
                    for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                        for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                            if (Stair.isStair(world.getBlockAt(x, y, z).getTypeId())) {
                                Block block = world.getBlockAt(x, y, z);
                                if (!(Stair.getById(block.getTypeId()) == toStair)) {
                                    if (amountReplaced >= Session.getConfig().getMaxBlockChange()) {
                                        break;
                                    }
                                    com.mcjeffr.stairreplacer.object.Block changedBlock = replaceStair(block, fromStair, toStair);
                                    if (changedBlock != null) {
                                        changedBlocks.put(new Location(sel.getWorld(), x, y, z), changedBlock);
                                        amountReplaced++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (amountReplaced != 0) {
                Session.addSnapshot(player, new Snapshot(changedBlocks));
            }
        }
        return amountReplaced;
    }

    /**
     * This method replaces all steps in a specific WorldEdit Selection.
     *
     * @param player The player who ran the method. Used for storing a snapshot.
     * @param sel The WorldEdit Selection in which the steps need to be
     * replaced.
     * @param fromStep The type of step that needs to be replaced from. This can
     * be null when all steps need to be replaced, and not just one specific
     * type of step.
     * @param toStep The step that needs to be replaced to.
     * @return The amount of steps that have been replaced.
     */
    public static int replaceSteps(Player player, Selection sel, Step fromStep, Step toStep) {
        int amountReplaced = 0;
        if (sel != null) {
            Location min = sel.getMinimumPoint();
            Location max = sel.getMaximumPoint();
            World world = sel.getWorld();
            Map<Location, com.mcjeffr.stairreplacer.object.Block> changedBlocks = new HashMap<>();
            if (world != null) {
                for (int x = min.getBlockX(); x <= max.getBlockX(); x++) {
                    for (int y = min.getBlockY(); y <= max.getBlockY(); y++) {
                        for (int z = min.getBlockZ(); z <= max.getBlockZ(); z++) {
                            if (Step.isStep(world.getBlockAt(x, y, z).getTypeId(), world.getBlockAt(x, y, z).getData())) {
                                Block block = world.getBlockAt(x, y, z);
                                if (!(Step.getById(block.getTypeId(), block.getData()) == toStep)) {
                                    if (amountReplaced >= Session.getConfig().getMaxBlockChange()) {
                                        break;
                                    }
                                    boolean inverted = Step.isInverted(block.getTypeId(), block.getData());
                                    com.mcjeffr.stairreplacer.object.Block changedBlock = replaceStep(block, fromStep, toStep, inverted);
                                    if (changedBlock != null) {
                                        changedBlocks.put(new Location(sel.getWorld(), x, y, z), changedBlock);
                                        amountReplaced++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (amountReplaced != 0) {
                Session.addSnapshot(player, new Snapshot(changedBlocks));
            }
        }
        return amountReplaced;
    }

    /**
     * This method replaces a stair with that of the provided stair, and sets
     * the data of the replaced stair to that of the stair before it's type was
     * replaced.
     *
     * @param block The block that needs to be replaced.
     * @param fromStair The stair that needs to be replaced from. Can be null
     * when all stairs need to be replaced, and not just one specific type of
     * stair.
     * @param toStair The stair that needs to be replaced to.
     * @return True when the stair was replaced, false otherwise.
     */
    private static com.mcjeffr.stairreplacer.object.Block replaceStair(Block block, Stair fromStair, Stair toStair) {
        int id = block.getTypeId();
        byte data = block.getData();
        if (fromStair != null) {
            if (block.getTypeId() == fromStair.getId()) {
                block.setTypeId(toStair.getId());
                block.setData(data);
            } else {
                return null;
            }
        } else {
            block.setTypeId(toStair.getId());
            block.setData(data);
        }
        return new com.mcjeffr.stairreplacer.object.Block(id, data);
    }

    /**
     * This method replaces a step with that of the provided step, and sets the
     * data of the replaced step to that of the step before it's type was
     * replaced.
     *
     * @param block The block that needs to be replaced.
     * @param fromStep The step that needs to be replaced from. Can be null when
     * all steps need to be replaced, and not just one specific type of step.
     * @param toStep The step that needs to be replaced to.
     * @param inverted The boolean value indicating if the step should be
     * inverted or not.
     * @return True when the step was replaced, false otherwise.
     */
    private static com.mcjeffr.stairreplacer.object.Block replaceStep(Block block, Step fromStep, Step toStep, boolean inverted) {
        int id = block.getTypeId();
        byte data = block.getData();
        if (fromStep != null) {
            if (id == fromStep.getId() && (data == fromStep.getNormal() || data == fromStep.getInverted())) {
                block.setTypeId(toStep.getId());
                if (inverted) {
                    block.setData(toStep.getInverted());
                } else {
                    block.setData(toStep.getNormal());
                }
            } else {
                return null;
            }
        } else {
            block.setTypeId(toStep.getId());
            if (inverted) {
                block.setData(toStep.getInverted());
            } else {
                block.setData(toStep.getNormal());
            }
        }
        return new com.mcjeffr.stairreplacer.object.Block(id, data);
    }

}
