/*
 * This is the latest source code of Extended Creative Inventory.
 * Minecraft version: 1.18.2.
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

package com.natamus.extendedcreativeinventory.events;

import com.natamus.extendedcreativeinventory.process.ProcessItems;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.Item;

public class ProcessItemsEvent {
    private static boolean processedItems = false;

    public static void onWorldLoad(ServerLevel serverLevel) {
        if (!processedItems) {
            Registry<Item> itemRegistry = serverLevel.registryAccess().registryOrThrow(Registry.ITEM_REGISTRY);

            ProcessItems.process(itemRegistry);

            processedItems = true;
        }
    }
}
