package bobcorn.twilightopia.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TwilitFlowersFeature extends FlowersFeature {
	private static final BlockState[] FLOWERS = { 
			ModBlocks.TANGERINE_ROSE.getDefaultState(),
			ModBlocks.ROSE.getDefaultState(),
			ModBlocks.WHITE_ROSE.getDefaultState(),
			ModBlocks.TANGERINE_ROSE.getDefaultState(),
			ModBlocks.FUCHSIA_ROSE.getDefaultState(),
			ModBlocks.BLUE_ROSE.getDefaultState(),
			ModBlocks.CANDELION.getDefaultState()
	};

	public TwilitFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_) {
		super(p_i49876_1_);
	}

	@Override
	public BlockState getRandomFlower(Random random, BlockPos pos) {
		if (random.nextInt(100) < 10)
			return ModBlocks.RAINBUSH.getDefaultState();
		return FLOWERS[random.nextInt(FLOWERS.length)];
	}
}
