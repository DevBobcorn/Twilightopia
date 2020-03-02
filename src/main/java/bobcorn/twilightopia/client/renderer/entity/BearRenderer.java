package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.model.BearModel;
import bobcorn.twilightopia.entity.BearEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BearRenderer extends MobRenderer<BearEntity, BearModel<BearEntity>> {
   private static final ResourceLocation BEAR_TEXTURE = new ResourceLocation(TwilightopiaMod.MODID, "textures/entity/bear/brownbear.png");

   public BearRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new BearModel<>(), 0.9F);
   }

   public ResourceLocation getEntityTexture(BearEntity entity) {
      return BEAR_TEXTURE;
   }
   
   protected void func_225620_a_(BearEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
      p_225620_2_.func_227862_a_(1.2F, 1.2F, 1.2F);
      super.func_225620_a_(p_225620_1_, p_225620_2_, p_225620_3_);
   }
}