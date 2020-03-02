package bobcorn.twilightopia.blocks;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.lighting.LightEngine;
import net.minecraft.world.server.ServerWorld;

public class GrassedChocolateBlock extends SpreadableSnowyDirtBlock implements IGrowable {
	public GrassedChocolateBlock(Block.Properties properties) {
		super(properties);
	}

	private static boolean func_220257_b(BlockState p_220257_0_, IWorldReader p_220257_1_, BlockPos p_220257_2_) {
		BlockPos blockpos = p_220257_2_.up();
		BlockState blockstate = p_220257_1_.getBlockState(blockpos);
		if (blockstate.getBlock() == Blocks.SNOW && blockstate.get(SnowBlock.LAYERS) == 1) {
			return true;
		} else {
			int i = LightEngine.func_215613_a(p_220257_1_, p_220257_0_, p_220257_2_, blockstate, blockpos, Direction.UP,
					blockstate.getOpacity(p_220257_1_, blockpos));
			return i < p_220257_1_.getMaxLightLevel();
		}
	}

	private static boolean func_220256_c(BlockState p_220256_0_, IWorldReader p_220256_1_, BlockPos p_220256_2_) {
		BlockPos blockpos = p_220256_2_.up();
		return func_220257_b(p_220256_0_, p_220256_1_, p_220256_2_)
				&& !p_220256_1_.getFluidState(blockpos).isTagged(FluidTags.WATER);
	}

	@Override
	public void func_225534_a_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isRemote) {
			if (!worldIn.isAreaLoaded(pos, 3))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light and
						// spreading
			if (!func_220257_b(state, worldIn, pos)) {
				if (state.getBlock() == ModBlocks.GRASSED_CHOCOLATE_BLOCK.get())
					worldIn.setBlockState(pos, ModBlocks.CHOCOLATE_BLOCK.get().getDefaultState());
				else
					worldIn.setBlockState(pos, ModBlocks.WHITE_CHOCOLATE_BLOCK.get().getDefaultState());
			} else {
				if (worldIn.getLight(pos.up()) >= 9) {
					BlockState blockstate = this.getDefaultState();
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3,
								random.nextInt(3) - 1);
						Block spreadTarget = (state.getBlock() == ModBlocks.GRASSED_CHOCOLATE_BLOCK.get())
								? ModBlocks.CHOCOLATE_BLOCK.get()
								: ModBlocks.WHITE_CHOCOLATE_BLOCK.get();
						if (worldIn.getBlockState(blockpos).getBlock() == spreadTarget
								&& func_220256_c(blockstate, worldIn, blockpos)) {
							worldIn.setBlockState(blockpos, blockstate.with(SNOWY,
									Boolean.valueOf(worldIn.getBlockState(blockpos.up()).getBlock() == Blocks.SNOW)));
						}
					}
				}

			}
		}
	}

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	@Override
	public void func_225535_a_(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_,
			BlockState p_225535_4_) {
		// TODO Grow Grass		
	}
}