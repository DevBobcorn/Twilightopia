package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import java.util.Random;

import javax.annotation.Nullable;

import bobcorn.twilightopia.tileentity.ModTileEntityType;
import bobcorn.twilightopia.tileentity.ProphetLogTileEntity;

public class ProphetLogBlock extends HorizontalBlock {
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

	public ProphetLogBlock(final Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		return ModTileEntityType.PROPHET_LOG.get().create();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
		super.animateTick(state, world, pos, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int i = x;
		int j = y;
		int k = z;
		if (true)
			for (int l = 0; l < 4; ++l) {
				double d0 = (i + random.nextFloat());
				double d1 = (j + random.nextFloat());
				double d2 = (k + random.nextFloat());
				double d3 = (random.nextFloat() - 0.5D) * 0.5D;
				double d4 = (random.nextFloat() - 0.5D) * 0.5D;
				double d5 = (random.nextFloat() - 0.5D) * 0.5D;
				world.addParticle(ParticleTypes.ENCHANT, d0, d1, d2, d3, d4, d5);
			}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
		if (state.getBlock() != newState.getBlock()) {
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ProphetLogTileEntity) {
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
						((ProphetLogTileEntity) tileEntity).rubySlot.getStackInSlot(0));
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
						((ProphetLogTileEntity) tileEntity).bookSlot.getStackInSlot(0));
				InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(),
						((ProphetLogTileEntity) tileEntity).itemSlot.getStackInSlot(0));
			}
		}
		super.onReplaced(state, worldIn, pos, newState, isMoving);
	}

	@Override
	public ActionResultType func_225533_a_(final BlockState state, final World worldIn, final BlockPos pos,
			final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
		if (!worldIn.isRemote) {
			final TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ProphetLogTileEntity) {
				NetworkHooks.openGui(((ServerPlayerEntity) player), ((ProphetLogTileEntity) tileEntity), pos);
			}
		}
		return ActionResultType.SUCCESS;
	}

	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
}
