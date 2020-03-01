package bobcorn.twilightopia.client.gui;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.client.renderer.ProjectableMapModel;
import bobcorn.twilightopia.tileentity.ProjectableTileEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class ProjectableScreen extends Screen {

	private final ProjectableTileEntity tileEntity;

	public ProjectableScreen(final ProjectableTileEntity tileEntity) {
		super(ModBlocks.PROJECTABLE.getNameTextComponent());
		this.tileEntity = tileEntity;
	}

	@Override
	public void render(final int mouseX, final int mouseY, final float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void init() {
		final int halfW = this.width / 2;
		final int halfH = this.height / 2;
		// "Refresh Mini Model" button rebuilds the tile's MiniModel
		// "Done" button exits the GUI
		this.addButton(new GuiButtonExt(halfW, halfH, 150, 20, I18n.format("gui.done"),
				$ -> this.minecraft.displayGuiScreen(null)
		));
		this.addButton(new GuiButtonExt(0, halfH, 150, 20, I18n.format("gui." + TwilightopiaMod.MODID + ".refresh"),
				$ -> {
					final ProjectableMapModel miniMap = this.tileEntity.mapModel;
					if (miniMap != null)
						miniMap.rebuild();
				}
		));
		super.init();
	}

	@Override
	public boolean isPauseScreen() {
		return false; // Don't pause the game when this screen is open
	}

}
