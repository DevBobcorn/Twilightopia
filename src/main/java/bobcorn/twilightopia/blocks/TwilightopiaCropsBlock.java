package bobcorn.twilightopia.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class TwilightopiaCropsBlock extends CropsBlock {
	public TwilightopiaCropsBlock(Properties builder) {
		super(builder);
	}

	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return (state.getBlock() == Blocks.FARMLAND || state.getBlock() == ModBlocks.CHOCOLATE_BLOCK.get() || state.getBlock() == ModBlocks.WHITE_CHOCOLATE_BLOCK.get());
	}
}
