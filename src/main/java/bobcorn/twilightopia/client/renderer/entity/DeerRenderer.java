package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.client.renderer.entity.model.DeerModel;
import bobcorn.twilightopia.entity.passive.DeerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DeerRenderer extends MobRenderer<DeerEntity, DeerModel<DeerEntity>> {
   public DeerRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new DeerModel<>(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(DeerEntity entity) {
      return entity.getElkTypeTex();
   }
}