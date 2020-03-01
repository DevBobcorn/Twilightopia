package bobcorn.twilightopia.world.dimension;

import java.util.function.BiFunction;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

public class TwilightopiaDimension extends ModDimension {
	private BiFunction<World, DimensionType, ? extends Dimension> factory;
	
	public TwilightopiaDimension(BiFunction<World, DimensionType, ? extends Dimension> factory) {
		this.factory = factory;
	}
	
	@Override
	public BiFunction<World, DimensionType, ? extends Dimension> getFactory(){
		return this.factory;
	}
}
