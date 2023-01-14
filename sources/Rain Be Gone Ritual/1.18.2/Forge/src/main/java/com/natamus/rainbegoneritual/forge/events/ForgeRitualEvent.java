/*
 * This is the latest source code of Rain Be Gone Ritual.
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

package com.natamus.rainbegoneritual.forge.events;

import com.natamus.rainbegoneritual.events.RitualEvent;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeRitualEvent {
	@SubscribeEvent
	public void onClick(PlayerInteractEvent.RightClickBlock e) {
		RitualEvent.onClick(e.getWorld(), e.getPlayer(), e.getHand(), e.getPos(), e.getHitVec());
	}
	
	@SubscribeEvent
	public void onExplosionDamage(LivingHurtEvent e) {
		Entity entity = e.getEntity();
		if (RitualEvent.onExplosionDamage(entity.getCommandSenderWorld(), entity, e.getSource(), e.getAmount()) == 0.0F) {
			e.setCanceled(true);
		}
	}
}
