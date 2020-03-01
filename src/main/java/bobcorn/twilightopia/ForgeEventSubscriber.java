package bobcorn.twilightopia;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bobcorn.twilightopia.blocks.AbstractSignPlusBlock;
import bobcorn.twilightopia.blocks.ModBlocks;
import bobcorn.twilightopia.blocks.TwilightopiaButtonBlock;
import bobcorn.twilightopia.blocks.TwilightopiaDoorBlock;
import bobcorn.twilightopia.blocks.TwilightopiaPressurePlateBlock;
import bobcorn.twilightopia.blocks.TwilightopiaSaplingBlock;
import bobcorn.twilightopia.blocks.TwilightopiaSlabBlock;
import bobcorn.twilightopia.blocks.TwilightopiaStairsBlock;
import bobcorn.twilightopia.blocks.TwilightopiaTrapDoorBlock;
import bobcorn.twilightopia.entity.ModEntityType;
import bobcorn.twilightopia.entity.passive.ChoxEntity;
import bobcorn.twilightopia.entity.passive.ChoxEntity.ChoxType;
import bobcorn.twilightopia.init.ModVanillaCompat;
import bobcorn.twilightopia.items.BoatPlusItem;
import bobcorn.twilightopia.items.ModItems;
import bobcorn.twilightopia.items.QuillEdit;
import bobcorn.twilightopia.items.SignPlusItem;
import bobcorn.twilightopia.world.dimension.TwilightopiaDimensions;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Subscribe to events from the FORGE EventBus that should be handled on both
 * PHYSICAL sides in this class
 */
@EventBusSubscriber(modid = TwilightopiaMod.MODID, bus = EventBusSubscriber.Bus.FORGE)
public final class ForgeEventSubscriber {
	private static final Logger LOGGER = LogManager.getLogger(TwilightopiaMod.MODID + " Forge Event Subscriber");
	protected final static Random rand = new Random();

	@SubscribeEvent
	public static void onRegisterDimensions(RegisterDimensionsEvent event) {
		TwilightopiaDimensions.registerDimensions();
		LOGGER.debug("Registered Dimensions");
	}

	@SubscribeEvent
	public static void onPlayerInteract(EntityInteractSpecific event) {
		PlayerEntity player = event.getPlayer();
		ItemStack itemUsed = player.getHeldItem(event.getHand());
		Entity target = event.getTarget();
		if (!(target instanceof LivingEntity))
			return;
		if (itemUsed.getItem() == ModItems.chocolate_bar || itemUsed.getItem() == ModItems.white_chocolate_bar) {
			if (target.getType() == EntityType.PARROT || target.getType() == EntityType.WOLF) {
				if (!event.getPlayer().abilities.isCreativeMode) {
					itemUsed.shrink(1);
				}
				if (target.getType() == EntityType.PARROT)
					target.world.playSound((PlayerEntity) null, target.posX, target.posY, target.posZ,
							SoundEvents.ENTITY_PARROT_EAT, target.getSoundCategory(), 1.0F,
							1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);
				else
					target.world.playSound((PlayerEntity) null, target.posX, target.posY, target.posZ,
							SoundEvents.ENTITY_WOLF_SHAKE, target.getSoundCategory(), 1.0F,
							1.0F + (rand.nextFloat() - rand.nextFloat()) * 0.2F);

				((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.POISON, 900));
				if (player.isCreative() || !target.isInvulnerable()) {
					target.attackEntityFrom(DamageSource.causePlayerDamage(player), Float.MAX_VALUE);
				}
			} else if (target instanceof FoxEntity) { // Fox or Chox
				transfox(itemUsed.getItem() == ModItems.chocolate_bar, (FoxEntity) target);
			}
		}
	}

	@SuppressWarnings("deprecation")
	protected static void transfox(boolean dark, FoxEntity fox) {
		if (!fox.removed) {
			ChoxEntity choxentity = ModEntityType.CHOX.create(fox.world);
			choxentity.copyLocationAndAnglesFrom(fox);
			choxentity.setCanPickUpLoot(fox.canPickUpLoot());
			choxentity.setNoAI(fox.isAIDisabled());

			for (EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
				ItemStack itemstack = fox.getItemStackFromSlot(equipmentslottype);
				if (!itemstack.isEmpty()) {
					choxentity.setItemStackToSlot(equipmentslottype, itemstack.copy());
					itemstack.setCount(0);
				}
			}

			if (fox.hasCustomName()) {
				choxentity.setCustomName(fox.getCustomName());
				choxentity.setCustomNameVisible(fox.isCustomNameVisible());
			}

			if (dark)
				choxentity.setChoxType(ChoxType.DARK);
			else
				choxentity.setChoxType(ChoxType.MILKY);

			fox.world.addEntity(choxentity);
			fox.remove();
		}
	}

