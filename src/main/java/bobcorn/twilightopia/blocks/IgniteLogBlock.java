package bobcorn.twilightopia.blocks;

import java.util.Random;

import bobcorn.twilightopia.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class IgniteLogBlock extends LogBlock implements IGrowable {
   public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
   public static final IntegerProperty AGE = BlockStateProperties.AGE_0_5;
   
   public IgniteLogBlock(MaterialColor verticalColor, Block.Properties properties) {
      super(verticalColor, properties);
      this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(AGE, 0));
   }
   
   public int getLightValue(BlockState state) {
	   return 12;
   }
   
   //On Block Activated
   @Override
   public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity entityIn, Hand handIn, BlockRayTraceResult hit) {
	   if (state.get(AGE) >= 5) {
		   if (entityIn.getHeldItemMainhand().getItem().equals(Items.DIAMOND_PICKAXE) || entityIn.getHeldItemMainhand().getItem().equals(Items.GOLDEN_PICKAXE)) {
			   spawnAsEntity(worldIn,pos,new ItemStack(ModItems.flame_nut.get()));
			   worldIn.setBlockState(pos, harvestNut(state,worldIn,pos), 3);
			   return ActionResultType.SUCCESS;
		   }
	   }
	   return ActionResultType.FAIL;
   }
   
   /**
    * Returns the block-state with the given rotation from the passed block-state. If inapplicable, returns the passed
    * block-state.
    * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
    * fine.
    */

   public BlockState rotate(BlockState state, Rotation rot) {
      switch(rot) {
      case COUNTERCLOCKWISE_90:
      case CLOCKWISE_90:
         switch((Direction.Axis)state.get(AXIS)) {
         case X:
            return state.with(AXIS, Direction.Axis.Z);
         case Z:
            return state.with(AXIS, Direction.Axis.X);
         default:
            return state;
         }
      default:
         return state;
      }
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AXIS);
      builder.add(AGE);
   }

   public BlockState getStateForPlacement(BlockItemUseContext context) {
      return this.getDefaultState().with(AXIS, context.getFace().getAxis());
   }
   
   //Random Tick TODO Confirm
   @Override
   public void func_225542_b_(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
	   worldIn.setBlockState(pos, updateAge(state, worldIn, pos), 3);
   }

   private static BlockState updateAge(BlockState state, IWorld worldIn, BlockPos pos) {
	   int new_age = state.get(AGE);
	   if (new_age >= 5) return state;
	   return state.with(AGE, Integer.valueOf(new_age));
   }
   
   private static BlockState harvestNut(BlockState state, IWorld worldIn, BlockPos pos) {
	   return state.with(AGE, Integer.valueOf(0));
   }
   
   private static BlockState growNut(BlockState state, IWorld worldIn, BlockPos pos) {
	   return state.with(AGE, Integer.valueOf((state.get(AGE) + 1) > 5 ? 5 : (state.get(AGE) + 1)));
   }

	@Override
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}
	
	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return true;
	}

	//grow
	@Override
	public void func_225535_a_(ServerWorld p_225535_1_, Random p_225535_2_, BlockPos p_225535_3_,
			BlockState p_225535_4_) {
		p_225535_1_.setBlockState(p_225535_3_, growNut(p_225535_4_,p_225535_1_,p_225535_3_), 3);
	}
}