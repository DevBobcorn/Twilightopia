package bobcorn.twilightopia;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.config.ConfigHolder;
import bobcorn.twilightopia.container.ModContainerType;
import bobcorn.twilightopia.entity.ModEntityTypes;
import bobcorn.twilightopia.items.ModItems;
import bobcorn.twilightopia.tileentity.ModTileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Bobcorn
 */
@Mod(TwilightopiaMod.MODID)
public final class TwilightopiaMod {
	public static final String MODID = "twilightopia";

	public static final Logger LOGGER = LogManager.getLogger(MODID);
	
	private static final String NETWORK_PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(MODID, "channel"),
			() -> NETWORK_PROTOCOL_VERSION,
			NETWORK_PROTOCOL_VERSION::equals,
			NETWORK_PROTOCOL_VERSION::equals
	);
	
	public TwilightopiaMod() {
		LOGGER.debug("Welcome To Twilightopia!");

		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModBlocks.BLOCKS.register(modEventBus);
		ModItems.ITEMS.register(modEventBus);
		ModContainerType.CONTAINER_TYPES.register(modEventBus);
		ModEntityTypes.ENTITY_TYPES.register(modEventBus);
		ModTileEntityType.TILE_ENTITY_TYPES.register(modEventBus);
		// Register Configs
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
	}
}
