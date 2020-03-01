package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.entity.monster.HypnotomEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.PhantomEyesLayer;
import net.minecraft.client.renderer.entity.model.PhantomModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HypnotomRenderer extends MobRenderer<HypnotomEntity, PhantomModel<HypnotomEntity>> {
   public HypnotomRenderer(EntityRendererManager p_i48829_1_) {
      super(p_i48829_1_, new PhantomModel<>(), 0.75F);
      this.addLayer(new PhantomEyesLayer<>(this));
   }

   protected ResourceLocation getEntityTexture(HypnotomEntity entity) {
      return entity.getTex();
   }

   protected void preRenderCallback(HypnotomEntity entitylivingbaseIn, float partialTickTime) {
      int i = entitylivingbaseIn.getPhantomSize();
      float f = 1.0F + 0.15F * (float)i;
      GlStateManager.scalef(f, f, f);
      GlStateManager.translatef(0.0F, 1.3125F, 0.1875F);
   }

   protected void applyRotations(HypnotomEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      GlStateManager.rotatef(entityLiving.rotationPitch, 1.0F, 0.0F, 0.0F);
   }
}