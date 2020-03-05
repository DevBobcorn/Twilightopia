package bobcorn.twilightopia.blocks;

import java.util.Random;

import bobcorn.twilightopia.StoryTeller;
import bobcorn.twilightopia.items.ModItems;
import bobcorn.twilightopia.tileentity.SignPlusTileEntity;
import bobcorn.twilightopia.world.TeleportationUtil;
import bobcorn.twilightopia.world.dimension.TwilightopiaDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.LecternBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.ByteNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.LecternTileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TuliportalBlock extends Block {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AABB = Block.makeCuboidShape(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 10.0D);
	protected static final VoxelShape Z_AABB = Block.makeCuboidShape(6.0D, 0.0D, 0.0D, 10.0D, 16.0D, 16.0D);

	public TuliportalBlock(Block.Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(AXIS, Direction.Axis.X));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		switch ((Direction.Axis) state.get(AXIS)) {
		case Z:
			return Z_AABB;
		case X:
		default:
			return X_AABB;
		}
	}

	/**
	 * Gets the render layer this block will render on. SOLID for solid blocks,
	 * CUTOUT or CUTOUT_MIPPED for on-off transparency (glass, reeds), TRANSLUCENT
	 * for fully blended transparency (stained glass)
	 */
	public BlockRenderLayer getRenderLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (TwilightopiaDimensions.getDimensionType() == null) {
			System.out.println("ERROR:DimensionTypeIsNull!");
			return;
		}
		if (!worldIn.isRemote && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss()
				&& VoxelShapes.compare(
						VoxelShapes.create(entityIn.getBoundingBox().offset((double) (-pos.getX()),
								(double) (-pos.getY()), (double) (-pos.getZ()))),
						state.getShape(worldIn, pos), IBooleanFunction.AND)) {
			// entityIn.changeDimension(worldIn.dimension.getType() ==
			// TPWDimensions.TWILIGHTOPIA ? DimensionType.OVERWORLD :
			// TPWDimensions.TWILIGHTOPIA);
			// The above line of code will generate a nether portal, which we don't want...
			if (entityIn instanceof PlayerEntity) {
				PlayerEntity player = (PlayerEntity) entityIn;
				if (player.getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.lodge_herb) {
					if (!player.isCreative())
						player.getHeldItem(Hand.MAIN_HAND).shrink(1);
				} else
					return;
			}
			BlockPos lodgePoint;
			TeleportationUtil.changeDimension(
					worldIn.dimension.getType() == DimensionType.OVERWORLD ? TwilightopiaDimensions.getDimensionType()
							: DimensionType.OVERWORLD,
					entityIn, getPortalPoint(entityIn.world));
			lodgePoint = getPortalPoint(entityIn.world);
			entityIn.fallDistance = 0.0F;
			if (entityIn instanceof PlayerEntity
					&& entityIn.world.getDimension().getType() == TwilightopiaDimensions.getDimensionType()) {
				if (!ScanLodge(entityIn.world, lodgePoint)) {
					genLodge(entityIn.world, lodgePoint);
					entityIn.setPosition(lodgePoint.getX(), lodgePoint.getY(), lodgePoint.getZ());
				} else {
					System.out.println("Lodge Found!");
					entityIn.setPosition(scanRes.getX(), scanRes.getY() + 1, scanRes.getZ());
				}
			}
		}
	}

	public BlockPos getPortalPoint(World world) {
		BlockPos blockpos = world.getHeight(Heightmap.Type.MOTION_BLOCKING,
				new BlockPos(world.getWorldBorder().getCenterX(), 0.0D, world.getWorldBorder().getCenterZ()));
		return blockpos;
	}

	/**
	 * Called periodically client-side on blocks near the player to show effects
	 * (like furnace fire particles). Note that this method is unrelated to
	 * {@link randomTick} and {@link #needsRandomTick}, and will always be called
	 * regardless of whether the block can receive random update ticks
	 */
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		if (rand.nextInt(100) == 0) {
			worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D,
					SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F,
					false);
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = (double) ((float) pos.getX() + rand.nextFloat());
			double d1 = (double) ((float) pos.getY() + rand.nextFloat());
			double d2 = (double) ((float) pos.getZ() + rand.nextFloat());
			double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;
			if (worldIn.getBlockState(pos.west()).getBlock() != this
					&& worldIn.getBlockState(pos.east()).getBlock() != this) {
				d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
				d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
			} else {
				d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
				d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
			}

			worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
		}

	}

	public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
		return ItemStack.EMPTY;
	}

	/**
	 * Returns the blockstate with the given rotation from the passed blockstate. If
	 * inapplicable, returns the passed blockstate.
	 * 
	 * @deprecated call via {@link IBlockState#withRotation(Rotation)} whenever
	 *             possible. Implementing/overriding is fine.
	 */
	public BlockState rotate(BlockState state, Rotation rot) {
		switch (rot) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch ((Direction.Axis) state.get(AXIS)) {
			case Z:
				return state.with(AXIS, Direction.Axis.X);
			case X:
				return state.with(AXIS, Direction.Axis.Z);
			default:
				return state;
			}
		default:
			return state;
		}
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AXIS);
	}

	public static final Boolean checkPortal(World worldIn, BlockPos tulipos) {
		if (!checkXPortal(worldIn, tulipos))
			return checkZPortal(worldIn, tulipos);
		return true;
	}

	private static final Boolean checkXPortal(World worldIn, BlockPos tulipos) {
		// Check X Direction
		int radius = 1;
		int radius2;
		int height = 1;
		int height2 = 1;
		Boolean flag = false;
		Boolean flag2 = false;
		Boolean flag3 = false;
		while (radius < 5 && worldIn.getBlockState(tulipos.add(radius, 0, 0)) != Blocks.VOID_AIR.getDefaultState()) {
			BlockState curBlock = worldIn.getBlockState(tulipos.add(radius, 0, 0));
			if (curBlock.getBlock() != Blocks.AIR) {
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					flag = true;
				break;
			}
			radius++;
		}
		// System.out.println("Radius Found: " + radius);
		if (flag) // Check +X pillar
			while (height < 6
					&& worldIn.getBlockState(tulipos.add(radius, height, 0)) != Blocks.VOID_AIR.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(radius, height, 0));
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					height++;
				else
					break;
			}
		// System.out.println("Pillar 1 Height: " + height);
		if (height >= 2) {// Check -X pillar
			while (height2 <= height
					&& worldIn.getBlockState(tulipos.add(-radius, height2, 0)) != Blocks.VOID_AIR.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(-radius, height2, 0));
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					height2++;
				else
					break;
			}
		}
		// System.out.println("Pillar 2 Height: " + height);
		if (height2 == height && height >= 2) {
			// System.out.println("Pillar Available!");
			flag2 = true;
			radius2 = -(radius - 1);
			while (radius2 < radius && worldIn.getBlockState(tulipos.add(radius2, height - 1, 0)) != Blocks.VOID_AIR
					.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height - 1, 0));
				if (curBlock.getBlock() != Blocks.DARK_OAK_LOG
						|| curBlock.get(RotatedPillarBlock.AXIS) != Direction.Axis.X) {
					flag2 = false;
					break;
				}
				radius2++;
			}
			if (flag2) {
				flag3 = true;
				// System.out.println("Horizontal Pillar Available!");
				// Check Roof 1
				radius2 = -(radius + 1);
				while (radius2 < 0 && worldIn.getBlockState(
						tulipos.add(radius2, height + (radius2 + radius), 0)) != Blocks.VOID_AIR.getDefaultState()) {
					BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height + (radius2 + radius), 0));
					if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS
							|| curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.EAST)
						flag3 = false;
					// worldIn.setBlockState(tulipos.add(radius2, height + (radius2 + radius), 0),
					// Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING,
					// Direction.EAST));
					radius2++;
				}
				// Check Roof 2
				radius2 = 1;
				while (radius2 <= radius + 1 && worldIn.getBlockState(
						tulipos.add(radius2, height + (radius - radius2), 0)) != Blocks.VOID_AIR.getDefaultState()) {
					BlockState curBlock = worldIn.getBlockState(tulipos.add(radius2, height + (radius - radius2), 0));
					if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS
							|| curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.WEST)
						flag3 = false;
					// worldIn.setBlockState(tulipos.add(radius2, height + (radius - radius2), 0),
					// Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING,
					// Direction.WEST));
					radius2++;
				}
			}
		}
		if (flag3) {
			System.out.println("Portal Available!");
			// Build Portal Blocks...
			for (int targetX = -radius + 1; targetX < radius; targetX++)
				for (int targetY = 0; targetY < height - 1; targetY++) {
					worldIn.setBlockState(tulipos.add(targetX, targetY, 0),
							ModBlocks.TULIPORTAL.getDefaultState().with(TuliportalBlock.AXIS, Direction.Axis.X));
				}
			// Generate Hint
			genHint(worldIn, tulipos.add(0, height + radius - 1, 0), true);
		}
		return flag3;
	}

	private static final Boolean checkZPortal(World worldIn, BlockPos tulipos) {
		// Check X Direction
		int radius = 1;
		int radius2;
		int height = 1;
		int height2 = 1;
		Boolean flag = false;
		Boolean flag2 = false;
		Boolean flag3 = false;
		while (radius < 5 && worldIn.getBlockState(tulipos.add(0, 0, radius)) != Blocks.VOID_AIR.getDefaultState()) {
			BlockState curBlock = worldIn.getBlockState(tulipos.add(0, 0, radius));
			if (curBlock.getBlock() != Blocks.AIR) {
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					flag = true;
				break;
			}
			radius++;
		}
		// System.out.println("Radius Found: " + radius);
		if (flag) // Check +X pillar
			while (height < 6
					&& worldIn.getBlockState(tulipos.add(0, height, radius)) != Blocks.VOID_AIR.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height, radius));
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					height++;
				else
					break;
			}
		// System.out.println("Pillar 1 Height: " + height);
		if (height >= 2) {// Check -X pillar
			while (height2 <= height
					&& worldIn.getBlockState(tulipos.add(0, height2, -radius)) != Blocks.VOID_AIR.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height2, -radius));
				if (curBlock.getBlock() == Blocks.DARK_OAK_LOG
						&& curBlock.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y)
					height2++;
				else
					break;
			}
		}
		// System.out.println("Pillar 2 Height: " + height);
		if (height2 == height && height >= 2) {
			// System.out.println("Pillar Available!");
			flag2 = true;
			radius2 = -(radius - 1);
			while (radius2 < radius && worldIn.getBlockState(tulipos.add(0, height - 1, radius2)) != Blocks.VOID_AIR
					.getDefaultState()) {
				BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height - 1, radius2));
				if (curBlock.getBlock() != Blocks.DARK_OAK_LOG
						|| curBlock.get(RotatedPillarBlock.AXIS) != Direction.Axis.Z) {
					flag2 = false;
					break;
				}
				radius2++;
			}
			if (flag2) {
				flag3 = true;
				// System.out.println("Horizontal Pillar Available!");
				// Check Roof 1
				radius2 = -(radius + 1);
				while (radius2 < 0 && worldIn.getBlockState(
						tulipos.add(0, height + (radius2 + radius), radius2)) != Blocks.VOID_AIR.getDefaultState()) {
					BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height + (radius2 + radius), radius2));
					if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS
							|| curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.SOUTH)
						flag3 = false;
					// worldIn.setBlockState(tulipos.add(radius2, height + (radius2 + radius), 0),
					// Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING,
					// Direction.EAST));
					radius2++;
				}
				// Check Roof 2
				radius2 = 1;
				while (radius2 <= radius + 1 && worldIn.getBlockState(
						tulipos.add(0, height + (radius - radius2), radius2)) != Blocks.VOID_AIR.getDefaultState()) {
					BlockState curBlock = worldIn.getBlockState(tulipos.add(0, height + (radius - radius2), radius2));
					if (curBlock.getBlock() != Blocks.NETHER_BRICK_STAIRS
							|| curBlock.get(HorizontalBlock.HORIZONTAL_FACING) != Direction.NORTH)
						flag3 = false;
					// worldIn.setBlockState(tulipos.add(radius2, height + (radius - radius2), 0),
					// Blocks.NETHER_BRICK_STAIRS.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING,
					// Direction.WEST));
					radius2++;
				}
			}
		}
		if (flag3) {
			System.out.println("Portal Available!");
			// Build Portal Blocks...
			for (int targetZ = -radius + 1; targetZ < radius; targetZ++)
				for (int targetY = 0; targetY < height - 1; targetY++) {
					worldIn.setBlockState(tulipos.add(0, targetY, targetZ),
							ModBlocks.TULIPORTAL.getDefaultState().with(TuliportalBlock.AXIS, Direction.Axis.Z));
				}
			// Generate Hint
			genHint(worldIn, tulipos.add(0, height + radius - 1, 0), false);
		}
		return flag3;
	}

	private static final void genHint(World worldIn, BlockPos pos, boolean facingZ) {
		BlockState lectState = Blocks.LECTERN.getDefaultState().with(LecternBlock.FACING,
				facingZ ? Direction.SOUTH : Direction.EAST);
		worldIn.setBlockState(pos.down(1), ModBlocks.CHERRY_LOG.getDefaultState().with(LogBlock.AXIS,
				facingZ ? Direction.Axis.Z : Direction.Axis.X));
		worldIn.setBlockState(pos, Blocks.LECTERN.getDefaultState());
		if (worldIn.getTileEntity(pos) instanceof LecternTileEntity) {
			LecternTileEntity lect = (LecternTileEntity) worldIn.getTileEntity(pos);
			ItemStack NewBookStack = new ItemStack(Items.WRITTEN_BOOK);
			NewBookStack.setTagInfo("resolved", new ByteNBT((byte) 1));
			NewBookStack.setTagInfo("generation", new IntNBT(0));
			NewBookStack.setTagInfo("author", new StringNBT("???"));
			NewBookStack.setTagInfo("title", new StringNBT(I18n.format("texts.twilightopia.oracle")));
			String[] story = StoryTeller.GetStory(StoryTeller.getPath("oracle"));
			int pages = Integer.parseInt(story[0]);
			ListNBT NewPageList = new ListNBT();
			for (int i = 1;i <= pages;++i)
				if (story[i] != null && story[i] != "")
					NewPageList.add(new StringNBT(story[i]));
			NewBookStack.setTagInfo("pages", NewPageList);
			lect.setBook(NewBookStack);
			LecternBlock.setHasBook(worldIn, pos, lectState, true);
		}
	}

	private static final BlockState AIR = Blocks.AIR.getDefaultState();
	private static final BlockState FLOOR = ModBlocks.CHERRY_PLANKS.getDefaultState();
	private static final BlockState WALL = ModBlocks.IGNITE_PLANKS.getDefaultState();
	private static final BlockState PILLAR = Blocks.DARK_OAK_LOG.getDefaultState();
	private static final BlockState ROOF = Blocks.NETHER_BRICK_STAIRS.getDefaultState();
	private static final BlockState BEAM = Blocks.NETHER_BRICK_SLAB.getDefaultState();
	private static final BlockState MARK = ModBlocks.STRIPPED_IGNITE_LOG.getDefaultState();
	private static final BlockState KRAM = ModBlocks.BLESSING_PLANKS.getDefaultState();

	private static BlockPos scanRes = new BlockPos(0, 0, 0);

	private static final Boolean ScanLodge(World worldIn, BlockPos pos) {
		MutableBlockPos cur = new MutableBlockPos();
		System.out.println("Scan Lodge!");
		for (int x = pos.getX() - 2; x <= pos.getX() + 2; x++)
			for (int z = pos.getZ() - 2; z <= pos.getZ() + 2; z++)
				for (int y = 10; y < 250; y++) {
					cur.setPos(x, y, z);
					// System.out.println("Checking position: " + cur.getX() + ' ' + cur.getY() + ' ' +
					// cur.getZ());
					if (worldIn.getBlockState(cur) == MARK && worldIn.getBlockState(cur.down(1)) == KRAM) {
						scanRes = cur;
						return true;
					}
				}
		return false;
	}

	private static final void genLodge(World worldIn, BlockPos pos) {
		if (worldIn.isRemote())
			return;
		int radius = 3;
		int height = 4;
		System.out.println("Gen Lodge!");
		BlockPos org = pos.down(1);
		BlockPos cur = org;
		// Build Base & Floor
		for (int i = -2; i <= 2; i++)
			for (int j = -3; j <= 3; j++) {
				cur = org.east(j).north(i);
				worldIn.setBlockState(cur, FLOOR);
				for (int y = 1; worldIn.getBlockState(cur.down(y)) != Blocks.VOID_AIR.getDefaultState()
						&& !worldIn.getBlockState(cur.down(y)).isSolid(); y++) {
					worldIn.setBlockState(cur.down(y), TwilightopiaSoilHelper.getChocoTypeIn(worldIn.getBiome(cur)));
				}
			}
		worldIn.setBlockState(org, MARK);
		worldIn.setBlockState(org.down(1), KRAM);
		// Build Walls
		for (int e = -3; e <= 3; e++) {
			cur = org.east(e);
			for (int y = 1; y <= 4 && worldIn.getBlockState(org.down(y)) != Blocks.VOID_AIR.getDefaultState(); y++) {
				cur = cur.up(1);
				worldIn.setBlockState(cur.north(3), (Math.abs(e) == 3 ? PILLAR : WALL));
				worldIn.setBlockState(cur.north(-3), (Math.abs(e) == 3 ? PILLAR : WALL));
			}
		}
		for (int e = -2; e <= 3; e++) {
			cur = org.east(e);
			for (int y = 1; y <= 4 && worldIn.getBlockState(org.down(y)) != Blocks.VOID_AIR.getDefaultState(); y++) {
				cur = cur.up(1);
				for (int n = -2; n <= 2; n++) {
					worldIn.setBlockState(cur.north(n), AIR);
				}
			}
		}
		cur = org.east(-3);
		for (int y = 1; y <= 4 && worldIn.getBlockState(org.down(y)) != Blocks.VOID_AIR.getDefaultState(); y++) {
			cur = cur.up(1);
			for (int n = -2; n <= 2; n++) {
				worldIn.setBlockState(cur.north(n), WALL);
			}
		}
		// Build Roof
		for (int e = -4; e <= 4; e++) {
			// Build Roof 1
			int radius2 = -(radius + 1);
			while (radius2 < 0
					&& worldIn.getBlockState(pos.add(radius2, height + (radius2 + radius), e)) != Blocks.VOID_AIR
							.getDefaultState()) {
				worldIn.setBlockState(pos.add(radius2, height + (radius2 + radius), e),
						ROOF.with(HorizontalBlock.HORIZONTAL_FACING, Direction.EAST));
				for (int y = height + (radius2 + radius) - 1; y >= height; y--)
					worldIn.setBlockState(pos.add(radius2, y, e), Math.abs(e) == 3 ? WALL : AIR);
				radius2++;
			}
			// Top Beam
			worldIn.setBlockState(pos.add(radius2, height + (radius2 + radius), e), e == 0 ? AIR : BEAM);
			for (int y = height + (radius + radius2) - 1; y >= height; y--)
				worldIn.setBlockState(pos.add(radius2, y, e), Math.abs(e) == 3 ? WALL : AIR);
			// Build Roof 2
			radius2 = 1;
			while (radius2 <= radius + 1
					&& worldIn.getBlockState(pos.add(radius2, height + (radius - radius2), e)) != Blocks.VOID_AIR
							.getDefaultState()) {
				worldIn.setBlockState(pos.add(radius2, height + (radius - radius2), e),
						ROOF.with(HorizontalBlock.HORIZONTAL_FACING, Direction.WEST));
				for (int y = height + (radius - radius2) - 1; y >= height; y--)
					worldIn.setBlockState(pos.add(radius2, y, e), Math.abs(e) == 3 ? WALL : AIR);
				radius2++;
			}
		}
		// Interior Decoration
		worldIn.setBlockState(pos.west(2).north(2), Blocks.BOOKSHELF.getDefaultState());
		worldIn.setBlockState(pos.west(2).north(1), ModBlocks.PROJECTABLE.getDefaultState());
		worldIn.setBlockState(pos.west(2).north(-1), ModBlocks.POTTED_TANGERINE_ROSE.getDefaultState());
		BlockPos signPos = pos.west(2).north(0).up(1);
		worldIn.setBlockState(signPos,
				ModBlocks.CHERRY_WALL_SIGN.getDefaultState().with(WallSignPlusBlock.FACING, Direction.EAST));
		if (worldIn.getTileEntity(signPos) instanceof SignPlusTileEntity) {
			SignPlusTileEntity sien = (SignPlusTileEntity) worldIn.getTileEntity(signPos);
			TranslationTextComponent txt1 = new TranslationTextComponent("texts.twilightopia.welcome");
			TranslationTextComponent txt2 = new TranslationTextComponent("texts.twilightopia.to");
			TranslationTextComponent txt3 = new TranslationTextComponent("texts.twilightopia.twilightopia");
			txt1.getStyle().setColor(TextFormatting.DARK_BLUE);
			txt2.getStyle().setColor(TextFormatting.DARK_PURPLE);
			txt3.getStyle().setColor(TextFormatting.LIGHT_PURPLE);
			sien.setText(0, txt1);
			sien.setText(1, txt2);
			sien.setText(2, txt3);
		} else
			System.out.println("ERROR::Sign+ Entity Not Found!");
		BlockPos lectPos = pos.west(2).north(0);
		BlockState lectState = Blocks.LECTERN.getDefaultState().with(LecternBlock.FACING, Direction.EAST);
		worldIn.setBlockState(lectPos, lectState);

		// LecternBlock.tryPlaceBook(worldIn, signPos, lectState, NewBookStack);
		if (worldIn.getTileEntity(lectPos) instanceof LecternTileEntity) {
			LecternTileEntity lect = (LecternTileEntity) worldIn.getTileEntity(lectPos);
			ItemStack NewBookStack = new ItemStack(Items.WRITTEN_BOOK);
			NewBookStack.setTagInfo("resolved", new ByteNBT((byte) 1));
			NewBookStack.setTagInfo("generation", new IntNBT(0));
			NewBookStack.setTagInfo("author", new StringNBT("DevBobcorn"));
			NewBookStack.setTagInfo("title", new StringNBT(I18n.format("texts.twilightopia.welcome")));
			String[] story = StoryTeller.GetStory(StoryTeller.getPath("poem"));
			int pages = Integer.parseInt(story[0]);
			
			ListNBT NewPageList = new ListNBT();
			for (int i = 1;i <= pages;++i)
				if (story[i] != null && story[i] != "")
					NewPageList.add(new StringNBT(story[i]));
			NewBookStack.setTagInfo("pages", NewPageList);
			lect.setBook(NewBookStack);
			LecternBlock.setHasBook(worldIn, lectPos, lectState, true);
		} else
			System.out.println("ERROR::Lectern Entity Not Found!");
	}
}