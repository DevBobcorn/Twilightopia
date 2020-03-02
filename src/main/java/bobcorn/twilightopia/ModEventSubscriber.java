package bobcorn.twilightopia;

import com.google.common.base.Preconditions;

import bobcorn.twilightopia.advancements.TwilitAdvancements;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.blocks.PizzaBlock;
import bobcorn.twilightopia.blocks.StandingSignPlusBlock;
import bobcorn.twilightopia.blocks.TuliportalBlock;
import bobcorn.twilightopia.blocks.TwilightopiaCakeBlock;
import bobcorn.twilightopia.blocks.TwilightopiaCropsBlock;
import bobcorn.twilightopia.blocks.WallSignPlusBlock;
import bobcorn.twilightopia.config.ConfigHelper;
import bobcorn.twilightopia.config.ConfigHolder;
import bobcorn.twilightopia.container.ProphetLogContainer;
import bobcorn.twilightopia.effects.ModEffects;
import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.init.ModItemGroups;
import bobcorn.twilightopia.items.ModItems;
import bobcorn.twilightopia.world.biome.TwilightopiaBiomes;
import bobcorn.twilightopia.world.dimension.TwilightopiaDimensions;
import bobcorn.twilightopia.world.gen.TwilightopiaChunkGenerator;
import bobcorn.twilightopia.world.gen.feature.ModFeature;
import bobcorn.twilightopia.tileentity.ModTileEntityType;
import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

/**
 * Subscribe to events from the MOD EventBus that should be handled on both PHYSICAL sides in this class
 */
