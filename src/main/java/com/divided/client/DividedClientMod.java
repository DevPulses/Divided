package com.divided.client;

import com.divided.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.BatEntityRenderer;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.ZombieEntityRenderer;

public class DividedClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Регистрация рендереров мобов, чтобы клиент не крашился при их спавне
        EntityRendererRegistry.register(ModEntities.GRANDFATHER_BOSS, IronGolemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.PLUMBER_SANYA_BOSS, IronGolemEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.NEIGHBOR_WITH_MOP, ZombieEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHERNOBYL_PIGEON, BatEntityRenderer::new);
    }
}
