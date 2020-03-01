package bobcorn.twilightopia.client.renderer.entity.model;

import bobcorn.twilightopia.entity.monster.MobCEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MobCModel<T extends MobCEntity> extends EntityModel<T> {
	protected RendererModel headu;
	protected RendererModel headl;
	protected RendererModel body;
	protected RendererModel leg1;
	protected RendererModel leg2;
	protected RendererModel leg3;
	protected RendererModel leg4;
	protected float childYOffset = 8.0F;
	protected float childZOffset = 4.0F;

	public MobCModel(int height, float scale) {
		this.headu = new RendererModel(this, 0, 0);
		this.headu.addBox(-4.0F, -4.0F, -8.0F, 8, 3, 10, scale);
		this.headu.setRotationPoint(0.0F, (float) (20 - height), 2.0F);
		this.headl = new RendererModel(this, 0, 14);
		this.headl.addBox(-4.0F, -4.0F, -8.0F, 8, 3, 10, scale);
		this.headl.setRotationPoint(0.0F, (float) (23 - height), 2.0F);
		this.leg1 = new RendererModel(this, 0, 0);
		this.leg1.addBox(-2.0F, 0.0F, -2.0F, 2, height, 2, scale);
		this.leg1.setRotationPoint(-1F, (float) (24 - height), 6.0F);
		this.leg2 = new RendererModel(this, 0, 0);
		this.leg2.addBox(-2.0F, 0.0F, -2.0F, 2, height, 2, scale);
		this.leg2.setRotationPoint(3F, (float) (24 - height), 6.0F);
		this.leg3 = new RendererModel(this, 0, 0);
		this.leg3.addBox(-2.0F, 0.0F, -2.0F, 2, height, 2, scale);
		this.leg3.setRotationPoint(-1F, (float) (24 - height), 2.0F);
		this.leg4 = new RendererModel(this, 0, 0);
		this.leg4.addBox(-2.0F, 0.0F, -2.0F, 2, height, 2, scale);
		this.leg4.setRotationPoint(3F, (float) (24 - height), 2.0F);
		this.body = new RendererModel(this, 27, 0);
		this.body.addBox(-4.0F, -10.0F, -11.0F, 6, 6, 3, 0.0F);
		this.body.setRotationPoint(1.0F, 10.0F, 8.9F);
		--this.leg1.rotationPointX;
		++this.leg2.rotationPointX;
		this.leg1.rotationPointZ += 0.0F;
		this.leg2.rotationPointZ += 0.0F;
		--this.leg3.rotationPointX;
		++this.leg4.rotationPointX;
		--this.leg3.rotationPointZ;
		--this.leg4.rotationPointZ;
		this.childZOffset += 2.0F;
	}
	
	public MobCModel() {
		this(4, 0.0F);
	}

	public void render(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		this.setRotationAngles(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		this.headu.render(scale);
		this.headl.render(scale);
		this.body.render(scale);
		this.leg1.render(scale);
		this.leg2.render(scale);
		this.leg3.render(scale);
		this.leg4.render(scale);
	}

	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch, float scaleFactor) {
		this.headu.rotateAngleX = (headPitch * 0.2F - entityIn.getMouthRot()  * 0.8F) * ((float) Math.PI / 180F);
		this.headu.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.headl.rotateAngleX = (headPitch * 0.2F + entityIn.getMouthRot()  * 0.2F) * ((float) Math.PI / 180F);
		this.headl.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.body.rotateAngleX = ((float) Math.PI / 2F);
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	}
}