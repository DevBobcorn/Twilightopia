package bobcorn.twilightopia.world.gen.feature.structure;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ToriiStruture extends TwilitTemplateStructure<NoFeatureConfig> {
	public ToriiStruture(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	ResourceLocation getRes() {
		return new ResourceLocation(TwilightopiaMod.MODID, "japan/torii");
	}
	
	@Override
	protected BlockPos getOffset() {
		return new BlockPos(0, -3, 0); //To bury the chocolate dirt underneath the structure
	}
	
	protected Rotation getRotation() {
		return (random.nextInt(3) == 0) ? Rotation.NONE : Rotation.CLOCKWISE_90;
	}
}
