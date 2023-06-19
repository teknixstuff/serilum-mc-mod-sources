/*
 * This is the latest source code of Player Death Kick.
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

package com.natamus.playerdeathkick.forge.events;

import com.natamus.playerdeathkick.events.DeathEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeDeathEvent {
	@SubscribeEvent
	public void onDeathEvent(LivingDeathEvent e) {
		Entity entity = e.getEntity();
		Level world = entity.level();
		if (world.isClientSide) {
			return;
		}
		
		if (!(entity instanceof ServerPlayer)) {
			return;
		}

		DeathEvent.onDeathEvent((ServerPlayer)entity, e.getSource(), 0);
	}
}
