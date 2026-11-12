package com.divided.world;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

public class BellRitualHandler {
    // Последовательности колоколов из ideas.txt
    // Святой: South -> West -> North -> East
    // Испорченный: East -> North -> West -> South

    public static void triggerHolyRitual(ServerWorld world, BlockPos centerPos) {
        // Удар святой молнии в центр структуры
        LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
        if (lightning != null) {
            lightning.refreshPositionAfterTeleport(centerPos.getX() + 0.5, centerPos.getY(), centerPos.getZ() + 0.5);
            world.spawnEntity(lightning);
        }

        // Выпадение подписанной книги "XXX" с координатами портала
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        book.set(net.minecraft.component.DataComponentTypes.CUSTOM_NAME, Text.literal("XXX"));

        // Спавн книги
        ItemEntity drop = new ItemEntity(world, centerPos.getX() + 0.5, centerPos.getY() + 1.0, centerPos.getZ() + 0.5, book);
        world.spawnEntity(drop);
    }
}
