package bobcorn.twilightopia.world.dimension;

import java.util.Random;
import javax.annotation.Nullable;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.client.renderer.TwilightopiaCloudRenderer;
import bobcorn.twilightopia.client.renderer.TwilightopiaSkyRenderer;
import bobcorn.twilightopia.colors.TwilitColors;
import bobcorn.twilightopia.world.biome.TwilightopiaBiomes;
import bobcorn.twilightopia.world.biome.provider.TwilightopiaBiomeProvider;
import bobcorn.twilightopia.world.gen.TwilightopiaChunkGenerator;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.ChunkGeneratorType;
import net.minecraft.world.gen.OverworldGenSettings;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TwilandDimension extends Dimension {
	public static final BlockPos SPAWN = new BlockPos(20, 50, 0);;
	public static Biome[] dimensionBiomes = new Biome[] { 
			TwilightopiaBiomes.TASTY_LAND,
			TwilightopiaBiomes.TASTY_TUNDRA,
			TwilightopiaBiomes.TASTY_PLAINS,
			TwilightopiaBiomes.HOLY_WOODS,
			TwilightopiaBiomes.FOGGY_FOREST
		};
	private final float[] colorsSunriseSunset = new float[4];

	TwilightopiaSkyRenderer skyRenderer;

	public TwilandDimension(final World worldIn, final DimensionType dimension) {
		super(worldIn, dimension);
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		OverworldGenSettings genSettings = ChunkGeneratorType.SURFACE.createSettings();
		genSettings.setDefaultBlock(ModBlocks.CHEESE_BLOCK.getDefaultState());
		genSettings.setDefaultFluid((Blocks.WATER.getDefaultState()));
		/*
		 * return ChunkGeneratorType.SURFACE.create(this.world, BiomeProviderType.FIXED
		 * .create(BiomeProviderType.FIXED.createSettings().setBiome(TwilightopiaBiomes.
		 * TASTY_LAND)), genSettings);
		 */
		if (TwilightopiaChunkGenerator.TYPE != null)
			return TwilightopiaChunkGenerator.TYPE.create(world, new TwilightopiaBiomeProvider(world), genSettings);
		else {
			System.out.println("ERROR: ChunkGenerator Type is Null!");
			return new TwilightopiaChunkGenerator(world, new TwilightopiaBiomeProvider(world), genSettings);
		}
	}

	/**
	 * Creates the light to brightness table
	 */
	protected void generateLightBrightnessTable() {
		for (int i = 0; i <= 15; ++i) {
			float f1 = 1.0F - (float) i / 15.0F;
			this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 1.0F + 0.0F;
			this.lightBrightnessTable[i] /= 1.5F;
		}
	}

	/**
	 * Calculates the angle of sun and moon in the sky relative to a specified time
	 * (usually worldTime)
	 */
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
		double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
		return (float) (d0 * 2.0D + d1) / 3.0F;
	}

	/**
	 * Returns array with sunrise/sunset colors
	 */
	@Nullable
	@OnlyIn(Dist.CLIENT)
	public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
		float f1 = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F));
		float proc = 0.5F;
		if (f1 >= -0.4F && f1 <= 0.4F) {
			proc = f1 * 1.25F + 0.5F;
		}
		else if (f1 > 0.4F) proc = 1.0F;
		else proc = 0.0F;
		int col = TwilitColors.getColorBetween(proc, TwilitColors.sky1, TwilitColors.sky2);
		colorsSunriseSunset[0] = TwilitColors.getRedf(col);
		colorsSunriseSunset[1] = TwilitColors.getGreenf(col);
		colorsSunriseSunset[2] = TwilitColors.getBluef(col);
		colorsSunriseSunset[3] = (f1 >= -0.4F && f1 <= 0.4F) ? 1F : (-1.667F * Math.abs(f1) + 1.66F);
		return colorsSunriseSunset;
	}

	/**
	 * Return Vec3D with biome specific fog color
	 */
	@OnlyIn(Dist.CLIENT)
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		float f = MathHelper.cos(celestialAngle * ((float) Math.PI * 2F)) * 2.0F + 0.5F;
		f = MathHelper.clamp(f, 0.0F, 1.0F);
		float f1 = 0.627451F;
		float f2 = 0.5019608F;
		float f3 = 0.627451F;
		f1 = f1 * (f * 0.1F + 0.15F);
		f2 = f2 * (f * 0.1F + 0.15F);
		f3 = f3 * (f * 0.1F + 0.15F);
		return new Vec3d((double) f1, (double) f2, (double) f3);
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public boolean hasSkyLight() {
		return true;
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public float getCloudHeight() {
		return 128.0f;
	}

	public DimensionType getType() {
		return TwilightopiaDimensions.getDimensionType();
	}

	@Override
	@Nullable
	public BlockPos findSpawn(final ChunkPos p_206920_1_, final boolean p_206920_2_) {
		final Random random = new Random(this.world.getSeed());
		final BlockPos blockpos = new BlockPos(p_206920_1_.getXStart() + random.nextInt(15), 0,
				p_206920_1_.getZEnd() + random.nextInt(15));
		return this.world.getGroundAboveSeaLevel(blockpos).getMaterial().blocksMovement() ? blockpos : null;
	}

	@Override
	public BlockPos getSpawnCoordinate() {
		return TwilandDimension.SPAWN;
	}

	@Override
	@Nullable
	public BlockPos findSpawn(final int p_206921_1_, final int p_206921_2_, final boolean p_206921_3_) {
		return this.findSpawn(new ChunkPos(p_206921_1_ >> 4, p_206921_2_ >> 4), p_206921_3_);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean doesXZShowFog(final int p_76568_1_, final int p_76568_2_) {
		return false;
	}

	@Override
	public void onWorldSave() {
		final CompoundNBT compoundnbt = new CompoundNBT();
		this.world.getWorldInfo().setDimensionData(this.world.getDimension().getType(), compoundnbt);
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	@Override
	public net.minecraftforge.client.IRenderHandler getSkyRenderer() {
		if (super.getSkyRenderer() == null) {
			this.setSkyRenderer(new TwilightopiaSkyRenderer());
		}
		return super.getSkyRenderer();
	}

	@Nullable
	@OnlyIn(Dist.CLIENT)
	@Override
	public net.minecraftforge.client.IRenderHandler getCloudRenderer() {
		if (super.getCloudRenderer() == null) {
			this.setCloudRenderer(new TwilightopiaCloudRenderer());
		}
		return super.getSkyRenderer();
	}
}