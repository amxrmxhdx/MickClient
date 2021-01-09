package me.mick.client.mixin;

import me.mick.client.Main;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;

@Mixin({InGameHud.class})
public class GameText {

    @Inject(method = { "render" }, at = { @At("RETURN") })
    private void CustomRender(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (Main.getScreenTextColor() < 16777215) {
            Main.setScreenTextColor(Main.getScreenTextColor() + 1);
        } else {
            Main.setScreenTextColor(0);
        }
        MinecraftClient.getInstance().textRenderer.draw(matrices, Main.getClientName()+" "+Main.formatClientVersion(), 1, 1, Main.getScreenTextColor());
    }

}
