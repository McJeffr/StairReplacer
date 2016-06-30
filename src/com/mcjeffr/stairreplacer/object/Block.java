package com.mcjeffr.stairreplacer.object;

/**
 * This class contains the object of Block, a simple object with an ID and a
 * data that resemble either a stair or slab. To keep memory usage to a minimum,
 * this object is being stored in an Undo object instead of the object of
 * Material with the MaterialData and the pain it brings to replace that in an
 * undo (see the enumerations for more info about this pain).
 *
 * @author McJeffr
 */
public class Block {

    /* Attributes */
    private final int ID;
    private final byte DATA;

    /**
     * Constructor of a Block object.
     *
     * @param id The ID of the block.
     * @param data The data of the block.
     */
    public Block(int id, byte data) {
        this.ID = id;
        this.DATA = data;
    }

    /**
     * Getter for the ID of the Block object.
     *
     * @return The ID of the Block object.
     */
    public int getId() {
        return ID;
    }

    /**
     * Getter for the data of the Block object.
     *
     * @return The data of the Block object.
     */
    public byte getData() {
        return DATA;
    }
    
    @Override
    public String toString() {
        return "ID: " + ID + " DATA: " + DATA;
    }

}
