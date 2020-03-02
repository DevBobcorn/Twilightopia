package bobcorn.twilightopia.entity.monster;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.blocks.RatBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class RatEntity extends MonsterEntity {
	private RatEntity.SummonRatGoal summonRat;
	
	private static final Predicate<LivingEntity> TASTY = new Predicate<LivingEntity>() {
		public boolean test(@Nullable LivingEntity p) {
			if (p instanceof PlayerEntity) {
				PlayerEntity tar = (PlayerEntity) p;
				for (int i = 0;i < tar.inventory.getSizeInventory();i++) {
					if (tar.inventory.getStackInSlot(i).getItem().isFood())
						return true;
				}
			}
			return false;
		}
	};

	public RatEntity(EntityType<? extends RatEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.summonRat = new RatEntity.SummonRatGoal(this);
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, this.summonRat);
		this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new RatEntity.HideInCheeseGoal(this));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, TASTY));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_FOX_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_FOX_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_FOX_DEATH;
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
	}

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC)
					&& this.summonRat != null) {
				this.summonRat.notifyHurt();
			}
			return super.attackEntityFrom(source, amount);
		}
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return sizeIn.height * 0.8f;
	}

	static class HideInCheeseGoal extends RandomWalkingGoal {
		private Direction facing;
		private boolean doMerge;

		public HideInCheeseGoal(RatEntity ratIn) {
			super(ratIn, 1.0D, 10);
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			if (this.creature.getAttackTarget() != null) {
				return false;
			} else if (!this.creature.getNavigator().noPath()) {
				return false;
			} else {
				Random random = this.creature.getRNG();
				if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.creature.world, this.creature)
						&& random.nextInt(10) == 0) {
					this.facing = Direction.random(random);
					BlockPos blockpos = (new BlockPos(this.creature.func_226277_ct_(), this.creature.func_226278_cu_() + 0.5D,
							this.creature.func_226281_cx_())).offset(this.facing);
					BlockState blockstate = this.creature.world.getBlockState(blockpos);
					if (RatBlock.canContainRat(blockstate)) {
						this.doMerge = true;
						return true;
					}
				}

				this.doMerge = false;
				return super.shouldExecute();
			}
		}

		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean shouldContinueExecuting() {
			return this.doMerge ? false : super.shouldContinueExecuting();
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			if (!this.doMerge) {
				super.startExecuting();
			} else {
				IWorld iworld = this.creature.world;
				BlockPos blockpos = (new BlockPos(this.creature.func_226277_ct_(), this.creature.func_226278_cu_() + 0.5D, this.creature.func_226281_cx_()))
						.offset(this.facing);
				BlockState blockstate = iworld.getBlockState(blockpos);
				if (RatBlock.canContainRat(blockstate)) {
					iworld.setBlockState(blockpos, RatBlock.infest(blockstate.getBlock()), 3);
					this.creature.spawnExplosionParticle();
					this.creature.remove();
				}

			}
		}
	}

	static class SummonRatGoal extends Goal {
		private final RatEntity rat;
		private int lookForFriends;

		public SummonRatGoal(RatEntity ratIn) {
			this.rat = ratIn;
		}

		public void notifyHurt() {
			if (this.lookForFriends == 0) {
				this.lookForFriends = 20;
			}

		}

		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute() {
			return this.lookForFriends > 0;
		}

		/**
		 * Keep ticking a continuous task that has already been started
		 */
		public void tick() {
			--this.lookForFriends;
			if (this.lookForFriends <= 0) {
				World world = this.rat.world;
				Random random = this.rat.getRNG();
				BlockPos blockpos = new BlockPos(this.rat);

				for (int i = 0; i <= 5 && i >= -5; i = (i <= 0 ? 1 : 0) - i) {
					for (int j = 0; j <= 10 && j >= -10; j = (j <= 0 ? 1 : 0) - j) {
						for (int k = 0; k <= 10 && k >= -10; k = (k <= 0 ? 1 : 0) - k) {
							BlockPos blockpos1 = blockpos.add(j, i, k);
							BlockState blockstate = world.getBlockState(blockpos1);
							Block block = blockstate.getBlock();
							if (block instanceof RatBlock) {
								if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, this.rat)) {
									world.setBlockState(blockpos1, ModBlocks.CHEESE_BLOCK.get().getDefaultState());
									
									
									world.destroyBlock(blockpos1, true);
								} else {
									world.setBlockState(blockpos1,
											((RatBlock) block).getMimickedBlock().getDefaultState(), 3);
								}

								if (random.nextBoolean()) {
									return;
								}
							}
						}
					}
				}
			}

		}
	}
}