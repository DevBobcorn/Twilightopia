package bobcorn.twilightopia.entity.passive;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class ChoxEntity extends FoxEntity {
	private static final DataParameter<Integer> CHOX_TYPE = EntityDataManager.createKey(ChoxEntity.class,
			DataSerializers.VARINT);

	public ChoxEntity(EntityType<? extends ChoxEntity> type, World worldIn) {
		super(type, worldIn);
	}

	protected void registerData() {
		super.registerData();
		this.dataManager.register(CHOX_TYPE, 0);
	}

	public ChoxType getChoxType() {
		return ChoxType.getTypeByIndex(this.dataManager.get(CHOX_TYPE));
	}

	public void setChoxType(ChoxType typeIn) {
		this.dataManager.set(CHOX_TYPE, typeIn.getIndex());
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		Biome biome = worldIn.func_225523_d_().func_226836_a_(new BlockPos(this));
		ChoxType type1 = ChoxType.getChoxTypeByBiome(biome);
		//System.out.println("Chox Spawned! In " + biome.getDisplayName() + " ,type: " + type1.name);
		this.setChoxType(type1);
		
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static enum ChoxType {
		/*
		DARK(0, "dark", Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_TREE_TAIGA,
				Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.GIANT_SPRUCE_TAIGA_HILLS,
				TwilightopiaBiomes.TASTY_LAND),
		MILKY(1, "milky", Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS,
				TwilightopiaBiomes.TASTY_TUNDRA);
		*/
		//TODO Write Biomes
		DARK(0, "dark", Biomes.TAIGA, Biomes.TAIGA_HILLS, Biomes.TAIGA_MOUNTAINS, Biomes.GIANT_TREE_TAIGA,
				Biomes.GIANT_SPRUCE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.GIANT_SPRUCE_TAIGA_HILLS),
		MILKY(1, "milky", Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.SNOWY_TAIGA_MOUNTAINS);
		
		private static final ChoxType[] field_221088_c = Arrays.stream(values())
				.sorted(Comparator.comparingInt(ChoxType::getIndex)).toArray((p_221084_0_) -> {
					return new ChoxType[p_221084_0_];
				});
		private static final Map<String, ChoxType> TYPES_BY_NAME = Arrays.stream(values())
				.collect(Collectors.toMap(ChoxType::getName, (p_221081_0_) -> {
					return p_221081_0_;
				}));
		private final int index;
		private final String name;
		private final List<Biome> spawnBiomes;

		private ChoxType(int indexIn, String nameIn, Biome... spawnBiomesIn) {
			this.index = indexIn;
			this.name = nameIn;
			this.spawnBiomes = Arrays.asList(spawnBiomesIn);
		}

		public String getName() {
			return this.name;
		}

		public List<Biome> getSpawnBiomes() {
			return this.spawnBiomes;
		}

		public int getIndex() {
			return this.index;
		}

		public static ChoxType getTypeByName(String nameIn) {
			return TYPES_BY_NAME.getOrDefault(nameIn, DARK);
		}

		public static ChoxType getTypeByIndex(int indexIn) {
			if (indexIn < 0 || indexIn > field_221088_c.length) {
				indexIn = 0;
			}

			return field_221088_c[indexIn];
		}

		/**
		 * Gets the type of fox that can spawn in this biome. The default type is red
		 * fox.
		 */
		public static ChoxType getChoxTypeByBiome(Biome biomeIn) {
			return MILKY.getSpawnBiomes().contains(biomeIn) ? MILKY : DARK;
		}
	}
}
