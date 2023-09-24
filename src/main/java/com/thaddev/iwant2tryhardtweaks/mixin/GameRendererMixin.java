package com.thaddev.iwant2tryhardtweaks.mixin;

import com.thaddev.iwant2tryhardtweaks.IWant2TryHardTweaksConfig;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(method = "tiltViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void tiltViewWhenHurt(MatrixStack matrices, float tickDelta, CallbackInfo ci){
        if (IWant2TryHardTweaksConfig.noHurtTilt) ci.cancel();
    }
}
