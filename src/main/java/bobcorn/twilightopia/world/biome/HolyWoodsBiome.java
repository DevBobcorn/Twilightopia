package bobcorn.twilightopia.world.biome;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.colors.TwilitColors;
import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.world.gen.TwilightopiaSurfaceBuilder;
import bobcorn.twilightopia.world.gen.feature.ModFeature;
import bobcorn.twilightopia.world.gen.feature.TastyOreFeatureConfig;
import bobcorn.twilightopia.world.gen.feature.structure.MazeConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GrassFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public final class HolyWoodsBiome extends Biome {
	public HolyWoodsBiome() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, TwilightopiaSurfaceBuilder.GRASSY_WHITE_CHOCO_CONFIG)
				.precipitation(Biome.RainType.RAIN).category(Biome.Category.FOREST).depth(0.125F).scale(0.05F)
				.temperature(0.6F).downfall(0.5F).waterColor(TwilitColors.holy2).waterFogColor(TwilitColors.holy1).parent((String) null));

		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addStructures(this);
		// DefaultBiomeFeatures.addLakes(this); We don't want LAVA ones...
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE,
				new LakesConfig(Blocks.WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(7)));
		// DefaultBiomeFeatures.addSprings(this);
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()),
						Placement.COUNT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.MISTY_BIRCH_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.8F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.MISTY_TAIGA_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.5F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.IGNITE_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.PROPHET_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.05F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.PROPHET_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				createDecoratedFeature(ModFeature.MAZE_LARGE,
						new MazeConfig(14, 5, Blocks.STONE_BRICKS.getDefaultState(),
						ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.getDefaultState(),
						ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.getDefaultState()),
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.01F, 1)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Biome.createDecoratedFeature(ModFeature.TASTY_ORE,
						new TastyOreFeatureConfig(TastyOreFeatureConfig.FillerBlockType.CHEESE,
								ModBlocks.RUBY_ORE.getDefaultState(), 1),
						Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 24)));
		this.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Biome.createDecoratedFeature(ModFeature.TASTY_ORE,
						new TastyOreFeatureConfig(TastyOreFeatureConfig.FillerBlockType.CHEESE,
								ModBlocks.INFESTED_CHEESE_BLOCK.getDefaultState(), 17),
						Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(ModFeature.TWILIT_FLOWERS, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(ModBlocks.MISTY_GRASS.getDefaultState()),
						Placement.NOISE_HEIGHTMAP_DOUBLE, new NoiseDependant(-0.8D, 5, 10)));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.DEER, 7, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.BEAR, 8, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.CHOX, 10, 1, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.RAT, 10, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.MOB_C, 8, 4, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 6, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(ModEntityType.NEONLIME, 6, 1, 4));
	}

	/**
	 * returns the chance a creature has to spawn.
	 */
	public float getSpawningChance() {
		return 0.09F;
	}
}