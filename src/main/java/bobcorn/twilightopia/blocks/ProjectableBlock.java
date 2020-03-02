package bobcorn.twilightopia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;

import bobcorn.twilightopia.client.gui.ProjectableScreen;
import bobcorn.twilightopia.tileentity.ModTileEntityType;
import bobcorn.twilightopia.tileentity.ProjectableTileEntity;

public class ProjectableBlock extends Block {
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);
	
	public ProjectableBlock(final Properties properties) {
		super(properties);
	}

	@Override
	public boolean hasTileEntity(final BlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) {
		// Always use TileEntityType#create to allow registry overrides to work.
		return ModTileEntityType.PROJECTABLE.get().create();
	}

   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
	      return SHAPE;
	   }

    //On Block Activated
	@Override
	public ActionResultType func_225533_a_(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) {
		// Only open the gui on the physical client
		DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> openGui(worldIn, pos));
		return ActionResultType.SUCCESS;
	}

	// @OnlyIn(Dist.CLIENT) Makes it so this method will be removed from the class on the PHYSICAL SERVER
	// This is because we only want to handle opening the GUI on the physical client.
	@OnlyIn(Dist.CLIENT)
	private void openGui(final World worldIn, final BlockPos pos) {
		// Only handle opening the Gui screen on the logical client
		if (worldIn.isRemote) {
			final TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof ProjectableTileEntity) {
				Minecraft.getInstance().displayGuiScreen(new ProjectableScreen(((ProjectableTileEntity) tileEntity)));
			}
		}
	}
}
