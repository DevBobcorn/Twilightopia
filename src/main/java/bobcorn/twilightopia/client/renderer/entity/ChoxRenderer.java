package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.layers.ChoxHeldItemLayer;
import bobcorn.twilightopia.entity.passive.ChoxEntity;

import net.minecraft.client.renderer.Vector3f;
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

   protected void func_225621_a_(ChoxEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
	      super.func_225621_a_(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
	      if (p_225621_1_.func_213480_dY() || p_225621_1_.func_213472_dX()) {
	         float f = -MathHelper.lerp(p_225621_5_, p_225621_1_.prevRotationPitch, p_225621_1_.rotationPitch);
	         p_225621_2_.func_227863_a_(Vector3f.field_229179_b_.func_229187_a_(f));
	      }

	   }

	   public ResourceLocation getEntityTexture(ChoxEntity entity) {
	      if (entity.getVariantType() == ChoxEntity.Type.RED) {
	         return entity.isSleeping() ? field_217768_j : field_217767_a;
	      } else {
	         return entity.isSleeping() ? field_217770_l : field_217769_k;
	      }
	   }
}