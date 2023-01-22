/*
 * This is the latest source code of Player Tracking Compass.
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

package com.natamus.playertrackingcompass;

import com.natamus.collective.check.RegisterMod;
import com.natamus.playertrackingcompass.fabric.network.PacketToServerRequestTarget;
import com.natamus.playertrackingcompass.items.CompassVariables;
import com.natamus.playertrackingcompass.items.TrackingCompassItem;
import com.natamus.playertrackingcompass.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		registerItems();
		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void registerItems() {
		CompassVariables.TRACKING_COMPASS = new TrackingCompassItem((new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

		Registry.register(Registry.ITEM, new ResourceLocation(Reference.MOD_ID, "tracking_compass"), CompassVariables.TRACKING_COMPASS);
	}

	private void loadEvents() {
		PacketToServerRequestTarget.registerHandle();
	}

	private static void setGlobalConstants() {

	}
}
