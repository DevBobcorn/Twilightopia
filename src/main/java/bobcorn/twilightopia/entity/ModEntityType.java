package bobcorn.twilightopia.entity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import bobcorn.twilightopia.TwilightopiaMod;
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


public class ModEntityType {
	public static final EntityType<RainbullEntity> RAINBULL = EntityType.Builder.create(RainbullEntity::new, EntityClassification.CREATURE).size(0.9F, 1.4F).build(TwilightopiaMod.MODID + ":rainbull");  
	public static final EntityType<DeerEntity> DEER = EntityType.Builder.create(DeerEntity::new, EntityClassification.CREATURE).size(0.7F, 2.3F).build(TwilightopiaMod.MODID + ":deer");
	public static final EntityType<SantaClausEntity> SANTA_CLAUS = EntityType.Builder.create(SantaClausEntity::new, EntityClassification.CREATURE).size(0.7F, 2.4F).build(TwilightopiaMod.MODID + ":santa_claus");
	public static final EntityType<BearEntity> BEAR = EntityType.Builder.create(BearEntity::new, EntityClassification.CREATURE).size(1.4F, 1.4F).build(TwilightopiaMod.MODID + ":bear");
	public static final EntityType<RatEntity> RAT = EntityType.Builder.create(RatEntity::new, EntityClassification.MONSTER).size(0.6F, 0.6F).build(TwilightopiaMod.MODID + ":rat");
	public static final EntityType<ChoxEntity> CHOX = EntityType.Builder.create(ChoxEntity::new, EntityClassification.CREATURE).size(0.6F, 0.6F).build(TwilightopiaMod.MODID + ":chox");
	public static final EntityType<MobCEntity> MOB_C = EntityType.Builder.create(MobCEntity::new, EntityClassification.MONSTER).size(0.9F, 1.4F).build(TwilightopiaMod.MODID + ":mob_c");
	public static final EntityType<HypnotomEntity> HYPNOTOM = EntityType.Builder.create(HypnotomEntity::new, EntityClassification.MONSTER).size(0.9F, 0.5F).build(TwilightopiaMod.MODID + ":hypnotom");
	public static final EntityType<GingerBreadManEntity> GINGER_BREAD_MAN = EntityType.Builder.create(GingerBreadManEntity::new, EntityClassification.MONSTER).size(0.6F, 1.95F).build(TwilightopiaMod.MODID + ":ginger_bread_man");
	public static final EntityType<GingerBreadBossEntity> GINGER_BREAD_BOSS = EntityType.Builder.create(GingerBreadBossEntity::new, EntityClassification.MONSTER).size(3.6F, 12.0F).build(TwilightopiaMod.MODID + ":ginger_bread_boss");
	public static final EntityType<SnomeEntity> SNOME = EntityType.Builder.create(SnomeEntity::new, EntityClassification.MONSTER).size(2F, 2F).build(TwilightopiaMod.MODID + ":snome");
	public static final EntityType<JelimeEntity> JELIME = EntityType.Builder.create(JelimeEntity::new, EntityClassification.MONSTER).size(1.34F, 1.34F).build(TwilightopiaMod.MODID + ":jelime");
	public static final EntityType<NeonlimeEntity> NEONLIME = EntityType.Builder.create(NeonlimeEntity::new, EntityClassification.MONSTER).size(2F, 2F).build(TwilightopiaMod.MODID + ":neonlime");
	//public static final EntityType<? extends BoatPlusEntity> BOAT_PLUS = EntityType.Builder.<BoatPlusEntity>create(BoatPlusEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build(TwilightopiaMod.MODID + ":boat_plus");
}
