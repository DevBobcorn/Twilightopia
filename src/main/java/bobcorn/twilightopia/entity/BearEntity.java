package bobcorn.twilightopia.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.world.World;

public class BearEntity extends PolarBearEntity {
	public BearEntity(EntityType<? extends BearEntity> type, World worldIn) {
		super(type, worldIn);
	}
}
