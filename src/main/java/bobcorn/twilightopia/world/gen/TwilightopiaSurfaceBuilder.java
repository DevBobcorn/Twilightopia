package bobcorn.twilightopia.world.gen;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class TwilightopiaSurfaceBuilder {
	public static final BlockState WHITE_CHOCO = ModBlocks.WHITE_CHOCOLATE_BLOCK.getDefaultState();
	public static final BlockState CHOCO = ModBlocks.CHOCOLATE_BLOCK.getDefaultState();
	public static final BlockState GRASSY_WHITE_CHOCO = ModBlocks.GRASSED_WHITE_CHOCOLATE_BLOCK.getDefaultState();
	public static final BlockState GRASSY_CHOCO = ModBlocks.GRASSED_CHOCOLATE_BLOCK.getDefaultState();
	public static final BlockState MISTY_CHOCO = ModBlocks.MISTY_CHOCOLATE_BLOCK.getDefaultState();
	
	public static final SurfaceBuilderConfig GRASSY_CHOCO_CONFIG = new SurfaceBuilderConfig(GRASSY_CHOCO, CHOCO, WHITE_CHOCO);
	public static final SurfaceBuilderConfig GRASSY_WHITE_CHOCO_CONFIG = new SurfaceBuilderConfig(GRASSY_WHITE_CHOCO, WHITE_CHOCO, WHITE_CHOCO);
	public static final SurfaceBuilderConfig MISTY_CHOCO_CONFIG = new SurfaceBuilderConfig(MISTY_CHOCO, WHITE_CHOCO, WHITE_CHOCO);
}
