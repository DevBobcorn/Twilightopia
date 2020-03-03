package bobcorn.twilightopia.world.gen.feature;

import bobcorn.twilightopia.world.gen.feature.structure.LargeMazeStructure;
import bobcorn.twilightopia.world.gen.feature.structure.MazeConfig;
import bobcorn.twilightopia.world.gen.feature.structure.MazeStructure;
import bobcorn.twilightopia.world.gen.feature.structure.PagodaStructure;
import bobcorn.twilightopia.world.gen.feature.structure.TempleStructure;
import bobcorn.twilightopia.world.gen.feature.structure.ToriiStruture;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ModFeature {
	public static final Feature<NoFeatureConfig> HUGE_CANDY_CANE = new CandyCaneFeature(NoFeatureConfig::deserialize, false, true);
	public static final Feature<NoFeatureConfig> CHERRY_TREE = new CherryTreeFeature(NoFeatureConfig::deserialize, false, true);
	public static final Feature<NoFeatureConfig> IGNITE_TREE = new IgniteTreeFeature(NoFeatureConfig::deserialize, false);
	public static final Feature<NoFeatureConfig> PROPHET_TREE = new ProphetTreeFeature(NoFeatureConfig::deserialize, false, true);
	public static final Feature<NoFeatureConfig> MISTY_BIRCH_TREE = new MistyBirchTreeFeature(NoFeatureConfig::deserialize, false, true);
	public static final Feature<NoFeatureConfig> MISTY_TAIGA_TREE = new MistyTaigaTreeFeature(NoFeatureConfig::deserialize, false);
	public static final Feature<TastyOreFeatureConfig> TASTY_ORE = new TastyOreFeature(TastyOreFeatureConfig::deserialize);
	public static final TwilitFlowersFeature TWILIT_FLOWERS = new TwilitFlowersFeature(NoFeatureConfig::deserialize);
	public static final TwilitMushroomFeature TWILIT_MUSHROOM = new TwilitMushroomFeature(NoFeatureConfig::deserialize);
	public static final MazeStructure MAZE = new MazeStructure(MazeConfig::deserialize);
	public static final LargeMazeStructure MAZE_LARGE = new LargeMazeStructure(MazeConfig::deserialize);
	public static final ToriiStruture TORII = new ToriiStruture(NoFeatureConfig::deserialize);
	public static final PagodaStructure PAGODA = new PagodaStructure(NoFeatureConfig::deserialize);
	public static final TempleStructure TEMPLE = new TempleStructure(NoFeatureConfig::deserialize);
	public static final OceanLampFeature OCEAN_LAMP = new OceanLampFeature(NoFeatureConfig::deserialize);
}
