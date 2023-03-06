/*
 * This is the latest source code of Move Minecarts.
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

package com.natamus.moveminecarts.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;

public class MinecartEvent {
	private static final HashMap<String, Entity> pickedUpMinecarts = new HashMap<String, Entity>();
	private static final HashMap<String, Boolean> playerStoodUp = new HashMap<String, Boolean>();

	public static void onPlayerTick(ServerLevel world, ServerPlayer player) {
		String playerName = player.getName().getString();
		if (!pickedUpMinecarts.containsKey(playerName)) {
			return;
		}

		Entity pickedUpMinecart = pickedUpMinecarts.get(playerName);
		for (Entity passenger : pickedUpMinecart.getPassengers()) {
			if (passenger.is(player)) {
				pickedUpMinecarts.remove(playerName);
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
				pickedUpMinecarts.remove(playerName);
				playerStoodUp.remove(playerName);
				return;
			}
		}

		Vec3 look = player.getLookAngle();
		float distance = 2.0F;
		double dx = player.getX() + (look.x * distance);
		double dy = player.getY() + player.getEyeHeight();
		double dz = player.getZ() + (look.z * distance);
		pickedUpMinecart.setPos(dx, dy, dz);
	}

	public static InteractionResult onMinecartClick(Player player, Level world, InteractionHand hand, Entity target, EntityHitResult hitResult) {
		if (!(target instanceof AbstractMinecart)) {
			return InteractionResult.PASS;
		}

		if (world.isClientSide || !hand.equals(InteractionHand.MAIN_HAND)) {
			return InteractionResult.PASS;
		}

		if (!player.isCrouching()) {
			return InteractionResult.PASS;
		}

		String playerName = player.getName().getString();
		pickedUpMinecarts.put(playerName, target);
		playerStoodUp.put(playerName, false);

		return InteractionResult.SUCCESS;
	}
}
