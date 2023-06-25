/*
 * This is the latest source code of Quick Right-Click.
 * Minecraft version: 1.19.2.
 *
 * Please don't distribute without permission.
 * For all Minecraft modding projects, feel free to visit my profile page on CurseForge or Modrinth.
 *  CurseForge: https://curseforge.com/members/serilum/projects
 *  Modrinth: https://modrinth.com/user/serilum
 *  Overview: https://serilum.com/
 *
 * If you are feeling generous and would like to support the development of the mods, you can!
 *  https://ricksouth.com/donate contains all the information. <3
 *
 * Thanks for looking at the source code! Hope it's of some use to your project. Happy modding!
 */

package com.natamus.quickrightclick.features;

import com.natamus.quickrightclick.config.ConfigHandler;
import com.natamus.quickrightclick.data.Constants;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;

public class EnderChestFeature {
    public static boolean init(Player player) {
        if (!ConfigHandler.enableQuickEnderChests) {
            return false;
        }

        PlayerEnderChestContainer enderChestContainer = player.getEnderChestInventory();
        if (enderChestContainer == null) {
            return false;
        }

        player.openMenu(new SimpleMenuProvider((id, inventory, p) -> ChestMenu.threeRows(id, inventory, enderChestContainer), Constants.ENDER_CHEST_TITLE));
        return true;
    }
}
