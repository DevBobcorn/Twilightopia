package bobcorn.twilightopia.effects;

import javax.annotation.Nonnull;

import bobcorn.twilightopia.init.ModDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class FrozenEffect extends Effect {
	public FrozenEffect() {
		super(EffectType.HARMFUL, 0xFD90FF);
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		//System.out.println("Effect Worked!");
		return duration % 200 == 0;
	}

	@Override
	public void performEffect(@Nonnull LivingEntity living, int amplified) {
		if(living instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity)living;
			if (player.getHealth() > player.getMaxHealth() * 0.5F)
				player.attackEntityFrom(ModDamageSource.FROZEN,0.5F);
		}
	}
}
