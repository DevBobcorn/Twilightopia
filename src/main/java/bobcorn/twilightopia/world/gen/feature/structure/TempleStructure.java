package bobcorn.twilightopia.world.gen.feature.structure;

import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class TempleStructure extends TwilitTemplateStructure<NoFeatureConfig> {
	public TempleStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
		super(configFactoryIn);
	}
	
	@Override
	protected Boolean mustOnSoil() {
		return true;
	}
	
	@Override
	ResourceLocation getRes() {
		return new ResourceLocation(TwilightopiaMod.MODID, "japan/temple");
	}
	
	@Override
	protected BlockPos getOffset() {
		return new BlockPos(0, -3, 0); //To bury the chocolate dirt underneath the structure
	}
	
	protected Rotation getRotation() {
		switch(random.nextInt(3)) {
		case 0:
			return Rotation.NONE;
		case 1:
			return Rotation.CLOCKWISE_90;
		case 2:
			return Rotation.CLOCKWISE_180;
		default:
			return Rotation.COUNTERCLOCKWISE_90;
		}
	}
}
