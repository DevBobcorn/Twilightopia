package bobcorn.twilightopia.items;

import javax.annotation.Nullable;

import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class SignPlusItem extends WallOrFloorItem {
   public SignPlusItem(Item.Properties propertiesIn, Block floorBlockIn, Block wallBlockIn) {
      super(floorBlockIn, wallBlockIn, propertiesIn);
   }

   protected boolean onBlockPlaced(BlockPos pos, World worldIn, @Nullable PlayerEntity player, ItemStack stack, BlockState state) {
      boolean flag = super.onBlockPlaced(pos, worldIn, player, stack, state);
      System.out.println("Sign+ Placed!");
      
      if (!worldIn.isRemote && !flag && player != null && worldIn.getTileEntity(pos) instanceof SignPlusTileEntity) {
          //player.openSignEditor((SignTileEntity)worldIn.getTileEntity(pos));
    	  final SignPlusTileEntity tileEntity = (SignPlusTileEntity) worldIn.getTileEntity(pos);
    	  DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
    		  tileEntity.openGuiClient(tileEntity);
    	  });
    	  if (player instanceof ServerPlayerEntity) {
			  tileEntity.openGuiServer((ServerPlayerEntity) player,tileEntity);
		  }
      }
      return flag;
   }
}