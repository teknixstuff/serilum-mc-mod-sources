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

package com.natamus.playertrackingcompass.forge.services;

import com.natamus.playertrackingcompass.forge.network.NetworkConstants;
import com.natamus.playertrackingcompass.forge.network.PacketToClientUpdateTarget;
import com.natamus.playertrackingcompass.services.helpers.PacketToClientHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;

public class ForgePacketToClientHelper implements PacketToClientHelper {
    @Override
    public void setTrackingTarget(ServerPlayer serverPlayer, BlockPos targetPos) {
        NetworkConstants.network.sendTo(new PacketToClientUpdateTarget(targetPos), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }
}