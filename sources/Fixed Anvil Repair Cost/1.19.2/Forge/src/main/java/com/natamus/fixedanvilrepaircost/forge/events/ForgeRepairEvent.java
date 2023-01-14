/*
 * This is the latest source code of Fixed Anvil Repair Cost.
 * Minecraft version: 1.19.2.
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

package com.natamus.fixedanvilrepaircost.forge.events;

import com.natamus.fixedanvilrepaircost.events.RepairEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import oshi.util.tuples.Triplet;

@EventBusSubscriber
public class ForgeRepairEvent {
	@SubscribeEvent
	public void onRepairEvent(AnvilUpdateEvent e) {
		Triplet<Integer, Integer, ItemStack> triple = RepairEvent.onRepairEvent(null, e.getLeft(), e.getRight(), e.getOutput(), e.getName(), e.getCost(), e.getPlayer());
		if (triple == null) {
			return;
		}

		if (triple.getA() >= 0) {
			e.setCost(triple.getA());
		}

		if (triple.getB() >= 0) {
			e.setMaterialCost(triple.getB());
		}

		if (triple.getC() != null) {
			e.setOutput(triple.getC());
		}
	}
}
