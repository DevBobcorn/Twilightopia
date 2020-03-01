package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.ZombieModel;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GingerBreadManRenderer extends AbstractZombieRenderer<ZombieEntity, ZombieModel<ZombieEntity>> {
	private static final ResourceLocation Textures = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/ginger_man.png");
	
	public GingerBreadManRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ZombieModel<>(), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));
	}

	protected ResourceLocation getEntityTexture(ZombieEntity entity) {
		return Textures;
	}
}