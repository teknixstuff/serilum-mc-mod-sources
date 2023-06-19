/*
 * This is the latest source code of Player Tracking Compass.
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

package com.natamus.playertrackingcompass.forge.network;

import com.natamus.playertrackingcompass.network.CompassTrack;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToClientUpdateTarget {
	private int x;
	private int y;
	private int z;

	public PacketToClientUpdateTarget() {}

	public PacketToClientUpdateTarget(BlockPos newTarget) {
		this.x = newTarget.getX();
		this.y = newTarget.getY();
		this.z = newTarget.getZ();
	}

	public PacketToClientUpdateTarget(FriendlyByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}

	public void toBytes(FriendlyByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}

	public void handle(Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			CompassTrack.updateTarget(x, y, z);
		});
		ctx.get().setPacketHandled(true);
	}
}
