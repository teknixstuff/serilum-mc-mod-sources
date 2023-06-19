/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;

public class CollectivePistonEvents {
	private CollectivePistonEvents() { }
	 
    public static final Event<Piston_Activate> PRE_PISTON_ACTIVATE = EventFactory.createArrayBacked(Piston_Activate.class, callbacks -> (level, blockPos, direction, isExtending) -> {
        for (Piston_Activate callback : callbacks) {
        	if (!callback.onPistonActivate(level, blockPos, direction, isExtending)) {
				return false;
			}
        }
		return true;
    });
    
	@FunctionalInterface
	public interface Piston_Activate {
		 boolean onPistonActivate(Level level, BlockPos blockPos, Direction direction, boolean isExtending);
	}
}
