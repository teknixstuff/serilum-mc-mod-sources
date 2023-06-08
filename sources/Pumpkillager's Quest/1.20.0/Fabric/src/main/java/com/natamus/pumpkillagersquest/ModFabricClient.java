/*
 * This is the latest source code of Pumpkillager's Quest.
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

package com.natamus.pumpkillagersquest;

import com.natamus.collective.fabric.callbacks.CollectiveSoundEvents;
import com.natamus.pumpkillagersquest.events.PkSoundEvents;
import com.natamus.pumpkillagersquest.events.PkTickEvents;
import com.natamus.pumpkillagersquest.events.rendering.ClientRenderEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		// PkSoundEvents
		CollectiveSoundEvents.SOUND_PLAY.register((SoundEngine soundEngine, SoundInstance soundInstance) -> {
			return PkSoundEvents.onSoundEvent(soundEngine, soundInstance);
		});

		// PkTickEvents
		ClientTickEvents.START_WORLD_TICK.register((ClientLevel level) -> {
			PkTickEvents.onLevelTick(level);
			ClientRenderEvent.onClientTick(level);
		});
	}
}
