package com.divided.registry;

import com.divided.DividedMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    // 1. Материал для Шапочки из Фольги (серебристый блестящий алюминий)
    public static final RegistryEntry<ArmorMaterial> TINFOIL = register("tinfoil",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 7);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.BOOTS, 3);
            }), 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0f, 0.0f, () -> Ingredient.ofItems(ModItems.INSANE_ALLOY));

    // 2. Материал для Батиного Комплекта (Белая майка, Треники, Тапочки)
    public static final RegistryEntry<ArmorMaterial> DADS_SUIT = register("dads_suit",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 10);
                map.put(ArmorItem.Type.LEGGINGS, 8);
                map.put(ArmorItem.Type.BOOTS, 5);
            }), 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 2.0f, 0.1f, () -> Ingredient.ofItems(ModItems.INSANE_ALLOY));

    // 3. Материал для Маминого Халата (Абсолютная Защита)
    public static final RegistryEntry<ArmorMaterial> MOMS_ROBE = register("moms_robe",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.HELMET, 10);
                map.put(ArmorItem.Type.CHESTPLATE, 50); // Богоподобная броня
                map.put(ArmorItem.Type.LEGGINGS, 40);
                map.put(ArmorItem.Type.BOOTS, 10);
            }), 50, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 10.0f, 1.0f, () -> Ingredient.ofItems(ModItems.INSANE_SINGULARITY));

    private static RegistryEntry<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantability,
            RegistryEntry<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient
    ) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of(DividedMod.MOD_ID, name)));
        ArmorMaterial material = new ArmorMaterial(defense, enchantability, equipSound, repairIngredient, layers, toughness, knockbackResistance);
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(DividedMod.MOD_ID, name), material);
    }

    public static void registerArmorMaterials() {
        DividedMod.LOGGER.info("Registering Armor Materials for " + DividedMod.MOD_ID);
    }
}
