/*
 * This is the latest source code of Collective.
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

package com.natamus.collective.functions;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SignText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignFunctions {
    public static List<String> getSignText(BlockEntity blockEntity) {
        if (blockEntity instanceof SignBlockEntity) {
            return getSignText((SignBlockEntity)blockEntity);
        }
        else if (blockEntity instanceof HangingSignBlockEntity) {
            return getSignText((HangingSignBlockEntity)blockEntity);
        }
        return new ArrayList<String>();
    }

    public static List<String> getSignText(SignBlockEntity signBlockEntity) {
        return getSignText(Arrays.asList(signBlockEntity.getFrontText(), signBlockEntity.getBackText()));
    }
    public static List<String> getSignText(HangingSignBlockEntity hangingSignBlockEntity) {
        return getSignText(Arrays.asList(hangingSignBlockEntity.getFrontText(), hangingSignBlockEntity.getBackText()));
    }

    public static List<String> getSignText(List<SignText> signTextList) {
        List<String> lines = new ArrayList<String>();

        for (SignText signText : signTextList) {
            for (Component line : signText.getMessages(false)) {
                if (line.equals(Component.EMPTY)) {
                    lines.add("");
                    continue;
                }

                lines.add(line.getString());
            }
        }

        return lines;
    }
}
