package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.layers.ChoxHeldItemLayer;
import bobcorn.twilightopia.entity.passive.ChoxEntity;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.FoxModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChoxRenderer extends MobRenderer<ChoxEntity, FoxModel<ChoxEntity>> {
   private static final ResourceLocation field_217767_a = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/chox/chox.png");
   private static final ResourceLocation field_217768_j = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/chox/chox_sleep.png");
   private static final ResourceLocation field_217769_k = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/chox/milky_chox.png");
   private static final ResourceLocation field_217770_l = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/chox/milky_chox_sleep.png");

   public ChoxRenderer(EntityRendererManager p_i50969_1_) {
      super(p_i50969_1_, new FoxModel<>(), 0.4F);
      this.addLayer(new ChoxHeldItemLayer(this));
   }

   protected void applyRotations(ChoxEntity entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      if (entityLiving.func_213480_dY() || entityLiving.func_213472_dX()) {
         GlStateManager.rotatef(-MathHelper.lerp(partialTicks, entityLiving.prevRotationPitch, entityLiving.rotationPitch), 1.0F, 0.0F, 0.0F);
      }
   }

   @Nullable
   protected ResourceLocation getEntityTexture(ChoxEntity entity) {
      if (entity.getChoxType() == ChoxEntity.ChoxType.DARK) {
         return entity.isSleeping() ? field_217768_j : field_217767_a;
      } else {
         return entity.isSleeping() ? field_217770_l : field_217769_k;
      }
   }
}