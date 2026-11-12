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

public class TapokItem extends Item {
    public TapokItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (!world.isClient) {
            world.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, SoundCategory.PLAYERS, 5.0f, 0.5f);
            world.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.PLAYERS, 2.0f, 2.0f);

            if (target instanceof PlayerEntity victim) {
                victim.sendMessage(Text.literal("Вас прихлопнул БОЕВОЙ ТАПОК!").formatted(Formatting.RED, Formatting.BOLD), true);
            }

            target.discard();
        }
        return true;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.PLAYERS, 2.0f, 0.8f);
            user.sendMessage(Text.literal("ТАПОК запущен в полёт!").formatted(Formatting.GOLD), true);
        }
        return TypedActionResult.success(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("«Стирает сущности прямо из оперативной памяти сервера.»").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("ЛКМ: Мгновенное удаление цели (Шлёп)").formatted(Formatting.RED));
        tooltip.add(Text.literal("ПКМ: Самонаводящийся бросок").formatted(Formatting.YELLOW));
    }
}
