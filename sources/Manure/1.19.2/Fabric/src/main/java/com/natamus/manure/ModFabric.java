/*
 * This is the latest source code of Manure.
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

package com.natamus.manure;

import com.natamus.collective.check.RegisterMod;
import com.natamus.manure.dispenser.RecipeManager;
import com.natamus.manure.events.ManureDropEvent;
import com.natamus.manure.items.ManureItems;
import com.natamus.manure.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BoneMealItem;
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
		ManureItems.MANURE = new BoneMealItem(new Item.Properties().tab(CreativeModeTab.TAB_MATERIALS));

		Registry.register(Registry.ITEM, new ResourceLocation(Reference.MOD_ID, "manure"), ManureItems.MANURE);

		RecipeManager.initDispenserBehavior();
	}

	private void loadEvents() {
		ServerTickEvents.END_SERVER_TICK.register((MinecraftServer minecraftServer) -> {
			ManureDropEvent.onServerTick(minecraftServer);
		});

		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			ManureDropEvent.onEntityJoin(entity, world);
		});

		ServerEntityEvents.ENTITY_UNLOAD.register((Entity entity, ServerLevel world) -> {
			ManureDropEvent.onEntityLeave(entity, world);
		});
	}

	private static void setGlobalConstants() {

	}
}
