package bobcorn.twilightopia.blocks;

import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SnomeBlock extends SlimeBlock {
	public SnomeBlock(Properties properties) {
		super(properties);
	}

	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);
		if (entityIn.isBurning()) entityIn.extinguish();
	}
}
