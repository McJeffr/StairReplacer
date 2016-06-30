package com.mcjeffr.stairreplacer.enumeration;

/**
 * This class contains the enum constants of a Step. The reason that Steps (and
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
public enum Step {

    /* Constants */
    STONE(44, (byte) 0, (byte) 8),
    SANDSTONE(44, (byte) 1, (byte) 9),
    WOOD(44, (byte) 2, (byte) 10),
    COBBLESTONE(44, (byte) 3, (byte) 11),
    BRICK(44, (byte) 4, (byte) 12),
    BRICK_STONE(44, (byte) 5, (byte) 13),
    BRICK_NETHER(44, (byte) 6, (byte) 14),
    QUARTZ(44, (byte) 7, (byte) 15),
    WOOD_OAK(126, (byte) 0, (byte) 8),
    WOOD_SPRUCE(126, (byte) 1, (byte) 9),
    WOOD_BIRCH(126, (byte) 2, (byte) 10),
    WOOD_JUNGLE(126, (byte) 3, (byte) 11),
    WOOD_ACACIA(126, (byte) 4, (byte) 12),
    WOOD_DARK(126, (byte) 5, (byte) 13),
    SANDSTONE_RED(182, (byte) 0, (byte) 8),
    PURPUR(205, (byte) 0, (byte) 8);

    /* Attributes */
    private final int ID;
    private final byte NORMAL;
    private final byte INVERTED;

    /**
     * Constructor of the enum constant of Step.
     *
     * @param id The ID of a step.
     * @param normal The normal position of a step.
     * @param inverted The inverted position of a step.
     */
    Step(int id, byte normal, byte inverted) {
        this.ID = id;
        this.NORMAL = normal;
        this.INVERTED = inverted;
    }

    /**
     * This method gets a Step enum from the list of enum constants that
     * contains the provided ID as ID.
     *
     * @param id The ID the step contains.
     * @param data The data the step contains.
     * @return The Step enum, null if the Step enum could not be found with the
     * provided ID.
     */
    public static Step getById(int id, byte data) {
        for (Step step : Step.values()) {
            if (step.getId() == id && (step.getNormal() == data || step.getInverted() == data)) {
                return step;
            }
        }
        return null;
    }

    /**
     * This method checks if the provided ID is that of a step.
     *
     * @param id The ID that needs to be checked.
     * @param data The data that needs to be checked.
     * @return True if the provided ID is a step, false otherwise.
     */
    public static boolean isStep(int id, byte data) {
        for (Step step : Step.values()) {
            if (step.getId() == id && (step.getNormal() == data || step.getInverted() == data)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns a boolean value containing if a step is inverted or
     * not.
     *
     * @param id The ID of the step.
     * @param data The data of the step.
     * @return True if the step is inverted, false otherwise.
     */
    public static boolean isInverted(int id, byte data) {
        for (Step step : Step.values()) {
            if (step.getId() == id && (step.getNormal() == data || step.getInverted() == data)) {
                return step.getInverted() == data;
            }
        }
        return false;
    }

    /**
     * Getter for the ID of an enum constant of type Step. This is the ID of the
     * block that the step represents.
     *
     * @return The ID of an enum constant of type Step.
     */
    public int getId() {
        return ID;
    }

    /**
     * Getter for the normal position of an enum constant of type Step. This is
     * the meta of a step that is in a normal (lower) position.
     *
     * @return The normal position meta of an enum constant of type Step.
     */
    public byte getNormal() {
        return NORMAL;
    }

    /**
     * Getter for the inverted position of an enum constant of type Step. This
     * is the meta of a step that is in a inverted (upper) position.
     *
     * @return The inverted position meta of an enum constant of type Step.
     */
    public byte getInverted() {
        return INVERTED;
    }

}
