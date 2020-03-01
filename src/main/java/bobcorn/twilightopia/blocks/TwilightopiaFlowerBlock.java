package bobcorn.twilightopia.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TwilightopiaFlowerBlock extends FlowerBlock {
	public TwilightopiaFlowerBlock(Effect p_i49984_1_, int effectDuration, Properties p_i49984_3_) {
		super(p_i49984_1_, effectDuration, p_i49984_3_);
	}

	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return TwilightopiaSoilHelper.isSoilOrChoco(worldIn, pos);
	}
}
