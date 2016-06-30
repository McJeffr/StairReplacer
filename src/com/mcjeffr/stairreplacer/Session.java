package com.mcjeffr.stairreplacer;

import com.mcjeffr.stairreplacer.object.Config;
import com.mcjeffr.stairreplacer.object.Snapshot;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * This class stores any variables that need to exist during runtime, so they
 * can easily be accessed via getters and setters.
 *
 * @author McJeffr
 */
public class Session {

    /* Attributes */
    private static WorldEditPlugin worldEditPlugin;
    private static Map<UUID, List<Snapshot>> snapshots;
    private static Config config;

    /**
     * This method gets the WorldEditPlugin instance.
     *
     * @return The WorldEditPlugin instance.
     */
    public static WorldEditPlugin getWorldEditPlugin() {
        return Session.worldEditPlugin;
    }

    /**
     * This method sets the WorldEditPlugin instance.
     *
     * @param plugin The WorldEditPlugin instance.
     */
    public static void setWorldEditPlugin(WorldEditPlugin plugin) {
        Session.worldEditPlugin = plugin;
    }

    /**
     * This method gets a selection of a player if a player has one.
     *
     * @param player The player of which the selection needs to be fetched.
     * @return The Selection object, or null when the player does not have a
     * selection.
     */
    public static Selection getWorldEditSelection(Player player) {
        Selection sel = null;
        if (worldEditPlugin != null) {
            sel = worldEditPlugin.getSelection(player);
        }
        return sel;
    }

    /**
     * This method initializes the snapshot Map that stores the snapshots of
     * several players, used in the undo command. Only use this method on plugin
     * startup.
     */
    public static void initializeSnapshots() {
        snapshots = new HashMap<>();
    }

    /**
     * This method adds a snapshot to the Map that stores the snapshots of the
     * different players. If the player does not yet exist in the Map, it will
     * be created automatically.
     *
     * @param player The player of which the Snapshot belongs to.
     * @param snapshot The snapshot that needs to be added to the list of
     * snapshots of a player in the map of snapshots.
     */
    public static void addSnapshot(Player player, Snapshot snapshot) {
        if (!snapshots.containsKey(player.getUniqueId())) {
            snapshots.put(player.getUniqueId(), new ArrayList<>());
        }
        snapshots.get(player.getUniqueId()).add(snapshot);
        if (snapshots.get(player.getUniqueId()).size() > config.getMaxSnapshots()) {
            snapshots.get(player.getUniqueId()).remove(0);
        }
    }

    /**
     * This method removes a snapshot from the Map that stores the snapshots of
     * the different players. If the player does not exist in the map, then
     * nothing happens.
     *
     * @param player The player of which the Snapshot belongs to.
     * @param snapshot The snapshot that needs to be removed from the list of
     * snapshots of a player in the map of snapshots.
     * @return True if the snapshot was removed, false otherwise.
     */
    public static boolean removeSnapshot(Player player, Snapshot snapshot) {
        if (snapshots.containsKey(player.getUniqueId())) {
            if (snapshots.get(player.getUniqueId()).contains(snapshot)) {
                snapshots.get(player.getUniqueId()).remove(snapshot);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * This method fetches the last registered snapshot.
     *
     * @param player The player of which the snapshot needs to be fetched.
     * @return The last registered snapshot, or null if nothing could be found.
     */
    public static Snapshot getLastSnapshot(Player player) {
        if (snapshots.containsKey(player.getUniqueId())) {
            if (!(snapshots.get(player.getUniqueId()).isEmpty())) {
                return snapshots.get(player.getUniqueId()).get(snapshots.get(player.getUniqueId()).size() - 1);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * This method gets the list of snapshots that belongs to a player.
     *
     * @param player The player whose list needs to be fetched.
     * @return The List of snapshots, or null if the player does not have any
     * snapshots registered yet.
     */
    public static List<Snapshot> getSnapshots(Player player) {
        if (snapshots.containsKey(player.getUniqueId())) {
            return snapshots.get(player.getUniqueId());
        } else {
            return null;
        }
    }

    /**
     * This method initializes the config. Only use this method on plugin
     * startup.
     *
     * @param config The FileConfiguration object from where the config needs to
     * be read from.
     */
    public static void initializeConfig(FileConfiguration config) {
        Session.config = new Config(config);
    }

    /**
     * Getter for the config of the plugin.
     *
     * @return The config of the plugin.
     */
    public static Config getConfig() {
        return config;
    }

}
