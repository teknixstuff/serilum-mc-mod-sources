/*
 * This is the latest source code of Configurable Furnace Burn Time.
 * Minecraft version: 1.19.4.
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

package com.natamus.configurablefurnaceburntime;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveFurnaceEvents;
import com.natamus.configurablefurnaceburntime.events.FurnaceBurnEvent;
import com.natamus.configurablefurnaceburntime.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.ItemStack;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectiveFurnaceEvents.CALCULATE_FURNACE_BURN_TIME.register((ItemStack itemStack, int burntime) -> {
			return FurnaceBurnEvent.furnaceBurnTimeEvent(itemStack, burntime);
		});
	}

	private static void setGlobalConstants() {

	}
}
