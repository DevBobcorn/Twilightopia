package bobcorn.twilightopia.effects;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;

public class ModEffects {
	static public final Effect NEON_ILLUSION = new NeonIllusionEffect();
	static public final Effect SAKURA_BLESSING = new SakuraBlessingEffect();
	static public final Effect HEAVY_MIST = new HeavyMistEffect();
	static public final Effect FROZEN = new FrozenEffect().addAttributesModifier(SharedMonsterAttributes.MOVEMENT_SPEED, "6C84B6B8-546C-4F15-A02B-7EAFABBAA95B", (double)-0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL);
}
