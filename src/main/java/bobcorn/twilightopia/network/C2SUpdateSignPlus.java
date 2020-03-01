package bobcorn.twilightopia.network;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkEvent;
import java.util.function.Supplier;

import bobcorn.twilightopia.tileentity.SignPlusTileEntity;

public final class C2SUpdateSignPlus {
	private BlockPos pos;
	private String[] lines;

	public C2SUpdateSignPlus(BlockPos p_i49822_1_, String p_i49822_2_, String p_i49822_3_, String p_i49822_4_,
			String p_i49822_5_) {
		this.pos = p_i49822_1_;
		this.lines = new String[] { p_i49822_2_, p_i49822_3_, p_i49822_4_, p_i49822_5_ };
	}

	public static void encode(final C2SUpdateSignPlus msg, final PacketBuffer packetBuffer) {
		packetBuffer.writeBlockPos(msg.pos);
		for (int i = 0; i < 4; ++i) {
			packetBuffer.writeString(msg.lines[i]);
		}
	}

	public static C2SUpdateSignPlus decode(final PacketBuffer packetBuffer) {
		String[] l = new String[4];
		BlockPos p = packetBuffer.readBlockPos();
		for (int i = 0; i < 4; ++i) {
			l[i] = packetBuffer.readString(384);
		}
		return new C2SUpdateSignPlus(p, l[0], l[1], l[2], l[3]);
	}

	@SuppressWarnings("deprecation")
	public static void handle(final C2SUpdateSignPlus msg, final Supplier<NetworkEvent.Context> contextSupplier) {
		final NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			//System.out.println("Handle C2S, Update Sign+");
			final ServerPlayerEntity player = context.getSender();
			if (player == null) {
				return;
			}
			player.markPlayerActive();
			ServerWorld serverworld = player.server.getWorld(player.dimension);
			BlockPos blockpos = msg.pos;
			if (serverworld.isBlockLoaded(blockpos)) {
				BlockState blockstate = serverworld.getBlockState(blockpos);
				TileEntity tileentity = serverworld.getTileEntity(blockpos);
				if (!(tileentity instanceof SignPlusTileEntity)) {
					return;
				}
				SignPlusTileEntity signtileentity = (SignPlusTileEntity) tileentity;
				if (!signtileentity.getIsEditable() || signtileentity.getPlayer() != player) {
					player.server.logWarning(
							"Player " + player.getName().getString() + " just tried to change non-editable sign");
					return;
				}
				String[] astring = msg.lines;
				for (int i = 0; i < astring.length; ++i) {
					signtileentity.setText(i,
							new StringTextComponent(TextFormatting.getTextWithoutFormattingCodes(astring[i])));
				}
				signtileentity.markDirty();
				serverworld.notifyBlockUpdate(blockpos, blockstate, blockstate, 3);
			}
		});
		context.setPacketHandled(true);
	}
}
