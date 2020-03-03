package bobcorn.twilightopia.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TwilitMushroomFeature extends FlowersFeature {
	private static final BlockState[] MUSHRROM = { 
			ModBlocks.GLOWING_FUNGI.getDefaultState(),
			ModBlocks.ILLUSHROOM.getDefaultState()
	};

	public TwilitMushroomFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i49876_1_) {
		super(p_i49876_1_);
	}

	@Override
	public BlockState getRandomFlower(Random random, BlockPos pos) {
		return MUSHRROM[random.nextInt(MUSHRROM.length)];
	}
}
