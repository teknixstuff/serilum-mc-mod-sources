/*
 * This is the latest source code of Starter Structure.
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

package com.natamus.starterstructure.events;

import com.natamus.starterstructure.config.ConfigHandler;
import com.natamus.starterstructure.util.Reference;
import com.natamus.starterstructure.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class StructureProtectionEvents {
	public static boolean onBlockBreak(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity) {
		if (level.isClientSide) {
			return true;
		}

		if (ConfigHandler.playersInCreativeModeIgnoreProtection) {
			if (player.isCreative()) {
				return true;
			}
		}

		if (Util.protectedMap.containsKey(level)) {
			return !Util.protectedMap.get(level).contains(pos);
		}
		return true;
	}

	public static boolean onBlockPlace(Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) {
		if (level.isClientSide) {
			return true;
		}

		if (ConfigHandler.playersInCreativeModeIgnoreProtection) {
			if (livingEntity instanceof Player) {
				if (((Player) livingEntity).isCreative()) {
					return true;
				}
			}
		}

		if (Util.protectedMap.containsKey(level)) {
			return !Util.protectedMap.get(level).contains(blockPos);
		}
		return true;
	}

	public static boolean onPistonMove(Level level, BlockPos blockPos, Direction direction, boolean isExtending) {
		BlockPos faceOffsetPos = blockPos.relative(direction);
		BlockPos nextPos = faceOffsetPos.relative(direction);

		if (Util.protectedMap.containsKey(level)) {
			return !Util.protectedMap.get(level).contains(faceOffsetPos) && !Util.protectedMap.get(level).contains(nextPos);
		}
		return true;
	}

	public static void onTNTExplode(Level level, Entity sourceEntity, Explosion explosion) {
		if (level.isClientSide) {
			return;
		}

		if (explosion == null) {
			return;
		}

		boolean cancel = false;
		if (Util.protectedMap.containsKey(level)) {
			for (BlockPos affectedPos : explosion.getToBlow()) {
				if (Util.protectedMap.get(level).contains(affectedPos)) {
					cancel = true;
					break;
				}
			}
		}

		if (cancel) {
			explosion.getToBlow().clear();
			explosion.getHitPlayers().clear();
		}
	}

	public static boolean onLivingAttack(Level level, Entity entity, DamageSource damageSource, float damageAmount) {
		if (ConfigHandler.protectSpawnedEntities) {
			if (entity.getTags().contains(Reference.MOD_ID + ".protected")) {
				if (ConfigHandler.playersInCreativeModeIgnoreEntityProtection) {
					if (damageSource.getEntity() instanceof Player) {
						return ((Player)damageSource.getEntity()).isCreative();
					}
				}
				return false;
			}
		}
		return true;
	}
}
