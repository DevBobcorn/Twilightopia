package bobcorn.twilightopia.container;

import javax.annotation.Nonnull;

import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.tileentity.ProphetLogTileEntity;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ReportedException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class ProphetLogContainer extends Container {
	public final ProphetLogTileEntity tileEntity;
	private final IWorldPosCallable canInteractWithCallable;

	
	public ProphetLogContainer(final int windowId, final ProphetLogTileEntity tileEntity,
			final PlayerInventory playerInventory, final PlayerEntity player) {
		super(ModContainerType.PROPHET_LOG, windowId);

		canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		this.tileEntity = tileEntity;

		addSlots(tileEntity, playerInventory);
	}
	
	public ProphetLogContainer(final int windowId, final PlayerInventory playerInventory, PacketBuffer data) {
		super(ModContainerType.PROPHET_LOG, windowId);

		final ProphetLogTileEntity tileEntity = getProphetLogTileEntity(playerInventory, data);
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());
		this.tileEntity = tileEntity;

		addSlots(tileEntity, playerInventory);
	}
	
	/**
	 * Adds all the slots for the tileEntity's inventory and the playerInventory to this container
	 */
	private void addSlots(final ProphetLogTileEntity tileEntity, final PlayerInventory playerInventory) {
		// Tile inventory slot
		this.addSlot(new SlotItemHandler(tileEntity.rubySlot, 0, 21, 54));
		this.addSlot(new SlotItemHandler(tileEntity.bookSlot, 0, 80, 54));
		this.addSlot(new SlotItemHandler(tileEntity.itemSlot, 0, 140, 54));

		final int playerInventoryStartX = 8;
		final int playerInventoryStartY = 84;
		final int slotSizePlus2 = 18; // slots are 16x16, plus 2 (for spacing/borders) is 18x18

		// Player Top Inventory slots
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, playerInventoryStartX + (column * slotSizePlus2), playerInventoryStartY + (row * slotSizePlus2)));
			}
		}

		final int playerHotbarY = playerInventoryStartY + slotSizePlus2 * 3 + 4;
		// Player Hotbar slots
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, playerInventoryStartX + (column * slotSizePlus2), playerHotbarY));
		}
	}
	
	private ProphetLogTileEntity getProphetLogTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		final BlockPos pos = data.readBlockPos();
		final World world = playerInventory.player.world;
		final TileEntity tileAtPos = world.getTileEntity(pos);

		if (!(tileAtPos instanceof ProphetLogTileEntity)) {
			final Throwable error;
			if (tileAtPos == null) // instanceof returns false for null, so we check it here
				error = new NullPointerException("No ProphetLogTileEntity at position");
			else
				error = new ClassCastException(tileAtPos.getClass() + " is not a ProphetLogTileEntity");
			CrashReport crashReport = CrashReport.makeCrashReport(error, "Creating Container for a ProphetLogTileEntity");
			CrashReportCategory category = crashReport.makeCategory("Block at position");
			CrashReportCategory.addBlockInfo(category, pos, world.getBlockState(pos));
			throw new ReportedException(crashReport);
		}
		return (ProphetLogTileEntity) tileAtPos;
	}
	
	@Override
	public boolean canInteractWith(@Nonnull final PlayerEntity player) {
		return isWithinUsableDistance(canInteractWithCallable, player, ModBlocks.PROPHET_LOG);
	}
}