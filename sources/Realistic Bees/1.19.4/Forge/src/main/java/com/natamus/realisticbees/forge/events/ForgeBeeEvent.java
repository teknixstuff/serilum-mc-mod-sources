/*
 * This is the latest source code of Realistic Bees.
 * Minecraft version: 1.19.4.
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

package com.natamus.realisticbees.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.realisticbees.events.BeeEvent;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeBeeEvent {
	@SubscribeEvent
	public void onBeeCheckSpawn(MobSpawnEvent.FinalizeSpawn e) {
		Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getLevel());
		if (level == null) {
			return;
		}

		BeeEvent.onBeeCheckSpawn(e.getEntity(), level, null, null, e.getSpawnType());
	}

	@SubscribeEvent
	public void onBeeSpawn(EntityJoinLevelEvent e) {
		BeeEvent.onBeeSpawn(e.getEntity(), e.getLevel());
	}
}
