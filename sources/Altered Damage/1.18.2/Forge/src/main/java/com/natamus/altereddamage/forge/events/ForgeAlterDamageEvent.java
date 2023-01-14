/*
 * This is the latest source code of Altered Damage.
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

package com.natamus.altereddamage.forge.events;

import com.natamus.altereddamage.events.AlterDamageEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeAlterDamageEvent {
	@SubscribeEvent
	public void onEntityDamageTaken(LivingHurtEvent e) {
		LivingEntity livingEntity = e.getEntityLiving();

		float originalDamage = e.getAmount();
		float newDamage = AlterDamageEvent.onEntityDamageTaken(livingEntity.level, livingEntity, e.getSource(), originalDamage);

		if (originalDamage != newDamage) {
			e.setAmount(newDamage);
		}
	}
}
