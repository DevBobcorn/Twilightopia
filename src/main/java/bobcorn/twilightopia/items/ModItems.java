package bobcorn.twilightopia.items;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.entity.ModEntityTypes;
import bobcorn.twilightopia.init.ModItemGroups;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class ModItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TwilightopiaMod.MODID);
	
	public static final RegistryObject<Item> lodge_herb = ITEMS.register("lodge_herb", () -> new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> quill = ITEMS.register("quill", () -> new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> chocolate_bar = ITEMS.register("chocolate_bar", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(2.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> white_chocolate_bar = ITEMS.register("white_chocolate_bar", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(5).saturation(3.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> cherry = ITEMS.register("cherry", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(2).saturation(3.5F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> apple_pie = ITEMS.register("apple_pie", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(7).saturation(4.2F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> cherry_pie = ITEMS.register("cherry_pie", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(6).saturation(5.2F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> cheese = ITEMS.register("cheese", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(0.4F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> candy_cane = ITEMS.register("candy_cane", () -> new BlockNamedItem(ModBlocks.CANDY_CANE_CROP.get(), new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.4F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> sakura = ITEMS.register("sakura", () -> new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> flame_nut = ITEMS.register("flame_nut", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(2).saturation(0.4F).effect(new EffectInstance(Effects.GLOWING, 1200, 0), 1.0F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> strawberry_seeds = ITEMS.register("strawberry_seeds", () -> new BlockNamedItem(ModBlocks.STRAWBERRY_CROP.get(), (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> tomato_seeds = ITEMS.register("tomato_seeds", () -> new BlockNamedItem(ModBlocks.TOMATO_CROP.get(), (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> strawberry = ITEMS.register("strawberry", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(3).saturation(4.0F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> tomato = ITEMS.register("tomato", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(4).saturation(2.0F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> venison = ITEMS.register("venison", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(3).saturation(1.8F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> cooked_venison = ITEMS.register("cooked_venison", () -> new Item(new Item.Properties().food((new Food.Builder()).hunger(8).saturation(12.8F).build()).group(ModItemGroups.TWILIGHT_FOOD_GROUP)));
	public static final RegistryObject<Item> ruby = ITEMS.register("ruby", () -> new Item(new Item.Properties().group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_axe = ITEMS.register("ruby_axe", () -> new AxeItem(ModItemTier.RUBY, 5.0F, -3.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_sword = ITEMS.register("ruby_sword", () -> new SwordItem(ModItemTier.RUBY, 3, -2.4F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_shovel = ITEMS.register("ruby_shovel", () -> new ShovelItem(ModItemTier.RUBY, 1.5F, -3.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_pickaxe = ITEMS.register("ruby_pickaxe", () -> new PickaxeItem(ModItemTier.RUBY, 1, -2.8F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_hoe = ITEMS.register("ruby_hoe", () -> new HoeItem(ModItemTier.RUBY, 0.0F, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_horse_armor = ITEMS.register("ruby_horse_armor", () -> new HorseArmorItem(14, "ruby", (new Item.Properties()).maxStackSize(1).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_helmet = ITEMS.register("ruby_helmet", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.HEAD, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_chestplate = ITEMS.register("ruby_chestplate", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.CHEST, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_leggings = ITEMS.register("ruby_leggings", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.LEGS, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> ruby_boots = ITEMS.register("ruby_boots", () -> new ArmorItem(ModArmorMaterial.RUBY, EquipmentSlotType.FEET, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> blaze_and_gold = ITEMS.register("blaze_and_gold", () -> new BlazeAndGoldItem(new Item.Properties().maxDamage(64).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> deer_spawn_egg = ITEMS.register("deer_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.DEER , 0x732424, 0xBB5B37, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> rainbull_spawn_egg = ITEMS.register("rainbull_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.RAINBULL , 0xFEFEFF, 0xF998FF, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> santa_claus_spawn_egg = ITEMS.register("santa_claus_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.SANTA_CLAUS , 0xFEFEFF, 0xFF0202, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> bear_spawn_egg = ITEMS.register("bear_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.BEAR , 0xCC3800, 0xAB3508, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> rat_spawn_egg = ITEMS.register("rat_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.RAT , 0xFF9133, 0xFFC033, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> chox_spawn_egg = ITEMS.register("chox_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.CHOX , 0x773200, 0x964500, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> mob_c_spawn_egg = ITEMS.register("mob_c_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.MOB_C , 0xFFCA33, 0xFF9B33, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<ModSpawnEggItem> hypnotom_spawn_egg = ITEMS.register("hypnotom_spawn_egg", () -> new ModSpawnEggItem(ModEntityTypes.HYPNOTOM , 0x20D6FF, 0x8900BD, (new Item.Properties()).group(ModItemGroups.TWILIGHT_WORLD_GROUP)));
	public static final RegistryObject<Item> cherry_sign = ITEMS.register("cherry_sign", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ignite_sign = ITEMS.register("ignite_sign", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> cherry_boat = ITEMS.register("cherry_boat", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> ignite_boat = ITEMS.register("ignite_boat", () -> new Item(new Item.Properties()));
}

