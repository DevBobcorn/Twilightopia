package bobcorn.twilightopia.world.gen;

import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class TwilightopiaChunkGenerator extends OverworldChunkGenerator {
	public static final ChunkGeneratorType<OverworldGenSettings, TwilightopiaChunkGenerator> TYPE = new ChunkGeneratorType<>(TwilightopiaChunkGenerator::new, false, OverworldGenSettings::new);
	
	private static final int SEALEVEL = 63;

    public TwilightopiaChunkGenerator(IWorld worldIn, BiomeProvider provider, OverworldGenSettings settingsIn) {
    	super(worldIn, provider, settingsIn);
    	this.randomSeed.skip(5349);
    }

	@Override
	public int getSeaLevel() {
		return SEALEVEL;
	}
}