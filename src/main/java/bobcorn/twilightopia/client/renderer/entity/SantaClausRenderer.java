package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.model.SantaClausModel;
import bobcorn.twilightopia.entity.SantaClausEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SantaClausRenderer extends MobRenderer<SantaClausEntity, SantaClausModel<SantaClausEntity>> {
	private static final ResourceLocation SANTA_TEXTURES = new ResourceLocation(TwilightopiaMod.MODID,
			"textures/entity/santa_claus.png");

	public SantaClausRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SantaClausModel<>(0.0F), 0.5F);
		// this.addLayer(new VillagerHeldItemLayer<>(this));
	}

	public ResourceLocation getEntityTexture(SantaClausEntity entity) {
		return SANTA_TEXTURES;
	}

	protected void func_225620_a_(WitchEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
		p_225620_2_.func_227862_a_(0.9375F, 0.9375F, 0.9375F);
	}
}