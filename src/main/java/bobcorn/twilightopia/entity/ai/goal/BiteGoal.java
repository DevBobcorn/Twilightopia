package bobcorn.twilightopia.entity.ai.goal;

import java.util.EnumSet;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import bobcorn.twilightopia.entity.monster.MobCEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

public class BiteGoal extends Goal {
	private Random rand = new Random();

	protected final CreatureEntity attacker;
	protected int attackTick;
	private final double speedTowardsTarget;
	private final boolean longMemory;
	private Path path;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected final int attackInterval = 20;
	private long field_220720_k;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;

	public BiteGoal(CreatureEntity creature, double speedIn, boolean useLongMemory) {
		this.attacker = creature;
		this.speedTowardsTarget = speedIn;
		this.longMemory = useLongMemory;
		this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	public boolean shouldExecute() {
		long i = this.attacker.world.getGameTime();
		if (i - this.field_220720_k < 20L) {
			return false;
		} else {
			this.field_220720_k = i;
			LivingEntity livingentity = this.attacker.getAttackTarget();
			if (livingentity == null) {
				return false;
			} else if (!livingentity.isAlive()) {
				return false;
			} else {
				if (canPenalize) {
					if (--this.delayCounter <= 0) {
						this.path = this.attacker.getNavigator().getPathToEntityLiving(livingentity, 0);
						this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
						return this.path != null;
					} else {
						return true;
					}
				}
				this.path = this.attacker.getNavigator().getPathToEntityLiving(livingentity, 0);
				if (this.path != null) {
					return true;
				} else {
					return this.getAttackReachSqr(livingentity) >= this.attacker.getDistanceSq(livingentity.posX,
							livingentity.getBoundingBox().minY, livingentity.posZ);
				}
			}
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	public boolean shouldContinueExecuting() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (livingentity == null) {
			return false;
		} else if (!livingentity.isAlive()) {
			return false;
		} else if (!this.longMemory) {
			return !this.attacker.getNavigator().noPath();
		} else if (!this.attacker.isWithinHomeDistanceFromPosition(new BlockPos(livingentity))) {
			return false;
		} else {
			return !(livingentity instanceof PlayerEntity)
					|| !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
		}
	}

	/**
	 * Execute a one shot task or start executing a continuous task
	 */
	public void startExecuting() {
		this.attacker.getNavigator().setPath(this.path, this.speedTowardsTarget);
		this.attacker.setAggroed(true);
		this.delayCounter = 0;
	}

	/**
	 * Reset the task's internal state. Called when this task is interrupted by
	 * another one
	 */
	public void resetTask() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		if (!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.attacker.setAttackTarget((LivingEntity) null);
		}
		if (this.attacker instanceof MobCEntity) {
			((MobCEntity) this.attacker).setMouthRot(0F);
		}
		this.attacker.setAggroed(false);
		this.attacker.getNavigator().clearPath();
		if (this.attacker instanceof MobCEntity)
			((MobCEntity) this.attacker).setMouthRot(0.0F);
	}

	/**
	 * Keep ticking a continuous task that has already been started
	 */
	public void tick() {
		LivingEntity livingentity = this.attacker.getAttackTarget();
		this.attacker.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
		double d0 = this.attacker.getDistanceSq(livingentity.posX, livingentity.getBoundingBox().minY,
				livingentity.posZ);
		--this.delayCounter;
		if ((this.longMemory || this.attacker.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0
				&& (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D
						|| livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D
						|| this.attacker.getRNG().nextFloat() < 0.05F)) {
			this.targetX = livingentity.posX;
			this.targetY = livingentity.getBoundingBox().minY;
			this.targetZ = livingentity.posZ;
			this.delayCounter = 4 + this.attacker.getRNG().nextInt(7);
			if (this.canPenalize) {
				this.delayCounter += failedPathFindingPenalty;
				if (this.attacker.getNavigator().getPath() != null) {
					net.minecraft.pathfinding.PathPoint finalPathPoint = this.attacker.getNavigator().getPath()
							.getFinalPathPoint();
					if (finalPathPoint != null
							&& livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
						failedPathFindingPenalty = 0;
					else
						failedPathFindingPenalty += 10;
				} else {
					failedPathFindingPenalty += 10;
				}
			}
			if (d0 > 1024.0D) {
				this.delayCounter += 10;
			} else if (d0 > 256.0D) {
				this.delayCounter += 5;
			}

			if (!this.attacker.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
				this.delayCounter += 15;
			}
		}

		this.attackTick = Math.max(this.attackTick - 1, 0);
		this.checkAndPerformAttack(livingentity, d0);
		if (this.attacker instanceof MobCEntity) {
			((MobCEntity) this.attacker).setMouthRot(attackTick * 1.1F);
		}
	}

	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			this.attackTick = 20;
			this.attacker.swingArm(Hand.MAIN_HAND);
			this.attacker.attackEntityAsMob(enemy);
			if (rand.nextInt(5) == 0 && enemy.getHeldItem(Hand.MAIN_HAND).isEnchanted()) {
				enemy.setItemStackToSlot(EquipmentSlotType.MAINHAND, removeEnchantment(enemy.getHeldItem(Hand.MAIN_HAND), enemy.getHeldItem(Hand.MAIN_HAND).getDamage()));
			}
			if (rand.nextInt(10) == 0 && enemy.getHeldItem(Hand.OFF_HAND).isEnchanted()) {
				enemy.setItemStackToSlot(EquipmentSlotType.OFFHAND, removeEnchantment(enemy.getHeldItem(Hand.OFF_HAND), enemy.getHeldItem(Hand.OFF_HAND).getDamage()));
			}
		}
	}

	private ItemStack removeEnchantment(ItemStack item, int damage) {
		ItemStack itemstack = item.copy();
		itemstack.removeChildTag("Enchantments");
		itemstack.removeChildTag("StoredEnchantments");
		if (damage > 0) {
			itemstack.setDamage(damage);
		} else {
			itemstack.removeChildTag("Damage");
		}
		Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(item).entrySet().stream()
				.filter((p_217012_0_) -> {
					return p_217012_0_.getKey().isCurse();
				}).collect(Collectors.toMap(Entry::getKey, Entry::getValue));
		EnchantmentHelper.setEnchantments(map, itemstack);
		itemstack.setRepairCost(0);
		if (itemstack.getItem() == Items.ENCHANTED_BOOK && map.size() == 0) {
			itemstack = new ItemStack(Items.BOOK);
			if (item.hasDisplayName()) {
				itemstack.setDisplayName(item.getDisplayName());
			}
		}

		return itemstack;
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return (double) (this.attacker.getWidth() * 2.0F * this.attacker.getWidth() * 2.0F + attackTarget.getWidth());
	}
}