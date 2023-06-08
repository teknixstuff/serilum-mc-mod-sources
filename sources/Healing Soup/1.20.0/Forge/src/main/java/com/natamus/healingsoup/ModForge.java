/*
 * This is the latest source code of Healing Soup.
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

package com.natamus.healingsoup;

import com.natamus.collective.check.RegisterMod;
import com.natamus.healingsoup.forge.config.IntegrateForgeConfig;
import com.natamus.healingsoup.forge.events.ForgePopulateCreativeTabEvent;
import com.natamus.healingsoup.forge.events.ForgeSoupEvent;
import com.natamus.healingsoup.items.SoupFoods;
import com.natamus.healingsoup.items.SoupItems;
import com.natamus.healingsoup.util.Reference;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(Reference.MOD_ID)
public class ModForge {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);

	public static final RegistryObject<Item> MUSHROOM_SOUP_ITEM = ITEMS.register("mushroom_soup", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).food(SoupFoods.MUSHROOM_SOUP)));
	public static final RegistryObject<Item> CACTUS_SOUP_ITEM = ITEMS.register("cactus_soup", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).food(SoupFoods.CACTUS_SOUP)));
	public static final RegistryObject<Item> CHOCOLATE_MILK_ITEM = ITEMS.register("chocolate_milk", () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).food(SoupFoods.CHOCOLATE_MILK)));

	
	public ModForge() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::loadComplete);

		ITEMS.register(modEventBus);
		modEventBus.register(new ForgePopulateCreativeTabEvent());

		setGlobalConstants();
		ModCommon.init();

		IntegrateForgeConfig.registerScreen(ModLoadingContext.get());

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadComplete(final FMLLoadCompleteEvent event) {
		SoupItems.MUSHROOM_SOUP = MUSHROOM_SOUP_ITEM.get();
		SoupItems.CACTUS_SOUP = CACTUS_SOUP_ITEM.get();
		SoupItems.CHOCOLATE_MILK = CHOCOLATE_MILK_ITEM.get();

		SoupItems.soups.add(SoupItems.MUSHROOM_SOUP);
		SoupItems.soups.add(SoupItems.CACTUS_SOUP);
		SoupItems.soups.add(SoupItems.CHOCOLATE_MILK);

    	MinecraftForge.EVENT_BUS.register(new ForgeSoupEvent());
	}

	private static void setGlobalConstants() {

	}
}