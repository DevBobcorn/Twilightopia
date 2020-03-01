package bobcorn.twilightopia.client.renderer.entity.model;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.entity.monster.RatEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RatModel<T extends RatEntity> extends EntityModel<T> {
   public final RendererModel head;
   private final RendererModel leftEar;
   private final RendererModel rightEar;
   private final RendererModel mouth;
   private final RendererModel body;
   private final RendererModel leg1;
   private final RendererModel leg2;
   private final RendererModel leg3;
   private final RendererModel leg4;
   private final RendererModel tail;

   // head,ears,mouth  back    z +3
   // mouth            back    z +0.5
   // tail             forward z -1
   // tail             lower   y -5
   
   public RatModel() {
      this.textureWidth = 48;
      this.textureHeight = 32;
      this.head = new RendererModel(this, 1, 5);
      this.head.addBox(-1.0F, -2.0F, -3.0F, 4, 4, 4);
      this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
      this.leftEar = new RendererModel(this, 8, 1);
      this.leftEar.addBox(-1F, -3.0F, 0.0F, 1, 1, 1);
      this.rightEar = new RendererModel(this, 15, 1);
      this.rightEar.addBox(2F, -3.0F, 0.0F, 1, 1, 1);
      this.mouth = new RendererModel(this, 6, 18);
      this.mouth.addBox(0F, 0.0F, -4.5F, 2, 2, 2);
      this.head.addChild(this.leftEar);
      this.head.addChild(this.rightEar);
      this.head.addChild(this.mouth);
      this.body = new RendererModel(this, 24, 15);
      this.body.addBox(-3.0F, 4F, -4.5F, 6, 11, 6);
      this.body.setRotationPoint(0.0F, 16.0F, -6.0F);
      this.leg1 = new RendererModel(this, 13, 24);
      this.leg1.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
      this.leg1.setRotationPoint(-5.0F, 17.5F, 7.0F);
      this.leg2 = new RendererModel(this, 4, 24);
      this.leg2.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
      this.leg2.setRotationPoint(-1.0F, 17.5F, 7.0F);
      this.leg3 = new RendererModel(this, 13, 24);
      this.leg3.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
      this.leg3.setRotationPoint(-5.0F, 17.5F, 0.0F);
      this.leg4 = new RendererModel(this, 4, 24);
      this.leg4.addBox(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
      this.leg4.setRotationPoint(-1.0F, 17.5F, 0.0F);
      this.tail = new RendererModel(this, 30, 0);
      this.tail.addBox(3.5F, 12.0F, -1.0F, 1, 8, 1);
      this.tail.setRotationPoint(-4.0F, 0.0F, 0.0F);
      this.body.addChild(this.tail);
   }

   public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
      this.body.rotateAngleX = ((float)Math.PI / 2F); //!!!
      this.tail.rotateAngleX = -0.05235988F;
      this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
      this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
      this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
      this.head.rotateAngleY = 0.0F;
      this.leg1.showModel = true;
      this.leg2.showModel = true;
      this.leg3.showModel = true;
      this.leg4.showModel = true;
      this.body.setRotationPoint(0.0F, 16.0F, -6.0F);
      this.body.rotateAngleZ = 0.0F;
      this.leg1.setRotationPoint(-5.0F, 17.5F, 7.0F);
      this.leg2.setRotationPoint(-1.0F, 17.5F, 7.0F);
   }
   
   public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
      GlStateManager.pushMatrix();
      this.head.render(scale);
      this.body.render(scale);
      this.leg1.render(scale);
      this.leg2.render(scale);
      this.leg3.render(scale);
      this.leg4.render(scale);
      GlStateManager.popMatrix();
   }

   public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
      super.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
      this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
      this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
   }
}