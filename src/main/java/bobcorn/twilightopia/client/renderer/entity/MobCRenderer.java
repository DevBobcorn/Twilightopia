package bobcorn.twilightopia.client.renderer.entity;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.model.MobCModel;
import bobcorn.twilightopia.entity.monster.MobCEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MobCRenderer extends MobRenderer<MobCEntity, MobCModel<MobCEntity>> {
   private static final ResourceLocation TEXTURES = new ResourceLocation(TwilightopiaMod.MODID,"textures/entity/mob_c/mob_t.png");

   public MobCRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new MobCModel<>(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(MobCEntity entity) {
      return TEXTURES;
   }
}