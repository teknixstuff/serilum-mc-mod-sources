/*
 * This is the latest source code of Realistic Bees.
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

package com.natamus.realisticbees.fabric.mixin;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.realisticbees.config.ConfigHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = Bee.class, priority = 1001)
public abstract class BeeMixin extends Animal {
    protected BeeMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At(value = "TAIL"))
    public void Bee(EntityType<? extends Bee> beeEntityType, Level level, CallbackInfo ci) {
        Bee bee = (Bee)(Object)this;
        EntityFunctions.setEntitySize(bee, (new EntityDimensions(0.7F, 0.6F, false)).scale((float)ConfigHandler.beeSizeModifier), bee.getEyeHeight(Pose.STANDING));
    }

    @Inject(method = "hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", at = @At(value = "HEAD"), cancellable = true)
    private void hurt(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (ConfigHandler.preventBeeSuffocationDamage && damageSource.equals(DamageSource.IN_WALL)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public @NotNull EntityDimensions getDimensions(@NotNull Pose pose) {
        EntityDimensions defaultDimensions = (pose == Pose.SLEEPING ? SLEEPING_DIMENSIONS : super.getDimensions(pose).scale(this.getScale()));
        return defaultDimensions.scale((float)ConfigHandler.beeSizeModifier);
    }
}
