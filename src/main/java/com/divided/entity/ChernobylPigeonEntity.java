package com.divided.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class ChernobylPigeonEntity extends FlyingEntity implements Monster {
    public ChernobylPigeonEntity(EntityType<? extends FlyingEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FlyingEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 300.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        // Раз в 10 секунд сбрасывает на игрока радиоактивную бомбу
        if (!this.getWorld().isClient && this.age % 200 == 0 && this.getTarget() != null) {
            PlayerEntity target = (PlayerEntity) this.getTarget();
            this.getWorld().createExplosion(this, target.getX(), target.getY(), target.getZ(), 2.0f, World.ExplosionSourceType.NONE);
            target.sendMessage(Text.literal("Радиоактивный голубь нагадил прямо на голову!").formatted(Formatting.DARK_GREEN), true);
        }
    }
}
