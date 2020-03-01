package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class OrePlusBlock extends OreBlock {
   public OrePlusBlock(Block.Properties properties) {
      super(properties);
   }

   @Override
   public int getExpDrop(BlockState state, net.minecraft.world.IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
      return silktouch == 0 ? MathHelper.nextInt(RANDOM, 2, 5) : 0;
   }
}