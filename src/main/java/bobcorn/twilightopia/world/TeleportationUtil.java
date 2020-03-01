package bobcorn.twilightopia.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;

public class TeleportationUtil
{
	public static void changeDimension(DimensionType type, Entity entity, BlockPos pos)
	{
		MinecraftServer server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

		if (server == null)
		{
			return;
		}

		DimensionType transferDimension = entity.dimension == type ? DimensionType.OVERWORLD : type;
		ServerWorld transferWorld = server.getWorld(transferDimension);

		if (!ForgeHooks.onTravelToDimension(entity, transferDimension) || entity.timeUntilPortal > 0)
		{
			return;
		}

		IChunk chunk = transferWorld.getChunk(pos);
		int transferY = chunk.getTopBlockY(Heightmap.Type.MOTION_BLOCKING, pos.getX(), pos.getZ()) + 1;

		Vec3d endpointPos = new Vec3d(pos.getX() + 0.5, transferY, pos.getZ() + 0.5);
		Entity teleportedEntity = teleportEntity(entity, transferWorld, endpointPos);

		teleportedEntity.fallDistance = 0.0F;
	}

	private static Entity teleportEntity(Entity entity, ServerWorld transferWorld, Vec3d transferPos)
	{
		if (entity instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity player = (ServerPlayerEntity) entity;
			player.teleport(transferWorld, transferPos.x, transferPos.y, transferPos.z, entity.rotationYaw, entity.rotationPitch);
			player.timeUntilPortal = 50;
			return player;
		}
		else
		{
			entity.timeUntilPortal = 300;
		}

		entity.detach();
		entity.dimension = transferWorld.dimension.getType();

		Entity teleportedEntity = entity.getType().create(transferWorld);

		if (teleportedEntity == null)
		{
			return entity;
		}

		teleportedEntity.copyDataFromOld(entity);
		teleportedEntity.setLocationAndAngles(transferPos.x, transferPos.y, transferPos.z, entity.rotationYaw, entity.rotationPitch);
		teleportedEntity.setRotationYawHead(entity.rotationYaw);
		teleportedEntity.setMotion(Vec3d.ZERO);
		transferWorld.func_217460_e(teleportedEntity);
		entity.remove();

		return teleportedEntity;
	}
}
