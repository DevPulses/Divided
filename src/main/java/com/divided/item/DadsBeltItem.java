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
import net.minecraft.world.World;

import java.util.List;

public class DadsBeltItem extends Item {
    public DadsBeltItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = target.getWorld();
        if (!world.isClient) {
            // Звук хлесткого свистящего удара ремнем
            world.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS, 2.0f, 1.2f);
            world.playSound(null, target.getX(), target.getY(), target.getZ(),
                    SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 2.0f, 0.5f);

            // Наносим 150 чистого урона
            if (attacker instanceof PlayerEntity player) {
                target.damage(world.getDamageSources().playerAttack(player), 150.0f);
            } else {
                target.damage(world.getDamageSources().generic(), 150.0f);
            }

            // Мощнейший батин отброс цели назад на десятки блоков
            double dx = target.getX() - attacker.getX();
            double dz = target.getZ() - attacker.getZ();
            target.takeKnockback(5.0, -dx, -dz);

            if (target instanceof PlayerEntity victim) {
                victim.sendMessage(Text.literal("«Я КОМУ СКАЗАЛ?!» — Батин ремень прилетел прямо по жопе!").formatted(Formatting.RED, Formatting.BOLD), true);
            }
        }
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Тяжелый армейский ремень с начищенной латунной пряжкой.").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("Урон: +150").formatted(Formatting.DARK_RED, Formatting.BOLD));
        tooltip.add(Text.literal("Эффект: Катапультирование цели назад со свистом").formatted(Formatting.GOLD));
    }
}
