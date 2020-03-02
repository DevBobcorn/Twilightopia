package bobcorn.twilightopia.entity;

import java.util.Random;

import javax.annotation.Nullable;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.items.ModItems;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.IllusionerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class SantaClausEntity extends MonsterEntity {
	private Random rand = new Random();

	public SantaClausEntity(EntityType<? extends SantaClausEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new MeleeAttackGoal());
		this.targetSelector.addGoal(2, new HurtByTargetGoal());
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VILLAGER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_VILLAGER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_VILLAGER_DEATH;
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(26.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.62F;
	}

	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		this.setHeldItem(Hand.MAIN_HAND, new ItemStack(rand.nextInt(5) == 0 ? ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "illushroom")) : ModItems.candy_cane));
		return spawnDataIn;
	}

	class HurtByTargetGoal extends net.minecraft.entity.ai.goal.HurtByTargetGoal {
		public HurtByTargetGoal() {
			super(SantaClausEntity.this);
		}

		/**
		 * Execute a one shot task or start executing a continuous task
		 */
		public void startExecuting() {
			super.startExecuting();
			if (SantaClausEntity.this.isChild()) {
				this.alertOthers();
				this.resetTask();
			}
		}

		protected void setAttackTarget(MobEntity mobIn, LivingEntity targetIn) {
			if (mobIn instanceof SantaClausEntity && !mobIn.isChild()) {
				super.setAttackTarget(mobIn, targetIn);
			}
		}
	}

	class MeleeAttackGoal extends net.minecraft.entity.ai.goal.MeleeAttackGoal {
		public MeleeAttackGoal() {
			super(SantaClausEntity.this, 1.25D, true);
		}

		protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
			double d0 = this.getAttackReachSqr(enemy);
			if (distToEnemySqr <= d0 && this.attackTick <= 0) {
				this.attackTick = 20;
				this.attacker.attackEntityAsMob(enemy);
			}
		}

		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}
	
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int var3 = this.rand.nextInt(6);

		switch (var3) {
			case 0:
				entityDropItem(ModItems.santa_hat);
				break;
			case 1:
				entityDropItem(ModItems.santa_coat);
				break;
			case 2:
				entityDropItem(ModItems.santa_trousers);
				break;
			case 3:
				entityDropItem(ModItems.santa_boots);
				break;
		}
		if (this.getHeldItemMainhand().getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "illushroom"))) {
			IllusionerEntity il = EntityType.ILLUSIONER.create(this.world);
			il.copyLocationAndAnglesFrom(this);
			this.world.addEntity(il);
		} else
		if (rand.nextInt(3) != 0) entityDropItem(this.getHeldItemMainhand());
	}
}