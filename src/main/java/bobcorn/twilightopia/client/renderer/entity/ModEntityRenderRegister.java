package bobcorn.twilightopia.client.renderer.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.entity.BearEntity;
import bobcorn.twilightopia.entity.SantaClausEntity;
import bobcorn.twilightopia.entity.boss.GingerBreadBossEntity;
import bobcorn.twilightopia.entity.monster.GingerBreadManEntity;
import bobcorn.twilightopia.entity.monster.HypnotomEntity;
import bobcorn.twilightopia.entity.monster.JelimeEntity;
import bobcorn.twilightopia.entity.monster.MobCEntity;
import bobcorn.twilightopia.entity.monster.NeonlimeEntity;
import bobcorn.twilightopia.entity.monster.RatEntity;
import bobcorn.twilightopia.entity.monster.SnomeEntity;
import bobcorn.twilightopia.entity.passive.ChoxEntity;
import bobcorn.twilightopia.entity.passive.DeerEntity;
import bobcorn.twilightopia.entity.passive.RainbullEntity;
//import net.minecraft.client.renderer.entity.DefaultRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ModEntityRenderRegister {
	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Client Mod Event Subscriber");
	
	
    @OnlyIn(Dist.CLIENT)
    public static void registerRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(RainbullEntity.class, RainbullRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(DeerEntity.class, DeerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SantaClausEntity.class, SantaClausRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BearEntity.class, BearRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(RatEntity.class, RatRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(ChoxEntity.class, ChoxRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(MobCEntity.class, MobCRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(HypnotomEntity.class, HypnotomRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GingerBreadManEntity.class, GingerBreadManRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(GingerBreadBossEntity.class, GingerBreadBossRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(JelimeEntity.class, NeonlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(SnomeEntity.class, NeonlimeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(NeonlimeEntity.class, NeonlimeRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(BoatPlusEntity.class, BoatPlusRenderer::new);
        LOGGER.debug("Registered Entity Renderers");
    }
}