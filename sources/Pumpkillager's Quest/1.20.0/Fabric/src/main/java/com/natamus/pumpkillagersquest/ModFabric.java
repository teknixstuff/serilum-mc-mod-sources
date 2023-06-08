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

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.*;
import com.natamus.pumpkillagersquest.cmds.CommandPumpkillager;
import com.natamus.pumpkillagersquest.events.*;
import com.natamus.pumpkillagersquest.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		// PkAttackEvents
		CollectiveAttackEvents.ON_ARROW_NOCK.register((ItemStack itemStack, Level level, Player player, InteractionHand hand, boolean hasAmmo) -> {
			PkAttackEvents.onArrowShoot(itemStack, level, player, hand, hasAmmo);
			return InteractionResultHolder.pass(itemStack);
		});

		// PkBlockEvents
		PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, entity) -> {
			return PkBlockEvents.onBlockBreak(level, player, pos, state, entity);
		});

		CollectiveBlockEvents.BLOCK_RIGHT_CLICK.register((Level level, Player player, InteractionHand hand, BlockPos pos, BlockHitResult hitVec) -> {
			return PkBlockEvents.onCandleClick(level, player, hand, pos, hitVec);
		});

		CollectiveBlockEvents.BLOCK_PLACE.register((Level level, BlockPos blockPos, BlockState blockState, LivingEntity livingEntity, ItemStack itemStack) -> {
			return PkBlockEvents.onBlockPlace(level, blockPos, blockState, livingEntity, itemStack);
		});

		// PkEntityEvents
		ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerLevel level) -> {
			PkEntityEvents.onEntityJoin(entity, level);
		});

		ServerEntityEvents.ENTITY_UNLOAD.register((Entity entity, ServerLevel level) -> {
			PkEntityEvents.onEntityLeave(entity, level);
		});

		CollectivePlayerEvents.ON_ITEM_PICKED_UP.register((Level level, Player player, ItemStack itemStack) -> {
			PkEntityEvents.onItemPickup(level, player, itemStack);
		});

		CollectiveEntityEvents.ON_ENTITY_LIGHTNING_STRIKE.register((Level level, Entity entity, LightningBolt lightningBolt) -> {
			return PkEntityEvents.onEntityHitByLightning(level, entity, lightningBolt);
		});

		// PkLivingEvents
		CollectiveEntityEvents.ON_LIVING_DAMAGE_CALC.register((Level level, Entity entity, DamageSource damageSource, float damageAmount) -> {
			return PkLivingEvents.onDamagePumpkillager(level, entity, damageSource, damageAmount);
		});

		ServerLivingEntityEvents.ALLOW_DEATH.register((LivingEntity livingEntity, DamageSource damageSource, float damageAmount) -> {
			return PkLivingEvents.onLivingDeath(livingEntity, damageSource, damageAmount);
		});

		CollectiveEntityEvents.ON_ENTITY_IS_DROPPING_LOOT.register((Level level, Entity entity, DamageSource damageSource) -> {
			if (entity instanceof LivingEntity) {
				PkLivingEvents.onEntityItemDrop(level, (LivingEntity)entity, damageSource);
			}
		});

		// PkOtherEvents
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            CommandPumpkillager.register(dispatcher);
        });

		CollectiveExplosionEvents.EXPLOSION_DETONATE.register((Level level, Entity sourceEntity, Explosion explosion) -> {
			PkOtherEvents.onTNTExplode(level, sourceEntity, explosion);
		});

		CollectivePistonEvents.PRE_PISTON_ACTIVATE.register((Level level, BlockPos blockPos, Direction direction, boolean isExtending) -> {
			return PkOtherEvents.onPistonMove(level, blockPos, direction, isExtending);
		});

		// PkPlayerEvents
		UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
			return PkPlayerEvents.onCharacterInteract(player, level, hand, entity, hitResult);
		});

		UseItemCallback.EVENT.register((player, level, hand) -> {
			return PkPlayerEvents.onRightClickItem(player, level, hand);
		});

		// PkTickEvents
		ServerTickEvents.START_WORLD_TICK.register((ServerLevel level) -> {
			PkTickEvents.onLevelTick(level);
		});

		CollectivePlayerEvents.PLAYER_TICK.register((ServerLevel level, ServerPlayer player) -> {
			PkTickEvents.onPlayerTick(level, player);
		});

		// PkWorldEvents
		CollectiveWorldEvents.WORLD_UNLOAD.register((ServerLevel level) -> {
			PkWorldEvents.onWorldUnload(level);
		});
	}

	private static void setGlobalConstants() {

	}
}
