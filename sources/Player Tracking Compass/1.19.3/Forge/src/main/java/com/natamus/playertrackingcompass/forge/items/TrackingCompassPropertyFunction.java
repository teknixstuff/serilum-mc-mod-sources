/*
 * This is the latest source code of Player Tracking Compass.
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

package com.natamus.playertrackingcompass.forge.items;

import com.natamus.playertrackingcompass.items.CompassVariables;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrackingCompassPropertyFunction implements ItemPropertyFunction {

	@SubscribeEvent
	public static void models(FMLClientSetupEvent e) {
		ItemProperties.register(CompassVariables.TRACKING_COMPASS, new ResourceLocation("angle"), new TrackingCompassPropertyFunction());
	}

	private double prevAngle = 0.0D;
	private double prevWobble = 0.0D;
	private long prevWorldTime = 0L;

	@Override
	public float call(@NotNull ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity livingEntity, int i) {
		boolean isLiving = livingEntity != null;

		if (!isLiving && !stack.isFramed()) {
			return 0;
		}

		Entity entity = isLiving ? livingEntity : stack.getFrame();

		if (world == null) {
			world = (ClientLevel)entity.level;
		}

		if (CompassVariables.trackingTarget == null) {
			return 0;
		}
		double angle;

		double entityAngle = isLiving ? entity.getYRot() : getFrameAngle((ItemFrame) entity);
		entityAngle /= 360.0D;
		entityAngle = Mth.positiveModulo(entityAngle, 1.0D);
		double posAngle = getPosToAngle(CompassVariables.trackingTarget, entity);
		posAngle /= Math.PI * 2D;
		angle = 0.5D - (entityAngle - 0.25D - posAngle);

		if (isLiving) {
			angle = wobble(world, angle);
		}

		return Mth.positiveModulo((float) angle, 1.0F);
	}

	private double wobble(Level world, double angle) {
		long worldTime = world.getGameTime();
		if (worldTime != prevWorldTime) {
			prevWorldTime = worldTime;
			double angleDifference = angle - prevAngle;
			angleDifference = Mth.positiveModulo(angleDifference + 0.5D, 1.0D) - 0.5D;
			prevWobble += angleDifference * 0.1D;
			prevWobble *= 0.8D;
			prevAngle = Mth.positiveModulo(prevAngle + prevWobble, 1.0D);
		}
		return prevAngle;
	}

	private double getFrameAngle(ItemFrame entity) {
		return Mth.wrapDegrees(180 + entity.getDirection().get2DDataValue() * 90);
	}

	private double getPosToAngle(int[] pos, Entity entity) {
		return Math.atan2(pos[2] - entity.getZ(), pos[0] - entity.getX());
	}
}
