package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TwilightopiaBushBlock extends BushBlock {
	protected TwilightopiaBushBlock(Block.Properties properties) {
		super(properties);
	}

	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return TwilightopiaSoilHelper.isSoilOrChoco(worldIn, pos);
	}
}
