package bobcorn.twilightopia.client.renderer.entity.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import bobcorn.twilightopia.entity.monster.RatEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RatModel<T extends RatEntity> extends EntityModel<T> {
	public final ModelRenderer head;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer mouth;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer tail;

	// head,ears,mouth back z +3
	// mouth back z +0.5
	// tail forward z -1
	// tail lower y -5

	public RatModel() {
		this.textureWidth = 48;
		this.textureHeight = 32;
		this.head = new ModelRenderer(this, 1, 5);
		this.head.func_228300_a_(-1.0F, -2.0F, -3.0F, 4, 4, 4);
		this.head.setRotationPoint(-1.0F, 16.5F, -3.0F);
		this.leftEar = new ModelRenderer(this, 8, 1);
		this.leftEar.func_228300_a_(-1F, -3.0F, 0.0F, 1, 1, 1);
		this.rightEar = new ModelRenderer(this, 15, 1);
		this.rightEar.func_228300_a_(2F, -3.0F, 0.0F, 1, 1, 1);
		this.mouth = new ModelRenderer(this, 6, 18);
		this.mouth.func_228300_a_(0F, 0.0F, -4.5F, 2, 2, 2);
		this.head.addChild(this.leftEar);
		this.head.addChild(this.rightEar);
		this.head.addChild(this.mouth);
		this.body = new ModelRenderer(this, 24, 15);
		this.body.func_228300_a_(-3.0F, 4F, -4.5F, 6, 11, 6);
		this.body.setRotationPoint(0.0F, 16.0F, -6.0F);
		this.leg1 = new ModelRenderer(this, 13, 24);
		this.leg1.func_228301_a_(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
		this.leg1.setRotationPoint(-5.0F, 17.5F, 7.0F);
		this.leg2 = new ModelRenderer(this, 4, 24);
		this.leg2.func_228301_a_(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
		this.leg2.setRotationPoint(-1.0F, 17.5F, 7.0F);
		this.leg3 = new ModelRenderer(this, 13, 24);
		this.leg3.func_228301_a_(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
		this.leg3.setRotationPoint(-5.0F, 17.5F, 0.0F);
		this.leg4 = new ModelRenderer(this, 4, 24);
		this.leg4.func_228301_a_(2.0F, 0.5F, -1.0F, 2, 6, 2, 0.001F);
		this.leg4.setRotationPoint(-1.0F, 17.5F, 0.0F);
		this.tail = new ModelRenderer(this, 30, 0);
		this.tail.func_228300_a_(3.5F, 12.0F, -1.0F, 1, 8, 1);
		this.tail.setRotationPoint(-4.0F, 0.0F, 0.0F);
		this.body.addChild(this.tail);
	}

	public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
		this.body.rotateAngleX = ((float) Math.PI / 2F); // !!!
		this.tail.rotateAngleX = -0.05235988F;
		this.leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
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

	protected Iterable<ModelRenderer> func_225602_a_() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelRenderer> func_225600_b_() {
		return ImmutableList.of(this.leg1, this.leg2, this.leg3, this.leg4, this.body);
	}

	public void func_225597_a_(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		// super.func_225597_a_(entityIn, limbSwing, limbSwingAmount, ageInTicks,
		// netHeadYaw, headPitch, scaleFactor);
		this.head.rotateAngleX = headPitch * ((float) Math.PI / 180F);
		this.head.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
	}

	@Override
	public void func_225598_a_(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_,
			float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		this.func_225602_a_().forEach((p_228228_8_) -> {
            p_228228_8_.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
         });
         this.func_225600_b_().forEach((p_228227_8_) -> {
            p_228227_8_.func_228309_a_(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_, p_225598_5_, p_225598_6_, p_225598_7_, p_225598_8_);
         });
	}
}