@EventBusSubscriber(modid = TwilightopiaMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber {
	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Mod Event Subscriber");
	
	/**
	 * This method will be called by Forge when it is time for the mod to register its Blocks.
	 * This method will always be called before the Item registry method.
	 */
	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event) {
		// Register all your blocks inside this registerAll call
		event.getRegistry().registerAll(
				setup(ModBlocks.CHOCOLATE_BLOCK, "chocolate_block"),
				setup(ModBlocks.GRASSED_CHOCOLATE_BLOCK, "grassed_chocolate_block"),
				setup(ModBlocks.WHITE_CHOCOLATE_BLOCK, "white_chocolate_block"),
				setup(ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK, "grassed_white_chocolate_block"),
				setup(ModBlocks.MISTY_CHOCOLATE_BLOCK, "misty_chocolate_block"),
				setup(ModBlocks.VELVET_GRASS, "velvet_grass"),
				setup(ModBlocks.MISTY_GRASS, "misty_grass"),
				setup(ModBlocks.RAINBUSH, "rainbush"),
				setup(ModBlocks.GLOWING_FUNGI, "glowing_fungi"),
				setup(ModBlocks.CANDELION, "candelion"),
				setup(ModBlocks.CHEESE_BLOCK, "cheese_block"),
				setup(ModBlocks.RUBY_ORE, "ruby_ore"),
				setup(ModBlocks.INFESTED_CHEESE_BLOCK, "infested_cheese_block"),
				setup(ModBlocks.PROPHET_LOG, "prophet_log"),
				setup(ModBlocks.PROPHET_SAPLING, "prophet_sapling"),
				setup(ModBlocks.BELL_LEAVES, "bell_leaves"),
				setup(ModBlocks.CHERRY_LOG, "cherry_log"),
				setup(ModBlocks.STRIPPED_CHERRY_LOG, "stripped_cherry_log"),
				setup(ModBlocks.CHERRY_WOOD, "cherry_wood"),
				setup(ModBlocks.STRIPPED_CHERRY_WOOD, "stripped_cherry_wood"),
				setup(ModBlocks.CHERRY_LEAVES, "cherry_leaves"),
				setup(ModBlocks.PINK_CHERRY_LEAVES, "pink_cherry_leaves"),
				setup(ModBlocks.CHERRY_SAPLING, "cherry_sapling"),
				setup(ModBlocks.CHERRY_PLANKS, "cherry_planks"),
				setup(ModBlocks.CHERRY_SLAB, "cherry_slab"),
				setup(ModBlocks.CHERRY_STAIRS, "cherry_stairs"),
				setup(ModBlocks.CHERRY_DOOR, "cherry_door"),
				setup(ModBlocks.CHERRY_TRAPDOOR, "cherry_trapdoor"),
				setup(ModBlocks.CHERRY_FENCE, "cherry_fence"),
				setup(ModBlocks.CHERRY_FENCE_GATE, "cherry_fence_gate"),
				setup(ModBlocks.CHERRY_PRESSURE_PLATE, "cherry_pressure_plate"),
				setup(ModBlocks.CHERRY_BUTTON, "cherry_button"),
				setup(ModBlocks.CHERRY_SIGN, "cherry_sign"),
				setup(ModBlocks.CHERRY_WALL_SIGN, "cherry_wall_sign"),
				setup(ModBlocks.BLESSING_PLANKS, "blessing_planks"),
				setup(ModBlocks.IGNITE_LOG, "ignite_log"),
				setup(ModBlocks.STRIPPED_IGNITE_LOG, "stripped_ignite_log"),
				setup(ModBlocks.IGNITE_WOOD, "ignite_wood"),
				setup(ModBlocks.STRIPPED_IGNITE_WOOD, "stripped_ignite_wood"),
				setup(ModBlocks.IGNITE_LEAVES, "ignite_leaves"),
				setup(ModBlocks.IGNITE_SAPLING, "ignite_sapling"),
				setup(ModBlocks.IGNITE_PLANKS, "ignite_planks"),
				setup(ModBlocks.IGNITE_SLAB, "ignite_slab"),
				setup(ModBlocks.IGNITE_STAIRS, "ignite_stairs"),
				setup(ModBlocks.IGNITE_DOOR, "ignite_door"),
				setup(ModBlocks.IGNITE_TRAPDOOR, "ignite_trapdoor"),
				setup(ModBlocks.IGNITE_FENCE, "ignite_fence"),
				setup(ModBlocks.IGNITE_FENCE_GATE, "ignite_fence_gate"),
				setup(ModBlocks.IGNITE_PRESSURE_PLATE, "ignite_pressure_plate"),
				setup(ModBlocks.IGNITE_BUTTON, "ignite_button"),
				setup(ModBlocks.IGNITE_SIGN, "ignite_sign"),
				setup(ModBlocks.IGNITE_WALL_SIGN, "ignite_wall_sign"),
				setup(ModBlocks.CHOCOLATE_CAKE, "chocolate_cake"),
				setup(ModBlocks.PIZZA, "pizza"),
				setup(ModBlocks.CANDY_CANE_CROP, "candy_cane_crop"),
				setup(ModBlocks.STRAWBERRY_CROP, "strawberry_crop"),
				setup(ModBlocks.TOMATO_CROP, "tomato_crop"),
				setup(ModBlocks.TULIPORTAL, "tuliportal"),
				setup(ModBlocks.PROJECTABLE, "projectable"),
				setup(ModBlocks.RUBY_BLOCK, "ruby_block"),
				setup(ModBlocks.CANDY_BLOCK, "candy_block"),
				setup(ModBlocks.YELLOW_CANDY_BLOCK, "yellow_candy_block"),
				setup(ModBlocks.ORANGE_CANDY_BLOCK, "orange_candy_block"),
				setup(ModBlocks.RED_CANDY_BLOCK, "red_candy_block"),
				setup(ModBlocks.PURPLE_CANDY_BLOCK, "purple_candy_block"),
				setup(ModBlocks.PINK_CANDY_BLOCK, "pink_candy_block"),
				setup(ModBlocks.ROSE, "rose"),
				setup(ModBlocks.BLUE_ROSE, "blue_rose"),
				setup(ModBlocks.TANGERINE_ROSE, "tangerine_rose"),
				setup(ModBlocks.WHITE_ROSE, "white_rose"),
				setup(ModBlocks.FUCHSIA_ROSE, "fuchsia_rose"),
				setup(ModBlocks.ILLUSHROOM, "illushroom"),
				setup(ModBlocks.POTTED_ROSE, "potted_rose"),
				setup(ModBlocks.POTTED_BLUE_ROSE, "potted_blue_rose"),
				setup(ModBlocks.POTTED_TANGERINE_ROSE, "potted_tangerine_rose"),
				setup(ModBlocks.POTTED_WHITE_ROSE, "potted_white_rose"),
				setup(ModBlocks.POTTED_FUCHSIA_ROSE, "potted_fuchsia_rose"),
				setup(ModBlocks.POTTED_CHERRY_SAPLING, "potted_cherry_sapling"),
				setup(ModBlocks.POTTED_IGNITE_SAPLING, "potted_ignite_sapling"),
				setup(ModBlocks.POTTED_PROPHET_SAPLING, "potted_prophet_sapling"),
				setup(ModBlocks.POTTED_RAINBUSH, "potted_rainbush"),
				setup(ModBlocks.POTTED_CANDELION, "potted_candelion"),
				setup(ModBlocks.POTTED_GLOWING_FUNGI, "potted_glowing_fungi"),
				setup(ModBlocks.POTTED_ILLUSHROOM, "potted_illushroom"),
				setup(ModBlocks.SNOME_BLOCK, "snome_block"),
				setup(ModBlocks.JELIME_BLOCK, "jelime_block"),
				setup(ModBlocks.NEONLIME_BLOCK, "neonlime_block"),
				setup(ModBlocks.ANDESITE_PEDESTAL, "andesite_pedestal"),
				setup(ModBlocks.DIORITE_PEDESTAL, "diorite_pedestal"),
				setup(ModBlocks.GRANITE_PEDESTAL, "granite_pedestal"),
				setup(ModBlocks.POLISHED_ANDESITE_PEDESTAL, "polished_andesite_pedestal"),
				setup(ModBlocks.POLISHED_DIORITE_PEDESTAL, "polished_diorite_pedestal"),
				setup(ModBlocks.POLISHED_GRANITE_PEDESTAL, "polished_granite_pedestal"),
				setup(ModBlocks.ANDESITE_ROOF, "andesite_roof"),
				setup(ModBlocks.DIORITE_ROOF, "diorite_roof"),
				setup(ModBlocks.GRANITE_ROOF, "granite_roof"),
				setup(ModBlocks.POLISHED_ANDESITE_ROOF, "polished_andesite_roof"),
				setup(ModBlocks.POLISHED_DIORITE_ROOF, "polished_diorite_roof"),
				setup(ModBlocks.POLISHED_GRANITE_ROOF, "polished_granite_roof"),
				setup(ModBlocks.OCEAN_LAMP, "ocean_lamp")
		);
		LOGGER.debug("Registered Blocks");
	}

	/**
	 * This method will be called by Forge when it is time for the mod to register its Items.
	 * This method will always be called after the Block registry method.
	 */
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		registry.registerAll(
				// This is a very simple Item. It has no special properties except for being on our creative tab.
				setup(ModItems.lodge_herb, "lodge_herb"),
				setup(ModItems.quill, "quill"),
				setup(ModItems.chocolate_bar, "chocolate_bar"),
				setup(ModItems.white_chocolate_bar, "white_chocolate_bar"),
				setup(ModItems.cherry, "cherry"),
				setup(ModItems.cherry_pie, "cherry_pie"),
				setup(ModItems.apple_pie, "apple_pie"),
				setup(ModItems.cheese, "cheese"),
				setup(ModItems.sakura, "sakura"),
				setup(ModItems.flame_nut, "flame_nut"),
				setup(ModItems.candy_cane, "candy_cane"),
				setup(ModItems.strawberry, "strawberry"),
				setup(ModItems.strawberry_seeds, "strawberry_seeds"),
				setup(ModItems.tomato, "tomato"),
				setup(ModItems.tomato_seeds, "tomato_seeds"),
				setup(ModItems.venison, "venison"),
				setup(ModItems.cooked_venison, "cooked_venison"),
				setup(ModItems.ginger_bread, "ginger_bread"),
				setup(ModItems.jelly, "jelly"),
				setup(ModItems.jelly_ball, "jelly_ball"),
				setup(ModItems.neonlime_ball, "neonlime_ball"),
				setup(ModItems.neoncolate, "neoncolate"),
				setup(ModItems.ruby, "ruby"),
				setup(ModItems.ruby_axe, "ruby_axe"),
				setup(ModItems.ruby_shovel, "ruby_shovel"),
				setup(ModItems.ruby_sword, "ruby_sword"),
				setup(ModItems.ruby_pickaxe, "ruby_pickaxe"),
				setup(ModItems.ruby_hoe, "ruby_hoe"),
				setup(ModItems.ruby_horse_armor, "ruby_horse_armor"),
				setup(ModItems.ruby_helmet, "ruby_helmet"),
				setup(ModItems.ruby_chestplate, "ruby_chestplate"),
				setup(ModItems.ruby_leggings, "ruby_leggings"),
				setup(ModItems.ruby_boots, "ruby_boots"),
				setup(ModItems.santa_hat, "santa_hat"),
				setup(ModItems.santa_coat, "santa_coat"),
				setup(ModItems.santa_trousers, "santa_trousers"),
				setup(ModItems.santa_boots, "santa_boots"),
				setup(ModItems.crown, "crown"),
				setup(ModItems.blaze_and_gold, "blaze_and_gold"),
				setup(ModItems.deer_spawn_egg, "deer_spawn_egg"),
				setup(ModItems.rainbull_spawn_egg, "rainbull_spawn_egg"),
				setup(ModItems.santa_claus_spawn_egg, "santa_claus_spawn_egg"),
				setup(ModItems.bear_spawn_egg, "bear_spawn_egg"),
				setup(ModItems.rat_spawn_egg, "rat_spawn_egg"),
				setup(ModItems.chox_spawn_egg, "chox_spawn_egg"),
				setup(ModItems.mob_c_spawn_egg, "mob_c_spawn_egg"),
				setup(ModItems.hypnotom_spawn_egg, "hypnotom_spawn_egg"),
				setup(ModItems.ginger_bread_man_spawn_egg, "ginger_bread_man_spawn_egg"),
				setup(ModItems.ginger_bread_boss_spawn_egg, "ginger_bread_boss_spawn_egg"),
				setup(ModItems.snome_spawn_egg, "snome_spawn_egg"),
				setup(ModItems.jelime_spawn_egg, "jelime_spawn_egg"),
				setup(ModItems.neonlime_spawn_egg, "neonlime_spawn_egg"),
				setup(ModItems.cherry_sign, "cherry_sign"),
				setup(ModItems.ignite_sign, "ignite_sign"),
				setup(ModItems.cherry_boat, "cherry_boat"),
				setup(ModItems.ignite_boat, "ignite_boat"),
				setup(ModItems.magic_debris, "magic_debris")
		);

		// We need to go over the entire registry so that we include any potential Registry Overrides
		for (final Block block : ForgeRegistries.BLOCKS.getValues()) {

			final ResourceLocation blockRegistryName = block.getRegistryName();
			// An extra safe-guard against badly registered blocks
			Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");

			// Check that the blocks is from our mod, if not, continue to the next block
			if (!blockRegistryName.getNamespace().equals(TwilightopiaMod.MODID)) {
				continue;
			}

			// Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
			final Item.Properties properties;
			if (block instanceof TwilightopiaCakeBlock || block instanceof PizzaBlock) properties = new Item.Properties().group(ModItemGroups.TWILIGHT_FOOD_GROUP);
			else if (block instanceof TwilightopiaCropsBlock || block instanceof WallSignPlusBlock || block instanceof StandingSignPlusBlock || block instanceof FlowerPotBlock) continue;
			else if (block instanceof TuliportalBlock) properties = new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP);
			else properties = new Item.Properties().group(ModItemGroups.TWILIGHT_BLOCK_GROUP);
			// Create the new BlockItem with the block and it's properties
			final BlockItem blockItem = new BlockItem(block, properties);
			// Setup the new BlockItem with the block's registry name and register it
			registry.register(setup(blockItem, blockRegistryName));
		}
		LOGGER.debug("Registered Items");
	}

	/**
	 * This method will be called by Forge when it is time for the mod to register its Entities.
	 * This method will always be called after the Block registry method.
	 */
	@SubscribeEvent
	public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {	
		event.getRegistry().registerAll(
		    ModEntityType.RAINBULL.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"rainbull")),
		    ModEntityType.DEER.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"deer")),
		    ModEntityType.SANTA_CLAUS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"santa_claus")),
		    ModEntityType.BEAR.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"bear")),
		    ModEntityType.RAT.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"rat")),
		    ModEntityType.CHOX.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"chox")),
		    ModEntityType.MOB_C.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"mob_c")),
		    ModEntityType.HYPNOTOM.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"hypnotom")),
		    ModEntityType.GINGER_BREAD_MAN.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"ginger_bread_man")),
		    ModEntityType.GINGER_BREAD_BOSS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"ginger_bread_boss")),
		    ModEntityType.SNOME.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"snome")),
		    ModEntityType.JELIME.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"jelime")),
		    ModEntityType.NEONLIME.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"neonlime"))
		);
		//event.getRegistry().register(ModEntityType.BOAT_PLUS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"boat_plus")));
		LOGGER.debug("Registered Entities");
	}
	/**
	 * This method will be called by Forge when it is time for the mod to register its TileEntities.
	 * This method will always be called after the Block and Item registry methods.
	 */
	@SubscribeEvent
	public static void onRegisterTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event) {
		// Register your TileEntityTypes here if you have them
		event.getRegistry().registerAll(
				ModTileEntityType.PROJECTOR_MAP.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"projectable")),
				ModTileEntityType.SIGN_PLUS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"sign_plus")),
				ModTileEntityType.PROPHET_LOG.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"prophet_log"))
		);
		LOGGER.debug("Registered TileEntities");
	}
	
	/**
	 * This method will be called by Forge when it is time for the mod to register its ContainerTypes.
	 * This method will always be called after the Block and Item registry methods.
	 */
	@SubscribeEvent
	public static void onRegisterContainerTypes(@Nonnull final RegistryEvent.Register<ContainerType<?>> event) {
		// Register your ContainerTypes here if you have them
		event.getRegistry().registerAll(
				setup(IForgeContainerType.create(ProphetLogContainer::new), "prophet_log")
		);
		LOGGER.debug("Registered ContainerTypes");
	}
	
	/**
	 * This method will be called by Forge when it is time for the mod to register its Effects.
	 * This method will always be called after the Block registry method.
	 */
	@SubscribeEvent
	public static void onRegisterEffects(final RegistryEvent.Register<Effect> event) {	
		event.getRegistry().registerAll(
		    setup(ModEffects.NEON_ILLUSION, "neon_illusion"),
		    setup(ModEffects.SAKURA_BLESSING, "sakura_blessing"),
		    setup(ModEffects.HEAVY_MIST, "heavy_mist"),
		    setup(ModEffects.FROZEN, "frozen")
		);
		
		LOGGER.debug("Registered Effects");
	}
	
	@SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event)
	{
		event.getRegistry().registerAll(
			TwilightopiaBiomes.TASTY_LAND.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"tasty_land")),
			TwilightopiaBiomes.TASTY_TUNDRA.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"tasty_tundra")),
			TwilightopiaBiomes.TASTY_PLAINS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"tasty_plains")),
			TwilightopiaBiomes.HOLY_WOODS.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"holy_woods")),
			TwilightopiaBiomes.FOGGY_FOREST.setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,"foggy_forest"))
		);
			
		LOGGER.debug("Registered Biomes");
	}
	
	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().registerAll(
			ModFeature.CHERRY_TREE.setRegistryName(TwilightopiaMod.MODID,"cherry_tree"),
			ModFeature.IGNITE_TREE.setRegistryName(TwilightopiaMod.MODID,"ignite_tree"),
			ModFeature.PROPHET_TREE.setRegistryName(TwilightopiaMod.MODID,"prophet_tree"),
			ModFeature.MISTY_BIRCH_TREE.setRegistryName(TwilightopiaMod.MODID,"misty_birch_tree"),
			ModFeature.MISTY_TAIGA_TREE.setRegistryName(TwilightopiaMod.MODID,"misty_taiga_tree"),
			ModFeature.HUGE_CANDY_CANE.setRegistryName(TwilightopiaMod.MODID,"huge_candy_cane"),
			ModFeature.TASTY_ORE.setRegistryName(TwilightopiaMod.MODID,"tasty_ore"),
			ModFeature.TWILIT_FLOWERS.setRegistryName(TwilightopiaMod.MODID,"twilit_flower"),
			ModFeature.MAZE.setRegistryName(TwilightopiaMod.MODID,"maze"),
			ModFeature.MAZE_LARGE.setRegistryName(TwilightopiaMod.MODID,"maze_large"),
			ModFeature.TORII.setRegistryName(TwilightopiaMod.MODID,"torii"),
			ModFeature.PAGODA.setRegistryName(TwilightopiaMod.MODID,"pagoda")
		);
		
		LOGGER.debug("Registered Features");
	}

	@SubscribeEvent
	public static void registerChunkGenerators(RegistryEvent.Register<ChunkGeneratorType<?, ?>> event) {
		event.getRegistry().registerAll(TwilightopiaChunkGenerator.TYPE.setRegistryName(TwilightopiaMod.MODID, "twilightopia_gen"));
		
		LOGGER.debug("Registered ChunkGenerators");
	}
	
	@SubscribeEvent
	public static void onRegisterModDimensions(RegistryEvent.Register<ModDimension> event)
	{
		final IForgeRegistry<ModDimension> registry = event.getRegistry();
		registry.registerAll(
			TwilightopiaDimensions.twilightopiaDim
		);
		DimensionManager.registerDimension(new ResourceLocation(TwilightopiaMod.MODID,TwilightopiaDimensions.dimName), TwilightopiaDimensions.twilightopiaDim, null, false);
		LOGGER.debug("Registered Dimensions");
	}
	
	/**
	 * This method will be called by Forge when a config changes.
	 */
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		final ModConfig config = event.getConfig();
		// Re-bake the configs when they change
		if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
			ConfigHelper.bakeClient(config);
			LOGGER.debug("Baked client config");
		} else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
			ConfigHelper.bakeServer(config);
			LOGGER.debug("Baked server config");
		}
	}
	
	/**
	 * Performs setup on a registry entry
	 *
	 * @param name The path of the entry's name. Used to make a name who's domain is our mod's modid
	 */
	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
		Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
		return setup(entry, new ResourceLocation(TwilightopiaMod.MODID, name));
	}

	/**
	 * Performs setup on a registry entry
	 *
	 * @param registryName The full registry name of the entry
	 */
	@Nonnull
	private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
		Preconditions.checkNotNull(entry, "Entry cannot be null!");
		Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
		entry.setRegistryName(registryName);
		return entry;
	}
	
	@SubscribeEvent
	public static void onFMLCommonSetupEvent(final FMLCommonSetupEvent event) {
		TwilitAdvancements.init();
		LOGGER.debug("Advancements Initializied");
	}
}
