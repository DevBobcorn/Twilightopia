package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import javax.annotation.Nullable;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CubeRenderer extends EntityRenderer<Entity> {
   public CubeRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.pushMatrix();
      renderOffsetAABB(new AxisAlignedBB(-1, -1, -1, 1, 1, 1), x - entity.lastTickPosX, y - entity.lastTickPosY, z - entity.lastTickPosZ);
      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   @Nullable
   protected ResourceLocation getEntityTexture(Entity entity) {
      return null;
   }
}