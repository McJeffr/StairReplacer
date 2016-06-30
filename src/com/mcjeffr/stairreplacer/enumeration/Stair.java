package com.mcjeffr.stairreplacer.enumeration;

/**
 * This class contains the enum constants of a Stair. The reason that Steps (and
 * Stairs) have self-defined enum constants and the constants of Material that
 * Bukkit has have not been used is because of the absolute pain that Mojang has
 * caused with their own system. Have fun trying to get a 'Birch Wooden Step' in
 * the Bukkit API. Not worth it, unless you like to mess around with the enum of
 * TreeSpecies in the Bukkit API and connect that to a Material's enum MetaData
 * object of MaterialData. Not to mention the awful casting between a block and
 * the MetaData of a Step or Stair.
 *
 * @author McJeffr
 */
public enum Stair {

    /* Constants */
    SANDSTONE(128, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    COBBLESTONE(67, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    BRICK(108, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    BRICK_STONE(109, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    BRICK_NETHER(114, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    QUARTZ(156, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_OAK(53, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_SPRUCE(134, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_BIRCH(135, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_JUNGLE(136, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_ACACIA(163, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    WOOD_DARK(164, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    SANDSTONE_RED(180, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13),
    PURPUR(203, (byte) 3, (byte) 0, (byte) 2, (byte) 1, (byte) 15, (byte) 12, (byte) 14, (byte) 13);

    /* Attributes */
    private final int ID;
    private final byte NORTH, EAST, SOUTH, WEST, NORTH_INVERTED, EAST_INVERTED,
            SOUTH_INVERTED, WEST_INVERTED;

    /**
     * Constructor of the enum constant of Stair.
     *
     * @param id The ID of a step.
     * @param north The meta of the north direction of a step.
     * @param east The meta of the east direction of a step.
     * @param south The meta of the south direction of a step.
     * @param west The meta of the west direction of a step.
     * @param northInverted The meta of the north inverted direction of a step.
     * @param eastInverted The meta of the east inverted direction of a step.
     * @param southInverted The meta of the south inverted direction of a step.
     * @param westInverted The meta of the west inverted direction of a step.
     */
    Stair(int id, byte north, byte east, byte south, byte west, byte northInverted,
            byte eastInverted, byte southInverted, byte westInverted) {
        this.ID = id;
        this.NORTH = north;
        this.EAST = east;
        this.SOUTH = south;
        this.WEST = west;
        this.NORTH_INVERTED = northInverted;
        this.EAST_INVERTED = eastInverted;
        this.SOUTH_INVERTED = southInverted;
        this.WEST_INVERTED = westInverted;
    }

    /**
     * This method gets a Stair enum from the list of enum constants that
     * contains the provided ID as ID.
     *
     * @param id The ID the stair contains.
     * @return The Stair enum, null if the Stair enum could not be found with
     * the provided ID.
     */
    public static Stair getById(int id) {
        for (Stair stair : Stair.values()) {
            if (stair.getId() == id) {
                return stair;
            }
        }
        return null;
    }

    /**
     * This method checks if the provided ID is that of a stair.
     *
     * @param id The ID that needs to be checked.
     * @return True if the provided ID is a stair, false otherwise.
     */
    public static boolean isStair(int id) {
        for (Stair stair : Stair.values()) {
            if (stair.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for the ID of an enum constant of type Step. This is the ID of the
     * block that the step represents. Example: '44' in WOOD(44, (byte) 2).
     *
     * @return The ID of an enum constant of type Step.
     */
    public int getId() {
        return ID;
    }

    /**
     * Getter for the north direction meta of an enum constant of type Step.
     * This is the meta of the block direction that the stair represents.
     *
     * @return The north direction meta of an enum constant of type Step.
     */
    public byte getNorth() {
        return NORTH;
    }

    /**
     * Getter for the east direction meta of an enum constant of type Step. This
     * is the meta of the block direction that the stair represents.
     *
     * @return The east direction meta of an enum constant of type Step.
     */
    public byte getEast() {
        return EAST;
    }

    /**
     * Getter for the south direction meta of an enum constant of type Step.
     * This is the meta of the block direction that the stair represents.
     *
     * @return The south direction meta of an enum constant of type Step.
     */
    public byte getSouth() {
        return SOUTH;
    }

    /**
     * Getter for the west direction meta of an enum constant of type Step. This
     * is the meta of the block direction that the stair represents.
     *
     * @return The west direction meta of an enum constant of type Step.
     */
    public byte getWest() {
        return WEST;
    }

    /**
     * Getter for the north inverted direction meta of an enum constant of type
     * Step. This is the meta of the block direction that the stair represents.
     *
     * @return The north inverted direction meta of an enum constant of type
     * Step.
     */
    public byte getNorthInverted() {
        return NORTH_INVERTED;
    }

    /**
     * Getter for the east inverted direction meta of an enum constant of type
     * Step. This is the meta of the block direction that the stair represents.
     *
     * @return The east inverted direction meta of an enum constant of type
     * Step.
     */
    public byte getEastInverted() {
        return EAST_INVERTED;
    }

    /**
     * Getter for the south inverted direction meta of an enum constant of type
     * Step. This is the meta of the block direction that the stair represents.
     *
     * @return The south inverted direction meta of an enum constant of type
     * Step.
     */
    public byte getSouthInverted() {
        return SOUTH_INVERTED;
    }

    /**
     * Getter for the west inverted direction meta of an enum constant of type
     * Step. This is the meta of the block direction that the stair represents.
     *
     * @return The west inverted direction meta of an enum constant of type
     * Step.
     */
    public byte getWestInverted() {
        return WEST_INVERTED;
    }

}
