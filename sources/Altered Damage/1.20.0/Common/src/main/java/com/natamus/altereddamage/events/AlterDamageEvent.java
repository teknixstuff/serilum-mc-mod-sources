/*
 * This is the latest source code of Altered Damage.
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

package com.natamus.altereddamage.events;

import com.natamus.altereddamage.config.ConfigHandler;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AlterDamageEvent {
	public static float onEntityDamageTaken(Level world, Entity target, DamageSource damageSource, float originalDamage) {
		double modifier;
		if (target instanceof Player) {
			if (!ConfigHandler.alterPlayerDamageTaken) {
				return originalDamage;
			}
			
			modifier = ConfigHandler.playerDamageModifier;
		}
		else {
			if (!ConfigHandler.alterEntityDamageTaken) {
				return originalDamage;
			}
			
			modifier = ConfigHandler.entityDamageModifier;
		}
		
		float damage = (float)(originalDamage*modifier);
		
		if (ConfigHandler.preventFatalModifiedDamage) {
			LivingEntity le = (LivingEntity)target;
			float health = le.getHealth();
			if (damage >= health && originalDamage < health) {
				return health-0.1F;
			}
		}
		
		return damage;
	}
}
