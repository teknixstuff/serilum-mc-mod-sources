/*
 * This is the latest source code of Configurable Mob Potion Effects.
 * Minecraft version: 1.19.3.
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

package com.natamus.configurablemobpotioneffects;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.configurablemobpotioneffects.cmd.CommandCmpe;
import com.natamus.configurablemobpotioneffects.events.MobEffectsEvent;
import com.natamus.configurablemobpotioneffects.util.Reference;
import com.natamus.configurablemobpotioneffects.util.Util;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		try {
			Util.loadMobConfigFile();
		} catch (Exception ex) {
			System.out.println("[" + Reference.NAME + "] Error loading the entity config file. The mod has been disabled.");
			return;
		}

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CommandCmpe.register(dispatcher);
        });

		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel world) -> {
			MobEffectsEvent.onEntityJoin(world, entity);
		});

		CollectiveEntityEvents.ON_LIVING_ATTACK.register((Level world, Entity entity, DamageSource damageSource, float damageAmount) -> {
			MobEffectsEvent.onEntityDamage(world, entity, damageSource, damageAmount);
			return true;
		});
	}

	private static void setGlobalConstants() {

	}
}
