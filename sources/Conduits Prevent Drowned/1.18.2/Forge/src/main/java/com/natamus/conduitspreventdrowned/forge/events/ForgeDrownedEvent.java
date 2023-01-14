/*
 * This is the latest source code of Conduits Prevent Drowned.
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

package com.natamus.conduitspreventdrowned.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.conduitspreventdrowned.events.DrownedEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeDrownedEvent {
	@SubscribeEvent
	public void onDrownedSpawn(LivingSpawnEvent.CheckSpawn e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
		if (level == null) {
			return;
		}
		
		Entity entity = e.getEntity();
		if (!(entity instanceof Mob)) {
			return;
		}
		
		if (!DrownedEvent.onDrownedSpawn((Mob)entity, (ServerLevel)level, null, e.getSpawnReason())) {
			e.setCanceled(true);
		}
	}
}
