package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.model.BearModel;
import bobcorn.twilightopia.entity.BearEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BearRenderer extends MobRenderer<BearEntity, BearModel<BearEntity>> {
   private static final ResourceLocation BEAR_TEXTURE = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/bear/brownbear.png");

   public BearRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new BearModel<>(), 0.9F);
   }

   protected ResourceLocation getEntityTexture(BearEntity entity) {
      return BEAR_TEXTURE;
   }

   protected void preRenderCallback(BearEntity entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scalef(1.2F, 1.2F, 1.2F);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }
}