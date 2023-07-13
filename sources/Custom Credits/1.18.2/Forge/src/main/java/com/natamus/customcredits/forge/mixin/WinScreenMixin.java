/*
 * This is the latest source code of Custom Credits.
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

package com.natamus.customcredits.forge.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.natamus.customcredits.config.ConfigHandler;
import com.natamus.customcredits.util.Util;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.GsonHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@Mixin(value = WinScreen.class, priority = 999)
public abstract class WinScreenMixin extends Screen {
	@Shadow private static @Final Component SECTION_HEADING;
	@Shadow private static @Final String OBFUSCATE_TOKEN;
	@Shadow private float scroll;

	@Shadow private void addEmptyLine() {}
	@Shadow private void addCreditsLine(Component componentLine, boolean shouldCenter) {}
	@Shadow private void addPoemLines(String line) {}

	@Shadow private static @Final ResourceLocation LOGO_LOCATION;
	@Shadow private static @Final ResourceLocation EDITION_LOCATION;
	@Shadow private static @Final ResourceLocation VIGNETTE_LOCATION;

	@Shadow private void renderBg() {}
	@Shadow private List<FormattedCharSequence> lines;
	@Shadow private IntSet centeredLines;
	@Shadow private float scrollSpeed;

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

	@Inject(method = "addCreditsFile(Ljava/io/InputStreamReader;)V", at = @At(value = "HEAD"), cancellable = true)
	private void addCreditsFile(InputStreamReader oldReader, CallbackInfo ci) throws IOException {
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
         this.addCreditsLine((new TextComponent(s)).withStyle(ChatFormatting.YELLOW), true);
         this.addCreditsLine(SECTION_HEADING, true);
         this.addEmptyLine();
         this.addEmptyLine();

         for(JsonElement jsonelement1 : jsonobject.getAsJsonArray("titles")) {
            JsonObject jsonobject1 = jsonelement1.getAsJsonObject();
            String s1 = jsonobject1.get("title").getAsString();
            JsonArray jsonarray = jsonobject1.getAsJsonArray("names");
            this.addCreditsLine((new TextComponent(s1)).withStyle(ChatFormatting.GRAY), false);

            for(JsonElement jsonelement2 : jsonarray) {
               String s2 = jsonelement2.getAsString();
               this.addCreditsLine((new TextComponent("           ")).append(s2).withStyle(ChatFormatting.WHITE), false);
            }

            this.addEmptyLine();
            this.addEmptyLine();
         }
      }

		ci.cancel();
	}

	@Inject(method = "addPoemFile(Ljava/io/InputStreamReader;)V", at = @At(value = "HEAD"), cancellable = true)
	private void addPoemFile(InputStreamReader oldReader, CallbackInfo ci) throws IOException {
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
		Random random = new Random(8124371L);

		String s;
		while((s = bufferedreader.readLine()) != null) {
			int i;
			String s1;
			String s2;
			for(s = s.replaceAll("PLAYERNAME", this.minecraft.getUser().getName()); (i = s.indexOf(OBFUSCATE_TOKEN)) != -1; s = s1 + ChatFormatting.WHITE + ChatFormatting.OBFUSCATED + "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s2) {
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

	/**
	 * @author Rick South
	 * @reason Unable to achieve with simple mixin. Overwriting with higher priority to keep compatibility.
	 */
	@Overwrite
	public void render(PoseStack p_96884_, int p_96885_, int p_96886_, float p_96887_) {
		this.scroll += p_96887_ * this.scrollSpeed;
		this.renderBg();
		int i = this.width / 2 - 137;
		int j = this.height + 50;
		float f = -this.scroll;
		p_96884_.pushPose();
		p_96884_.translate(0.0D, f, 0.0D);
		if (ConfigHandler.showMinecraftLogoInCredits) {
			RenderSystem.setShaderTexture(0, LOGO_LOCATION);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.enableBlend();
			this.blitOutlineBlack(i, j, (p_96890_, p_96891_) -> {
				this.blit(p_96884_, p_96890_, p_96891_, 0, 0, 155, 44);
				this.blit(p_96884_, p_96890_ + 155, p_96891_, 0, 45, 155, 44);
			});
			RenderSystem.disableBlend();
			RenderSystem.setShaderTexture(0, EDITION_LOCATION);
			blit(p_96884_, i + 88, j + 37, 0.0F, 0.0F, 98, 14, 128, 16);
		}
		int k = j + 100;

		for (int l = 0; l < this.lines.size(); ++l) {
			if (l == this.lines.size() - 1) {
				float f1 = (float) k + f - (float) (this.height / 2 - 6);
				if (f1 < 0.0F) {
					p_96884_.translate(0.0D, -f1, 0.0D);
				}
			}

			if ((float) k + f + 12.0F + 8.0F > 0.0F && (float) k + f < (float) this.height) {
				FormattedCharSequence formattedcharsequence = this.lines.get(l);
				if (this.centeredLines.contains(l)) {
					this.font.drawShadow(p_96884_, formattedcharsequence, (float) (i + (274 - this.font.width(formattedcharsequence)) / 2), (float) k, 16777215);
				} else {
					this.font.drawShadow(p_96884_, formattedcharsequence, (float) i, (float) k, 16777215);
				}
			}

			k += 12;
		}

		p_96884_.popPose();
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, VIGNETTE_LOCATION);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR);
		int i1 = this.width;
		int j1 = this.height;
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
		bufferbuilder.vertex(0.0D, j1, this.getBlitOffset()).uv(0.0F, 1.0F).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(i1, j1, this.getBlitOffset()).uv(1.0F, 1.0F).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(i1, 0.0D, this.getBlitOffset()).uv(1.0F, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, this.getBlitOffset()).uv(0.0F, 0.0F).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
		tesselator.end();
		RenderSystem.disableBlend();
		super.render(p_96884_, p_96885_, p_96886_, p_96887_);
	}
}
