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

public class MopWeaponItem extends Item {
    public MopWeaponItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.PLAYERS, 2.0f, 1.0f);
            user.sendMessage(Text.literal("Вы помыли пол! Мобы в радиусе 10 блоков поскальзываются и падают!").formatted(Formatting.AQUA), true);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Мокрая деревянная швабра со старой тряпкой.").formatted(Formatting.GRAY, Formatting.ITALIC));
        tooltip.add(Text.literal("ЛКМ: Удар «Куда по помытому?!» (огромный отброс)").formatted(Formatting.RED));
        tooltip.add(Text.literal("ПКМ: Помыть пол (каток для мобов)").formatted(Formatting.BLUE));
    }
}