	@SubscribeEvent
	public static void onPlayerInteract(RightClickBlock event) {
		World world = event.getWorld();
		BlockPos blockpos = event.getPos();
		PlayerEntity player = event.getPlayer();
		BlockState blockstate = world.getBlockState(blockpos);
		if (player.getHeldItemMainhand().getItem() instanceof AxeItem) {
			Block block = ModVanillaCompat.BLOCK_STRIPPING_MAP_PLUS.get(blockstate.getBlock());
			if (block != null) {
				world.playSound(player, blockpos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
				if (!world.isRemote) {
					world.setBlockState(blockpos, block.getDefaultState().with(RotatedPillarBlock.AXIS,
							blockstate.get(RotatedPillarBlock.AXIS)), 11);
					if (player != null) {
						player.getHeldItemMainhand().getItem().damageItem(player.getHeldItemMainhand(), 1, player,
								(p_220040_1_) -> {
									p_220040_1_.sendBreakAnimation(event.getHand());
								});
					}
				}
			}
		} else if (player.getHeldItemMainhand().getItem() == ModItems.quill
				&& (blockstate.getBlock() instanceof AbstractSignBlock
						|| blockstate.getBlock() instanceof AbstractSignPlusBlock)) {
			// System.out.println("Editin' Sign");
			QuillEdit.Use(world, blockpos, player);
		}
	}

	@SubscribeEvent
	public static void onItemBurn(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() instanceof BoatPlusItem)
			event.setBurnTime(1200);
		else if (event.getItemStack().getItem() instanceof SignPlusItem)
			event.setBurnTime(200);
		else if (event.getItemStack().getItem() instanceof BlockItem) {
			Block target = ((BlockItem) event.getItemStack().getItem()).getBlock();
			if (target == ModBlocks.CHERRY_LOG)
				event.setBurnTime(300);
			else if (target == ModBlocks.STRIPPED_CHERRY_LOG)
				event.setBurnTime(300);
			else if (target == ModBlocks.CHERRY_WOOD)
				event.setBurnTime(300);
			else if (target == ModBlocks.STRIPPED_CHERRY_WOOD)
				event.setBurnTime(300);
			else if (target == ModBlocks.CHERRY_PLANKS)
				event.setBurnTime(300);
			else if (target == ModBlocks.BLESSING_PLANKS)
				event.setBurnTime(300);
			else if (target == ModBlocks.CHERRY_FENCE)
				event.setBurnTime(300);
			else if (target == ModBlocks.CHERRY_FENCE_GATE)
				event.setBurnTime(300);
			else if (target == ModBlocks.IGNITE_LOG)
				event.setBurnTime(350);
			else if (target == ModBlocks.STRIPPED_IGNITE_LOG)
				event.setBurnTime(350);
			else if (target == ModBlocks.IGNITE_WOOD)
				event.setBurnTime(350);
			else if (target == ModBlocks.STRIPPED_IGNITE_WOOD)
				event.setBurnTime(350);
			else if (target == ModBlocks.IGNITE_PLANKS)
				event.setBurnTime(350);
			else if (target == ModBlocks.IGNITE_FENCE)
				event.setBurnTime(300);
			else if (target == ModBlocks.IGNITE_FENCE_GATE)
				event.setBurnTime(300);
			else if (target instanceof TwilightopiaButtonBlock)
				event.setBurnTime(100);
			else if (target instanceof TwilightopiaDoorBlock)
				event.setBurnTime(200);
			else if (target instanceof TwilightopiaTrapDoorBlock)
				event.setBurnTime(300);
			else if (target instanceof TwilightopiaSlabBlock)
				event.setBurnTime(150);
			else if (target instanceof TwilightopiaStairsBlock)
				event.setBurnTime(300);
			else if (target instanceof TwilightopiaPressurePlateBlock)
				event.setBurnTime(300);
			else if (target instanceof TwilightopiaSaplingBlock)
				event.setBurnTime(100);
		}
	}	
	
	@SubscribeEvent
	public static void onTick(LivingUpdateEvent event) {
		// System.out.println(event.getEntityLiving().getType().getName().getFormattedText());
	}
}
