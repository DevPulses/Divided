package com.divided.registry;

import com.divided.DividedMod;
import com.divided.item.DadsBeltItem;
import com.divided.item.MopWeaponItem;
import com.divided.item.PickleJarItem;
import com.divided.item.TVRemoteItem;
import com.divided.item.TapokItem;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    // 🩴 Абсолютный Боевой ТАПОК
    public static final Item TAPOK = registerItem("tapok",
            new TapokItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

    // 🌸 Мамин Халат
    public static final Item MOMS_ROBE = registerItem("moms_robe",
            new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

    // 🧢 Батин Комплект
    public static final Item TINFOIL_HAT = registerItem("tinfoil_hat",
            new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.HELMET,
                    new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item WHITE_TANK_TOP = registerItem("white_tank_top",
            new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.CHESTPLATE,
                    new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item SWEATPANTS = registerItem("sweatpants",
            new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.LEGGINGS,
                    new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item SLIPPERS = registerItem("slippers",
            new ArmorItem(ArmorMaterials.NETHERITE, ArmorItem.Type.BOOTS,
                    new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    // 🌀 Ебанутые Материалы
    public static final Item INSANE_ALLOY = registerItem("insane_alloy",
            new Item(new Item.Settings().rarity(Rarity.RARE).fireproof()));

    public static final Item INSANE_SINGULARITY = registerItem("insane_singularity",
            new Item(new Item.Settings().rarity(Rarity.EPIC).fireproof()));

    // 🎖️ Батин Ремень (Боевое оружие)
    public static final Item DADS_BELT = registerItem("dads_belt",
            new DadsBeltItem(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item INSANE_DRILL = registerItem("insane_drill",
            new Item(new Item.Settings().maxCount(1).rarity(Rarity.EPIC).fireproof()));

    // 🧹 Оружие: Мокрая Швабра
    public static final Item WET_MOP = registerItem("wet_mop",
            new MopWeaponItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON)));

    // 📺 Артефакты
    public static final Item TV_REMOTE = registerItem("tv_remote",
            new TVRemoteItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));

    public static final Item PICKLE_JAR = registerItem("pickle_jar",
            new PickleJarItem(new Item.Settings().maxCount(16).rarity(Rarity.UNCOMMON)));

    public static final Item CARPET_ON_WALL = registerItem("carpet_on_wall",
            new Item(new Item.Settings().maxCount(64)));

    public static final Item SOVIET_DOOR_KEY = registerItem("soviet_door_key",
            new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    public static final Item SANYA_WRENCH = registerItem("sanya_wrench",
            new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(DividedMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        DividedMod.LOGGER.info("Registering Items for " + DividedMod.MOD_ID);
    }
}
