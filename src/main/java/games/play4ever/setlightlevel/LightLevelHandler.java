package games.play4ever.setlightlevel;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;

public class LightLevelHandler {

    public static void setLightLevel(String worldName, int x, int y, int z, int lightLevel) {
        Block block = Bukkit.getWorld(worldName).getBlockAt(x, y, z); // Get block (Don't do this, just example)
        if (block.getType() != Material.LIGHT) {
            return;
        } // Not a light block
        Levelled level = (Levelled) block.getBlockData(); // Get data to change
        level.setLevel(lightLevel);
        block.setBlockData(level, true);
    }
 }
