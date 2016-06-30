package com.mcjeffr.stairreplacer.object;

import java.util.Map;
import org.bukkit.Location;

/**
 * This class contains the object of Snapshot. This object contains a list of
 * all locations that were edited and the stair that was replaced.
 *
 * @author McJeffr
 */
public class Snapshot {

    /* Attributes */
    private final Map<Location, Block> CHANGES;

    /**
     * Constructor of an Undo object.
     *
     * @param changes The Map containing the changes that were made in one
     * snapshot.
     */
    public Snapshot(Map<Location, Block> changes) {
        this.CHANGES = changes;
    }

    /**
     * Getter for the Map that contains the changes made in one snapshot.
     *
     * @return The Map that contains the changes that were made in one snapshot.
     */
    public Map<Location, Block> getChanges() {
        return CHANGES;
    }

    /**
     * This method undoes a snapshot and restores the blocks in the snapshot
     * back in the world. THIS CAN NOT BE UNDONE!
     *
     * @return The amount of blocks that have been undone.
     */
    public int undo() {
        for (Map.Entry<Location, Block> entry : CHANGES.entrySet()) {
            org.bukkit.block.Block block = entry.getKey().getBlock();
            block.setTypeId(entry.getValue().getId());
            block.setData(entry.getValue().getData());
        }
        return CHANGES.size();
    }

}
