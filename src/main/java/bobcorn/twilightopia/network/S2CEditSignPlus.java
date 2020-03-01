package bobcorn.twilightopia.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

import bobcorn.twilightopia.tileentity.SignPlusTileEntity;

public final class S2CEditSignPlus {
	private BlockPos signPosition;

	public S2CEditSignPlus(final BlockPos posIn) {
		this.signPosition = posIn;
	}

	public static void encode(final S2CEditSignPlus msg, final PacketBuffer packetBuffer) {
		packetBuffer.writeBlockPos(msg.signPosition);
	}

	public static S2CEditSignPlus decode(final PacketBuffer packetBuffer) {
		return new S2CEditSignPlus(packetBuffer.readBlockPos());
	}

	public static void handle(final S2CEditSignPlus msg, final Supplier<NetworkEvent.Context> contextSupplier) {
		final NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
			//System.out.println("Handle S2C, Open Edit Gui!");
			TileEntity tileentity = Minecraft.getInstance().world.getTileEntity(msg.signPosition);
			if (!(tileentity instanceof SignPlusTileEntity)) {
				tileentity = new SignPlusTileEntity();
				tileentity.setWorld(Minecraft.getInstance().world);
				tileentity.setPos(msg.signPosition);
			}

			// this.client.player.openSignEditor((SignTileEntity)tileentity);
			((SignPlusTileEntity) tileentity).openGuiClient((SignPlusTileEntity) tileentity);
		}));
		context.setPacketHandled(true);
	}
}
