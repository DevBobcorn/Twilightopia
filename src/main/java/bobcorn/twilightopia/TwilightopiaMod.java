package bobcorn.twilightopia;

import bobcorn.twilightopia.config.ConfigHolder;
import bobcorn.twilightopia.init.ModVanillaCompat;
import bobcorn.twilightopia.network.C2SUpdateSignPlus;
import bobcorn.twilightopia.network.S2CEditSignPlus;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
		
		// Register Configs
		modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
		modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);
		
		int networkId = 0;
		CHANNEL.registerMessage(networkId++,
				S2CEditSignPlus.class,
				S2CEditSignPlus::encode,
				S2CEditSignPlus::decode,
				S2CEditSignPlus::handle
		);
		CHANNEL.registerMessage(networkId++,
				C2SUpdateSignPlus.class,
				C2SUpdateSignPlus::encode,
				C2SUpdateSignPlus::decode,
				C2SUpdateSignPlus::handle
		);
		ModVanillaCompat.init();
	}
}
