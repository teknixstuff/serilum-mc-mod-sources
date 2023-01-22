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

package com.natamus.playertrackingcompass.network;

import com.natamus.collective.functions.StringFunctions;
import com.natamus.playertrackingcompass.items.CompassVariables;
import com.natamus.playertrackingcompass.services.Services;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class CompassTrack {
    public static void request(ServerPlayer serverPlayer) {
        BlockPos targetPos = new BlockPos(0, 0, 0);
        BlockPos serverPlayerPos = serverPlayer.blockPosition();
        BlockPos comparepp = new BlockPos(serverPlayerPos.getX(), 1, serverPlayerPos.getZ());

        ServerPlayer closestplayer = null;
        double closestdistance = 999999999999.0;

        ServerLevel world = serverPlayer.getLevel();
        for (ServerPlayer oplayer : world.players()) {
            BlockPos oplayerpos = oplayer.blockPosition();
            BlockPos compareop = new BlockPos(oplayerpos.getX(), 1, oplayerpos.getZ());

            double distance = comparepp.distManhattan(compareop);
            if (distance < 10) {
                continue;
            }
            if (distance < closestdistance) {
                closestdistance = distance;
                closestplayer = oplayer;
            }
        }

        if (closestplayer != null) {
            targetPos = closestplayer.blockPosition().immutable();

            StringFunctions.sendMessage(serverPlayer, "The compass is pointing at " + closestplayer.getName().getString() + ".", ChatFormatting.YELLOW);
        }
        else {
            StringFunctions.sendMessage(serverPlayer, "Unable to redirect the compass. There are no players around or they're too close.", ChatFormatting.YELLOW);
        }

        Services.PACKETTOCLIENT.setTrackingTarget(serverPlayer, targetPos);
    }

    public static void updateTarget(int x, int y, int z) {
        CompassVariables.trackingTarget = new int[]{x, y, z};
    }
}
