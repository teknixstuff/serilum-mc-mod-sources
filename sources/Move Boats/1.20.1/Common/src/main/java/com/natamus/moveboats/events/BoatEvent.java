/*
 * This is the latest source code of Move Boats.
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

package com.natamus.moveboats.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;

public class BoatEvent {
	private static final HashMap<String, Entity> pickedUpBoats = new HashMap<String, Entity>();
	private static final HashMap<String, Boolean> playerStoodUp = new HashMap<String, Boolean>();

	public static void onPlayerTick(ServerLevel world, ServerPlayer player) {
		String playerName = player.getName().getString();
		if (!pickedUpBoats.containsKey(playerName)) {
			return;
		}

		Entity pickedUpBoat = pickedUpBoats.get(playerName);
		for (Entity passenger : pickedUpBoat.getPassengers()) {
			if (passenger.is(player)) {
				pickedUpBoats.remove(playerName);
				return;
			}
		}

		boolean stoodUp = playerStoodUp.getOrDefault(playerName, false);
		if (!stoodUp) {
			if (!player.isCrouching()) {
				playerStoodUp.put(playerName, true);
			}
		}
		else {
			if (player.isCrouching()) {
				pickedUpBoats.remove(playerName);
				playerStoodUp.remove(playerName);
				return;
			}
		}

		Vec3 look = player.getLookAngle();
		float distance = 2.0F;
		double dx = player.getX() + (look.x * distance);
		double dy = player.getY() + player.getEyeHeight();
		double dz = player.getZ() + (look.z * distance);
		pickedUpBoat.setPos(dx, dy, dz);
	}

	public static InteractionResult onBoatClick(Player player, Level world, InteractionHand hand, Entity target, EntityHitResult hitResult) {
		if (!(target instanceof Boat)) {
			return InteractionResult.PASS;
		}

		if (world.isClientSide || !hand.equals(InteractionHand.MAIN_HAND)) {
			return InteractionResult.PASS;
		}

		if (!player.isCrouching()) {
			return InteractionResult.PASS;
		}

		String playerName = player.getName().getString();
		pickedUpBoats.put(playerName, target);
		playerStoodUp.put(playerName, false);

		return InteractionResult.SUCCESS;
	}
}
