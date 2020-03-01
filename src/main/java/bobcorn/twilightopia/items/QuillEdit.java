package bobcorn.twilightopia.items;

import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class QuillEdit {
	public static boolean Use(World world, BlockPos pos, PlayerEntity player) {
		System.out.println("Open GUI!");
		if (!world.isRemote && player != null && world.getTileEntity(pos) instanceof SignPlusTileEntity) {
			// player.openSignEditor((SignTileEntity)worldIn.getTileEntity(pos));
			//System.out.println("Edit Sign+!");
			final SignPlusTileEntity tileEntity = (SignPlusTileEntity) world.getTileEntity(pos);
			DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
				tileEntity.setEditable(true);
				tileEntity.openGuiClient(tileEntity);
			});
			if (player instanceof ServerPlayerEntity) {
				tileEntity.setEditable(true);
				tileEntity.openGuiServer((ServerPlayerEntity) player, tileEntity);
			}
			return true;
		}

		if (!world.isRemote && player != null && world.getTileEntity(pos) instanceof SignTileEntity) {
			//System.out.println("Edit Sign!");
			final SignTileEntity tileEntity = (SignTileEntity) world.getTileEntity(pos);
			tileEntity.setEditable(true);
			player.openSignEditor(tileEntity);
		}
		return false;
	}
}
