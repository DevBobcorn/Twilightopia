package bobcorn.twilightopia.world.gen.feature.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.TwilightopiaSoilHelper;
import bobcorn.twilightopia.maze.Lattice;
import bobcorn.twilightopia.maze.Maze;
import bobcorn.twilightopia.world.biome.TwilightopiaBiomes;

public class MazeStructure extends TwilitStructure<MazeConfig> {
	public MazeStructure(Function<Dynamic<?>, ? extends MazeConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, MazeConfig config) {
		Biome biome = TwilightopiaBiomes.HOLY_WOODS;
		try {
			biome = worldIn.getBiome(pos);
		} catch(RuntimeException var) {
			System.err.println("Biome NOT Found");
		}
		
		BlockState und = config.under == Blocks.VOID_AIR.getDefaultState() ? TwilightopiaSoilHelper.getChocoTypeIn(biome) : config.under;
		BlockState gnd = config.ground == Blocks.VOID_AIR.getDefaultState() ? TwilightopiaSoilHelper.getGrassyChocoTypeIn(biome) : config.ground;
		Maze maze = new Maze(config.width, config.width);
		maze.setCreateMaze(Maze.DepthFirstSearchCreateMaze);
		maze.createMaze();
		BlockPos org = pos.north(config.width / 2).west(config.width  / 2);
		Lattice[][] lat = maze.getLattice();
		for (int i = 0;i < maze.getColNumber() + 1;++i) {
			for (int j = 0;j < maze.getRowNumber() + 1;++j) {
				pos = org.east(i).south(j);
				for (int y = 4;y > 0;--y) {
					worldIn.setBlockState(pos.down(y), TwilightopiaSoilHelper.getChocoTypeIn(biome), 2);
				}
				worldIn.setBlockState(pos, lat[i][j].isPassable() ? gnd : und, 2);
				for (int y = 1;y <= config.height;++y) {
					worldIn.setBlockState(pos.up(y), lat[i][j].isPassable() ? Blocks.AIR.getDefaultState() : config.wall, 2);
				}
			}
		}
		//Generate Loots
		BlockPos chestPos = org.east((int) maze.getColNumber() - 1).south((int) maze.getRowNumber() - 1);
		worldIn.setBlockState(chestPos, Blocks.GOLD_BLOCK.getDefaultState(), 2);
		worldIn.setBlockState(chestPos.up(), Blocks.CHEST.getDefaultState(), 2);
		if (worldIn.getTileEntity(chestPos.up()) instanceof ChestTileEntity) {
			((ChestTileEntity) worldIn.getTileEntity(chestPos.up())).setLootTable(new ResourceLocation(TwilightopiaMod.MODID, "chests/maze"), 20021222L + Minecraft.getInstance().world.getGameTime());  
		}
		return true;
	}
}
