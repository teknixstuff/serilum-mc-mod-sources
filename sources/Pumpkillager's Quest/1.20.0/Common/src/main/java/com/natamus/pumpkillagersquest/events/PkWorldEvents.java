/*
 * This is the latest source code of Pumpkillager's Quest.
 * Minecraft version: 1.20.0.
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

package com.natamus.pumpkillagersquest.events;

import com.natamus.pumpkillagersquest.pumpkillager.Manage;
import com.natamus.pumpkillagersquest.util.Data;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.Villager;

public class PkWorldEvents {
    public static void onWorldUnload(ServerLevel level) {
        if (Data.allPumpkillagers.containsKey(level)) {
            for (Villager pumpkillager : Data.allPumpkillagers.get(level)) {
                BlockPos pos = pumpkillager.blockPosition();
                Manage.resetPlacedBlocks(level, pos);
            }
        }

        Data.allPumpkillagers.remove(level);
        Data.entitiesToYeet.remove(level);
        Data.messagesToSend.remove(level);
    }
}
