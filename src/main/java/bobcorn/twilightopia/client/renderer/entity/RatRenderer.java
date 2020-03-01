package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.model.RatModel;
import bobcorn.twilightopia.entity.monster.RatEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RatRenderer extends MobRenderer<RatEntity, RatModel<RatEntity>> {
	private static final ResourceLocation RAT_TEXTURE = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/rat/rat.png");
	
   public RatRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new RatModel<>(), 0.4F);
   }

   protected ResourceLocation getEntityTexture(RatEntity entity) {
      return RAT_TEXTURE;
   }
}