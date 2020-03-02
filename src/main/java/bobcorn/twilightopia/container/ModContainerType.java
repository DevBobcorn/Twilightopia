package bobcorn.twilightopia.container;

import bobcorn.twilightopia.TwilightopiaMod;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.extensions.IForgeContainerType;

public final class ModContainerType {
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, TwilightopiaMod.MODID);

	public static final RegistryObject<ContainerType<ProphetLogContainer>> PROPHET_LOG = CONTAINER_TYPES.register("prophet_log", () -> IForgeContainerType.create(ProphetLogContainer::new));
}
