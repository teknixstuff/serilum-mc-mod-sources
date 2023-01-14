/*
 * This is the latest source code of Snowballs Freeze Mobs.
 * Minecraft version: 1.19.3.
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

package com.natamus.snowballsfreezemobs.forge.events;

import com.natamus.snowballsfreezemobs.events.SnowEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeSnowEvent {
	@SubscribeEvent
	public void onEntityHurt(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		if (SnowEvent.onEntityHurt(entity.getCommandSenderWorld(), entity, e.getSource(), e.getAmount()) == 0.0F) {
			e.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public void onLivingUpdate(LivingEvent.LivingTickEvent e) {
		Entity entity = e.getEntity();
		SnowEvent.onLivingUpdate(entity.getCommandSenderWorld(), entity);
	}
}
