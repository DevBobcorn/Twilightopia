package bobcorn.twilightopia.entity.passive;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import bobcorn.twilightopia.ModUtil;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.entity.ModEntityTypes;
import bobcorn.twilightopia.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class RainbullEntity extends AnimalEntity {
	private static final DataParameter<Integer> RAINBULL_TYPE = EntityDataManager.createKey(RainbullEntity.class,
			DataSerializers.VARINT);

	public RainbullEntity(EntityType<? extends RainbullEntity> type, World worldIn) {
		super(type, worldIn);
	}

	public static final Map<Integer, ResourceLocation> textures = Util.make(Maps.newHashMap(), (texmap) -> {
		texmap.put(0, new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/rainbull/rainbull.png"));
		texmap.put(1, new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/rainbull/purple.png"));
		texmap.put(2, new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/rainbull/tangerine.png"));
	});
	
	public static final Map<Item, Integer> dyes = Util.make(Maps.newHashMap(), (dyemap) -> {
		dyemap.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "rainbush")), 0);
		dyemap.put(Items.PURPLE_DYE, 1);
		dyemap.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "fuchsia_rose")), 1);
		dyemap.put(Items.ORANGE_DYE, 2);
		dyemap.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "tangerine_rose")), 2);
	});

	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(ModItems.candy_cane.get()), false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}

	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue((double) 0.2F);
	}

	public ResourceLocation getBullTypeTex() {
		return textures.get(this.getBullType());
	}

	public int getBullType() {
		return this.dataManager.get(RAINBULL_TYPE);
	}

	public void setBullType(int Type) {
		if (Type < 0 || Type >= textures.size()) {
			Type = this.rand.nextInt(textures.size());
		}

		this.dataManager.set(RAINBULL_TYPE, Type);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(RAINBULL_TYPE, 0);
	}

	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("BullType", this.getBullType());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setBullType(compound.getInt("BullType"));
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.setBullType(this.rand.nextInt(textures.size()));
		return spawnDataIn;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_COW_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_COW_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_COW_DEATH;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_COW_STEP, 0.15F, 1.0F);
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume() {
		return 0.4F;
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it
	 * (wheat, carrots or seeds depending on the animal type)
	 */
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return stack.getItem() == ModItems.candy_cane.get();
	}

	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (dyes.containsKey(itemstack.getItem())) {
			player.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0F, 1.0F);
			if (!player.isCreative()) itemstack.shrink(1);
			this.setBullType(dyes.get(itemstack.getItem()));
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + looting);
		int var4;

		for (var4 = 0; var4 < var3; ++var4) {
			this.entityDropItem(Items.LEATHER);
		}

		var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + looting);

		for (var4 = 0; var4 < var3; ++var4) {
			if (this.isBurning()) {
				this.entityDropItem(Items.COOKED_BEEF, 1);
			} else {
				this.entityDropItem(Items.BEEF, 1);
			}
		}
		
		if (dyes.containsValue(this.getBullType())) {
			Set<Item> possible = ModUtil.getKeysByLoop(dyes, this.getBullType());
			if (possible.size() > 0)
				this.entityDropItem((Item)(possible.toArray()[this.rand.nextInt(possible.size())]));
		}
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? sizeIn.height * 0.95F : 1.3F;
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return new RainbullEntity(ModEntityTypes.RAINBULL.get(), world);
	}
}