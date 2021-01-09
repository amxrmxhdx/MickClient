package me.mick.client.mixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.mick.client.Main;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;

@Mixin(TitleScreen.class)
public abstract class TitlescreenText extends Screen {

    protected TitlescreenText(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "render")
    public void CustomText(MatrixStack matrices, int mouseX, int mouseY, float delta,  CallbackInfo cl) {
        TitlescreenText.drawStringWithShadow(matrices, this.textRenderer, Main.getClientName()+" "+Main.formatClientVersion(), 2, 0, 10223488 | (MathHelper.ceil(255.0F) << 24));//157255
    }
}
