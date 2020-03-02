package bobcorn.twilightopia.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TuliportalBlock extends Block {
   public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
   protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
   protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

   public TuliportalBlock(Block.Properties properties) {
      super(properties);
      this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
   }

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
      switch((Direction.Axis)state.get(AXIS)) {
      case Z:
         return Z_AABB;
      case X:
      default:
         return X_AABB;
      }
   }

   public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
	   /*
	   if (TwilightopiaDimensions.getDimensionType() == null) {
		   System.out.println("ERROR:DimensionTypeIsNull!");
		   return;
	   }
      if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && VoxelShapes.compare(VoxelShapes.create(entityIn.getBoundingBox().offset((double)(-pos.getX()), (double)(-pos.getY()), (double)(-pos.getZ()))), state.getShape(worldIn, pos), IBooleanFunction.AND)) {
          //entityIn.changeDimension(worldIn.dimension.getType() == TPWDimensions.TWILIGHTOPIA ? DimensionType.OVERWORLD : TPWDimensions.TWILIGHTOPIA);
    	  //The above line of code will generate a nether portal, which we don't want...
    	  if (entityIn instanceof PlayerEntity) {
    		  PlayerEntity player = (PlayerEntity)entityIn;
    		  if (player.getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.lodge_herb.get()) {
    			  if (!player.isCreative())
    				  player.getHeldItem(Hand.MAIN_HAND).shrink(1);
    		  } else return;
    	  }
    	  TeleportationUtil.changeDimension(worldIn.dimension.getType() == DimensionType.OVERWORLD ? TwilightopiaDimensions.getDimensionType() : DimensionType.OVERWORLD, entityIn, entityIn.world.getSpawnPoint());
       }
       */
   }

   /**
    * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
    * this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
    * of whether the block can receive random update ticks
    */
   @OnlyIn(Dist.CLIENT)
   public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      if (rand.nextInt(100) == 0) {
         worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
      }

      for(int i = 0; i < 4; ++i) {
         double d0 = (double)((float)pos.getX() + rand.nextFloat());
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)((float)pos.getZ() + rand.nextFloat());
         double d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         double d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         double d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
         int j = rand.nextInt(2) * 2 - 1;
         if (worldIn.getBlockState(pos.west()).getBlock() != this && worldIn.getBlockState(pos.east()).getBlock() != this) {
            d0 = (double)pos.getX() + 0.5D + 0.25D * (double)j;
            d3 = (double)(rand.nextFloat() * 2.0F * (float)j);
         } else {
            d2 = (double)pos.getZ() + 0.5D + 0.25D * (double)j;
            d5 = (double)(rand.nextFloat() * 2.0F * (float)j);
         }

         worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
      }

   }

   public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
      return ItemStack.EMPTY;
   }

   /**
    * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
    * blockstate.
    * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever possible. Implementing/overriding is
    * fine.
    */
   public BlockState rotate(BlockState state, Rotation rot) {
      switch(rot) {
      case COUNTERCLOCKWISE_90:
      case CLOCKWISE_90:
         switch((Direction.Axis)state.get(AXIS)) {
         case Z:
            return state.with(AXIS, Direction.Axis.X);
         case X:
            return state.with(AXIS, Direction.Axis.Z);
         default:
            return state;
         }
      default:
         return state;
      }
   }

   protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
      builder.add(AXIS);
   }
   
   public static final Boolean checkPortal(World worldIn,BlockPos tulipos) {
	   if (!checkXPortal(worldIn, tulipos)) return checkZPortal(worldIn, tulipos);
	   return true;
   }
   
   private static final Boolean checkXPortal(World worldIn,BlockPos tulipos) {
	   //Check X Direction
	   int radius = 1;
	   int radius2;
	   int height = 1;
	   int height2 = 1;
	   Boolean flag = false;
	   Boolean flag2 = false;
	   Boolean flag3 = false;
	   while (radius < 5 && worldIn.getBlockState(tulipos.add(radius, 0, 0)) != Blocks.VOID_AIR.getDefaultState()) {
		   BlockState curBlock = worldIn.getBlockState(tulipos.add(radius, 0, 0));
		   if (curBlock.getBlock() != Blocks.AIR) {
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   flag = true;
			   break;
		   }
		   radius++;
	   }
	   //System.out.println("Radius Found: " + radius);
	   if (flag) //Check +X pillar
		   while (height < 6 && worldIn.getBlockState(tulipos.add(radius, height, 0)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(radius, height, 0));
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   height++;
			   else break;
		   }
	   //System.out.println("Pillar 1 Height: " + height);
	   if (height >= 2) {//Check -X pillar
		   while (height2 <= height && worldIn.getBlockState(tulipos.add(-radius, height2, 0)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(-radius, height2, 0));
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   height2++;
			   else break;
		   }
	   }
	   //System.out.println("Pillar 2 Height: " + height);
	   if (height2 == height && height >= 2) {
		   //System.out.println("Pillar Available!");
		   flag2 = true;
		   radius2 = -(radius - 1);
		   while (radius2 < radius && worldIn.getBlockState(tulipos.add(radius2, height - 1, 0)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height - 1, 0));
			   if (curBlock.getBlock() != Blocks.DARK_OAK_LOG || curBlock.get(RotatedPillarBlock.AXIS) != Direction.Axis.X) {
				   flag2 = false;
				   break;
			   }
			   radius2++;
		   }
		   if (flag2) {
			   flag3 = true;
			   //System.out.println("Horizontal Pillar Available!");
			   //Check Roof 1
			   radius2 = -(radius + 1);
			   while (radius2 < 0 && worldIn.getBlockState(tulipos.add(radius2, height + (radius2 + radius), 0)) != Blocks.VOID_AIR.getDefaultState()) {
				   BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height + (radius2 + radius), 0));
				   if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS || curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.EAST)
					   flag3 = false;
				   //worldIn.setBlockState(tulipos.add(radius2, height + (radius2 + radius), 0), Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST));
				   radius2++;
			   }
			   //Check Roof 2
			   radius2 = 1;
			   while (radius2 <= radius + 1 && worldIn.getBlockState(tulipos.add(radius2, height + (radius - radius2), 0)) != Blocks.VOID_AIR.getDefaultState()) {
				   BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height + (radius - radius2), 0));
				   if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS || curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.WEST)
					   flag3 = false;
				   //worldIn.setBlockState(tulipos.add(radius2, height + (radius - radius2), 0), Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST));
				   radius2++;
			   }
		   }
	   }
	   if (flag3) {
		   System.out.println("Portal Available!");
		   //Build Portal Blocks...
		   for (int targetX = -radius + 1;targetX < radius;targetX++)
			   for (int targetY = 0;targetY < height - 1;targetY++) {
				   worldIn.setBlockState(tulipos.add(targetX, targetY, 0), ModBlocks.TULIPORTAL.get().getDefaultState().with(TuliportalBlock.AXIS, Direction.Axis.X));
			   }		   
	   }
	   return flag3;
   }

   private static final Boolean checkZPortal(World worldIn,BlockPos tulipos) {
	   //Check X Direction
	   int radius = 1;
	   int radius2;
	   int height = 1;
	   int height2 = 1;
	   Boolean flag = false;
	   Boolean flag2 = false;
	   Boolean flag3 = false;
	   while (radius < 5 && worldIn.getBlockState(tulipos.add(0, 0, radius)) != Blocks.VOID_AIR.getDefaultState()) {
		   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, 0, radius));
		   if (curBlock.getBlock() != Blocks.AIR) {
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   flag = true;
			   break;
		   }
		   radius++;
	   }
	   //System.out.println("Radius Found: " + radius);
	   if (flag) //Check +X pillar
		   while (height < 6 && worldIn.getBlockState(tulipos.add(0, height, radius)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height, radius));
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   height++;
			   else break;
		   }
	   //System.out.println("Pillar 1 Height: " + height);
	   if (height >= 2) {//Check -X pillar
		   while (height2 <= height && worldIn.getBlockState(tulipos.add(0, height2, -radius)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height2, -radius));
			   if (curBlock.getBlock() == Blocks.DARK_OAK_LOG && curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
				   height2++;
			   else break;
		   }
	   }
	   //System.out.println("Pillar 2 Height: " + height);
	   if (height2 == height && height >= 2) {
		   //System.out.println("Pillar Available!");
		   flag2 = true;
		   radius2 = -(radius - 1);
		   while (radius2 < radius && worldIn.getBlockState(tulipos.add(0, height - 1, radius2)) != Blocks.VOID_AIR.getDefaultState()) {
			   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height - 1, radius2));
			   if (curBlock.getBlock() != Blocks.DARK_OAK_LOG || curBlock.get(RotatedPillarBlock.AXIS) != Direction.Axis.Z) {
				   flag2 = false;
				   break;
			   }
			   radius2++;
		   }
		   if (flag2) {
			   flag3 = true;
			   //System.out.println("Horizontal Pillar Available!");
			   //Check Roof 1
			   radius2 = -(radius + 1);
			   while (radius2 < 0 && worldIn.getBlockState(tulipos.add(0, height + (radius2 + radius), radius2)) != Blocks.VOID_AIR.getDefaultState()) {
				   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height + (radius2 + radius), radius2));
				   if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS || curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.SOUTH)
					   flag3 = false;
				   //worldIn.setBlockState(tulipos.add(radius2, height + (radius2 + radius), 0), Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST));
				   radius2++;
			   }
			   //Check Roof 2
			   radius2 = 1;
			   while (radius2 <= radius + 1 && worldIn.getBlockState(tulipos.add(0, height + (radius - radius2), radius2)) != Blocks.VOID_AIR.getDefaultState()) {
				   BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height + (radius - radius2), radius2));
				   if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS || curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.NORTH)
					   flag3 = false;
				   //worldIn.setBlockState(tulipos.add(radius2, height + (radius - radius2), 0), Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST));
				   radius2++;
			   }
		   }
	   }
	   if (flag3) {
		   System.out.println("Portal Available!");
		   //Build Portal Blocks...
		   for (int targetZ = -radius + 1;targetZ < radius;targetZ++)
			   for (int targetY = 0;targetY < height - 1;targetY++) {
				   worldIn.setBlockState(tulipos.add(0, targetY, targetZ), ModBlocks.TULIPORTAL.get().getDefaultState().with(TuliportalBlock.AXIS, Direction.Axis.Z));
			   }		   
	   }
	   return flag3;
   }
}