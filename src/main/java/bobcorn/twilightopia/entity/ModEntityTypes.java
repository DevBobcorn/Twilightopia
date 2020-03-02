package bobcorn.twilightopia.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.entity.monster.HypnotomEntity;
import bobcorn.twilightopia.entity.monster.MobCEntity;
import bobcorn.twilightopia.entity.monster.RatEntity;
import bobcorn.twilightopia.entity.passive.ChoxEntity;
import bobcorn.twilightopia.entity.passive.DeerEntity;
import bobcorn.twilightopia.entity.passive.RainbullEntity;

public class ModEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, TwilightopiaMod.MODID);
	
	public static final RegistryObject<EntityType<RainbullEntity>> RAINBULL = ENTITY_TYPES.register("rainbull", () -> EntityType.Builder.create(RainbullEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build(TwilightopiaMod.MODID + ":rainbull"));  
	public static final RegistryObject<EntityType<DeerEntity>> DEER = ENTITY_TYPES.register("deer", () ->EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(0.7F, 2.3F).build(TwilightopiaMod.MODID + ":deer"));
	public static final RegistryObject<EntityType<SantaClausEntity>> SANTA_CLAUS = ENTITY_TYPES.register("santa_claus", () -> EntityType.Builder.create(SantaClausEntity::new, EntityClassification.CREATURE).size(0.7F, 2.4F).build(TwilightopiaMod.MODID + ":santa_claus"));
	public static final RegistryObject<EntityType<BearEntity>> BEAR = ENTITY_TYPES.register("bear", () -> EntityType.Builder.create(BearEntity::new, EntityClassification.CREATURE).size(1.4F, 1.4F).build(TwilightopiaMod.MODID + ":bear"));
	public static final RegistryObject<EntityType<RatEntity>> RAT = ENTITY_TYPES.register("rat", () -> EntityType.Builder.create(RatEntity::new, EntityClassification.MONSTER).size(0.6F, 0.6F).build(TwilightopiaMod.MODID + ":rat"));
	public static final RegistryObject<EntityType<ChoxEntity>> CHOX = ENTITY_TYPES.register("chox", () -> EntityType.Builder.create(ChoxEntity::new, EntityClassification.CREATURE).size(0.6F, 0.6F).build(TwilightopiaMod.MODID + ":chox"));
	public static final RegistryObject<EntityType<MobCEntity>> MOB_C = ENTITY_TYPES.register("mob_c", () -> EntityType.Builder.create(MobCEntity::new, EntityClassification.MONSTER).size(0.9F, 1.4F).build(TwilightopiaMod.MODID + ":mob_c"));
	public static final RegistryObject<EntityType<HypnotomEntity>> HYPNOTOM = ENTITY_TYPES.register("hypnotom", () -> EntityType.Builder.create(HypnotomEntity::new, EntityClassification.MONSTER).size(0.9F, 0.5F).build(TwilightopiaMod.MODID + ":hypnotom"));
}
