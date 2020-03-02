package bobcorn.twilightopia.items;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.effects.ModEffects;
import bobcorn.twilightopia.entity.BoatPlusEntity;
import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.init.ModItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.HoeItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SoupItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(TwilightopiaMod.MODID)
public final class ModItems {
	public static final Item lodge_herb = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item quill = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item chocolate_bar = new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(0.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item white_chocolate_bar = new Item(new Item.Properties().food((new Food.Builder()).hunger(5).saturation(0.7F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item cherry = new Item(new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.8F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item apple_pie = new Item(new Item.Properties().food((new Food.Builder()).hunger(7).saturation(0.3F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item cherry_pie = new Item(new Item.Properties().food((new Food.Builder()).hunger(6).saturation(0.9F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item cheese = new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(0.4F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item candy_cane = new BlockNamedItem(ModBlocks.CANDY_CANE_CROP, new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.4F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item sakura = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item flame_nut = new Item(new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.2F).effect(new EffectInstance(Effects.GLOWING, 600, 0), 1.0F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item strawberry_seeds = new BlockNamedItem(ModBlocks.STRAWBERRY_CROP, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item tomato_seeds = new BlockNamedItem(ModBlocks.TOMATO_CROP, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item strawberry = new Item(new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item tomato = new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(2.0F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item venison = new Item(new Item.Properties().food((new Food.Builder()).hunger(3).saturation(0.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item cooked_venison = new Item(new Item.Properties().food((new Food.Builder()).hunger(8).saturation(0.8F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP));
	public static final Item jelly = new SoupItem((new Item.Properties()).maxStackSize(1).group(ModItemGroups.TWILIGHT_FOOD_GROUP).food(Foods.RABBIT_STEW));
	public static final Item jelly_ball = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item neonlime_ball = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item neoncolate = new Item((new Item.Properties()).group(ModItemGroups.TWILIGHT_FOOD_GROUP).food((new Food.Builder()).hunger(8).saturation(0.8F).effect(new EffectInstance(ModEffects.NEON_ILLUSION, 1000, 0), 1.0F).build()));
	public static final Item ginger_bread = new Item((new Item.Properties()).group(ModItemGroups.TWILIGHT_FOOD_GROUP).food(Foods.BREAD));
	public static final Item ruby = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_axe = new AxeItem(ModItemTier.RUBY, 5.0F, -3.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_sword = new SwordItem(ModItemTier.RUBY, 3, -2.4F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_shovel = new ShovelItem(ModItemTier.RUBY, 1.5F, -3.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_pickaxe = new PickaxeItem(ModItemTier.RUBY, 1, -2.8F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_hoe = new HoeItem(ModItemTier.RUBY, 0.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_horse_armor = new HorseArmorItem(14, "ruby", (new Item.Properties()).maxStackSize(1).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_helmet = new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_chestplate = new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_leggings = new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ruby_boots = new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item santa_hat = new ArmorItem(ModArmorMaterial.SANTA, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item santa_coat = new ArmorItem(ModArmorMaterial.SANTA, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item santa_trousers = new ArmorItem(ModArmorMaterial.SANTA, EquipmentSlotType.LEGS, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item santa_boots = new ArmorItem(ModArmorMaterial.SANTA, EquipmentSlotType.FEET, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item crown = new ArmorItem(ModArmorMaterial.CROWN, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP).rarity(Rarity.EPIC));
	public static final Item blaze_and_gold = new BlazeAndGoldItem(new Item.Properties().maxDamage(64).group(ModItemGroups.TWILIGHT_WORLD_GROUP).rarity(Rarity.UNCOMMON));
	public static final Item deer_spawn_egg = new SpawnEggItem(ModEntityType.DEER, 0x732424, 0xBB5B37, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item rainbull_spawn_egg = new SpawnEggItem(ModEntityType.RAINBULL, 0xFEFEFF, 0xF998FF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item santa_claus_spawn_egg = new SpawnEggItem(ModEntityType.SANTA_CLAUS, 0xFEFEFF, 0xFF0202, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item bear_spawn_egg = new SpawnEggItem(ModEntityType.BEAR, 0xCC3800, 0xAB3508, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item rat_spawn_egg = new SpawnEggItem(ModEntityType.RAT, 0xFF9133, 0xFFC033, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item chox_spawn_egg = new SpawnEggItem(ModEntityType.CHOX, 0x773200, 0x964500, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item mob_c_spawn_egg = new SpawnEggItem(ModEntityType.MOB_C, 0xFFCA33, 0xFF9B33, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item hypnotom_spawn_egg = new SpawnEggItem(ModEntityType.HYPNOTOM, 0x3388FF, 0xBE67FF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ginger_bread_man_spawn_egg = new SpawnEggItem(ModEntityType.GINGER_BREAD_MAN, 0xCC3800, 0xA02A00, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item ginger_bread_boss_spawn_egg = new SpawnEggItem(ModEntityType.GINGER_BREAD_BOSS, 0xFF9153, 0xA02A00, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item snome_spawn_egg = new SpawnEggItem(ModEntityType.SNOME, 0xFEFEFF, 0x55BBFF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item jelime_spawn_egg = new SpawnEggItem(ModEntityType.JELIME, 0xDD55DD, 0xFF88FF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item neonlime_spawn_egg = new SpawnEggItem(ModEntityType.NEONLIME, 0xFF88FF, 0xBE67FF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP));
	public static final Item cherry_sign = new SignPlusItem(new Item.Properties().group(ModItemGroups.TWILIGHT_BLOCK_GROUP), ModBlocks.CHERRY_SIGN, ModBlocks.CHERRY_WALL_SIGN);
	public static final Item ignite_sign = new SignPlusItem(new Item.Properties().group(ModItemGroups.TWILIGHT_BLOCK_GROUP), ModBlocks.IGNITE_SIGN, ModBlocks.IGNITE_WALL_SIGN);
	public static final Item cherry_boat = new BoatPlusItem(BoatPlusEntity.Type.CHERRY, new Item.Properties());
	public static final Item ignite_boat = new BoatPlusItem(BoatPlusEntity.Type.IGNITE, new Item.Properties());
	public static final Item magic_debris = new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP));
}

