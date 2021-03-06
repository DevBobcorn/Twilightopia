package bobcorn.twilightopia.blocks;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.effects.ModEffects;
import bobcorn.twilightopia.trees.CherryTree;
import bobcorn.twilightopia.trees.IgniteTree;
import bobcorn.twilightopia.trees.ProphetTree;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.potion.Effects;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TwilightopiaMod.MODID)
public final class ModBlocks {
	static public final Block CHOCOLATE_BLOCK = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block GRASSED_CHOCOLATE_BLOCK = new GrassedChocolateBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE).tickRandomly());
	static public final Block WHITE_CHOCOLATE_BLOCK = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block GRASSED_WHITE_CHOCOLATE_BLOCK = new GrassedChocolateBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE).tickRandomly());
	static public final Block MISTY_CHOCOLATE_BLOCK = new MistyChocolateBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE).tickRandomly());
	static public final Block VELVET_GRASS = new TwilightopiaBushBlock(Block.Properties.create(Material.TALL_PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT));
	static public final Block MISTY_GRASS = new TwilightopiaBushBlock(Block.Properties.create(Material.TALL_PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT));
	static public final Block RAINBUSH = new TwilightopiaBushBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.2F).doesNotBlockMovement().sound(SoundType.PLANT));
	static public final Block CHEESE_BLOCK = new Block(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F, 3.0F));
	static public final Block RUBY_ORE = new OrePlusBlock(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(3.0F, 3.0F));
	static public final Block INFESTED_CHEESE_BLOCK = new RatBlock(CHEESE_BLOCK, Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(0.0F, 0.9F));
	static public final Block PROPHET_LOG = new ProphetLogBlock(Block.Properties.create(Material.WOOD, MaterialColor.GRAY).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block BELL_LEAVES = new TwilightopiaLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).tickRandomly());
	static public final Block PROPHET_SAPLING = new TwilightopiaSaplingBlock(new ProphetTree(),Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block CHERRY_LOG = new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block STRIPPED_CHERRY_LOG = new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_WOOD = new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block STRIPPED_CHERRY_WOOD = new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_PLANKS = new Block(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_SIGN = new StandingSignPlusBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_WALL_SIGN = new WallSignPlusBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(CHERRY_SIGN));
	static public final Block CHERRY_DOOR = new TwilightopiaDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_TRAPDOOR = new TwilightopiaTrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_STAIRS = new TwilightopiaStairsBlock(CHERRY_PLANKS.getDefaultState(), Block.Properties.from(CHERRY_PLANKS));
	static public final Block CHERRY_SLAB = new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_BUTTON = new TwilightopiaButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));
	static public final Block CHERRY_FENCE_GATE = new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_FENCE = new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_PRESSURE_PLATE = new TwilightopiaPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.PINK_TERRACOTTA).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD));    
	static public final Block BLESSING_PLANKS = new BlessingPlanksBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD));
	static public final Block CHERRY_LEAVES = new TwilightopiaLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).tickRandomly());
	static public final Block PINK_CHERRY_LEAVES = new TwilightopiaLeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).sound(SoundType.PLANT).tickRandomly());
	static public final Block CHERRY_SAPLING = new TwilightopiaSaplingBlock(new CherryTree(),Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block IGNITE_LOG = new IgniteLogBlock(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(3.0F).sound(SoundType.WOOD).lightValue(12));
	static public final Block STRIPPED_IGNITE_LOG = new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block IGNITE_WOOD = new LogBlock(MaterialColor.WHITE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block STRIPPED_IGNITE_WOOD = new LogBlock(MaterialColor.ORANGE_TERRACOTTA, Block.Properties.create(Material.WOOD, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(2.0F).sound(SoundType.WOOD));
	static public final Block IGNITE_PLANKS = new Block(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).lightValue(13));
	static public final Block IGNITE_SIGN = new StandingSignPlusBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lightValue(13));
	static public final Block IGNITE_WALL_SIGN = new WallSignPlusBlock(Block.Properties.create(Material.WOOD).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD).lootFrom(IGNITE_SIGN).lightValue(13));
	static public final Block IGNITE_DOOR = new TwilightopiaDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(3.0F).sound(SoundType.WOOD).lightValue(13));
	static public final Block IGNITE_TRAPDOOR = new TwilightopiaTrapDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(3.0F).sound(SoundType.WOOD).lightValue(13));
	static public final Block IGNITE_STAIRS = new TwilightopiaStairsBlock(IGNITE_PLANKS.getDefaultState(), Block.Properties.from(IGNITE_PLANKS).lightValue(13));
	static public final Block IGNITE_SLAB = new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).lightValue(13));
	static public final Block IGNITE_BUTTON = new TwilightopiaButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD).lightValue(10));
	static public final Block IGNITE_FENCE_GATE = new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).lightValue(12));
	static public final Block IGNITE_FENCE = new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD).lightValue(12));
	static public final Block IGNITE_PRESSURE_PLATE = new TwilightopiaPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.YELLOW).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD).lightValue(11));
	static public final Block IGNITE_LEAVES = new TwilightopiaLeavesBlock(Block.Properties.create(Material.LEAVES, MaterialColor.YELLOW_TERRACOTTA).hardnessAndResistance(0.3F).sound(SoundType.PLANT).tickRandomly().lightValue(14));
	static public final Block IGNITE_SAPLING = new TwilightopiaSaplingBlock(new IgniteTree(),Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).doesNotBlockMovement().sound(SoundType.PLANT).lightValue(7).tickRandomly());
	static public final Block CHOCOLATE_CAKE = new TwilightopiaCakeBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH));
	static public final Block PIZZA = new PizzaBlock(Block.Properties.create(Material.CAKE).hardnessAndResistance(0.5F).sound(SoundType.CLOTH));
	static public final Block CANDY_CANE_CROP = new TwilightopiaCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
	static public final Block STRAWBERRY_CROP = new TwilightopiaCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
	static public final Block TOMATO_CROP = new TwilightopiaCropsBlock(Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0.0f).sound(SoundType.PLANT));
	public static final Block TULIPORTAL = new TuliportalBlock(Block.Properties.create(Material.PORTAL).doesNotBlockMovement().tickRandomly().hardnessAndResistance(-1.0F).sound(SoundType.GLASS).lightValue(11).noDrops());
	static public final Block PROJECTABLE = new ProjectableBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(10.0F).sound(SoundType.STONE));
	static public final Block RUBY_BLOCK = new Block(Block.Properties.create(Material.IRON, MaterialColor.PINK).hardnessAndResistance(7.0F, 8.0F).sound(SoundType.METAL));
	static public final Block CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block PINK_CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.PINK).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block PURPLE_CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.PURPLE).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block ORANGE_CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.ORANGE_TERRACOTTA).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block YELLOW_CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.YELLOW).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block RED_CANDY_BLOCK = new Block(Block.Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(3.0F, 3.0F).sound(SoundType.STONE));
	static public final Block ROSE = new TwilightopiaFlowerBlock(Effects.INSTANT_HEALTH, 6, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block TANGERINE_ROSE = new TwilightopiaFlowerBlock(Effects.GLOWING, 9, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block FUCHSIA_ROSE = new TwilightopiaFlowerBlock(Effects.LUCK, 6, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block BLUE_ROSE = new TwilightopiaFlowerBlock(ModEffects.FROZEN, 4, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block WHITE_ROSE = new TwilightopiaFlowerBlock(ModEffects.HEAVY_MIST, 5, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block CANDELION = new TwilightopiaFlowerBlock(ModEffects.HEAVY_MIST, 5, Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block GLOWING_FUNGI = new TwilightopiaMushroomBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block ILLUSHROOM = new TwilightopiaMushroomBlock(Block.Properties.create(Material.PLANTS).hardnessAndResistance(0.0F).doesNotBlockMovement().sound(SoundType.PLANT).tickRandomly());
	static public final Block POTTED_ROSE = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.ROSE; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_BLUE_ROSE = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.BLUE_ROSE; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_TANGERINE_ROSE = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.TANGERINE_ROSE; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_WHITE_ROSE = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.WHITE_ROSE; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_FUCHSIA_ROSE = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.FUCHSIA_ROSE; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_CHERRY_SAPLING = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.CHERRY_SAPLING; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_IGNITE_SAPLING = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.IGNITE_SAPLING; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_PROPHET_SAPLING = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.PROPHET_SAPLING; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_CANDELION = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.CANDELION; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_RAINBUSH = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.RAINBUSH; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_ILLUSHROOM = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.ILLUSHROOM; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block POTTED_GLOWING_FUNGI = new FlowerPotBlock(() -> { return (FlowerPotBlock)Blocks.FLOWER_POT; }, () -> { return ModBlocks.GLOWING_FUNGI; }, Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(0.0F));
	static public final Block SNOME_BLOCK = new SnomeBlock(Block.Properties.create(Material.CLAY, MaterialColor.CYAN).slipperiness(0.9F).sound(SoundType.SLIME));
	static public final Block JELIME_BLOCK = new SlimeBlock(Block.Properties.create(Material.CLAY, MaterialColor.PINK).slipperiness(0.8F).sound(SoundType.SLIME));
	static public final Block NEONLIME_BLOCK = new SlimeBlock(Block.Properties.create(Material.CLAY, MaterialColor.MAGENTA).slipperiness(0.8F).sound(SoundType.SLIME));
	static public final Block GRANITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block DIORITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block ANDESITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_GRANITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_DIORITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_ANDESITE_ROOF = new RoofBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block GRANITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block DIORITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block ANDESITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_GRANITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_DIORITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block POLISHED_ANDESITE_PEDESTAL = new PedestalBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1.5F, 6.0F).sound(SoundType.STONE));
	static public final Block OCEAN_LAMP = new OceanLampBlock(Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5F, 1.0F).sound(SoundType.GLASS).lightValue(15));
}
