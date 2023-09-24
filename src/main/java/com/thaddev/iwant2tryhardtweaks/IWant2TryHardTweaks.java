package com.thaddev.iwant2tryhardtweaks;

import eu.midnightdust.lib.config.MidnightConfig;
import me.shedaniel.rei.api.client.view.ViewSearchBuilder;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class IWant2TryHardTweaks implements ClientModInitializer {
    public static final String MODID = "iwant2tryhardtweaks";

    private static KeyBinding recipesKey;
    private static KeyBinding usagesKey;

    @Override
    public void onInitializeClient() {

        //config screen
        MidnightConfig.init(MODID, IWant2TryHardTweaksConfig.class);

        //keybinds
        recipesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
          "key.iwant2tryhardtweaks.reiopenrecipes",
          InputUtil.Type.KEYSYM,
          GLFW.GLFW_KEY_F6,
          "category.iwant2tryhardtweaks.rei"
        ));
        usagesKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
          "key.iwant2tryhardtweaks.reiopenusages",
          InputUtil.Type.KEYSYM,
          GLFW.GLFW_KEY_F7,
          "category.iwant2tryhardtweaks.rei"
        ));

        //keybind functions
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && client.getCameraEntity() != null) {
                if (recipesKey.isPressed()) {
                    Entity camera = client.getCameraEntity();
                    HitResult blockHit = camera.raycast(20.0, 0.0f, true);
                    if (blockHit != null && blockHit.getType() == HitResult.Type.BLOCK) {
                        BlockState blockState = client.world.getBlockState(((BlockHitResult) blockHit).getBlockPos());
                        Block block = blockState.getBlock();
                        if (block instanceof FluidBlock) {
                            openREIRecipeScreenFor(((FluidBlock) block).getFluidState(blockState).getFluid());
                        } else {
                            openREIRecipeScreenFor(block);
                        }
                    }
                } else if (usagesKey.isPressed()) {
                    Entity camera = client.getCameraEntity();
                    HitResult blockHit = camera.raycast(20.0, 0.0f, true);
                    if (blockHit != null && blockHit.getType() == HitResult.Type.BLOCK) {
                        BlockState blockState = client.world.getBlockState(((BlockHitResult) blockHit).getBlockPos());
                        Block block = blockState.getBlock();
                        if (block instanceof FluidBlock) {
                            openREIUsageScreenFor(((FluidBlock) block).getFluidState(blockState).getFluid());
                        } else {
                            openREIUsageScreenFor(block);
                        }
                    }
                }
            }
        });

    }

    public static void openREIRecipeScreenFor(ItemEntity itemEntity) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            EntryStack<?> stack = EntryStacks.of(itemEntity.getStack());
            ViewSearchBuilder.builder().addRecipesFor(stack).open();
        }
    }

    public static void openREIRecipeScreenFor(Block block) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            if (IWant2TryHardTweaksConfig.debug) System.out.println("Opening REI for " + block);
            EntryStack<?> stack = EntryStacks.of(block);
            ViewSearchBuilder.builder().addRecipesFor(stack).open();
        }
    }

    public static void openREIRecipeScreenFor(Fluid fluid) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            if (IWant2TryHardTweaksConfig.debug) System.out.println("Opening REI for " + fluid);
            EntryStack<?> stack = EntryStacks.of(fluid);
            ViewSearchBuilder.builder().addRecipesFor(stack).open();
        }
    }

    public static void openREIUsageScreenFor(ItemEntity itemEntity) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            EntryStack<?> stack = EntryStacks.of(itemEntity.getStack());
            ViewSearchBuilder.builder().addUsagesFor(stack).open();
        }
    }

    public static void openREIUsageScreenFor(Block block) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            if (IWant2TryHardTweaksConfig.debug) System.out.println("Opening REI for " + block);
            EntryStack<?> stack = EntryStacks.of(block);
            ViewSearchBuilder.builder().addUsagesFor(stack).open();
        }
    }

    public static void openREIUsageScreenFor(Fluid fluid) {
        if (FabricLoader.getInstance().isModLoaded("roughlyenoughitems")) {
            if (IWant2TryHardTweaksConfig.debug) System.out.println("Opening REI for " + fluid);
            EntryStack<?> stack = EntryStacks.of(fluid);
            ViewSearchBuilder.builder().addUsagesFor(stack).open();
        }
    }

//    public static Entity getTargeted(MinecraftClient client, float tickDelta) {
//        Entity entity2 = client.getCameraEntity();
//        if (entity2 == null) {
//            return null;
//        }
//        if (client.world == null) {
//            return null;
//        }
//        double d = client.interactionManager.getReachDistance();
//        client.crosshairTarget = entity2.raycast(d, tickDelta, false);
//        Vec3d vec3d = entity2.getCameraPosVec(tickDelta);
//        boolean bl = false;
//        int i = 3;
//        double e = d;
//        if (client.interactionManager.hasExtendedReach()) {
//            d = e = 6.0;
//        } else {
//            if (e > 3.0) {
//                bl = true;
//            }
//            d = e;
//        }
//        e *= e;
//        if (client.crosshairTarget != null) {
//            e = this.client.crosshairTarget.getPos().squaredDistanceTo(vec3d);
//        }
//        Vec3d vec3d2 = entity2.getRotationVec(1.0f);
//        Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
//        float f = 1.0f;
//        Box box = entity2.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
//        EntityHitResult entityHitResult = ProjectileUtil.raycast(entity2, vec3d, vec3d3, box, entity -> !entity.isSpectator() && entity.canHit(), e);
//        if (entityHitResult != null) {
//            Entity entity22 = entityHitResult.getEntity();
//            Vec3d vec3d4 = entityHitResult.getPos();
//            double g = vec3d.squaredDistanceTo(vec3d4);
//            if (bl && g > 9.0) {
//                this.client.crosshairTarget = BlockHitResult.createMissed(vec3d4, Direction.getFacing(vec3d2.x, vec3d2.y, vec3d2.z), BlockPos.ofFloored(vec3d4));
//            } else if (g < e || this.client.crosshairTarget == null) {
//                this.client.crosshairTarget = entityHitResult;
//                if (entity22 instanceof LivingEntity || entity22 instanceof ItemFrameEntity) {
//                    this.client.targetedEntity = entity22;
//                }
//            }
//        }
//    }
}
