package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class RoofBlock extends Block {
	protected static final VoxelShape SHAPE1 = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D);
	protected static final VoxelShape SHAPE2 = Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 8.0D, 12.0D);

	public RoofBlock(Properties properties) {
		super(properties);
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.or(SHAPE1, SHAPE2);
	}
}
