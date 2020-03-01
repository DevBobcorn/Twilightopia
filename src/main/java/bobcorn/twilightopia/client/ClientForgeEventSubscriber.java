package bobcorn.twilightopia.client;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.platform.GlStateManager;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.effects.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on the
 * PHYSICAL CLIENT side in this class
 */
@EventBusSubscriber(modid = TwilightopiaMod.MODID, bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public final class ClientForgeEventSubscriber {
	private static boolean UsingShaderN = false;
	private static boolean UsingShaderF = false;
	
	@SubscribeEvent
	public static void onFogColorChange(FogColors event) {
		if (!(event.getInfo().getRenderViewEntity() instanceof LivingEntity))
			return;
		LivingEntity entity = (LivingEntity) event.getInfo().getRenderViewEntity();

		double d0 = event.getInfo().getProjectedView().y * Minecraft.getInstance().world.dimension.getVoidFogYFactor() * 2F;
		if (event.getInfo().getRenderViewEntity() instanceof LivingEntity) {
			if (entity.isPotionActive(ModEffects.HEAVY_MIST)) {
				int i = ((LivingEntity) event.getInfo().getRenderViewEntity())
						.getActivePotionEffect(ModEffects.HEAVY_MIST).getDuration();
				if (i < 20) {
					d0 *= (double) (1.0F - (float) i / 15.0F);
				} else
					d0 = 0.0D;
				if (d0 < 1.0D) {
					d0 = 0.1D;
					if (((double) event.getRed() * d0) <= 0.7D)
						event.setRed((float) ((double) event.getRed() * d0) + 0.3F);
					else event.setRed(1.0F);
					if (((double) event.getGreen() * d0) <= 0.7D)
						event.setGreen((float) ((double) event.getGreen() * d0) + 0.3F);
					else event.setGreen(1.0F);
					if (((double) event.getBlue() * d0) <= 0.7D)
						event.setBlue((float) ((double) event.getBlue() * d0) + 0.3F);
					else event.setBlue(1.0F);
				}
			} else if (entity.isPotionActive(ModEffects.FROZEN)
					&& entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration() > 5) {
				// Render frost
				float proc = entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration() / 1000.0f;
				if (proc > 1.0f)
					proc = 1.0f;
				// System.out.println("Render Frost, Depth: " + proc + ' ' +
				// entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration());
				proc /= 3.333f;
				event.setRed((float) ((double) event.getRed() * (0.682f - proc)));
				event.setGreen((float) ((double) event.getGreen() * (0.807f - proc)));
				event.setBlue((float) ((double) event.getBlue()));
			}
		}
	}

	private static boolean ref = false;
	
	@SubscribeEvent
	public static void onFogRender(RenderFogEvent event) {
		if (!(event.getInfo().getRenderViewEntity() instanceof LivingEntity))
			return;
		LivingEntity entity = (LivingEntity) event.getInfo().getRenderViewEntity();

		if (entity.isPotionActive(ModEffects.HEAVY_MIST)) {
			float f2 = 5.0F;
			int i = entity.getActivePotionEffect(ModEffects.HEAVY_MIST).getDuration();
			if (i < 20) {
				f2 = MathHelper.lerp(1.0F - (float) i / 20.0F, 5.0F,
						Minecraft.getInstance().gameRenderer.getFarPlaneDistance());
			}

			GlStateManager.fogMode(GlStateManager.FogMode.LINEAR);
			GlStateManager.fogStart(0.4F);
			GlStateManager.fogEnd(f2 * 0.8F);

			GLX.setupNvFogDistance();
		} else if (entity.isPotionActive(ModEffects.FROZEN)) {
			float f2 = 5.0F;
			int i = entity.getActivePotionEffect(ModEffects.FROZEN).getDuration();
			if (i < 20) {
				f2 = MathHelper.lerp(1.0F - (float) i / 20.0F, 5.0F,
						Minecraft.getInstance().gameRenderer.getFarPlaneDistance());
			}

			GlStateManager.fogMode(GlStateManager.FogMode.LINEAR);
			GlStateManager.fogStart(0.8F);
			GlStateManager.fogEnd(f2 * 4.5F);

			GLX.setupNvFogDistance();
		}
		//ref = false;
		ref = (entity.isPotionActive(ModEffects.FROZEN) ^ UsingShaderF);
		//System.out.println("F: " + entity.isPotionActive(ModEffects.FROZEN) + ' ' + UsingShaderF + ' ' + ref);
		ref |= (entity.isPotionActive(ModEffects.NEON_ILLUSION) ^ UsingShaderN);
		//System.out.println("N: " + entity.isPotionActive(ModEffects.NEON_ILLUSION) + ' ' + UsingShaderN + ' ' + ref);
		if (ref) {
			//System.out.println("Refresh");
			if (event.getRenderer().getShaderGroup() != null)
				event.getRenderer().stopUseShader();
			if (entity.isPotionActive(ModEffects.FROZEN)) {
				event.getRenderer().loadShader(new ResourceLocation("shaders/post/blur.json"));
				UsingShaderF = true;
			} else UsingShaderF = false;
			if (entity.isPotionActive(ModEffects.NEON_ILLUSION)) {
				event.getRenderer().loadShader(new ResourceLocation("shaders/post/art.json"));
				UsingShaderN = true;
			} else UsingShaderN = false;
		}
	}

	@SubscribeEvent
	public static void preRenderLiving(
			net.minecraftforge.client.event.RenderLivingEvent.Pre<PlayerEntity, EntityModel<PlayerEntity>> event) {
		LivingEntity entity = event.getEntity();
		if (entity.isPotionActive(ModEffects.FROZEN)
				&& entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration() > 5) {
			// Render frost
			float proc = entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration() / 1000.0f;
			if (proc > 1.0f)
				proc = 1.0f;
			// System.out.println("Render Frost, Depth: " + proc + ' ' +
			// entity.getActivePotionMap().get(ModEffects.FROZEN).getDuration());
			proc /= 3.333f;
			GlStateManager.color3f(0.682f - proc, 0.807f - proc, 1f);
		}
	}
}
