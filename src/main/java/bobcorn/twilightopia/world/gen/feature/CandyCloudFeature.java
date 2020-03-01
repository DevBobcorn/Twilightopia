package bobcorn.twilightopia.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CandyCloudFeature extends Feature<NoFeatureConfig> {
	private static final BlockState[] CANDIES = { ModBlocks.CANDY_BLOCK.getDefaultState(),
			ModBlocks.RED_CANDY_BLOCK.getDefaultState(), ModBlocks.ORANGE_CANDY_BLOCK.getDefaultState(),
			ModBlocks.YELLOW_CANDY_BLOCK.getDefaultState(), ModBlocks.PINK_CANDY_BLOCK.getDefaultState(),
			ModBlocks.PURPLE_CANDY_BLOCK.getDefaultState() };

	public CandyCloudFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_) {
		super(p_i49876_1_);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) {
		BlockState blockstate = CANDIES[rand.nextInt(CANDIES.length)];
		int i = 0;

		for (int j = 0; j < 64; ++j) {
			BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4),
					rand.nextInt(8) - rand.nextInt(8));
			if (worldIn.isAirBlock(blockpos) && blockpos.getY() < 255) {
				worldIn.setBlockState(blockpos, blockstate, 2);
				++i;
			}
		}

		return i > 0;
	}
}
