/*
 * This is the latest source code of Bouncier Beds.
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

package com.natamus.bouncierbeds.forge.events;

import com.natamus.bouncierbeds.events.BouncyBedEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeBouncyBedEvent {
	@SubscribeEvent
	public void onLivingJump(LivingJumpEvent e) {
		LivingEntity livingEntity = e.getEntity();
		BouncyBedEvent.onLivingJump(livingEntity.level(), livingEntity);
	}
	
	@SubscribeEvent
	public void onFall(LivingFallEvent e) {
		Entity entity = e.getEntity();
		if (BouncyBedEvent.onFall(entity.level(), entity, 0, 0) == 0) {
			e.setCanceled(true);
		}
	}
}
