package bobcorn.twilightopia.entity.boss;

import javax.annotation.Nullable;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.ServerBossInfo;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class GingerBreadBossEntity extends GiantEntity implements IRangedAttackMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo) (new ServerBossInfo(this.getDisplayName(),
			BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);

	public GingerBreadBossEntity(EntityType<? extends GingerBreadBossEntity> typeIn, World worldIn) {
		super(typeIn, worldIn);
	}

	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_WITHER_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_WITHER_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_WITHER_DEATH;
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.8F);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
	}

	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (this.hasCustomName()) {
			this.bossInfo.setName(this.getDisplayName());
		}
	}

	public void updateAITasks() {
		super.updateAITasks();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);
		this.bossInfo.setName(this.getDisplayName());
	}

	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

	}

	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		this.entityDropItem(ModItems.crown);
		this.entityDropItem(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "tuliportal")), 6);
		for (int i = 100;i <= 2000;i += 100) {
			this.dropExperience(i);
		}
	}

	private void dropExperience(int amount) {
		while (amount > 0) {
			int i = ExperienceOrbEntity.getXPSplit(amount);
			amount -= i;
			this.world.addEntity(new ExperienceOrbEntity(this.world, this.posX - 10.0D + this.rand.nextDouble() * 20.0D, this.posY, this.posZ - 10.0D + this.rand.nextDouble() * 20.0D, i));
		}
	}
}