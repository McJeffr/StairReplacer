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
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 * This class stores any variables that need to exist during runtime, so they
 * can easily be accessed via getters and setters. This class is in the
 * Singleton Design Pattern format, meaning there is an instance of Session
 * during runtime that can be called upon.
 *
 * @author McJeffr
 */
public class Session {

    /* Attributes */
    private WorldEditPlugin worldEditPlugin;
    private final Map<UUID, List<Snapshot>> SNAPSHOTS;

    /* Instance */
    private static Session instance;

    /**
     * Singleton Design Pattern private constructor.
     */
    private Session() {
        worldEditPlugin = null;
        SNAPSHOTS = new HashMap<>();
        PluginManager pm = Bukkit.getPluginManager();
        for (Plugin plugin : pm.getPlugins()) {
            if (plugin instanceof WorldEditPlugin) {
                worldEditPlugin = (WorldEditPlugin) plugin;
                break;
            }
        }
    }

    /**
     * This method will return the instance of the Session object. If this
     * object has not yet been created, it will create a new Session object.
     *
     * @return
     */
    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    /**
     * This method gets the WorldEditPlugin instance.
     *
     * @return The WorldEditPlugin instance.
     */
    public WorldEditPlugin getWorldEditPlugin() {
        return worldEditPlugin;
    }

    /**
     * This method gets a selection of a player if a player has one.
     *
     * @param player The player of which the selection needs to be fetched.
     * @return The Selection object, or null when the player does not have a
     * selection.
     */
    public Selection getWorldEditSelection(Player player) {
        Selection sel = null;
        if (worldEditPlugin != null) {
            sel = worldEditPlugin.getSelection(player);
        }
        return sel;
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
    public void addSnapshot(Player player, Snapshot snapshot) {
        if (!SNAPSHOTS.containsKey(player.getUniqueId())) {
            SNAPSHOTS.put(player.getUniqueId(), new ArrayList<Snapshot>());
        }
        SNAPSHOTS.get(player.getUniqueId()).add(snapshot);
        if (SNAPSHOTS.get(player.getUniqueId()).size() > Config.getInstance().getMaxSnapshots()) {
            SNAPSHOTS.get(player.getUniqueId()).remove(0);
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
    public boolean removeSnapshot(Player player, Snapshot snapshot) {
        if (SNAPSHOTS.containsKey(player.getUniqueId())) {
            if (SNAPSHOTS.get(player.getUniqueId()).contains(snapshot)) {
                SNAPSHOTS.get(player.getUniqueId()).remove(snapshot);
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
    public Snapshot getLastSnapshot(Player player) {
        if (SNAPSHOTS.containsKey(player.getUniqueId())) {
            if (!(SNAPSHOTS.get(player.getUniqueId()).isEmpty())) {
                return SNAPSHOTS.get(player.getUniqueId()).get(SNAPSHOTS.get(player.getUniqueId()).size() - 1);
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
    public List<Snapshot> getSnapshots(Player player) {
        if (SNAPSHOTS.containsKey(player.getUniqueId())) {
            return SNAPSHOTS.get(player.getUniqueId());
        } else {
            return null;
        }
    }

}
