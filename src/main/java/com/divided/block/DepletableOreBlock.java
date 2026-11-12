package com.divided.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;

public class DepletableOreBlock extends Block {
    public static final BooleanProperty DEPLETED = BooleanProperty.of("depleted");
    public static final IntProperty CHARGE_TIMER = IntProperty.of("charge_timer", 0, 60);

    public DepletableOreBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(DEPLETED, false).with(CHARGE_TIMER, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(DEPLETED, CHARGE_TIMER);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Проверка кулдауна 3 минуты (3600 тиков)
        if (state.get(DEPLETED)) {
            // Восстановление руды
            world.setBlockState(pos, state.with(DEPLETED, false).with(CHARGE_TIMER, 0));
        }
    }

    public static void tickMobInteraction(ServerWorld world, BlockPos pos, BlockState state) {
        if (state.get(DEPLETED)) return;

        // Поиск мобов в радиусе 8 блоков
        Box searchBox = new Box(pos).expand(8.0);
        List<LivingEntity> mobs = world.getEntitiesByClass(LivingEntity.class, searchBox, mob ->
                mob instanceof ChickenEntity || mob instanceof PigEntity ||
                mob instanceof CowEntity || mob instanceof CreeperEntity ||
                mob instanceof SkeletonEntity || mob instanceof ZombieEntity ||
                mob instanceof EndermanEntity);

        for (LivingEntity mob : mobs) {
            // Если моб стоит непосредственно на блоке руды
            if (mob.getBlockPos().down().equals(pos)) {
                int timer = state.get(CHARGE_TIMER) + 1;
                // Партиклы зарядки (белые/черные)
                world.spawnParticles(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 3, 0.2, 0.2, 0.2, 0.05);

                if (timer >= 60) { // 3 секунды (60 тиков)
                    // Истощение руды
                    world.setBlockState(pos, state.with(DEPLETED, true).with(CHARGE_TIMER, 0));
                    world.spawnParticles(ParticleTypes.FLASH, pos.getX() + 0.5, pos.getY() + 1.2, pos.getZ() + 0.5, 1, 0, 0, 0, 0);
                    break;
                } else {
                    world.setBlockState(pos, state.with(CHARGE_TIMER, timer));
                }
            }
        }
    }
}
