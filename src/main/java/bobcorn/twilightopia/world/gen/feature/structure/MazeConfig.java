package bobcorn.twilightopia.world.gen.feature.structure;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class MazeConfig implements IFeatureConfig {
	public final int width;
	public final int height;
	public final BlockState wall;
	public final BlockState ground;
	public final BlockState under;

	public MazeConfig(int width, int height, BlockState wall, BlockState ground, BlockState under) {
		this.width = width;
		this.height = height;
		this.wall = wall;
		this.ground = ground;
		this.under = under;
	}

	public MazeConfig(int width, int height, BlockState wall, BlockState under) {
		this(width, height, wall, Blocks.VOID_AIR.getDefaultState(), under);
	}
	
	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		return new Dynamic<>(ops, 
			ops.createMap(ImmutableMap.of(
				ops.createString("width"), ops.createInt(this.width),
				ops.createString("height"), ops.createInt(this.height),
				ops.createString("wall"), BlockState.serialize(ops, this.wall).getValue(),
				ops.createString("ground"), BlockState.serialize(ops, this.ground).getValue(),
				ops.createString("under"), BlockState.serialize(ops, this.under).getValue()
			))
		);
	}

	public static <T> MazeConfig deserialize(Dynamic<T> src) {
		int w = src.get("width").asInt(5);
		int h = src.get("height").asInt(5);
		BlockState l = src.get("wall").map(BlockState::deserialize).orElse(Blocks.STONE_BRICKS.getDefaultState());
		BlockState g = src.get("ground").map(BlockState::deserialize).orElse(ModBlocks.GRASSED_CHOCOLATE_BLOCK.getDefaultState());
		BlockState u = src.get("under").map(BlockState::deserialize).orElse(Blocks.STONE_BRICKS.getDefaultState());
		return new MazeConfig(w, h, l, g, u);
	}
}