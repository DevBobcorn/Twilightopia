package bobcorn.twilightopia.container;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.gui.ProphetLogScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ContainerTypeScreenRegister {
	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Client Mod Event Subscriber");
	
    @OnlyIn(Dist.CLIENT)
    public static void registerScreens()
    {
    	ScreenManager.registerFactory(ModContainerType.PROPHET_LOG, ProphetLogScreen::new);
    	
		LOGGER.debug("Registered ContainerType Screens");
    }
}
