package bobcorn.twilightopia.blocks;

import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public abstract class AbstractSignPlusBlock extends AbstractSignBlock {
	protected AbstractSignPlusBlock(Block.Properties properties) {
		super(properties);
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new SignPlusTileEntity();
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn,
			BlockRayTraceResult hit) {
		if (worldIn.isRemote) {
			//System.out.println("World's remote!");
			return true;
		} else {
			//System.out.println("Interact!");
			TileEntity tileentity = worldIn.getTileEntity(pos);
			if (tileentity instanceof SignPlusTileEntity) {
				SignPlusTileEntity signtileentity = (SignPlusTileEntity) tileentity;
				ItemStack itemstack = player.getHeldItem(handIn);
				if (itemstack.getItem() instanceof DyeItem && player.abilities.allowEdit) {
					//System.out.println("Dye it!");
					boolean flag = signtileentity.setTextColor(((DyeItem) itemstack.getItem()).getDyeColor());
					if (flag && !player.isCreative()) {
						itemstack.shrink(1);
					}
				}
				return signtileentity.executeCommand(player);
			} else {
				return false;
			}
		}
	}
}