/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.forge.events;

import com.natamus.collective.events.CollectiveEvents;
import com.natamus.collective.functions.WorldFunctions;
import com.natamus.collective.util.CollectiveReference;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class RegisterCollectiveEvents {
    @SubscribeEvent
    public void onWorldLoad(LevelEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (level == null) {
            return;
        }

        CollectiveEvents.onWorldLoad((ServerLevel)level);
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.LevelTickEvent e) {
        Level level = e.level;
        if (level.isClientSide || !e.phase.equals(Phase.END)) {
            return;
        }

        CollectiveEvents.onWorldTick((ServerLevel)level);
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent e) {
        if (!e.phase.equals(Phase.END)) {
            return;
        }

        CollectiveEvents.onServerTick(e.getServer());
    }

    @SubscribeEvent
    public void onMobSpawnerSpawn(LivingSpawnEvent.SpecialSpawn e) {
        Level Level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
        if (Level == null) {
            return;
        }

        if (e.getSpawner() != null) {
            e.getEntity().addTag(CollectiveReference.MOD_ID + ".fromspawner");
        }
    }

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent e) {
        if (!CollectiveEvents.onEntityJoinLevel(e.getLevel(), e.getEntity())) {
            e.setCanceled(true);
        }
    }
}