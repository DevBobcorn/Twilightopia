package bobcorn.twilightopia.client.renderer.entity.model;

import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaClausModel<T extends Entity> extends VillagerModel<T> {
	public SantaClausModel(float scale) {
		super(scale, 64, 128);
		//this.villagerHead.removeChild(this.field_217151_b);
		this.field_217151_b = (new ModelRenderer(this)).setTextureSize(64, 128);
		this.field_217151_b.setRotationPoint(-5.0F, -10.03125F, -5.0F);
		this.field_217151_b.setTextureOffset(0, 64).func_228300_a_(0.0F, 0.0F, 0.0F, 10, 2, 10);
		this.villagerHead.addChild(this.field_217151_b);
		ModelRenderer renderermodel = (new ModelRenderer(this)).setTextureSize(64, 128);
		renderermodel.setRotationPoint(1.75F, -4.0F, 2.0F);
		renderermodel.setTextureOffset(0, 76).func_228300_a_(0.0F, 0.0F, 0.0F, 7, 4, 7);
		renderermodel.rotateAngleX = -0.05235988F;
		renderermodel.rotateAngleZ = 0.02617994F;
		this.field_217151_b.addChild(renderermodel);
		ModelRenderer renderermodel1 = (new ModelRenderer(this)).setTextureSize(64, 128);
		renderermodel1.setRotationPoint(1.75F, -4.0F, 2.0F);
		renderermodel1.setTextureOffset(0, 87).func_228300_a_(0.0F, 0.0F, 0.0F, 4, 4, 4);
		renderermodel1.rotateAngleX = -0.10471976F;
		renderermodel1.rotateAngleZ = 0.05235988F;
		renderermodel.addChild(renderermodel1);
		ModelRenderer renderermodel2 = (new ModelRenderer(this)).setTextureSize(64, 128);
		renderermodel2.setRotationPoint(1.75F, -2.0F, 2.0F);
		renderermodel2.setTextureOffset(0, 95).func_228300_a_(0.0F, 0.0F, 0.0F, 1, 2, 1);
		renderermodel2.rotateAngleX = -0.20943952F;
		renderermodel2.rotateAngleZ = 0.10471976F;
		renderermodel1.addChild(renderermodel2);
	}

	public ModelRenderer func_205073_b() {
		return this.villagerNose;
	}
}