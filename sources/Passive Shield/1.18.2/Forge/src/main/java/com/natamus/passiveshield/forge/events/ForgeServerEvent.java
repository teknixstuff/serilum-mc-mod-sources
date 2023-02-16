/*
 * This is the latest source code of Passive Shield.
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

package com.natamus.passiveshield.forge.events;

import com.natamus.passiveshield.events.ServerEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeServerEvent {
	@SubscribeEvent
	public void onEntityDamageTaken(LivingHurtEvent e) {
		Entity entity = e.getEntity();

		float damageAmount = e.getAmount();
		float newAmount = ServerEvent.onEntityDamageTaken(entity.getCommandSenderWorld(), entity, e.getSource(), damageAmount);

		if (newAmount != damageAmount) {
			e.setAmount(newAmount);
		}
	}
}