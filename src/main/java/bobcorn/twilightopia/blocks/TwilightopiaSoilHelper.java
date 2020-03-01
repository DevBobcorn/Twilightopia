package bobcorn.twilightopia.blocks;

import bobcorn.twilightopia.world.biome.TwilightopiaBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;

public class TwilightopiaSoilHelper {
	   public static boolean isSoilOrChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         return isSoil(worldIn,pos) || isDarkChoco(worldIn,pos) || isWhiteChoco(worldIn,pos);
		      });
	   }
	   
	   public static boolean isSoilOrChoco(IBlockReader worldIn, BlockPos pos) {
		   	  return isSoil(worldIn,pos) || isDarkChoco(worldIn,pos) || isWhiteChoco(worldIn,pos);
	   }
	   
	   public static boolean isChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         return isDarkChoco(worldIn,pos) || isWhiteChoco(worldIn,pos);
		      });
	   }
	   
	   public static boolean isChoco(IBlockReader worldIn, BlockPos pos) {
		   	  return isDarkChoco(worldIn,pos) || isWhiteChoco(worldIn,pos);
	   }
	   
	   public static boolean isSoil(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         Block block = p_214586_0_.getBlock();
		         return Block.isDirt(block) || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND;
		      });
	   }
	   
	   public static boolean isSoil(IBlockReader worldIn, BlockPos pos) {
		   	  Block block = worldIn.getBlockState(pos).getBlock();
	          return Block.isDirt(block) || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND;
	   }
	   
	   public static boolean isDarkChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         Block block = p_214586_0_.getBlock();
		         return block == ModBlocks.CHOCOLATE_BLOCK || block == ModBlocks.GRASSED_CHOCOLATE_BLOCK;
		      });
	   }
	   
	   public static boolean isDarkChoco(IBlockReader worldIn, BlockPos pos) {
		      Block block = worldIn.getBlockState(pos).getBlock();
		      return block == ModBlocks.CHOCOLATE_BLOCK || block == ModBlocks.GRASSED_CHOCOLATE_BLOCK;
	   }
	   
	   public static boolean isWhiteChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         Block block = p_214586_0_.getBlock();
		         return block == ModBlocks.WHITE_CHOCOLATE_BLOCK || block == ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK || block == ModBlocks.MISTY_CHOCOLATE_BLOCK;
		      });
	   }
	   
	   public static boolean isWhiteChoco(IBlockReader worldIn, BlockPos pos) {
		      Block block = worldIn.getBlockState(pos).getBlock();
		      return block == ModBlocks.WHITE_CHOCOLATE_BLOCK || block == ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK || block == ModBlocks.MISTY_CHOCOLATE_BLOCK;
	   }
	   
	   public static void RemoveGrassAt(IWorldGenerationReader p_214584_1_, BlockPos p_214584_2_) {
		      if (isWhiteChoco(p_214584_1_, p_214584_2_)) {
		    	  p_214584_1_.setBlockState(p_214584_2_, ModBlocks.WHITE_CHOCOLATE_BLOCK.getDefaultState(), 19);
		      } else if (isDarkChoco(p_214584_1_, p_214584_2_)) {
		    	  p_214584_1_.setBlockState(p_214584_2_, ModBlocks.CHOCOLATE_BLOCK.getDefaultState(), 19);
		      } else p_214584_1_.setBlockState(p_214584_2_, Blocks.DIRT.getDefaultState(), 19);
		   }
	   
	   private static final BlockState chocoD = ModBlocks.CHOCOLATE_BLOCK.getDefaultState();
	   private static final BlockState chocoM = ModBlocks.WHITE_CHOCOLATE_BLOCK.getDefaultState();
	   
	   private static final BlockState grassyF = ModBlocks.MISTY_CHOCOLATE_BLOCK.getDefaultState();
	   private static final BlockState grassyD = ModBlocks.GRASSED_CHOCOLATE_BLOCK.getDefaultState();
	   private static final BlockState grassyM = ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.getDefaultState();
	   
	   public static BlockState getChocoTypeIn(Biome biome) {
		   if (biome == TwilightopiaBiomes.TASTY_LAND) return chocoD;
		   else if (biome == TwilightopiaBiomes.TASTY_TUNDRA) return chocoM;
		   else if (biome == TwilightopiaBiomes.HOLY_WOODS) return chocoM;
		   else if (biome == TwilightopiaBiomes.TASTY_PLAINS) return chocoD;
		   else if (biome == TwilightopiaBiomes.FOGGY_FOREST) return chocoM;
		   return chocoD;
	   }
	   
	   public static BlockState getGrassyChocoTypeIn(Biome biome) {
		   if (biome == TwilightopiaBiomes.TASTY_LAND) return grassyD;
		   else if (biome == TwilightopiaBiomes.TASTY_TUNDRA) return grassyM;
		   else if (biome == TwilightopiaBiomes.HOLY_WOODS) return grassyM;
		   else if (biome == TwilightopiaBiomes.TASTY_PLAINS) return grassyD;
		   else if (biome == TwilightopiaBiomes.FOGGY_FOREST) return grassyF;
		   return chocoD;
	   }
}
