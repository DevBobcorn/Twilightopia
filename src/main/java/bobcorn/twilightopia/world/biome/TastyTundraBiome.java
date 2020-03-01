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
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;

public final class TastyTundraBiome extends Biome {
	public TastyTundraBiome() {
		super((new Biome.Builder())
				.surfaceBuilder(SurfaceBuilder.DEFAULT, TwilightopiaSurfaceBuilder.GRASSY_WHITE_CHOCO_CONFIG)
				.precipitation(Biome.RainType.SNOW).category(Biome.Category.ICY).depth(0.125F).scale(0.05F)
				.temperature(0.0F).downfall(0.5F).waterColor(4159204).waterFogColor(329011).parent((String) null));

		this.addStructure(Feature.IGLOO, IFeatureConfig.NO_FEATURE_CONFIG);
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
				createDecoratedFeature(ModFeature.HUGE_CANDY_CANE,
						IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP,
						new AtSurfaceWithExtraConfig(0, 0.2F, 1)));
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
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.DEER, 5, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.CHOX, 5, 4, 4));
		this.addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(ModEntityType.SANTA_CLAUS, 1, 1, 1));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(ModEntityType.SNOME, 6, 1, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 2, 1, 1));
	}

	/**
	 * returns the chance a creature has to spawn.
	 */
	public float getSpawningChance() {
		return 0.08F;
	}
}