/*
 * This is the latest source code of TNT Breaks Bedrock.
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

package com.natamus.tntbreaksbedrock.forge.events;

import com.natamus.tntbreaksbedrock.events.BoomEvent;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeBoomEvent {
	@SubscribeEvent
	public void onExplosion(ExplosionEvent.Detonate e) {
		Explosion explosion = e.getExplosion();
		BoomEvent.onExplosion(e.getWorld(), explosion.getExploder(), explosion);
	}
}
