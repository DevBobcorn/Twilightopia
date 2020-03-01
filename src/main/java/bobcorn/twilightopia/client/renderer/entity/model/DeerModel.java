package bobcorn.twilightopia.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.model.QuadrupedModel;


public class DeerModel<T extends Entity> extends QuadrupedModel<T> {
	public DeerModel() {
		super(12, 0.0F);

		// head height for baby
		childYOffset = 10F;

		headModel = new RendererModel(this, 0, 0);
		headModel.addBox(-2F, -8F, -6F, 4, 6, 6, 0F);
		headModel.setRotationPoint(0F, 4F, -7F);

		field_78148_b = new RendererModel(this, 27, 6);
		field_78148_b.addBox(-4F, -10F, -7F, 6, 18, 8, 0F);
		field_78148_b.setRotationPoint(1F, 5F, 2F);

		field_78148_b.rotateAngleX = 1.570796F;
		field_78146_d = new RendererModel(this, 0, 17);
		field_78146_d.addBox(-3F, 0F, -2F, 2, 12, 3, 0F);
		field_78146_d.setRotationPoint(0F, 12F, 9F);

		field_78149_c = new RendererModel(this, 0, 17);
		field_78149_c.addBox(-1F, 0F, -2F, 2, 12, 3, 0F);
		field_78149_c.setRotationPoint(2F, 12F, 9F);

		field_78147_e = new RendererModel(this, 0, 17);
		field_78147_e.addBox(-3F, 0F, -3F, 2, 12, 3, 0F);
		field_78147_e.setRotationPoint(0F, 12F, -5F);

		field_78144_f = new RendererModel(this, 0, 17);
		field_78144_f.addBox(-1F, 0F, -3F, 2, 12, 3, 0F);
		field_78144_f.setRotationPoint(2F, 12F, -5F);

		// neck
		neck = new RendererModel(this, 12, 19);
		neck.addBox(-2.5F, -8, -11F, 3, 9, 4, 0F);

		neck.rotateAngleX = 4.974188f;

		field_78148_b.addChild(neck);

		// nose
		headModel.setTextureOffset(13, 12).addBox(-1.5F, -5F, -9F, 3, 3, 3, 0F);

		headModel.setTextureOffset(50, 0);
		// antler 1
		headModel.addBox(-3F, -10F, -2F, 2, 2, 2, 0F);
		headModel.addBox(-4F, -10F, -1F, 1, 1, 3, 0F);
		headModel.addBox(-5F, -11F,  0F, 1, 1, 2, 0F);
		// branch 1
		headModel.addBox(-7F, -12F, 0F, 2, 1, 1, 0F);
		headModel.addBox(-8F, -13F, 0F, 2, 1, 1, 0F);
		headModel.addBox(-9F, -14F, 0F, 2, 1, 1, 0F);
		headModel.addBox(-9F, -15F, 1F, 1, 2, 1, 0F);
		// branch 2
		headModel.addBox(-5F, -13F, 0F, 1, 2, 1, 0F);
		headModel.addBox(-5F, -15F, 2F, 1, 4, 1, 0F);
		headModel.addBox(-4F, -14F, 2F, 1, 1, 1, 0F);

		// antler 2
		headModel.addBox(1F, -10F, -2F, 2, 2, 2, 0F);
		headModel.addBox(3F, -10F, -1F, 1, 1, 3, 0F);
		headModel.addBox(4F, -11F,  0F, 1, 1, 2, 0F);
		// branch 1
		headModel.addBox(5F, -12F, 0F, 2, 1, 1, 0F);
		headModel.addBox(6F, -13F, 0F, 2, 1, 1, 0F);
		headModel.addBox(7F, -14F, 0F, 2, 1, 1, 0F);
		headModel.addBox(8F, -15F, 1F, 1, 2, 1, 0F);
		// branch 2
		headModel.addBox(4F, -13F, 0F, 1, 2, 1, 0F);
		headModel.addBox(4F, -15F, 2F, 1, 4, 1, 0F);
		headModel.addBox(3F, -14F, 2F, 1, 1, 1, 0F);
	}

	//fields
	public RendererModel neck;
}