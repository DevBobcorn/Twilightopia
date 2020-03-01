package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.layers.SantaHeldItemLayer;
import bobcorn.twilightopia.client.renderer.entity.model.SantaClausModel;
import bobcorn.twilightopia.entity.SantaClausEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaClausRenderer extends MobRenderer<SantaClausEntity, SantaClausModel<SantaClausEntity>> {
   private static final ResourceLocation SANTA_TEXTURES = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/santa_claus.png");

   public SantaClausRenderer(EntityRendererManager renderManagerIn) {
	  super(renderManagerIn, new SantaClausModel<>(0.0F), 0.5F);
      this.addLayer(new SantaHeldItemLayer<>(this));
   }

   public void doRender(SantaClausEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(SantaClausEntity entity) {
      return SANTA_TEXTURES;
   }

   protected void preRenderCallback(WitchEntity entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      GlStateManager.scalef(f, f, f);
   }
}