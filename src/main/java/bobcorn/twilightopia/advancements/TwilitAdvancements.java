package bobcorn.twilightopia.advancements;

import bobcorn.twilightopia.advancements.criterion.MakeTuliportalTrigger;
import net.minecraft.advancements.CriteriaTriggers;

public class TwilitAdvancements {
	public static final MakeTuliportalTrigger MAKE_TULIPORTAL = CriteriaTriggers.register(new MakeTuliportalTrigger());
	
	public static void init() { } //Write this to make the triggers initialized.
}
