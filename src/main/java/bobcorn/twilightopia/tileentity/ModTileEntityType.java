package bobcorn.twilightopia.tileentity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TwilightopiaMod.MODID)
public final class ModTileEntityType {
	// We don't have a datafixer for our TileEntity, so we pass null into build
	public static final TileEntityType<?> PROJECTOR_MAP = TileEntityType.Builder.create(ProjectableTileEntity::new, ModBlocks.PROJECTABLE).build(null);
	public static final TileEntityType<?> PROPHET_LOG = TileEntityType.Builder.create(ProphetLogTileEntity::new, ModBlocks.PROPHET_LOG).build(null);
	public static final TileEntityType<?> SIGN_PLUS = TileEntityType.Builder.create(SignPlusTileEntity::new, ModBlocks.CHERRY_SIGN, ModBlocks.CHERRY_WALL_SIGN, ModBlocks.IGNITE_SIGN, ModBlocks.IGNITE_WALL_SIGN).build(null);
}
