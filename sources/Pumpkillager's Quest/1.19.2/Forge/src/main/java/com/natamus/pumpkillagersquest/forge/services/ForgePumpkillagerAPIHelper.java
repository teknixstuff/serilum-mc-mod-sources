/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest.forge.services;

import com.natamus.pumpkillagersquest.forge.api.PumpkillagerSummonEvent;
import com.natamus.pumpkillagersquest.services.helpers.PumpkillagerAPIHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;

public class ForgePumpkillagerAPIHelper implements PumpkillagerAPIHelper {
    @Override
    public void pumpkillagerSummonEvent(Player summoner, Villager pumpkillager, BlockPos pos, String typeString) {
        MinecraftForge.EVENT_BUS.post(new PumpkillagerSummonEvent(summoner, pumpkillager, pos, typeString));
    }
}