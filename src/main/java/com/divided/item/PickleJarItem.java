package com.divided.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class PickleJarItem extends Item {
    public PickleJarItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            user.getHungerManager().add(20, 20.0f);
            user.heal(40.0f);
            user.clearStatusEffects();

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.PLAYERS, 1.5f, 1.0f);
            user.sendMessage(Text.literal("Выпили рассол из 3-литровой банки! Полная бодрость!").formatted(Formatting.GREEN, Formatting.BOLD), true);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Бабушкина 3-литровая банка соленых огурцов.").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("ПКМ: Выпить рассол (моментальный хилл и снятие всех дебаффов)").formatted(Formatting.YELLOW));
    }
}
