package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
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
		         return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND;
		      });
	   }
	   
	   public static boolean isSoil(IBlockReader worldIn, BlockPos pos) {
		   	  Block block = worldIn.getBlockState(pos).getBlock();
	          return block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND;
	   }
	   
	   public static boolean isDarkChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         Block block = p_214586_0_.getBlock();
		         return block == ModBlocks.CHOCOLATE_BLOCK.get() || block == ModBlocks.GRASSED_CHOCOLATE_BLOCK.get();
		      });
	   }
	   
	   public static boolean isDarkChoco(IBlockReader worldIn, BlockPos pos) {
		      Block block = worldIn.getBlockState(pos).getBlock();
		      return block == ModBlocks.CHOCOLATE_BLOCK.get() || block == ModBlocks.GRASSED_CHOCOLATE_BLOCK.get();
	   }
	   
	   public static boolean isWhiteChoco(IWorldGenerationBaseReader worldIn, BlockPos pos) {
		      return worldIn.hasBlockState(pos, (p_214586_0_) -> {
		         Block block = p_214586_0_.getBlock();
		         return block == ModBlocks.WHITE_CHOCOLATE_BLOCK.get() || block == ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.get();
		      });
	   }
	   
	   public static boolean isWhiteChoco(IBlockReader worldIn, BlockPos pos) {
		      Block block = worldIn.getBlockState(pos).getBlock();
		      return block == ModBlocks.WHITE_CHOCOLATE_BLOCK.get() || block == ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.get();
	   }
	   
	   public static void RemoveGrassAt(IWorldGenerationReader p_214584_1_, BlockPos p_214584_2_) {
		      if (isWhiteChoco(p_214584_1_, p_214584_2_)) {
		    	  p_214584_1_.setBlockState(p_214584_2_, ModBlocks.WHITE_CHOCOLATE_BLOCK.get().getDefaultState(), 19);
		      } else if (isDarkChoco(p_214584_1_, p_214584_2_)) {
		    	  p_214584_1_.setBlockState(p_214584_2_, ModBlocks.CHOCOLATE_BLOCK.get().getDefaultState(), 19);
		      } else p_214584_1_.setBlockState(p_214584_2_, Blocks.DIRT.getDefaultState(), 19);
		   }
	   
}
