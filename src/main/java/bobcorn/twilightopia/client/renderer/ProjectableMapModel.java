package bobcorn.twilightopia.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.client.renderer.chunk.ChunkRender;
import net.minecraft.client.renderer.chunk.ChunkRenderTask;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * A wrapper around ChunkRender.
 * Stores the a render of the chunk (16x16x16) surrounding a TileEntity
 */
public class ProjectableMapModel {

	// Cache the result of BlockRenderLayer.values() instead of calling BlockRenderLayer.values()
	// each time (because each call creates a new BlockRenderLayer[] which is wasteful)
	public static final BlockRenderLayer[] BLOCK_RENDER_LAYERS = BlockRenderLayer.values();

	// We only create one of these per cache, we reset it each time we rebuild
	public final RegionRenderCacheBuilder regionRenderCacheBuilder;
	private final ChunkRender chunkRender;
	public ChunkRenderTask generator;

	private ProjectableMapModel(final ChunkRender chunkRender, final RegionRenderCacheBuilder regionRenderCacheBuilder) {
		this.chunkRender = chunkRender;
		this.regionRenderCacheBuilder = regionRenderCacheBuilder;
		rebuild();
	}

	public static ProjectableMapModel forTileEntity(final TileEntity tileEntity) {
		final ChunkRender chunkRender = new ChunkRender(tileEntity.getWorld(), Minecraft.getInstance().worldRenderer);
		final BlockPos pos = tileEntity.getPos();

		// We want to render everything in a 16x16x16 radius, with the centre being the TileEntity
		chunkRender.setPosition(pos.getX() - 8, pos.getY() - 8, pos.getZ() - 8);

		return new ProjectableMapModel(chunkRender, new RegionRenderCacheBuilder());
	}

	/**
	 * (re)build the render
	 */
	public void rebuild() {
		final ChunkRender chunkRender = this.chunkRender;
		final RegionRenderCacheBuilder buffers = this.regionRenderCacheBuilder;

		final ChunkRenderTask generator = chunkRender.makeCompileTaskChunk();
		this.generator = generator;

		// Setup generator
		generator.setStatus(ChunkRenderTask.Status.COMPILING);
		generator.setRegionRenderCacheBuilder(buffers);

		final Vec3d vec3d = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();

		// Rebuild the ChunkRender.
		// This resets all the buffers it uses and renders every block in the chunk to the buffers
		chunkRender.rebuildChunk((float) vec3d.x, (float) vec3d.y, (float) vec3d.z, generator);

		// ChunkRender#rebuildChunk increments this, we don't want it incremented so we decrement it.
		--ChunkRender.renderChunksUpdated;

		// Set the translation of each buffer back to 0
		final int length = BLOCK_RENDER_LAYERS.length;
		for (int ordinal = 0; ordinal < length; ++ordinal) {
			buffers.getBuilder(ordinal).setTranslation(0, 0, 0);
		}

	}

}
