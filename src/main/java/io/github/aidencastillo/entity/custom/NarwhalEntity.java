package io.github.aidencastillo.entity.custom;

import io.github.aidencastillo.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class NarwhalEntity extends WaterAnimal {

    public NarwhalEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            // Client-side logic, particle effects, etc.
            if (this.isInWater() && this.random.nextInt(200) == 0) {
                for (int i = 0; i < 5; ++i) {
                    this.level().addParticle(ParticleTypes.DOLPHIN, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                }
            }
        } else {
            if (this.isInWater()) {
                this.goalSelector.tick();
                this.updateSwimming();
            }
        }
    }

    public void updateSwimming() {
        if (this.random.nextInt(200) == 0) {
            Vec3 randomPos = this.getRandomWaterPos();
            if (randomPos != null) {
                System.out.println("Moving to: " + randomPos);
                this.getNavigation().moveTo(randomPos.x, randomPos.y, randomPos.z, 1.0D);
            }
        }
    }


    @Nullable
    private Vec3 getRandomWaterPos() {
        BlockPos randomPos = this.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, this.blockPosition());
        if (this.level().getFluidState(randomPos).is(FluidTags.WATER)) {
            return new Vec3(randomPos.getX() + 0.5D, randomPos.getY() + 0.5D, randomPos.getZ() + 0.5D);
        }
        return null;
    }




    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.SALMON), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.TROPICAL_FISH), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.PUFFERFISH), false));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 1D);

    }

    public boolean isPushedByFluid() {
        return false;
    }
    public boolean canBreatheUnderwater() {
        return true;
    }



}
