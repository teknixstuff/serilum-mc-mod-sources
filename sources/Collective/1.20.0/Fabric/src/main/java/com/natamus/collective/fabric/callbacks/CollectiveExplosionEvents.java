/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class CollectiveExplosionEvents {
	private CollectiveExplosionEvents() { }
	 
    public static final Event<Explosion_Detonate> EXPLOSION_DETONATE = EventFactory.createArrayBacked(Explosion_Detonate.class, callbacks -> (world, sourceEntity, explosion) -> {
        for (Explosion_Detonate callback : callbacks) {
        	callback.onDetonate(world, sourceEntity, explosion);
        }
    });
    
	@FunctionalInterface
	public interface Explosion_Detonate {
		 void onDetonate(Level world, Entity sourceEntity, Explosion explosion);
	}
}
