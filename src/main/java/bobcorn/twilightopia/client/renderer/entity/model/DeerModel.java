package bobcorn.twilightopia.client.renderer.entity.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;


public class DeerModel<T extends Entity> extends QuadrupedModel<T> {
	public DeerModel() {
		super(12, 0.0F, false, 10.0F, 4.0F, 2.0F, 2.0F, 24);

		headModel = new ModelRenderer(this, 0, 0);
		headModel.func_228301_a_(-2F, -8F, -6F, 4, 6, 6, 0F);
		headModel.setRotationPoint(0F, 4F, -7F);

		field_78148_b = new ModelRenderer(this, 27, 6);
		field_78148_b.func_228301_a_(-4F, -10F, -7F, 6, 18, 8, 0F);
		field_78148_b.setRotationPoint(1F, 5F, 2F);

		field_78148_b.rotateAngleX = 1.570796F;
		field_78146_d = new ModelRenderer(this, 0, 17);
		field_78146_d.func_228301_a_(-3F, 0F, -2F, 2, 12, 3, 0F);
		field_78146_d.setRotationPoint(0F, 12F, 9F);

		field_78149_c = new ModelRenderer(this, 0, 17);
		field_78149_c.func_228301_a_(-1F, 0F, -2F, 2, 12, 3, 0F);
		field_78149_c.setRotationPoint(2F, 12F, 9F);

		field_78147_e = new ModelRenderer(this, 0, 17);
		field_78147_e.func_228301_a_(-3F, 0F, -3F, 2, 12, 3, 0F);
		field_78147_e.setRotationPoint(0F, 12F, -5F);

		field_78144_f = new ModelRenderer(this, 0, 17);
		field_78144_f.func_228301_a_(-1F, 0F, -3F, 2, 12, 3, 0F);
		field_78144_f.setRotationPoint(2F, 12F, -5F);

		// neck
		neck = new ModelRenderer(this, 12, 19);
		neck.func_228301_a_(-2.5F, -8, -11F, 3, 9, 4, 0F);

		neck.rotateAngleX = 4.974188f;

		field_78148_b.addChild(neck);

		// nose
		headModel.setTextureOffset(13, 12).func_228301_a_(-1.5F, -5F, -9F, 3, 3, 3, 0F);

		headModel.setTextureOffset(50, 0);
		// antler 1
		headModel.func_228301_a_(-3F, -10F, -2F, 2, 2, 2, 0F);
		headModel.func_228301_a_(-4F, -10F, -1F, 1, 1, 3, 0F);
		headModel.func_228301_a_(-5F, -11F,  0F, 1, 1, 2, 0F);
		// branch 1
		headModel.func_228301_a_(-7F, -12F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(-8F, -13F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(-9F, -14F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(-9F, -15F, 1F, 1, 2, 1, 0F);
		// branch 2
		headModel.func_228301_a_(-5F, -13F, 0F, 1, 2, 1, 0F);
		headModel.func_228301_a_(-5F, -15F, 2F, 1, 4, 1, 0F);
		headModel.func_228301_a_(-4F, -14F, 2F, 1, 1, 1, 0F);

		// antler 2
		headModel.func_228301_a_(1F, -10F, -2F, 2, 2, 2, 0F);
		headModel.func_228301_a_(3F, -10F, -1F, 1, 1, 3, 0F);
		headModel.func_228301_a_(4F, -11F,  0F, 1, 1, 2, 0F);
		// branch 1
		headModel.func_228301_a_(5F, -12F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(6F, -13F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(7F, -14F, 0F, 2, 1, 1, 0F);
		headModel.func_228301_a_(8F, -15F, 1F, 1, 2, 1, 0F);
		// branch 2
		headModel.func_228301_a_(4F, -13F, 0F, 1, 2, 1, 0F);
		headModel.func_228301_a_(4F, -15F, 2F, 1, 4, 1, 0F);
		headModel.func_228301_a_(3F, -14F, 2F, 1, 1, 1, 0F);
	}

	//fields
	public ModelRenderer neck;
}