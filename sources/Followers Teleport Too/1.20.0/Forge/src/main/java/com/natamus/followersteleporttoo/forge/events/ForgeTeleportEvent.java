/*
 * This is the latest source code of Followers Teleport Too.
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

package com.natamus.followersteleporttoo.forge.events;

import com.natamus.followersteleporttoo.events.TeleportEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeTeleportEvent {
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerTeleport(EntityTeleportEvent.TeleportCommand e) {
		if (e.isCanceled()) {
			return;
		}

		Entity entity = e.getEntity();
		TeleportEvent.onPlayerTeleport(entity.level(), entity, e.getTargetX(), e.getTargetY(), e.getTargetZ());
	}

	@SubscribeEvent
	public void onFollowerDamage(LivingHurtEvent e) {
		LivingEntity livingEntity = e.getEntity();

		if (TeleportEvent.onFollowerDamage(livingEntity.level(), livingEntity, e.getSource(), e.getAmount()) == 0F) {
			e.setAmount(0F);
			e.setCanceled(true);
		}
	}
}
