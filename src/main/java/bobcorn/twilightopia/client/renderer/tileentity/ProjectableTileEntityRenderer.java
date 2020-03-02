package bobcorn.twilightopia.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import bobcorn.twilightopia.client.renderer.ProjectableMapModel;
import bobcorn.twilightopia.config.TwilightopiaModConfig;
import bobcorn.twilightopia.tileentity.ProjectableTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.lwjgl.opengl.GL11;

import java.nio.ByteBuffer;

/**
 * Handles rendering all MiniModel TileEntities.
 * The render method is called once each frame for every visible MiniModel.
 * <p>
 * Renders a model of the surrounding blocks.
 * This should really probably not be in an examplemod for beginners,
 * but I added comments to it so its all good
 *
 * @author Cadiboo
 */
public class ProjectableTileEntityRenderer extends TileEntityRenderer<ProjectableTileEntity> {

	public ProjectableTileEntityRenderer(final TileEntityRendererDispatcher tileEntityRendererDispatcher) {
		super(tileEntityRendererDispatcher);
	}

	/**
	 * Render our TileEntity
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void func_225616_a_(final ProjectableTileEntity tileEntityIn, final float partialTicks, final MatrixStack matrixStack, final IRenderTypeBuffer renderTypeBuffer, final int packedLight, final int backupPackedLight) {

		final ProjectableMapModel miniModel = tileEntityIn.mapModel;

		if (miniModel == null)
			return;

		if (!miniModel.isBuilt())
			miniModel.rebuild();

		// Setup correct GL state
  		this.field_228858_b_.textureManager.bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
		RenderHelper.disableStandardItemLighting();
		// Translucency
		if (TwilightopiaModConfig.modelTranslucency) {
			RenderSystem.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
		} else {
			RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}
		RenderSystem.enableBlend();

		if (Minecraft.isAmbientOcclusionEnabled()) {
			RenderSystem.shadeModel(GL11.GL_SMOOTH);
		} else {
			RenderSystem.shadeModel(GL11.GL_FLAT);
		}

		//RenderSystem.pushMatrix();

 		// Translate to render pos. The 0.5 is to translate into the centre of the block, rather than to the corner of it
 		//RenderSystem.translated(x + 0.5, y + 0.5, z + 0.5);
 
 		//final double scale = TwilightopiaModConfig.modelScale;
 		//RenderSystem.scaled(scale, scale, scale);
    
 		// Translate to start of render (our TileEntity is at its centre)
 		//RenderSystem.translated(-8, -8, -8);
 
 		// Render the buffers
 		//renderChunkBuffers(miniModel.regionRenderCacheBuilder, miniModel.generator.getCompiledChunk());
 
 		//RenderSystem.popMatrix();
 
 		// Clean up GL state
 		//RenderHelper.enableStandardItemLighting();
 		//RenderHelper.func_227780_a_();
	}

	@Override
	public boolean isGlobalRenderer(final ProjectableTileEntity te) {
		return true;
	}

	@SuppressWarnings("unused")
	private void drawBufferWithoutResetting(final BufferBuilder bufferBuilder) {
		// Get the internal data from the BufferBuilder (This resets the BufferBuilder's own copy of this data)
		final ByteBuffer byteBuffer = bufferBuilder.func_227832_f_().getSecond();
		// Set the BufferBuilder's internal data to the original data
		bufferBuilder.putBulkData(byteBuffer);
		// Draw the BufferBuilder (This resets the BufferBuilder's data)
		WorldVertexBufferUploader.draw(bufferBuilder);
		// Set the BufferBuilder's internal data back to the original data
		bufferBuilder.putBulkData(byteBuffer);
	}

}
