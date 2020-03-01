package bobcorn.twilightopia.world.gen.feature.structure;

import java.awt.Point;
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

public class LargeMazeStructure extends TwilitStructure<MazeConfig> {

	public LargeMazeStructure(Function<Dynamic<?>, ? extends MazeConfig> configFactoryIn) {
		super(configFactoryIn);
	}

	private Biome biome = TwilightopiaBiomes.HOLY_WOODS;
	BlockState und;
	BlockState gnd;
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, MazeConfig config) {
		try {
			biome = worldIn.getBiome(pos);
		} catch(RuntimeException var) {
			System.out.println("Error::Biome Not Found!");
		}
		
		und = config.under == Blocks.VOID_AIR.getDefaultState() ? TwilightopiaSoilHelper.getChocoTypeIn(biome) : config.under;
		gnd = config.ground == Blocks.VOID_AIR.getDefaultState() ? TwilightopiaSoilHelper.getGrassyChocoTypeIn(biome) : config.ground;
		Maze maze = new Maze(config.width * 2, config.width * 2);
		maze.setCreateMaze(Maze.RandomizedPrimCreateMaze);
		maze.setExit(new Point(config.width, config.width));
		maze.createMaze();
		BlockPos org = pos.north(config.width).west(config.width).up(1);
		Lattice[][] lat = maze.getLattice();
		for (int i = 0;i < maze.getColNumber() + 1;++i) {
			for (int j = 0;j < maze.getRowNumber() + 1;++j) {
				place2x2(worldIn, org, config, i, j, lat[i][j].isPassable());
			}
		}
		int i = maze.getColNumber() + 1, j = 0;
		for (;j <= maze.getRowNumber() + 1;++j) {
			place2x2(worldIn, org, config, i, j, false);
		}
		j = maze.getRowNumber() + 1;
		for (i = 0;i < maze.getColNumber();++i) {
			place2x2(worldIn, org, config, i, j, false);
		}
			
		//Generate Loots
		BlockPos chestPos = org.east((int) maze.getColNumber() - 1).south((int) maze.getRowNumber() - 1);
		worldIn.setBlockState(chestPos, Blocks.GOLD_BLOCK.getDefaultState(), 2);
		worldIn.setBlockState(chestPos.up(), Blocks.CHEST.getDefaultState(), 2);
		if (worldIn.getTileEntity(chestPos.up()) instanceof ChestTileEntity) {
			((ChestTileEntity) worldIn.getTileEntity(chestPos.up())).setLootTable(new ResourceLocation(TwilightopiaMod.MODID, "chests/maze_large"), 20021222L + Minecraft.getInstance().world.getGameTime());  
		}
		return true;
	}
	
	private void place2x2(IWorld worldIn, BlockPos org, MazeConfig config, int i, int j, boolean passable) {
		BlockPos pos = org.east(i * 2).south(j * 2);
		for (int y = 4;y > 0;--y) {
			worldIn.setBlockState(pos.down(y), TwilightopiaSoilHelper.getChocoTypeIn(biome), 2);
			worldIn.setBlockState(pos.down(y).east(1), TwilightopiaSoilHelper.getChocoTypeIn(biome), 2);
			worldIn.setBlockState(pos.down(y).south(1), TwilightopiaSoilHelper.getChocoTypeIn(biome), 2);
			worldIn.setBlockState(pos.down(y).east(1).south(1), TwilightopiaSoilHelper.getChocoTypeIn(biome), 2);
		}
		worldIn.setBlockState(pos, passable ? gnd : und, 2);
		worldIn.setBlockState(pos.east(1), passable ? gnd : und, 2);
		worldIn.setBlockState(pos.south(1), passable ? gnd : und, 2);
		worldIn.setBlockState(pos.east(1).south(1), passable ? gnd : und, 2);
		for (int y = 1;y <= config.height;++y) {
			worldIn.setBlockState(pos.up(y), passable ? Blocks.AIR.getDefaultState() : config.wall, 2);
			worldIn.setBlockState(pos.up(y).east(1), passable ? Blocks.AIR.getDefaultState() : config.wall, 2);
			worldIn.setBlockState(pos.up(y).south(1), passable ? Blocks.AIR.getDefaultState() : config.wall, 2);
			worldIn.setBlockState(pos.up(y).east(1).south(1), passable ? Blocks.AIR.getDefaultState() : config.wall, 2);
		}
	}
}
