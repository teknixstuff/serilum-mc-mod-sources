/*
 * This is the latest source code of Manure.
 * Minecraft version: 1.20.1.
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

package com.natamus.manure.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.manure.events.ManureDropEvent;
import com.natamus.manure.items.ManureItems;
import com.natamus.manure.util.Util;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeManureDropEvent {
    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (level == null) {
            return;
        }

        Util.attemptBlacklistProcessing(level);
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent e) {
        if (e.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            e.accept(ManureItems.MANURE);
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent e) {
        if (!e.phase.equals(TickEvent.Phase.END)) {
            return;
        }

        ManureDropEvent.onServerTick(e.getServer());
    }

    @SubscribeEvent
    public void onEntityJoin(EntityJoinLevelEvent e) {
        Level level = e.getLevel();
        if (level.isClientSide) {
            return;
        }

        ManureDropEvent.onEntityJoin(e.getEntity(), (ServerLevel)level);
    }

    @SubscribeEvent
    public void onEntityLeave(EntityLeaveLevelEvent e) {
        Level level = e.getLevel();
        if (level.isClientSide) {
            return;
        }

        ManureDropEvent.onEntityLeave(e.getEntity(), (ServerLevel)level);
    }
}