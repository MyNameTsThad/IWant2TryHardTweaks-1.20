package com.thaddev.iwant2tryhardtweaks;

import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.client.MinecraftClient;

import java.util.ArrayList;
import java.util.List;

public class IWant2TryHardTweaksConfig extends MidnightConfig {
    @Comment(centered = true)
    public static Comment dbg;
    @Entry
    public static boolean debug = false;


    @Comment(centered = true)
    public static Comment bp;

    @Entry
    public static boolean noBlockBreakParticles = false;
    @Entry(name = "Block ID Allow/Deny List")
    public static List<String> noBlockBreakParticlesList = new ArrayList<>();

    @Entry
    public static boolean noBlockBreakingParticles = false;
    @Entry(name = "Block ID Allow/Deny List")
    public static List<String> noBlockBreakingParticlesList = new ArrayList<>();

    @Comment
    public static Comment spacer1;
    @Comment(centered = true)
    public static Comment hfov;
    @Entry
    public static boolean handFovEnabled = false;
    @Entry(min = -3, max = 3, isSlider = true)
    public static float mainPosX = 0;
    @Entry(min = -3, max = 3, isSlider = true)
    public static float mainPosY = 0;
    @Entry(min = -3, max = 3, isSlider = true)
    public static float mainPosZ = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float mainRotX = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float mainRotY = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float mainRotZ = 0;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float mainScaleX = 1f;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float mainScaleY = 1f;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float mainScaleZ = 1f;

    @Entry(min = -3, max = 3, isSlider = true)
    public static float offPosX = 0;
    @Entry(min = -3, max = 3, isSlider = true)
    public static float offPosY = 0;
    @Entry(min = -3, max = 3, isSlider = true)
    public static float offPosZ = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float offRotX = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float offRotY = 0;
    @Entry(min = -180, max = 180, isSlider = true)
    public static float offRotZ = 0;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float offScaleX = 1f;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float offScaleY = 1f;
    @Entry(min = 0, max = 5f, isSlider = true)
    public static float offScaleZ = 1f;


    @Comment
    public static Comment spacer2;
    @Comment(centered = true)
    public static Comment nht;
    @Entry
    public static boolean noHurtTilt = false;

    @Comment(centered = true)
    public static Comment label1;



    @Comment(category = "xaero", centered = true)
    public static Comment waypoints;
    @Entry(category = "xaero")
    public static boolean xaeroMinimapDistanceTimer = false;

    @Comment(category = "xaero", centered = true)
    public static Comment label2;




    @Comment(category = "rei", centered = true)
    public static Comment recuse;
    @Entry(category = "rei")
    public static boolean reiEnableBlockPicking = false;

    @Comment(category = "rei", centered = true)
    public static Comment label3;
}
