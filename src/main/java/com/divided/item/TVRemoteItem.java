package com.divided.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class TVRemoteItem extends Item {
    public TVRemoteItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.PLAYERS, 2.0f, 1.5f);

            // Переключение погоды или времени суток кликом пульта!
            if (world.isRaining()) {
                world.getLevelProperties().setRaining(false);
                user.sendMessage(Text.literal("Канал переключен: Погода ясная").formatted(Formatting.AQUA), true);
            } else {
                world.getLevelProperties().setRaining(true);
                user.sendMessage(Text.literal("Канал переключен: Включен дождь").formatted(Formatting.BLUE), true);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Пульт от телевизора Рубин в целлофановом пакетике.").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("ПКМ: Переключить канал мира (погоду/время)").formatted(Formatting.GREEN));
    }
}
