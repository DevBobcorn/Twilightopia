package bobcorn.twilightopia.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.PooledMutable;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

@SuppressWarnings("deprecation")
public class TwilightopiaLeavesBlock extends LeavesBlock implements net.minecraftforge.common.IShearable {
	//The AABBs are modified slightly to prevent Minecraft's automatic face culling
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.01D, 0.01D, 0.01D, 15.99D, 15.99D, 15.99D);

	public static final IntegerProperty DISTANCE = BlockStateProperties.DISTANCE_1_7;
	public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
	protected static boolean renderTranslucent;

	public TwilightopiaLeavesBlock(Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(DISTANCE, Integer.valueOf(1)).with(PERSISTENT,
				Boolean.valueOf(false)));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	/**
	 * Returns whether or not this block is of a type that needs random ticking.
	 * Called for ref-counting purposes by ExtendedBlockStorage in order to broadly
	 * cull a chunk from the random chunk update list for efficiency's sake.
	 */
	@Override
	public boolean ticksRandomly(BlockState state) {
		return state.get(DISTANCE) == 7 && !state.get(PERSISTENT);
	}

	// random tick
	@Override
	public void func_225542_b_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!state.get(PERSISTENT) && state.get(DISTANCE) == 7) {
			spawnDrops(state, worldIn, pos);
			worldIn.removeBlock(pos, false);
		}
	}

	// tick
	@Override
	public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
	}

	private static BlockState updateDistance(BlockState p_208493_0_, IWorld p_208493_1_, BlockPos p_208493_2_) {
		int i = 7;

		try (PooledMutable blockpos$pooledmutableblockpos = BlockPos.PooledMutable.retain()) {
			for (Direction direction : Direction.values()) {
				blockpos$pooledmutableblockpos.setPos(p_208493_2_).move(direction);
				i = Math.min(i, getDistance(p_208493_1_.getBlockState(blockpos$pooledmutableblockpos)) + 1);
				if (i == 1) {
					break;
				}
			}
		}

		return p_208493_0_.with(DISTANCE, Integer.valueOf(i));
	}

	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn,
			BlockPos currentPos, BlockPos facingPos) {
		int i = getDistance(facingState) + 1;
		if (i != 1 || stateIn.get(DISTANCE) != i) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}
		return stateIn;
	}

	private static int getDistance(BlockState neighbor) {
		if (BlockTags.LOGS.contains(neighbor.getBlock()) || (neighbor.getBlock() instanceof LogBlock)) {
			return 0;
		} else {
			return (neighbor.getBlock() instanceof LeavesBlock
					|| neighbor.getBlock() instanceof TwilightopiaLeavesBlock) ? neighbor.get(DISTANCE) : 7;
		}
	}
}