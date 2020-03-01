package bobcorn.twilightopia.entity.monster;

import bobcorn.twilightopia.entity.ai.goal.BiteGoal;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class MobCEntity extends MonsterEntity {
	public MobCEntity(EntityType<? extends MobCEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	private static final DataParameter<Float> MOUTH_ROT = EntityDataManager.createKey(MobCEntity.class, DataSerializers.FLOAT);
	
	public float getMouthRot() {
		return this.dataManager.get(MOUTH_ROT);
	}

	public void setMouthRot(float rot) {
		this.dataManager.set(MOUTH_ROT, rot);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(MOUTH_ROT, 0.0F);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putFloat("MouthRot", this.getMouthRot());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setMouthRot(compound.getFloat("MouthRot"));
	}
	
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, OcelotEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, CatEntity.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(2, new BiteGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
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
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
		this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
	}

	public boolean attackEntityFrom(DamageSource source, float amount) {
		return this.isInvulnerableTo(source) ? false : super.attackEntityFrom(source, amount);
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.3F;
	}
	
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + looting);
		int var4;

		for (var4 = 0; var4 < var3; ++var4) {
			this.entityDropItem(Items.GOLD_NUGGET);
		}
	}
}