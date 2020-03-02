package bobcorn.twilightopia.tileentity;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Sets;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.container.ProphetLogContainer;
import bobcorn.twilightopia.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.resources.IResource;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.items.ItemStackHandler;

public class ProphetLogTileEntity extends TileEntity implements INamedContainerProvider, ITickableTileEntity {
	private static final Random rand = new Random();
	private static final AxisAlignedBB ZERO_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
	private static final Predicate<MobEntity> WARNING = new Predicate<MobEntity>() {
		public boolean test(@Nullable MobEntity p_test_1_) {
			return p_test_1_ != null && Types.contains(p_test_1_.getType());
		}
	};
	
	private static final Set<EntityType<?>> Types = Sets.newHashSet(EntityType.CAVE_SPIDER, EntityType.CREEPER,
			EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDER_DRAGON, EntityType.ENDERMAN,
			EntityType.ENDERMITE, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HUSK,
			EntityType.ILLUSIONER, EntityType.MAGMA_CUBE, EntityType.ZOMBIE_PIGMAN, EntityType.PHANTOM,
			EntityType.PILLAGER, EntityType.RAVAGER, EntityType.SHULKER, EntityType.SILVERFISH, EntityType.SKELETON,
			EntityType.SLIME, EntityType.SPIDER, EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR,
			EntityType.WITCH, EntityType.WITHER, EntityType.WITHER_SKELETON, EntityType.ZOMBIE,
			EntityType.ZOMBIE_VILLAGER);

	private boolean tellingStory = false;

	private static final String RUBY_SLOT_TAG = "ruby_slot";
	private static final String ITEM_SLOT_TAG = "item_slot";
	private static final String BOOK_SLOT_TAG = "result_slot";

	private boolean hasbell = false;
	public final ItemStackHandler rubySlot = new ItemStackHandler(1) {
		@Override
		public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
			return !stack.isEmpty() && stack.getItem() == ModItems.ruby.get();
		}

