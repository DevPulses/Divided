package com.divided.registry;

import com.divided.DividedMod;
import com.divided.entity.ChernobylPigeonEntity;
import com.divided.entity.GrandfatherTVBossEntity;
import com.divided.entity.NeighborWithMopEntity;
import com.divided.entity.PlumberSanyaBossEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    // 📺 Босс: Дед на кресле
    public static final EntityType<GrandfatherTVBossEntity> GRANDFATHER_BOSS = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DividedMod.MOD_ID, "grandfather_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GrandfatherTVBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1.2f, 1.8f))
                    .build()
    );

    // 🔧 Босс: Сантехник Саня
    public static final EntityType<PlumberSanyaBossEntity> PLUMBER_SANYA_BOSS = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DividedMod.MOD_ID, "plumber_sanya_boss"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, PlumberSanyaBossEntity::new)
                    .dimensions(EntityDimensions.fixed(1.0f, 2.0f))
                    .build()
    );

    // 🧹 Моб: Злая соседка со шваброй
    public static final EntityType<NeighborWithMopEntity> NEIGHBOR_WITH_MOP = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DividedMod.MOD_ID, "neighbor_with_mop"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, NeighborWithMopEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.8f))
                    .build()
    );

    // 🐦 Моб: Чернобыльский голубь
    public static final EntityType<ChernobylPigeonEntity> CHERNOBYL_PIGEON = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(DividedMod.MOD_ID, "chernobyl_pigeon"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ChernobylPigeonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.6f))
                    .build()
    );

    public static void registerModEntities() {
        DividedMod.LOGGER.info("Registering Entities and Bosses for " + DividedMod.MOD_ID);
        FabricDefaultAttributeRegistry.register(GRANDFATHER_BOSS, GrandfatherTVBossEntity.createGrandfatherAttributes());
        FabricDefaultAttributeRegistry.register(PLUMBER_SANYA_BOSS, PlumberSanyaBossEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(NEIGHBOR_WITH_MOP, NeighborWithMopEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CHERNOBYL_PIGEON, ChernobylPigeonEntity.createAttributes());
    }
}
