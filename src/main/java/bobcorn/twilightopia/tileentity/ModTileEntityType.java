package bobcorn.twilightopia.tileentity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModTileEntityType {
	// We don't have a datafixer for our TileEntity, so we pass null into build
	/*
	public static final TileEntityType<?> PROJECTOR_MAP = TileEntityType.Builder.create(ProjectableTileEntity::new, ModBlocks.PROJECTABLE).build(null);
	public static final TileEntityType<?> PROPHET_LOG = TileEntityType.Builder.create(ProphetLogTileEntity::new, ModBlocks.PROPHET_LOG).build(null);
	public static final TileEntityType<?> SIGN_PLUS = TileEntityType.Builder.create(SignPlusTileEntity::new, ModBlocks.CHERRY_SIGN, ModBlocks.CHERRY_WALL_SIGN, ModBlocks.IGNITE_SIGN, ModBlocks.IGNITE_WALL_SIGN).build(null);
	*/
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, TwilightopiaMod.MODID);

	// We don't have a datafixer for our TileEntities, so we pass null into build.
	public static final RegistryObject<TileEntityType<ProjectableTileEntity>> PROJECTABLE = TILE_ENTITY_TYPES.register("projectable", () ->
			TileEntityType.Builder.create(ProjectableTileEntity::new, ModBlocks.PROJECTABLE.get())
					.build(null)
	);
	public static final RegistryObject<TileEntityType<ProphetLogTileEntity>> PROPHET_LOG = TILE_ENTITY_TYPES.register("prophet_log", () ->
			TileEntityType.Builder.create(ProphetLogTileEntity::new, ModBlocks.PROPHET_LOG.get())
					.build(null)
	);
}
