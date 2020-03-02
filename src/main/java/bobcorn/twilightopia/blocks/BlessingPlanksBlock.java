package bobcorn.twilightopia.blocks;

//import bobcorn.twilightopia.effects.ModEffects;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlessingPlanksBlock extends Block {
	public BlessingPlanksBlock(Properties properties) {
		super(properties);
	}

	@Override
	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
		/*
		if (entityIn instanceof LivingEntity) {
			Boolean flag = true;
			Object[] effs = ((LivingEntity)entityIn).getActivePotionEffects().toArray();
			for (int i = 0; i < effs.length; i++) {
				EffectInstance effect = (EffectInstance)effs[i];
				if (effect.getPotion() == ModEffects.SAKURA_BLESSING && effect.getDuration() < 150) {
					((LivingEntity)entityIn).addPotionEffect(new EffectInstance(ModEffects.SAKURA_BLESSING,400));
					flag = false;
					break;
				}
			}
			if (flag) 
				((LivingEntity)entityIn).addPotionEffect(new EffectInstance(ModEffects.SAKURA_BLESSING,400));
		}
		*/
	}
}
