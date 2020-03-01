package bobcorn.twilightopia.world.biome;

import bobcorn.twilightopia.world.dimension.TwilandDimension;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.layer.traits.IC0Transformer;

public class TwilightopiaBiomeLayer implements IC0Transformer {
	@SuppressWarnings("deprecation")
	@Override
	public int apply(INoiseRandom context, int value) {
		return Registry.BIOME.getId(TwilandDimension.dimensionBiomes[context.random(TwilandDimension.dimensionBiomes.length)]);
	}
}
