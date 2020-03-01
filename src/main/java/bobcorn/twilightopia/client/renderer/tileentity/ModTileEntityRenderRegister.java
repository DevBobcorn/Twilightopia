package bobcorn.twilightopia.client.renderer.tileentity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.tileentity.ProjectableTileEntity;
import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class ModTileEntityRenderRegister {
	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Client Mod Event Subscriber");
	
	@OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
		ClientRegistry.bindTileEntitySpecialRenderer(ProjectableTileEntity.class, new ProjectableTileEntityRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(SignPlusTileEntity.class, new SignPlusTileEntityRenderer());
		LOGGER.debug("Registered TileEntity Renderers");
    }
}
