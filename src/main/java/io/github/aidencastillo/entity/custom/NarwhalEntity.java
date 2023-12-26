package io.github.aidencastillo.entity.custom;

import io.github.aidencastillo.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
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
            
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.5D));
//        this.goalSelector.addGoal(1, new BreedGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.COD), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.SALMON), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.TROPICAL_FISH), false));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.5D, Ingredient.of(Items.PUFFERFISH), false));
//        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
//        this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.5D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
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



//    @Nullable
//    @Override
//    public WaterAnimal getBreedOffspring(ServerLevel pLevel, WaterAnimal pOtherParent) {
//        return ModEntities.NARWHAL.get().create(pLevel);
//    }
}
