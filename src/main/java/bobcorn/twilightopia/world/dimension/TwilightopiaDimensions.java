package bobcorn.twilightopia.world.dimension;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;

public class TwilightopiaDimensions {
	public static final String dimName = "twiland";
	public static final ModDimension twilightopiaDim = new TwilightopiaDimension(TwilandDimension::new).setRegistryName(new ResourceLocation(TwilightopiaMod.MODID,dimName));       
	
	public static void registerDimensions() {
		if (getDimensionType() == null)
			DimensionManager.registerDimension(new ResourceLocation(TwilightopiaMod.MODID,dimName), twilightopiaDim, null, false);
	}
	
	public static DimensionType getDimensionType()
	{
		return DimensionType.byName(new ResourceLocation(TwilightopiaMod.MODID, dimName));
	}
}
