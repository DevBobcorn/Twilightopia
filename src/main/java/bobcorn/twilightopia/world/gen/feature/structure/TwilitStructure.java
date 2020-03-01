package bobcorn.twilightopia.world.gen.feature.structure;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

public abstract class TwilitStructure<C extends IFeatureConfig> extends Feature<C> {
	public TwilitStructure(Function<Dynamic<?>, ? extends C> configFactoryIn) {
		super(configFactoryIn);
	}
}
