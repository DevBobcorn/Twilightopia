package bobcorn.twilightopia.blocks;

import com.google.common.collect.Maps;

import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.entity.monster.RatEntity;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class RatBlock extends Block {
   private final Block mimickedBlock;
   private static final Map<Block, Block> field_196470_b = Maps.newIdentityHashMap();

   public RatBlock(Block blockIn, Block.Properties properties) {
      super(properties);
      this.mimickedBlock = blockIn;
      field_196470_b.put(blockIn, this);
   }

   public Block getMimickedBlock() {
      return this.mimickedBlock;
   }

   public static boolean canContainRat(BlockState state) {
      return field_196470_b.containsKey(state.getBlock());
   }

   /**
    * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
    * this block
    */
   public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
      super.onBlockHarvested(worldIn, pos, state, player);
      
	  //System.out.println("Spawn Drops");
      if (!worldIn.isRemote && worldIn.getGameRules().getBoolean(GameRules.DO_TILE_DROPS) && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItemMainhand()) == 0) {
         RatEntity ratentity = ModEntityType.RAT.create(worldIn);
         //System.out.println("Rat Spawned in " + worldIn.getWorldType().getName());
         ratentity.setLocationAndAngles((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, 0.0F, 0.0F);
         worldIn.addEntity(ratentity);
         ratentity.spawnExplosionParticle();
      }
   }

   public static BlockState infest(Block blockIn) {
      return field_196470_b.get(blockIn).getDefaultState();
   }
}