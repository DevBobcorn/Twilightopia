package bobcorn.twilightopia.client;

import net.minecraftforge.client.event.ColorHandlerEvent;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.client.renderer.entity.ModEntityRenderRegister;
import bobcorn.twilightopia.client.renderer.tileentity.ModTileEntityRenderRegister;
import bobcorn.twilightopia.colors.TwilitColors;
import bobcorn.twilightopia.container.ContainerTypeScreenRegister;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.FoliageColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 */
@EventBusSubscriber(modid = TwilightopiaMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientModEventSubscriber {

	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Client Mod Event Subscriber");

	@SubscribeEvent
	public static void registerBlockColors(final ColorHandlerEvent.Block event){
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.sweet1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 70, TwilitColors.sweet1, TwilitColors.sweet2);
	      }, ModBlocks.GRASSED_CHOCOLATE_BLOCK);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.holy1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 70, TwilitColors.holy1, TwilitColors.holy2);
	      }, ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.misty1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 70, TwilitColors.misty1, TwilitColors.misty2);
	      }, ModBlocks.MISTY_CHOCOLATE_BLOCK);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.white1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.white1, TwilitColors.white2);
	      }, ModBlocks.CHERRY_LEAVES);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.pink1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.pink1, TwilitColors.pink2);
	      }, ModBlocks.PINK_CHERRY_LEAVES);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.ignite1;
			return TwilitColors.getBlockColorAt(pos.getY(), 55, 80, TwilitColors.ignite1, TwilitColors.ignite2);
	      }, ModBlocks.IGNITE_LEAVES);
		event.getBlockColors().register((state, world, pos, idx) -> {
			return FoliageColors.getBirch();
	      }, ModBlocks.BELL_LEAVES);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.sweet1;
			return TwilitColors.getBlockColorAt(pos.getY(), 50, 80, TwilitColors.sweet1, TwilitColors.sweet2);
	      }, ModBlocks.VELVET_GRASS);
		event.getBlockColors().register((state, world, pos, idx) -> {
			if (world == null || pos == null)
				return TwilitColors.misty1;
			return TwilitColors.getBlockColorAt(pos.getY(), 50, 80, TwilitColors.misty1, TwilitColors.misty2);
	      }, ModBlocks.MISTY_GRASS);
		LOGGER.debug("Block Colors Registered");
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
			return TwilitColors.getItemColor(stack, TwilitColors.misty1, TwilitColors.misty2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "misty_chocolate_block")));
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
		event.getItemColors().register((stack, idx) -> { 
			return TwilitColors.getItemColor(stack, TwilitColors.misty1, TwilitColors.misty2);
		}, ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "misty_grass")));
        
		LOGGER.debug("Item Color Registered");
	}
	
	/**
	 * This method will be called by Forge when it is time for the mod to do its client-side setup
	 * This method will always be called after the Registry events.
	 * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
	 */
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		ModEntityRenderRegister.registerRendering();
		ModTileEntityRenderRegister.registerRendering();
		ContainerTypeScreenRegister.registerScreens();
	}
}