		@Override
		protected void onContentsChanged(final int slot) {
			super.onContentsChanged(slot);
			if (!tellingStory)
				TryTellStory();
			// Mark the tile entity as having changed whenever its inventory changes.
			// "markDirty" tells vanilla that the chunk containing the tile entity has
			// changed and means the game will save the chunk to disk later.
			ProphetLogTileEntity.this.markDirty();
		}
	};

	public final ItemStackHandler itemSlot = new ItemStackHandler(1) {
		@Override
		public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
			return !stack.isEmpty();
		}

		@Override
		protected void onContentsChanged(final int slot) {
			super.onContentsChanged(slot);
			if (!tellingStory)
				TryTellStory();
			// Mark the tile entity as having changed whenever its inventory changes.
			// "markDirty" tells vanilla that the chunk containing the tile entity has
			// changed and means the game will save the chunk to disk later.
			ProphetLogTileEntity.this.markDirty();
		}
	};

	public final ItemStackHandler bookSlot = new ItemStackHandler(1) {
		@Override
		public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
			return !stack.isEmpty() && (stack.getItem() == Items.BOOK || stack.getItem() == Items.WRITABLE_BOOK
					|| stack.getItem() == Items.WRITTEN_BOOK || stack.getItem() == Items.ENCHANTED_BOOK);
		}

		@Override
		protected void onContentsChanged(final int slot) {
			super.onContentsChanged(slot);
			if (!tellingStory)
				TryTellStory();
			// Mark the tile entity as having changed whenever its inventory changes.
			// "markDirty" tells vanilla that the chunk containing the tile entity has
			// changed and means the game will save the chunk to disk later.
			ProphetLogTileEntity.this.markDirty();
		}
	};

	public int getSizeInventory() {
		return 3;
	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		this.rubySlot.deserializeNBT(compound.getCompound(RUBY_SLOT_TAG));
		this.bookSlot.deserializeNBT(compound.getCompound(BOOK_SLOT_TAG));
		this.itemSlot.deserializeNBT(compound.getCompound(ITEM_SLOT_TAG));
	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		compound.put(RUBY_SLOT_TAG, this.rubySlot.serializeNBT());
		compound.put(BOOK_SLOT_TAG, this.bookSlot.serializeNBT());
		compound.put(ITEM_SLOT_TAG, this.itemSlot.serializeNBT());
		return compound;
	}

	@Nonnull
	@Override
	public ITextComponent getDisplayName() {
		return new TranslationTextComponent(ModBlocks.PROPHET_LOG.get().getTranslationKey());
	}

	public ProphetLogTileEntity() {
		super(ModTileEntityType.PROPHET_LOG.get());
	}

	public ProphetLogTileEntity(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	@Nonnull
	@Override
	public Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player) {
		return new ProphetLogContainer(windowId, this, inventory, player);
	}

	public void TryTellStory() {
		if (rubySlot.getStackInSlot(0).isEmpty() || bookSlot.getStackInSlot(0).isEmpty()
				|| itemSlot.getStackInSlot(0).isEmpty() || tellingStory)
			return;
		if (bookSlot.getStackInSlot(0).getTag().getString("title") == I18n.format("texts." + TwilightopiaMod.MODID + ".book_of_story"))
			return;
		tellingStory = true;
		System.out.println("Booknum: " + bookSlot.getStackInSlot(0).getCount());
		TranslationTextComponent prophetName = new TranslationTextComponent(ModBlocks.PROPHET_LOG.get().getTranslationKey());

		String story = GetStory(itemSlot.getStackInSlot(0));

		if (story != "") {
			ItemStack NewBookStack = new ItemStack(Items.WRITTEN_BOOK);
			NewBookStack.setTagInfo("resolved", ByteNBT.func_229671_a_((byte) 1));
			NewBookStack.setTagInfo("generation", IntNBT.func_229692_a_(0));
			NewBookStack.setTagInfo("author", StringNBT.func_229705_a_(prophetName.getString()));
			NewBookStack.setTagInfo("title", StringNBT.func_229705_a_(I18n.format("texts." + TwilightopiaMod.MODID + ".book_of_story")));
			/*
			ListNBT NewPageList = new ListNBT();
			NewPageList.add(StringNBT.func_229705_a_(story));
			NewBookStack.setTagInfo("pages", NewPageList);
			*/
			//TODO: Write book
			bookSlot.setStackInSlot(0, NewBookStack);
			rubySlot.getStackInSlot(0).shrink(1);
			itemSlot.getStackInSlot(0).shrink(1);
		}

		tellingStory = false;
	}

	private String GetStory(ItemStack Target) {
		IResource iresource = null;
		String lines = "";
		try {
			System.out.println("Tellin' Story From Path " + getPath(Target));

			String s = "" + TextFormatting.WHITE + TextFormatting.OBFUSCATED + TextFormatting.GREEN
					+ TextFormatting.AQUA;

			iresource = Minecraft.getInstance().getResourceManager()
					.getResource(new ResourceLocation(TwilightopiaMod.MODID, getPath(Target)));
			System.out.println("Story Found.");
			InputStream inputstream = iresource.getInputStream();
			BufferedReader bufferedreader = new BufferedReader(
					new InputStreamReader(inputstream, StandardCharsets.UTF_8));
			Random random = new Random(8124371L);
			String s1;
			while ((s1 = bufferedreader.readLine()) != null) {
				String s2;
				String s3;
				for (s1 = s1.replaceAll("PLAYERNAME", Minecraft.getInstance().getSession().getUsername()); s1
						.contains(s); s1 = s2 + TextFormatting.WHITE + TextFormatting.OBFUSCATED
								+ "XXXXXXXX".substring(0, random.nextInt(4) + 3) + s3) {
					int j = s1.indexOf(s);
					s2 = s1.substring(0, j);
					s3 = s1.substring(j + s.length());
				}
				System.out.println("Line: " + s1);
				lines = lines.concat(s1 + '\n');
			}
			inputstream.close();
		} catch (Exception exception) {
			// System.out.println("No Story / Execution Error!");
		} finally {
			IOUtils.closeQuietly((Closeable) iresource);
		}
		return lines;
	}

	private String getPath(ItemStack Target) {
		return "stories/" + Minecraft.getInstance().getLanguageManager().getCurrentLanguage().getCode() + '/'
				+ Target.getItem().getTranslationKey() + ".txt";
	}
	
	@Override
	public void tick() {
		if (hasbell) ringBell();
	}

	private boolean ringBell() {
		if (rand.nextInt(50) == 0) {
			List<MobEntity> list = getWorld().getEntitiesWithinAABB(MobEntity.class,
					ZERO_AABB.offset(getPos()).grow(20.0D), WARNING);
			if (!list.isEmpty()) {
				MobEntity mobentity = list.get(rand.nextInt(list.size()));
				if (!mobentity.isSilent()) {
					getWorld().playSound((PlayerEntity) null, this.getPos(), SoundEvents.BLOCK_BELL_USE,
							SoundCategory.BLOCKS, 0.7F, getPitch(rand));
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	protected float getSoundPitch() {
		return getPitch(rand);
	}

	private static float getPitch(Random random) {
		return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
	}
}
