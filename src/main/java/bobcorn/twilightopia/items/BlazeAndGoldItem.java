package bobcorn.twilightopia.items;

import bobcorn.twilightopia.blocks.TuliportalBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class BlazeAndGoldItem extends Item {
	public BlazeAndGoldItem(Item.Properties builder) {
		super(builder);
	}

	public ActionResultType onItemUse(ItemUseContext context) {
		//PlayerEntity player = context.getPlayer();
		World world = context.getWorld();
		BlockPos blockpos = context.getPos();
		//BlockPos blockpos1 = blockpos.offset(context.getFace());

		if (world.getBlockState(blockpos).getBlock() == Blocks.WHITE_TULIP || world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.WHITE_TULIP) {
			//System.out.println("Check Portal!");
			if (TuliportalBlock.checkPortal(world, blockpos)) {//Gen-portal, lightining effect-only
				LightningBoltEntity bolt1 = new LightningBoltEntity(world, blockpos.getX(),blockpos.getY(),blockpos.getZ(),true);
				if (world instanceof ClientWorld)
					((ClientWorld)world).addLightning(bolt1);
				else if (world instanceof ServerWorld)
					((ServerWorld)world).addLightningBolt(bolt1);
				else return ActionResultType.FAIL;
				return ActionResultType.SUCCESS;
			}
		}
		LightningBoltEntity bolt = new LightningBoltEntity(world, blockpos.getX(),blockpos.getY(),blockpos.getZ(),false);
		if (world instanceof ClientWorld)
			((ClientWorld)world).addLightning(bolt);
		else if (world instanceof ServerWorld)
			((ServerWorld)world).addLightningBolt(bolt);
		else return ActionResultType.FAIL;
		
		context.getPlayer().addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE,80));
		return ActionResultType.SUCCESS;
	}
}
