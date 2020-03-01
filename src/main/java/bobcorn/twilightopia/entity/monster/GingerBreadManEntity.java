package bobcorn.twilightopia.entity.monster;

import bobcorn.twilightopia.items.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class GingerBreadManEntity extends ZombieEntity {

	public GingerBreadManEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_HUSK_AMBIENT;
	}

	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_HUSK_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_HUSK_DEATH;
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		//To avoid setting fire
		if (this.func_223314_ad() > 0) this.extinguish();
	}
	
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int var3 = this.rand.nextInt(3) + this.rand.nextInt(1 + looting);
		int var4;

		for (var4 = 0; var4 < var3; ++var4) {
			this.entityDropItem(ModItems.ginger_bread);
		}
	}
}
