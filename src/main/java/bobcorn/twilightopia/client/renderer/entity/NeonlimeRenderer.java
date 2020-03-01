package bobcorn.twilightopia.client.renderer.entity;

import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.client.renderer.entity.layers.NeonlimeGelLayer;
import bobcorn.twilightopia.colors.RainbowColors;
import bobcorn.twilightopia.colors.TwilitColors;
import bobcorn.twilightopia.entity.monster.JelimeEntity;
import bobcorn.twilightopia.entity.monster.NeonlimeEntity;
import bobcorn.twilightopia.entity.monster.SnomeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.SlimeModel;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class NeonlimeRenderer extends MobRenderer<SlimeEntity, SlimeModel<SlimeEntity>> {
	private static final ResourceLocation SNOME = new ResourceLocation(TwilightopiaMod.MODID,
			"textures/entity/slime/snome.png");
	private static final ResourceLocation JELLY = new ResourceLocation(TwilightopiaMod.MODID,
			"textures/entity/slime/jelly.png");
	private static final ResourceLocation NEON = new ResourceLocation(TwilightopiaMod.MODID,
			"textures/entity/slime/neon.png");
	
	public NeonlimeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SlimeModel<>(16), 0.25F);
		this.addLayer(new NeonlimeGelLayer<>(this));
	}

	public void doRender(SlimeEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.shadowSize = 0.25F * (float) entity.getSlimeSize();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected void preRenderCallback(SlimeEntity entitylivingbaseIn, float partialTickTime) {
		float f = (entitylivingbaseIn instanceof JelimeEntity) ? 0.666F : 0.999F;
		GlStateManager.scalef(f, f, f);
		float f1 = (float) entitylivingbaseIn.getSlimeSize();
		float f2 = MathHelper.lerp(partialTickTime, entitylivingbaseIn.prevSquishFactor,
				entitylivingbaseIn.squishFactor) / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scalef(f3 * f1, 1.0F / f3 * f1, f3 * f1);
		if (entitylivingbaseIn instanceof NeonlimeEntity) {
			float time = ((float) (entitylivingbaseIn.ticksExisted % 40)) / 40.0F;
			int c = RainbowColors.getRainbowColorAt(time);
			GlStateManager.color3f(TwilitColors.getRedf(c), TwilitColors.getGreenf(c), TwilitColors.getBluef(c));
		}
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(SlimeEntity entity) {
		if (entity instanceof SnomeEntity)
			return SNOME;
		else if (entity instanceof JelimeEntity)
			return JELLY;
		return NEON;
	}
}
