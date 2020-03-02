package bobcorn.twilightopia.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.items.ModItems;

import java.util.function.Supplier;

public final class ModItemGroups {

	public static final ItemGroup TWILIGHT_WORLD_GROUP = new ModItemGroup("twilightopia_world", () -> new ItemStack(ModItems.lodge_herb.get()));
	public static final ItemGroup TWILIGHT_BLOCK_GROUP = new ModItemGroup("twilightopia_block", () -> new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TwilightopiaMod.MODID, "grassed_chocolate_block"))));
	public static final ItemGroup TWILIGHT_FOOD_GROUP = new ModItemGroup("twilightopia_food", () -> new ItemStack(ModItems.chocolate_bar.get()));

	public static final class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
}
