package bobcorn.twilightopia.client;

import net.minecraftforge.client.event.ColorHandlerEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.client.gui.ProphetLogScreen;
import bobcorn.twilightopia.client.renderer.entity.BearRenderer;
import bobcorn.twilightopia.client.renderer.entity.ChoxRenderer;
import bobcorn.twilightopia.client.renderer.entity.DeerRenderer;
import bobcorn.twilightopia.client.renderer.entity.HypnotomRenderer;
import bobcorn.twilightopia.client.renderer.entity.MobCRenderer;
import bobcorn.twilightopia.client.renderer.entity.RainbullRenderer;
import bobcorn.twilightopia.client.renderer.entity.RatRenderer;
import bobcorn.twilightopia.client.renderer.entity.SantaClausRenderer;
import bobcorn.twilightopia.client.renderer.tileentity.ProjectableTileEntityRenderer;
import bobcorn.twilightopia.colors.TwilitColors;
import bobcorn.twilightopia.container.ModContainerType;
import bobcorn.twilightopia.entity.ModEntityTypes;
import bobcorn.twilightopia.tileentity.ModTileEntityType;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author -
 */
@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = TwilightopiaMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Client Mod Event Subscriber");

	@SubscribeEvent
	public static void registerBlockColors(final ColorHandlerEvent.Block event){
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.sweet1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 70, TwilitColors.sweet1, TwilitColors.sweet2);
	      }, ModBlocks.GRASSED_CHOCOLATE_BLOCK.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.holy1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 70, TwilitColors.holy1, TwilitColors.holy2);
	      }, ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.white1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.white1, TwilitColors.white2);
	      }, ModBlocks.CHERRY_LEAVES.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.pink1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.pink1, TwilitColors.pink2);
	      }, ModBlocks.PINK_CHERRY_LEAVES.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.ignite1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.ignite1, TwilitColors.ignite2);
	      }, ModBlocks.IGNITE_LEAVES.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			return FoliageColors.getBirch();
	      }, ModBlocks.BELL_LEAVES.get());
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.sweet1;
			return TwilitColors.getBlockColorAt(pos.getY(), 50, 80, TwilitColors.sweet1, TwilitColors.sweet2);
	      }, ModBlocks.VELVET_GRASS.get());
		System.out.println("Block Colors Registered");
	}
	
	@SubscribeEvent
	public static void registerItemColors(final ColorHandlerEvent.Item event){
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.sweet1, TwilitColors.sweet2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "grassed_chocolate_block")));
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.holy1, TwilitColors.holy2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "grassed_white_chocolate_block")));
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.white1, TwilitColors.white2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "cherry_leaves")));
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.pink1, TwilitColors.pink2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "pink_cherry_leaves")));
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.ignite1, TwilitColors.ignite2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "ignite_leaves")));
		event.getItemColors().register((stack, idx) -> { 
			return FoliageColors.getBirch();
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "bell_leaves")));
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.sweet1, TwilitColors.sweet2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "velvet_grass")));
        
		System.out.println("Item Color Registered");
	}
	
	/**
	 * This method will be called by Forge when it is time for the mod to do its client-side setup
	 * This method will always be called after the Registry events.
	 * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
	 */
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		RenderType transl = RenderType.func_228645_f_();
		RenderType cutout = RenderType.func_228643_e_();
		RenderType transp = RenderType.func_228641_d_();
		
		RenderTypeLookup.setRenderLayer(ModBlocks.GRASSED_CHOCOLATE_BLOCK.get(), transp);
		RenderTypeLookup.setRenderLayer(ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.get(), transp);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.BELL_LEAVES.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.PINK_CHERRY_LEAVES.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.CHERRY_LEAVES.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.IGNITE_LEAVES.get(), cutout);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.CHERRY_DOOR.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.CHERRY_TRAPDOOR.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.IGNITE_DOOR.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.IGNITE_TRAPDOOR.get(), cutout);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.IGNITE_SAPLING.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.CHERRY_SAPLING.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.PROPHET_SAPLING.get(), cutout);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.VELVET_GRASS.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.CANDELION.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.RAINBUSH.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.GLOWING_FUNGI.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.ROSE.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.BLUE_ROSE.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.FUCHSIA_ROSE.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.TANGERINE_ROSE.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_ROSE.get(), cutout);
		RenderTypeLookup.setRenderLayer(ModBlocks.ILLUSHROOM.get(), cutout);
		
		RenderTypeLookup.setRenderLayer(ModBlocks.TULIPORTAL.get(), transl);
		
		LOGGER.debug("Registered Block Render Types");
		
		//TODO: Register Render
		// Register TileEntity Renderers
		ClientRegistry.bindTileEntityRenderer(ModTileEntityType.PROJECTABLE.get(), ProjectableTileEntityRenderer::new);
		LOGGER.debug("Registered TileEntity Renderers");

		// Register Entity Renderers
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.BEAR.get(), BearRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.CHOX.get(),ChoxRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.DEER.get(), DeerRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.HYPNOTOM.get(), HypnotomRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.MOB_C.get(), MobCRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RAINBULL.get(), RainbullRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.RAT.get(), RatRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.SANTA_CLAUS.get(), SantaClausRenderer::new);
		LOGGER.debug("Registered Entity Renderers");

		// Register ContainerType Screens
		// ScreenManager.registerFactory is not safe to call during parallel mod loading so we queue it to run later
		DeferredWorkQueue.runLater(() -> {
			ScreenManager.registerFactory(ModContainerType.PROPHET_LOG.get(), ProphetLogScreen::new);
			LOGGER.debug("Registered ContainerType Screens");
		});
	}
}
