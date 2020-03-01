package bobcorn.twilightopia.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.container.ProphetLogContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ProphetLogScreen extends ContainerScreen<ProphetLogContainer> {
	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(TwilightopiaMod.MODID, "textures/gui/container/prophet_log.png");
	
	public ProphetLogScreen(ProphetLogContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}

	@Override
	public void render(final int mouseX, final int mouseY, final float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(final int mouseX, final int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		// Copied from AbstractFurnaceScreen#drawGuiContainerForegroundLayer
		String s = this.title.getFormattedText();
		this.font.drawString(s, (float) (this.xSize / 2 - this.font.getStringWidth(s) / 2), 6.0F, 0x404040);
		this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 0x404040);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(final float partialTicks, final int mouseX, final int mouseY) {
		GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
		int startX = this.guiLeft;
		int startY = this.guiTop;

		// Screen#blit draws a part of the current texture (assumed to be 256x256) to the screen
		// The parameters are (x, y, u, v, width, height)

		this.blit(startX, startY, 0, 0, this.xSize, this.ySize);
	}	
}
