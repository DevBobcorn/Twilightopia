package bobcorn.twilightopia.entity.passive;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

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

public class DeerEntity extends AnimalEntity {
   private static final DataParameter<Integer> DEER_TYPE = EntityDataManager.createKey(DeerEntity.class, DataSerializers.VARINT);
	
   public DeerEntity(EntityType<? extends DeerEntity> type, World worldIn) {
      super(type, worldIn);
   }

   public static final Map<Integer, ResourceLocation> textures = Util.make(Maps.newHashMap(), (texmap) -> {
	      texmap.put(0, new ResourceLocation(TwilightopiaMod.MODID,"textures/entity/deer/elk.png"));
	      texmap.put(1, new ResourceLocation(TwilightopiaMod.MODID,"textures/entity/deer/deer.png"));
	   });
   
   protected void registerGoals() {
      this.goalSelector.addGoal(0, new SwimGoal(this));
      this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
      this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
      this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.fromItems(Items.CARROT), false));
      this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
      this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
   }

   public ResourceLocation getElkTypeTex() {
	      return textures.get(this.getDeerType());
	   }
   
   public int getDeerType() {
	      return this.dataManager.get(DEER_TYPE);
	   }

   public void setDeerType(int deerType) {
      if (deerType < 0 || deerType >= 2) {
         deerType = this.rand.nextInt(2);
      }

      this.dataManager.set(DEER_TYPE, deerType);
   }
   
   protected void registerData() {
	  super.registerData();
	  this.dataManager.register(DEER_TYPE, 0);
   }
   
   public void writeAdditional(CompoundNBT compound) {
	  super.writeAdditional(compound);
	  compound.putInt("DeerType", this.getDeerType());
   }

   /**
    * (abstract) Protected helper method to read subclass entity data from NBT.
    */
   public void readAdditional(CompoundNBT compound) {
      super.readAdditional(compound);
      this.setDeerType(compound.getInt("DeerType"));
   }
   
   @Nullable
   public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
      spawnDataIn = super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
      /*
      if (worldIn.func_225523_d_().func_226836_a_(new BlockPos(this)) == TwilightopiaBiomes.TASTY_TUNDRA) {
         this.setDeerType(0);
      } else if (worldIn.func_225523_d_().func_226836_a_(new BlockPos(this)) == TwilightopiaBiomes.TASTY_LAND) {
          this.setDeerType(1);
      } else this.setDeerType(this.rand.nextInt(2));
	  */
      //TODO Write Biomes
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
    * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
    * the animal type)
    */
   @Override
   public boolean isBreedingItem(ItemStack stack) {
      return stack.getItem() == Items.CARROT;
   }
   
   public boolean processInteract(PlayerEntity player, Hand hand) {
	   return super.processInteract(player, hand);
   }

   @Override
   protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn)
   {
       int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + looting);
       int var4;

       for (var4 = 0; var4 < var3; ++var4)
       {
           this.entityDropItem(Items.LEATHER);
       }

       var3 = this.rand.nextInt(3) + 1 + this.rand.nextInt(1 + looting);

       for (var4 = 0; var4 < var3; ++var4)
       {
           if (this.isBurning())
           {
               this.entityDropItem(ModItems.cooked_venison.get(), 1);
           }
           else
           {
               this.entityDropItem(ModItems.venison.get(), 1);
           }
       }
   }
   
   protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
      return this.isChild() ? sizeIn.height * 0.95F : 1.3F;
   }
   
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		DeerEntity elkentity = new DeerEntity(ModEntityTypes.DEER.get(), world);
		if (((DeerEntity)ageable).getDeerType() == 0 && this.getDeerType() == 0)
			elkentity.setDeerType(0);
		else if (((DeerEntity)ageable).getDeerType() == 1 && this.getDeerType() == 1)
			elkentity.setDeerType(1);
		else elkentity.setDeerType(this.rand.nextInt(2));
		return elkentity;
	}
}

/*
 	command:
	/data modify entity @e[type=twilightopia:deer,limit=1] DeerType set value 1
*/


