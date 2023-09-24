package com.thaddev.iwant2tryhardtweaks.mixin;

import com.thaddev.iwant2tryhardtweaks.IWant2TryHardTweaksConfig;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public abstract class ParticleManagerMixin {
    //disables block break particles
    @Inject(method = "addBlockBreakParticles", at = @At("HEAD"), cancellable = true)
    public void addBlockBreakParticles(BlockPos pos, BlockState state, CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world == null) return;

        if (IWant2TryHardTweaksConfig.debug) System.out.println("Block ID: " + Registries.BLOCK.getId(world.getBlockState(pos).getBlock()));

        //check if the block id in the blockpos is in the allow/deny list
        boolean inList = IWant2TryHardTweaksConfig.noBlockBreakParticlesList.contains(Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).toString());

        //if the config is set to deny particles and the block id is not in the allow list or the config is set to allow particles and the block id is in the deny list, cancel the particle
        if (IWant2TryHardTweaksConfig.noBlockBreakParticles && !inList || !IWant2TryHardTweaksConfig.noBlockBreakParticles && inList) {
            ci.cancel();
        }
    }

    //disables block breaking particles
    @Inject(method = "addBlockBreakingParticles", at = @At("HEAD"), cancellable = true)
    public void addBlockBreakParticles(BlockPos pos, Direction direction, CallbackInfo ci) {
        World world = MinecraftClient.getInstance().world;
        if (world == null) return;

        if (IWant2TryHardTweaksConfig.debug) System.out.println("Block ID: " + Registries.BLOCK.getId(world.getBlockState(pos).getBlock()));

        //check if the block id in the blockpos is in the allow/deny list
        boolean inList = IWant2TryHardTweaksConfig.noBlockBreakingParticlesList.contains(Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).toString());

        //if the config is set to deny particles and the block id is not in the allow list or the config is set to allow particles and the block id is in the deny list, cancel the particle
        if (IWant2TryHardTweaksConfig.noBlockBreakingParticles && !inList || !IWant2TryHardTweaksConfig.noBlockBreakingParticles && inList) {
            ci.cancel();
        }
    }
}
