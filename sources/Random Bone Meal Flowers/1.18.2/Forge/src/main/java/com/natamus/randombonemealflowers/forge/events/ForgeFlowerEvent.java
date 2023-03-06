/*
 * This is the latest source code of Random Bone Meal Flowers.
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

package com.natamus.randombonemealflowers.forge.events;

import com.natamus.collective.functions.WorldFunctions;
import com.natamus.randombonemealflowers.events.FlowerEvent;
import com.natamus.randombonemealflowers.util.Util;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class ForgeFlowerEvent {
    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load e) {
        Level level = WorldFunctions.getWorldIfInstanceOfAndNotRemote(e.getWorld());
        if (level == null) {
            return;
        }

		Util.attemptFlowerlistProcessing(level);
    }

	@SubscribeEvent
	public void onBonemeal(BonemealEvent e) {
		FlowerEvent.onBonemeal(e.getWorld(), e.getPos(), null, e.getStack());
	}
}
