package com.thaddev.iwant2tryhardtweaks.mixin;

import com.thaddev.iwant2tryhardtweaks.IWant2TryHardTweaksConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import xaero.common.minimap.render.MinimapRendererHelper;
import xaero.common.minimap.waypoints.render.WaypointsIngameRenderer;
import xaero.common.misc.Misc;

@Pseudo
@Mixin(value = WaypointsIngameRenderer.class)
public abstract class XaeroMinimapWaypointsIngameRendererMixin {
    @Inject(method = "renderWaypointLabel", at = @At(value = "INVOKE",
      target = "Lxaero/common/misc/Misc;drawNormalText(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFIZLnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
      shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD, require = 0)
    private void renderWaypointLabel(MatrixStack matrixStack, MinimapRendererHelper helper, TextRenderer fontrenderer, String label, double labelScale, float bgAlpha, VertexConsumerProvider.Immediate renderTypeBuffer, VertexConsumer waypointBackgroundConsumer, CallbackInfo ci, int nameW, int bgW, int halfBgW, int halfNamePixel) {
        if (!IWant2TryHardTweaksConfig.xaeroMinimapDistanceTimer) return;
        if (IWant2TryHardTweaksConfig.debug) System.out.println("Rendering Waypoint Label");
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (label.endsWith("m") && player != null) {
            String timer;

            //get distance from player to waypoint and check the horizontal player speed
            double distanceM = Double.parseDouble(label.substring(0, label.length() - 1));

            Vec3d playerPosVec = player.getPos();
            double travelledX = playerPosVec.x - player.lastRenderX;
            double travelledZ = playerPosVec.z - player.lastRenderZ;
            double horizontalPlayerSpeed = Math.sqrt(travelledX * travelledX + travelledZ * travelledZ) / 0.05000000074505806D;

            if (horizontalPlayerSpeed < 0.1) return;

            //calculate the time it would take to get to the waypoint
            double timeRaw = Math.round((distanceM / horizontalPlayerSpeed) * 100d) / 100d;

            //format the time
            if (timeRaw >= 60) {
                int minutes = (int) (timeRaw / 60);
                int seconds = (int) (timeRaw % 60);
                timer = minutes + "m " + seconds + "s";
            } else if (timeRaw >= 30) {
                int timeRounded = (int) Math.round(timeRaw);
                timer = timeRounded + "s";
            } else if (timeRaw >= 10) {
                double timeRounded = Math.round(timeRaw * 10d) / 10d;
                timer = timeRounded + "s";
            } else {
                String timeRounded = String.valueOf(timeRaw);
                if (timeRounded.length() == 3) timeRounded += "0";
                timer = timeRounded + "s";
            }

            int timerW = fontrenderer.getWidth(timer);
            int timerBgW = timerW + 3;
            int halfTimerBgW = timerBgW / 2;
            matrixStack.scale((float) 0.75, (float) 0.75, 1.0F);
            helper.addColoredRectToExistingBuffer(matrixStack.peek().getPositionMatrix(), waypointBackgroundConsumer, (float) (-halfTimerBgW), 12.5F, timerBgW, 9, 0.0F, 0.0F, 0.0F, bgAlpha);
            Misc.drawNormalText(matrixStack, timer, (float) (-halfTimerBgW + 2), 12.5F, -1, false, renderTypeBuffer);
            //Misc.drawNormalText(matrixStack, String.valueOf(Math.round(horizontalPlayerSpeed * 100) / 100f), (float) (-halfTimerBgW + 2), 25F, -1, false, renderTypeBuffer);
            matrixStack.scale((float) (1.0 / 0.75), (float) (1.0 / 0.75), 1.0F);
        }
    }
}
