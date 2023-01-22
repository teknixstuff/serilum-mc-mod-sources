/*
 * This is the latest source code of Player Tracking Compass.
 * Minecraft version: 1.18.2.
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

package com.natamus.playertrackingcompass.fabric.network;

import com.natamus.playertrackingcompass.network.CompassTrack;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;

public class PacketToServerRequestTarget {
	public static FriendlyByteBuf createBuffer() {
		return PacketByteBufs.create();
	}

	public static void registerHandle() {
		ServerPlayNetworking.registerGlobalReceiver(NetworkConstants.serverNetworkChannel, (server, player, handler, buf, responseSender) -> {
			server.execute(() -> {
				CompassTrack.request(player);
			});
		});
	}
}
