package bobcorn.twilightopia.world.gen.feature;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.blocks.TwilightopiaSoilHelper;

import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class CandyCaneFeature extends AbstractTreeFeature<NoFeatureConfig> {
   private static final BlockState[] CANDIES = { 
		   ModBlocks.CANDY_BLOCK.getDefaultState(), 
		   ModBlocks.RED_CANDY_BLOCK.getDefaultState(),
		   ModBlocks.ORANGE_CANDY_BLOCK.getDefaultState(),
		   ModBlocks.YELLOW_CANDY_BLOCK.getDefaultState(),
		   ModBlocks.PINK_CANDY_BLOCK.getDefaultState(),
		   ModBlocks.PURPLE_CANDY_BLOCK.getDefaultState(),
   };
   private static final Direction[] DIRECTIONS = { 
		   Direction.EAST,
		   Direction.WEST,
		   Direction.NORTH,
		   Direction.SOUTH
   };

   private final boolean useExtraRandomHeight;

   public CandyCaneFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configIn, boolean doBlockNotifyIn, boolean extraRandomHeightIn) {
      super(configIn, doBlockNotifyIn);
      this.useExtraRandomHeight = extraRandomHeightIn;
      //this.setSapling((net.minecraftforge.common.IPlantable)ModBlocks.CHERRY_SAPLING);
   }

   public boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox p_208519_5_) {
      BlockState CANDY1;
      BlockState CANDY2;
      
      Direction DIR = DIRECTIONS[rand.nextInt(4)];
      
	  if (rand.nextBoolean()) {
    	  int col1 = rand.nextInt(6);
    	  int col2 = rand.nextInt(6);
    	  if (col1 == col2) col2 = (col2 + 1) % 6;
    	  CANDY1 = CANDIES[col1];
    	  CANDY2 = CANDIES[col2];
      } else {
    	  CANDY1 = CANDIES[0];
    	  CANDY2 = CANDIES[1];
      }
	  
	  int pillar = rand.nextInt(3) + 5;
      if (this.useExtraRandomHeight) {
         pillar += rand.nextInt(7);
      }

      BlockPos.MutableBlockPos curPos = new BlockPos.MutableBlockPos();
      
      boolean flag = true;
      if (position.getY() >= 1 && position.getY() + pillar <= worldIn.getMaxHeight()) {
    	 // Pillar
         for (int j = position.getY(); j <= position.getY() + pillar; ++j) {
        	 if (!func_214587_a(worldIn, curPos.setPos(position.getX(), j, position.getZ()))) {
                 flag = false;
             }
         }
         
         // Beam
         for (int k = 1; k <= 3 ; ++k) {
        	 if (!func_214587_a(worldIn, curPos.setPos(position.offset(DIR,k).up(pillar + 1)))) {
                 flag = false;
             }
         }
         if (!func_214587_a(worldIn, curPos.setPos(position.offset(DIR,4).up(pillar)))) {
             flag = false;
         }
         
         if (!flag) {
            return false;
         } else if ((TwilightopiaSoilHelper.isChoco(worldIn, position.down())) && position.getY() < worldIn.getMaxHeight() - pillar - 1) {
        	TwilightopiaSoilHelper.RemoveGrassAt(worldIn, position.down());

        	Boolean alt = true;
       	 	// Pillar
            for (int j1 = position.getY(); j1 <= position.getY() + pillar; ++j1) {
            	if (isAirOrLeaves(worldIn, curPos.setPos(position.getX(), j1, position.getZ()))) {
           			this.setLogState(changedBlocks, worldIn, curPos.setPos(position.getX(), j1, position.getZ()), alt ? CANDY1 :CANDY2, p_208519_5_);
           			alt = !alt;
                }
            }
            
            // Beam
            for (int k = 1; k <= 3 ; ++k) {
            	if (isAirOrLeaves(worldIn, curPos.setPos(position.offset(DIR,k).up(pillar + 1)))) {
           		 	this.setLogState(changedBlocks, worldIn, curPos.setPos(position.offset(DIR,k).up(pillar + 1)), alt ? CANDY1 :CANDY2, p_208519_5_);
                    alt = !alt;
                }
            }
            if (isAirOrLeaves(worldIn, curPos.setPos(position.offset(DIR,4).up(pillar)))) {
            	this.setLogState(changedBlocks, worldIn, curPos.setPos(position.offset(DIR,4).up(pillar)), alt ? CANDY1 :CANDY2, p_208519_5_);
            }
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }
}