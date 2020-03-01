package bobcorn.twilightopia.tileentity;

import bobcorn.twilightopia.client.renderer.ProjectableMapModel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;



public class ProjectableTileEntity extends TileEntity {

	@Nullable // May be accessed before onLoad
	// @OnlyIn(Dist.CLIENT) Makes it so this field will be removed from the class on the PHYSICAL SERVER
	// This is because we only want the MiniModel on the physical client - its rendering only.
	@OnlyIn(Dist.CLIENT)
	public ProjectableMapModel mapModel;

	// Constructor without hardcoded TileEntityType so that subclasses can use their own.
	// Alternatively, subclasses can also override getType if a hardcoded type is used in a superclass' constructor
	public ProjectableTileEntity(final TileEntityType<?> type) {
		super(type);
	}

	public ProjectableTileEntity() {
		this(ModTileEntityType.PROJECTOR_MAP);
	}

	// @OnlyIn(Dist.CLIENT) Makes it so this method will be removed from the class on the PHYSICAL SERVER
	// This is because we only want the MiniModel on the physical client - its rendering only.
	@OnlyIn(Dist.CLIENT)
	@Override
	public void onLoad() {
		super.onLoad();
		World world = getWorld();
		if (world == null || !world.isRemote)
			return; // Return if the world is null or if we are on the logical server
		mapModel = ProjectableMapModel.forTileEntity(this);
	}

	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		// This, combined with isGlobalRenderer in the TileEntityRenderer makes it so that the
		// render does not disappear if the player can't see the block
		// This is useful for rendering larger models or dynamically sized models
		return INFINITE_EXTENT_AABB;
	}

}