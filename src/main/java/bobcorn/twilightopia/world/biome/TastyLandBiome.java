package bobcorn.twilightopia.world.biome;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.world.gen.TwilightopiaSurfaceBuilder;
import bobcorn.twilightopia.world.gen.feature.ModFeature;
import bobcorn.twilightopia.world.gen.feature.TastyOreFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
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

public final class TastyLandBiome extends Biome {
	public TastyLandBiome() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, TwilightopiaSurfaceBuilder.GRASSY_CHOCO_CONFIG)
				.precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.125F).scale(0.05F)
				.temperature(0.8F).downfall(0.4F).waterColor(0xFE72F3).waterFogColor(0xF4AAFF).parent((String) null));
		DefaultBiomeFeatures.addCarvers(this);
		// DefaultBiomeFeatures.addLakes(this); We don't want LAVA ones...
		this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE,
				new LakesConfig(Blocks.WATER.getDefaultState()), Placement.WATER_LAKE, new LakeChanceConfig(7)));
		// DefaultBiomeFeatures.addSprings(this);
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(Feature.SPRING_FEATURE, new LiquidsConfig(Fluids.WATER.getDefaultState()),
						Placement.COUNT_BIASED_RANGE, new CountRangeConfig(50, 8, 8, 256)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.CHERRY_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(1, 0.8F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.IGNITE_TREE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				createDecoratedFeature(ModFeature.HUGE_CANDY_CANE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				createDecoratedFeature(ModFeature.TORII, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.02F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				createDecoratedFeature(ModFeature.PAGODA, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.015F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				createDecoratedFeature(ModFeature.TEMPLE, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.01F, 1)));
		this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
				createDecoratedFeature(ModFeature.OCEAN_LAMP, IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.02F, 1)));
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
				Biome.createDecoratedFeature(Feature.GRASS,
						new GrassFeatureConfig(ModBlocks.VELVET_GRASS.getDefaultState()),
						Placement.NOISE_HEIGHTMAP_DOUBLE, new NoiseDependant(-0.8D, 5, 10)));

		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.RAINBULL, 2, 2, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.DEER, 5, 2, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.CHOX, 2, 4, 6));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.RAT, 5, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.OCELOT, 2, 4, 8));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
	}

	/**
	 * returns the chance a creature has to spawn.
	 */
	public float getSpawningChance() {
		return 0.09F;
	}
}