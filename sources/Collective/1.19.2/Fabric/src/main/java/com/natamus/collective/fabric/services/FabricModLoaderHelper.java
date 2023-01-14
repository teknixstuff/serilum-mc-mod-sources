/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.fabric.services;

import com.natamus.collective.fabric.data.GlobalFabricObjects;
import com.natamus.collective.services.helpers.ModLoaderHelper;
import net.fabricmc.api.EnvType;

public class FabricModLoaderHelper implements ModLoaderHelper {
    @Override
    public String getModLoaderName() {
        return "Fabric";
    }

    @Override
    public String getGameDirectory() {
        return GlobalFabricObjects.fabricLoader.getGameDir().toString();
    }

    @Override
    public boolean isModLoaded(String modId) {
        return GlobalFabricObjects.fabricLoader.isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return GlobalFabricObjects.fabricLoader.isDevelopmentEnvironment();
    }

    @Override
    public boolean isClientSide() {
        return GlobalFabricObjects.fabricLoader.getEnvironmentType() == EnvType.CLIENT;
    }
}
