package bobcorn.twilightopia.init;

import java.util.Map;

import com.google.common.collect.Maps;

import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

public class ModVanillaCompat
{
	public static final Map<Block, Block> BLOCK_STRIPPING_MAP_PLUS= Maps.newHashMap();
	
    public static void init()
    {
    	//Flammability
        registerFlammable(ModBlocks.CHERRY_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.PINK_CHERRY_LEAVES.get(), 30, 60);
        registerFlammable(ModBlocks.CHERRY_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.CHERRY_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.STRIPPED_CHERRY_LOG.get(), 5, 5);
        registerFlammable(ModBlocks.STRIPPED_CHERRY_WOOD.get(), 5, 5);
        registerFlammable(ModBlocks.CHERRY_PLANKS.get(), 5, 20);
        registerFlammable(ModBlocks.CHERRY_SLAB.get(), 5, 20);
        registerFlammable(ModBlocks.CHERRY_STAIRS.get(), 5, 20);
        registerFlammable(ModBlocks.CHERRY_FENCE.get(), 5, 20);
        registerFlammable(ModBlocks.CHERRY_FENCE_GATE.get(), 5, 20);

        registerFlammable(ModBlocks.IGNITE_LEAVES.get(), 40, 100);
        registerFlammable(ModBlocks.IGNITE_LOG.get(), 10, 10);
        registerFlammable(ModBlocks.IGNITE_WOOD.get(), 10, 10);
        registerFlammable(ModBlocks.STRIPPED_IGNITE_LOG.get(), 10, 10);
        registerFlammable(ModBlocks.STRIPPED_IGNITE_WOOD.get(), 10, 10);
        registerFlammable(ModBlocks.IGNITE_PLANKS.get(), 10, 40);
        registerFlammable(ModBlocks.IGNITE_SLAB.get(), 10, 40);
        registerFlammable(ModBlocks.IGNITE_STAIRS.get(), 10, 40);
        registerFlammable(ModBlocks.IGNITE_FENCE.get(), 10, 40);
        registerFlammable(ModBlocks.IGNITE_FENCE_GATE.get(), 10, 40);
        
        registerFlammable(ModBlocks.CHERRY_SAPLING.get(), 20, 30);
        registerFlammable(ModBlocks.IGNITE_SAPLING.get(), 40, 60);
        registerFlammable(ModBlocks.PROPHET_SAPLING.get(), 20, 30);
        
        registerFlammable(ModBlocks.BELL_LEAVES.get(), 40, 100);
        registerFlammable(ModBlocks.PROPHET_LOG.get(), 10, 10);
        
        registerFlammable(ModBlocks.ROSE.get(), 20, 30);
        registerFlammable(ModBlocks.TANGERINE_ROSE.get(), 20, 30);
        registerFlammable(ModBlocks.BLUE_ROSE.get(), 20, 30);
        registerFlammable(ModBlocks.FUCHSIA_ROSE.get(), 20, 30);
        registerFlammable(ModBlocks.WHITE_ROSE.get(), 20, 30);
        
        //Log Stripping
        registerStrippable(ModBlocks.CHERRY_LOG.get(), ModBlocks.STRIPPED_CHERRY_LOG.get());
        registerStrippable(ModBlocks.CHERRY_WOOD.get(), ModBlocks.STRIPPED_CHERRY_WOOD.get());
        registerStrippable(ModBlocks.IGNITE_LOG.get(), ModBlocks.STRIPPED_IGNITE_LOG.get());
        registerStrippable(ModBlocks.IGNITE_WOOD.get(), ModBlocks.STRIPPED_IGNITE_WOOD.get());
        registerStrippable(ModBlocks.PROPHET_LOG.get(), Blocks.STRIPPED_BIRCH_LOG);
    }

    public static void registerStrippable(Block log, Block stripped_log) {
    	BLOCK_STRIPPING_MAP_PLUS.put(log, stripped_log);
    }

    public static void registerFlammable(Block blockIn, int encouragement, int flammability)
    {
        FireBlock fireblock = (FireBlock)Blocks.FIRE;
        fireblock.setFireInfo(blockIn, encouragement, flammability);
    }
}