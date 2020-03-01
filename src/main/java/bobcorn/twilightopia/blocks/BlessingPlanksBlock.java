package bobcorn.twilightopia.blocks;

import bobcorn.twilightopia.effects.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlessingPlanksBlock extends Block {
	public BlessingPlanksBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		if (worldIn.getGameTime() % 40L == 0 && entityIn instanceof LivingEntity) {
			((LivingEntity)entityIn).addPotionEffect(new EffectInstance(ModEffects.SAKURA_BLESSING,400));
		}
	}
}
