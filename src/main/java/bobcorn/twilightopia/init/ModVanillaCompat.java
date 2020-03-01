package bobcorn.twilightopia.init;

import java.util.Map;

import com.google.common.collect.Maps;

import bobcorn.twilightopia.TwilightopiaMod;
import bobcorn.twilightopia.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.util.ResourceLocation;

public class ModVanillaCompat
{
	public static final Map<Block, Block> BLOCK_STRIPPING_MAP_PLUS= Maps.newHashMap();
	
    public static void init()
    {
    	//Flammability
        registerFlammable(ModBlocks.CHERRY_LEAVES, 30, 60);
        registerFlammable(ModBlocks.PINK_CHERRY_LEAVES, 30, 60);
        registerFlammable(ModBlocks.CHERRY_LOG, 5, 5);
        registerFlammable(ModBlocks.CHERRY_WOOD, 5, 5);
        registerFlammable(ModBlocks.STRIPPED_CHERRY_LOG, 5, 5);
        registerFlammable(ModBlocks.STRIPPED_CHERRY_WOOD, 5, 5);
        registerFlammable(ModBlocks.CHERRY_PLANKS, 5, 20);
        registerFlammable(ModBlocks.CHERRY_SLAB, 5, 20);
        registerFlammable(ModBlocks.CHERRY_STAIRS, 5, 20);
        registerFlammable(ModBlocks.CHERRY_FENCE, 5, 20);
        registerFlammable(ModBlocks.CHERRY_FENCE_GATE, 5, 20);

        registerFlammable(ModBlocks.IGNITE_LEAVES, 40, 100);
        registerFlammable(ModBlocks.IGNITE_LOG, 10, 10);
        registerFlammable(ModBlocks.IGNITE_WOOD, 10, 10);
        registerFlammable(ModBlocks.STRIPPED_IGNITE_LOG, 10, 10);
        registerFlammable(ModBlocks.STRIPPED_IGNITE_WOOD, 10, 10);
        registerFlammable(ModBlocks.IGNITE_PLANKS, 10, 40);
        registerFlammable(ModBlocks.IGNITE_SLAB, 10, 40);
        registerFlammable(ModBlocks.IGNITE_STAIRS, 10, 40);
        registerFlammable(ModBlocks.IGNITE_FENCE, 10, 40);
        registerFlammable(ModBlocks.IGNITE_FENCE_GATE, 10, 40);
        
        registerFlammable(ModBlocks.CHERRY_SAPLING, 20, 30);
        registerFlammable(ModBlocks.IGNITE_SAPLING, 40, 60);
        registerFlammable(ModBlocks.PROPHET_SAPLING, 20, 30);
        
        registerFlammable(ModBlocks.BELL_LEAVES, 40, 100);
        registerFlammable(ModBlocks.PROPHET_LOG, 10, 10);
        
        registerFlammable(ModBlocks.ROSE, 20, 30);
        registerFlammable(ModBlocks.TANGERINE_ROSE, 20, 30);
        registerFlammable(ModBlocks.BLUE_ROSE, 20, 30);
        registerFlammable(ModBlocks.FUCHSIA_ROSE, 20, 30);
        registerFlammable(ModBlocks.WHITE_ROSE, 20, 30);
        
        //Log Stripping
        registerStrippable(ModBlocks.CHERRY_LOG, ModBlocks.STRIPPED_CHERRY_LOG);
        registerStrippable(ModBlocks.CHERRY_WOOD, ModBlocks.STRIPPED_CHERRY_WOOD);
        registerStrippable(ModBlocks.IGNITE_LOG, ModBlocks.STRIPPED_IGNITE_LOG);
        registerStrippable(ModBlocks.IGNITE_WOOD, ModBlocks.STRIPPED_IGNITE_WOOD);
        registerStrippable(ModBlocks.PROPHET_LOG, Blocks.STRIPPED_BIRCH_LOG);
        
        //Flower Potting
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "rose"), () -> { return ModBlocks.POTTED_ROSE; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "blue_rose"), () -> { return ModBlocks.POTTED_BLUE_ROSE; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "tangerine_rose"), () -> { return ModBlocks.POTTED_TANGERINE_ROSE; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "white_rose"), () -> { return ModBlocks.POTTED_WHITE_ROSE; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "fuchsia_rose"), () -> { return ModBlocks.POTTED_FUCHSIA_ROSE; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "cherry_sapling"), () -> { return ModBlocks.POTTED_CHERRY_SAPLING; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "ignite_sapling"), () -> { return ModBlocks.POTTED_IGNITE_SAPLING; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "prophet_sapling"), () -> { return ModBlocks.POTTED_PROPHET_SAPLING; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "candelion"), () -> { return ModBlocks.POTTED_CANDELION; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "rainbush"), () -> { return ModBlocks.POTTED_RAINBUSH; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "illushroom"), () -> { return ModBlocks.POTTED_ILLUSHROOM; });
        ((FlowerPotBlock)Blocks.FLOWER_POT).addPlant(new ResourceLocation(TwilightopiaMod.MODID, "glowing_fungi"), () -> { return ModBlocks.POTTED_GLOWING_FUNGI; });
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