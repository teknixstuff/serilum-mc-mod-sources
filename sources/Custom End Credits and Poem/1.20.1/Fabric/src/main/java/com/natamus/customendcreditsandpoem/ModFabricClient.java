/*
 * This is the latest source code of Custom End Credits and Poem.
 * Minecraft version: 1.20.1.
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

package com.natamus.customendcreditsandpoem;

import com.natamus.collective.fabric.callbacks.CollectiveClientEvents;
import com.natamus.customendcreditsandpoem.events.WorldEvents;
import com.natamus.customendcreditsandpoem.fabric.cmds.FabricCommandCredits;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.multiplayer.ClientLevel;


public class ModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() { 
		registerEvents();
	}
	
	private void registerEvents() {
		CollectiveClientEvents.CLIENT_WORLD_LOAD.register((ClientLevel clientLevel) -> {
			WorldEvents.onWorldLoad(clientLevel);
		});

		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			FabricCommandCredits.register(dispatcher);
		});
	}
}
