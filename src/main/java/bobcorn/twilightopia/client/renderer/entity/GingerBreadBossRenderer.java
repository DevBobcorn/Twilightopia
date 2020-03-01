package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.GiantZombieRenderer;
import net.minecraft.entity.monster.GiantEntity;
import net.minecraft.util.ResourceLocation;

public class GingerBreadBossRenderer extends GiantZombieRenderer {
	private static final ResourceLocation Textures = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/ginger_man.png");

	public GingerBreadBossRenderer(EntityRendererManager p_i47206_1_) {
		super(p_i47206_1_, 6.0F);
	}
	
	public GingerBreadBossRenderer(EntityRendererManager p_i47206_1_, float scaleIn) {
		super(p_i47206_1_, scaleIn);
	}

	protected ResourceLocation getEntityTexture(GiantEntity entity) {
		return Textures;
	}
}
