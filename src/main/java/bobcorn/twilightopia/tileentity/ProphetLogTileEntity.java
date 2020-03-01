package bobcorn.twilightopia.tileentity;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import bobcorn.twilightopia.StoryTeller;
import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.container.ProphetLogContainer;
import bobcorn.twilightopia.items.ModItems;
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
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
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

	public final ItemStackHandler rubySlot = new ItemStackHandler(1) {
		@Override
		public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) {
			return !stack.isEmpty() && stack.getItem() == ModItems.ruby;
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
		return new TranslationTextComponent(ModBlocks.PROPHET_LOG.getTranslationKey());
	}

	public ProphetLogTileEntity() {
		super(ModTileEntityType.PROPHET_LOG);
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
		TranslationTextComponent prophetName = new TranslationTextComponent(ModBlocks.PROPHET_LOG.getTranslationKey());

		String story = StoryTeller.GetStory(itemSlot.getStackInSlot(0));

		if (story != "") {
			ItemStack NewBookStack = new ItemStack(Items.WRITTEN_BOOK);
			NewBookStack.setTagInfo("resolved", new ByteNBT((byte) 1));
			NewBookStack.setTagInfo("generation", new IntNBT(0));
			NewBookStack.setTagInfo("author", new StringNBT(prophetName.getString()));
			NewBookStack.setTagInfo("title",
					new StringNBT(I18n.format("texts." + TwilightopiaMod.MODID + ".book_of_story")));

			ListNBT NewPageList = new ListNBT();
			NewPageList.add(new StringNBT(story));
			NewBookStack.setTagInfo("pages", NewPageList);

			bookSlot.setStackInSlot(0, NewBookStack);
			rubySlot.getStackInSlot(0).shrink(1);
			//itemSlot.getStackInSlot(0).shrink(1);
		}

		tellingStory = false;
	}
	
	@Override
	public void tick() {
		ringBell();
	}
	
	private boolean ringBell() {
		if (rand.nextInt(100) == 0 && checkBell()) {
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
	
	private int rad = 3;
	private boolean checkBell() {
		for (int x = getPos().getX() - rad;x <= getPos().getX() + rad;x++)
			for (int z = getPos().getZ() - rad;z <= getPos().getZ() + rad;z++)
				for (int y = getPos().getY();y <= getPos().getY() + rad && y < getWorld().getMaxHeight();y++)
					if (getWorld().getBlockState(new BlockPos(x,y,z)).getBlock() == ModBlocks.BELL_LEAVES)
						return true;
		return false;
	}

	protected float getSoundPitch() {
		return getPitch(rand);
	}

	private static float getPitch(Random random) {
		return (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F;
	}
}
