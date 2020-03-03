package bobcorn.twilightopia.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.blocks.TwilightopiaSoilHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class OceanLampFeature extends Feature<NoFeatureConfig> {

	public OceanLampFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, NoFeatureConfig config) {
		if (TwilightopiaSoilHelper.isSoilOrChoco((IBlockReader)worldIn, pos.down(1)))
			return false;
		int i = rand.nextInt(10);
		if (i == 0) {
			worldIn.setBlockState(pos, ModBlocks.POLISHED_GRANITE_PEDESTAL.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(1), ModBlocks.OCEAN_LAMP.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(2), ModBlocks.POLISHED_GRANITE_ROOF.getDefaultState(), 2);
		} else if (i < 6) {
			worldIn.setBlockState(pos, ModBlocks.POLISHED_ANDESITE_PEDESTAL.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(1), ModBlocks.OCEAN_LAMP.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(2), ModBlocks.POLISHED_ANDESITE_ROOF.getDefaultState(), 2);
		} else {
			worldIn.setBlockState(pos, ModBlocks.POLISHED_DIORITE_PEDESTAL.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(1), ModBlocks.OCEAN_LAMP.getDefaultState(), 2);
			worldIn.setBlockState(pos.up(2), ModBlocks.POLISHED_DIORITE_ROOF.getDefaultState(), 2);
		}
		worldIn.setBlockState(pos.down(1), ModBlocks.CHOCOLATE_BLOCK.getDefaultState(), 2);
		return false;
	}

}
