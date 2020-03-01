package bobcorn.twilightopia.effects;

import javax.annotation.Nonnull;

import bobcorn.twilightopia.items.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class SakuraBlessingEffect extends Effect {
	public SakuraBlessingEffect() {
		super(EffectType.BENEFICIAL, 0xFD90FF);
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		//System.out.println("Effect Worked!");
		return duration % 50 == 0;
	}

	@Override
	public void performEffect(@Nonnull LivingEntity living, int amplified) {
		if(living instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)living;
			if (player.getHeldItemMainhand().getItem() == ModItems.sakura) {
				if (player.getHealth() <= player.getMaxHealth() * 0.85F - 0.1F)
					player.heal(0.5F);
			}
		}
	}
}
