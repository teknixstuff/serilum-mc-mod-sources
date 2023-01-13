/*
 * This is the latest source code of Omega Mute.
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

package com.natamus.omegamute;

import com.mojang.blaze3d.platform.InputConstants;
import com.natamus.collective.fabric.callbacks.CollectiveSoundEvents;
import com.natamus.omegamute.fabric.cmds.FabricCommandOmega;
import com.natamus.omegamute.data.Constants;
import com.natamus.omegamute.events.MuteEvent;
import com.natamus.omegamute.util.Util;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;

public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Constants.hotkey = KeyBindingHelper.registerKeyBinding(new KeyMapping("omegamute.key.reload", InputConstants.Type.KEYSYM, 46, "key.categories.misc"));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (Constants.hotkey.isDown()) {
				MuteEvent.onHotkeyPress();
				Constants.hotkey.setDown(false);
			}
		}); 
		
 		try {
			Util.loadSoundFile();
		} catch (Exception ex) {
			return;
		}
 		
		CollectiveSoundEvents.SOUND_PLAY.register((SoundEngine soundEngine, SoundInstance soundInstance) -> {
			return MuteEvent.onSoundEvent(soundEngine, soundInstance);
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			FabricCommandOmega.register(dispatcher);
		});
	}
}
