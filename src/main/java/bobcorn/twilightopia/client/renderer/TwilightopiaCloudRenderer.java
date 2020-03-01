package bobcorn.twilightopia.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.client.CloudRenderer;
import net.minecraftforge.client.IRenderHandler;

public class TwilightopiaCloudRenderer implements IRenderHandler {
	@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
		// TODO Auto-generated method stub
		CloudRenderer.renderClouds(ticks, partialTicks, world, mc);
	}
}
