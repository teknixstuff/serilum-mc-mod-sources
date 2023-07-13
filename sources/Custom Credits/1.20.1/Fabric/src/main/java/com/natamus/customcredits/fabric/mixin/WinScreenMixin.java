/*
 * This is the latest source code of Custom Credits.
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

package com.natamus.customcredits.fabric.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.natamus.customcredits.config.ConfigHandler;
import com.natamus.customcredits.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Mixin(value = WinScreen.class, priority = 1001)
public abstract class WinScreenMixin extends Screen {
	@Shadow private static @Final Component SECTION_HEADING;
	@Shadow private static @Final String OBFUSCATE_TOKEN;
	@Shadow private float scroll;

	@Shadow private void addEmptyLine() {}
	@Shadow private void addCreditsLine(Component componentLine, boolean shouldCenter) {}
	@Shadow private void addPoemLines(String line) {}

	@Unique private static boolean isPostCredits = false;

	protected WinScreenMixin(Component component) {
		super(component);
	}

	@Inject(method = "init()V", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/List;size()I"))
	protected void init(CallbackInfo ci) {
		if (!ConfigHandler.showMinecraftLogoInCredits) {
			this.scroll = 100;
		}
	}

	@Inject(method = "addCreditsFile(Ljava/io/Reader;)V", at = @At(value = "HEAD"), cancellable = true)
	private void addCreditsFile(Reader oldReader, CallbackInfo ci) throws IOException {
		Reader reader = null;
		if (Util.creditsFile.isFile()) {
			reader = new BufferedReader(new FileReader(Util.creditsFilePath, StandardCharsets.UTF_8));
		}

		if (reader == null) {
			reader = oldReader;
		}

		for(JsonElement jsonelement : GsonHelper.parseArray(reader)) {
			JsonObject jsonobject = jsonelement.getAsJsonObject();
			String s = jsonobject.get("section").getAsString();
			this.addCreditsLine(SECTION_HEADING, true);
			this.addCreditsLine(Component.literal(s).withStyle(ChatFormatting.YELLOW), true);
			this.addCreditsLine(SECTION_HEADING, true);
			this.addEmptyLine();
			this.addEmptyLine();

			for(JsonElement jsonelement1 : jsonobject.getAsJsonArray("disciplines")) {
				JsonObject jsonobject1 = jsonelement1.getAsJsonObject();
				String s1 = jsonobject1.get("discipline").getAsString();
				if (StringUtils.isNotEmpty(s1)) {
					this.addCreditsLine(Component.literal(s1).withStyle(ChatFormatting.YELLOW), true);
					this.addEmptyLine();
					this.addEmptyLine();
				}

				for(JsonElement jsonelement2 : jsonobject1.getAsJsonArray("titles")) {
					JsonObject jsonobject2 = jsonelement2.getAsJsonObject();
					String s2 = jsonobject2.get("title").getAsString();
					JsonArray jsonarray = jsonobject2.getAsJsonArray("names");
					this.addCreditsLine(Component.literal(s2).withStyle(ChatFormatting.GRAY), false);

					for(JsonElement jsonelement3 : jsonarray) {
						String s3 = jsonelement3.getAsString();
						this.addCreditsLine(Component.literal("           ").append(s3).withStyle(ChatFormatting.WHITE), false);
					}

					this.addEmptyLine();
					this.addEmptyLine();
				}
			}
		}

		ci.cancel();
	}

	@Inject(method = "addPoemFile(Ljava/io/Reader;)V", at = @At(value = "HEAD"), cancellable = true)
	private void addPoemFile(Reader oldReader, CallbackInfo ci) throws IOException {
		Reader reader = null;
		if (!isPostCredits) {
			if (Util.poemFile.isFile()) {
				reader = new BufferedReader(new FileReader(Util.poemFilePath, StandardCharsets.UTF_8));
			}
		}
		else {
			if (Util.postCreditsFile.isFile()) {
				reader = new BufferedReader(new FileReader(Util.postCreditsFilePath, StandardCharsets.UTF_8));
			}
		}

		if (reader == null) {
			reader = oldReader;
		}

		BufferedReader bufferedreader = new BufferedReader(reader);
		RandomSource randomsource = RandomSource.create(8124371L);

		String s;
		while((s = bufferedreader.readLine()) != null) {
			int i;
			String s1;
			String s2;
			for(s = s.replaceAll("PLAYERNAME", this.minecraft.getUser().getName()); (i = s.indexOf(OBFUSCATE_TOKEN)) != -1; s = s1 + ChatFormatting.WHITE + ChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, randomsource.nextInt(4) + 3) + s2) {
				s1 = s.substring(0, i);
				s2 = s.substring(i + OBFUSCATE_TOKEN.length());
			}

			this.addPoemLines(s);
			this.addEmptyLine();
		}

		for(int j = 0; j < 8; ++j) {
			this.addEmptyLine();
		}

		isPostCredits = true;

		ci.cancel();
	}
}
