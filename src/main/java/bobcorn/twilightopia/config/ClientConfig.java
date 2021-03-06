package bobcorn.twilightopia.config;

import net.minecraft.item.DyeColor;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

import bobcorn.twilightopia.TwilightopiaMod;

/**
 * For configuration settings that change the behaviour of code on the LOGICAL CLIENT.
 * This can be moved to an inner class of TwilightModConfig, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
final class ClientConfig {

	final ForgeConfigSpec.BooleanValue clientBoolean;
	final ForgeConfigSpec.ConfigValue<List<String>> clientStringList;
	final ForgeConfigSpec.ConfigValue<DyeColor> clientEnumDyeColor;

	final ForgeConfigSpec.BooleanValue modelTranslucency;
	final ForgeConfigSpec.DoubleValue modelScale;

	ClientConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		clientBoolean = builder
				.comment("An example boolean in the client config")
				.translation(TwilightopiaMod.MODID + ".config.clientBoolean")
				.define("clientBoolean", true);
		clientStringList = builder
				.comment("An example list of Strings in the client config")
				.translation(TwilightopiaMod.MODID + ".config.clientStringList")
				.define("clientStringList", new ArrayList<>());
		clientEnumDyeColor = builder
				.comment("An example enum DyeColor in the client config")
				.translation(TwilightopiaMod.MODID + ".config.clientEnumDyeColor")
				.defineEnum("clientEnumDyeColor", DyeColor.WHITE);

		modelTranslucency = builder
				.comment("If the model should be rendered translucent")
				.translation(TwilightopiaMod.MODID + ".config.modelTranslucency")
				.define("modelTranslucency", true);
		modelScale = builder
				.comment("The scale to render the model at")
				.translation(TwilightopiaMod.MODID + ".config.modelScale")
				.defineInRange("modelScale", 0.0625, 0.0001, 100);
		builder.pop();
	}

}
