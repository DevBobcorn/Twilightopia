package bobcorn.twilightopia.items;

import bobcorn.twilightopia.advancements.TwilitAdvancements;
import bobcorn.twilightopia.blocks.TuliportalBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
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
		PlayerEntity player = context.getPlayer();
		World worldIn = context.getWorld();
		BlockPos blockpos = context.getPos();
		//BlockPos blockpos1 = blockpos.offset(context.getFace());

		if (worldIn.getBlockState(blockpos).getBlock() == Blocks.WHITE_TULIP || worldIn.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.WHITE_TULIP) {
			//System.out.println("Check Portal!");
			if (TuliportalBlock.checkPortal(worldIn, blockpos)) {//Gen-portal, lightining effect-only
				LightningBoltEntity bolt1 = new LightningBoltEntity(worldIn, blockpos.getX(),blockpos.getY(),blockpos.getZ(),true);
				if (worldIn instanceof ClientWorld)
					((ClientWorld)worldIn).addLightning(bolt1);
				else if (worldIn instanceof ServerWorld)
					((ServerWorld)worldIn).addLightningBolt(bolt1);
				else return ActionResultType.FAIL;
				if (player instanceof ServerPlayerEntity) {
					TwilitAdvancements.MAKE_TULIPORTAL.trigger((ServerPlayerEntity) player);
				}
				return ActionResultType.SUCCESS;
			}
		}
		LightningBoltEntity bolt = new LightningBoltEntity(worldIn, blockpos.getX(),blockpos.getY(),blockpos.getZ(),false);
		if (worldIn instanceof ClientWorld)
			((ClientWorld)worldIn).addLightning(bolt);
		else if (worldIn instanceof ServerWorld) 
			((ServerWorld)worldIn).addLightningBolt(bolt);
			
		else return ActionResultType.FAIL;
		
		context.getPlayer().addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE,80));
		return ActionResultType.SUCCESS;
	}
}
