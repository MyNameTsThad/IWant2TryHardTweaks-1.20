package com.thaddev.iwant2tryhardtweaks.mixin;

import com.thaddev.iwant2tryhardtweaks.IWant2TryHardTweaksConfig;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.RotationAxis;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xaero.common.settings.XaeroCyclingOption;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem", at = @At(value = "INVOKE",  target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"))
    private void renderFirstPersonItem(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo info) {
        if (hand == Hand.MAIN_HAND) {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(IWant2TryHardTweaksConfig.mainRotX));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(IWant2TryHardTweaksConfig.mainRotY));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(IWant2TryHardTweaksConfig.mainRotZ));
            matrices.scale(IWant2TryHardTweaksConfig.mainScaleX, IWant2TryHardTweaksConfig.mainScaleY, IWant2TryHardTweaksConfig.mainScaleZ);
            matrices.translate(IWant2TryHardTweaksConfig.mainPosX, IWant2TryHardTweaksConfig.mainPosY, IWant2TryHardTweaksConfig.mainPosZ);
        } else {
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(IWant2TryHardTweaksConfig.offRotX));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(IWant2TryHardTweaksConfig.offRotY));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(IWant2TryHardTweaksConfig.offRotZ));
            matrices.scale(IWant2TryHardTweaksConfig.offScaleX, IWant2TryHardTweaksConfig.offScaleY, IWant2TryHardTweaksConfig.offScaleZ);
            matrices.translate(IWant2TryHardTweaksConfig.offPosX, IWant2TryHardTweaksConfig.offPosY, IWant2TryHardTweaksConfig.offPosZ);
        }
    }
}
