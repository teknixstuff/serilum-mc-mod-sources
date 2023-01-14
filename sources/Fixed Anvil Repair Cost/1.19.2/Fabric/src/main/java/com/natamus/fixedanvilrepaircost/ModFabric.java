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

package com.natamus.fixedanvilrepaircost;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveAnvilEvents;
import com.natamus.fixedanvilrepaircost.events.RepairEvent;
import com.natamus.fixedanvilrepaircost.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
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
		CollectiveAnvilEvents.ANVIL_CHANGE.register((AnvilMenu anvilmenu, ItemStack left, ItemStack right, ItemStack output, String itemName, int baseCost, Player player) -> {
			return RepairEvent.onRepairEvent(anvilmenu, left, right, output, itemName, baseCost, player);
		});
	}

	private static void setGlobalConstants() {

	}
}
