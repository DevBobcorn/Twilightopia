package bobcorn.twilightopia.entity.monster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class JelimeEntity extends SlimeEntity {
	public JelimeEntity(EntityType<? extends SlimeEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected SoundEvent getJumpSound() {
		return SoundEvents.ENTITY_SLIME_JUMP;
	}
	
	@Override
	protected SoundEvent getSquishSound() {
		return SoundEvents.ENTITY_SLIME_SQUISH;
	}
}
