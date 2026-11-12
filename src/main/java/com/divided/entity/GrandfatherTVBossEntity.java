package com.divided.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

public class GrandfatherTVBossEntity extends HostileEntity {
    private final ServerBossBar bossBar;

    public GrandfatherTVBossEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
        this.bossBar = (ServerBossBar) new ServerBossBar(
                Text.literal("§c§lДЕД НА КРЕСЛЕ (ХРАНИТЕЛЬ ПУЛЬТА)§r"),
                BossBar.Color.RED,
                BossBar.Style.PROGRESS
        ).setDarkenSky(true);
    }

    public static DefaultAttributeContainer.Builder createGrandfatherAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10000.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.45)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 150.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.8));
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 16.0f));
        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void tick() {
        super.tick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());

        if (this.getWorld().isClient) {
            this.getWorld().addParticle(ParticleTypes.PORTAL,
                    this.getX() + (this.random.nextDouble() - 0.5) * 2.0,
                    this.getY() + this.random.nextDouble() * 2.5,
                    this.getZ() + (this.random.nextDouble() - 0.5) * 2.0,
                    0, 0, 0);
        }
    }

    @Override
    public void onStartedTrackingBy(ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity player) {
            String heldItem = player.getMainHandStack().getItem().toString();
            if (!heldItem.contains("remote") && !heldItem.contains("tapok")) {
                player.sendMessage(Text.literal("«Ты чё канал переключил?!» — Дед блокирует весь урон!").formatted(Formatting.YELLOW), true);
                return false;
            }
        }
        return super.damage(source, amount);
    }
}
