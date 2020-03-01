package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.client.renderer.entity.model.RainbullModel;
import bobcorn.twilightopia.entity.passive.RainbullEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RainbullRenderer extends MobRenderer<RainbullEntity, RainbullModel<RainbullEntity>> {
   public RainbullRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new RainbullModel<>(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(RainbullEntity entity) {
      return entity.getBullTypeTex();
   }
}