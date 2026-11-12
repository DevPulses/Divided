package com.divided.registry;

import com.divided.DividedMod;
import com.divided.block.DepletableOreBlock;
import com.divided.block.EssenceInfuserBlock;
import com.divided.block.QuantumCompressorBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // Истощаемые руды
    public static final Block DEPLETED_COAL_ORE = registerBlock("depleted_coal_ore",
            new DepletableOreBlock(AbstractBlock.Settings.copy(Blocks.COAL_ORE).ticksRandomly()));

    public static final Block DEPLETED_IRON_ORE = registerBlock("depleted_iron_ore",
            new DepletableOreBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE).ticksRandomly()));

    public static final Block DEPLETED_GOLD_ORE = registerBlock("depleted_gold_ore",
            new DepletableOreBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE).ticksRandomly()));

    public static final Block DEPLETED_DIAMOND_ORE = registerBlock("depleted_diamond_ore",
            new DepletableOreBlock(AbstractBlock.Settings.copy(Blocks.DIAMOND_ORE).ticksRandomly()));

    // Механизмы
    public static final Block ESSENCE_INFUSER = registerBlock("essence_infuser",
            new EssenceInfuserBlock(AbstractBlock.Settings.copy(Blocks.ENCHANTING_TABLE)));

    public static final Block QUANTUM_COMPRESSOR = registerBlock("quantum_compressor",
            new QuantumCompressorBlock(AbstractBlock.Settings.copy(Blocks.ANVIL)));

    private static Block registerBlock(String name, Block block) {
        Registry.register(Registries.BLOCK, Identifier.of(DividedMod.MOD_ID, name), block);
        Registry.register(Registries.ITEM, Identifier.of(DividedMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
        return block;
    }

    public static void registerModBlocks() {
        DividedMod.LOGGER.info("Registering Blocks for " + DividedMod.MOD_ID);
    }
}
