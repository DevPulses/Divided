package com.divided.world;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ModDimensions {
    // 1. Святое Измерение (Небесный Сад)
    public static final RegistryKey<World> HOLY_DIMENSION = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of("divided", "holy_dimension"));

    // 2. Испорченное Измерение (Бездна Скверны)
    public static final RegistryKey<World> CORRUPTED_DIMENSION = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of("divided", "corrupted_dimension"));

    // 3. Лиминальная Хрущевка Бесконечности
    public static final RegistryKey<World> KHRUSHCHEVKA_DIMENSION = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of("divided", "khrushchevka_dimension"));

    // 4. ДАЧНЫЙ УЧАСТОК 6 СОТОК (The Dacha Void Dimension)
    public static final RegistryKey<World> DACHA_DIMENSION = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of("divided", "dacha_dimension"));

    // 5. ЗАТОПЛЕННЫЙ ПОДВАЛ (Flooded Basement Abyss)
    public static final RegistryKey<World> BASEMENT_DIMENSION = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of("divided", "basement_dimension"));
}
