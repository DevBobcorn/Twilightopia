package bobcorn.twilightopia.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class QuillEdit {
	public static boolean Use(World world, BlockPos pos, PlayerEntity player) {
		if (!world.isRemote && player != null && world.getTileEntity(pos) instanceof SignTileEntity) {
			//System.out.println("Edit Sign!");
			final SignTileEntity tileEntity = (SignTileEntity) world.getTileEntity(pos);
			tileEntity.setEditable(true);
			player.openSignEditor(tileEntity);
		}
		return false;
	}
}
