package bobcorn.twilightopia.world.gen.feature.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import bobcorn.twilightopia.blocks.TwilightopiaSoilHelper;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;

public abstract class TwilitTemplateStructure<C extends IFeatureConfig> extends TwilitStructure<C> {
	protected Random random = new Random();
	
	public TwilitTemplateStructure(Function<Dynamic<?>, ? extends C> configFactoryIn) {
		super(configFactoryIn);
	}

	abstract ResourceLocation getRes();
	
	protected BlockPos getOffset() {
		return BlockPos.ZERO;
	}
	
	protected Mirror getMirror() {
		return Mirror.NONE;
	}
	
	protected Rotation getRotation() {
		return Rotation.NONE;
	}
	
	protected Boolean getIgnoreEntities() {
		return true;
	}
	
	protected Boolean mustOnSoil() {
		return false;
	}
	
	@Override
	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
			BlockPos pos, C config) {
		if (worldIn instanceof WorldGenRegion) {
			//System.out.println("Structure placed at " + pos.getX() + ' ' +pos.getY() + ' ' + pos.getZ());
			ServerWorld serverworld = (ServerWorld) worldIn.getWorld();
			TemplateManager templatemanager = serverworld.getStructureTemplateManager();
			Template template;
			if (mustOnSoil() && !TwilightopiaSoilHelper.isSoilOrChoco((IBlockReader)worldIn, pos.down(1)))
				return false;
			
			try {
				//System.out.println("Structure Loading");
				template = templatemanager.getTemplate(getRes());
				//System.out.println("Structure Loaded!");
			} catch (ResourceLocationException var) {
				System.out.println("Struct not found!");
				return false;
			}
			if (template == null) {
				System.out.println("Template not found!");
	            return false;
	         } else {
	        	PlacementSettings placementsettings = (new PlacementSettings()).setMirror(getMirror()).setRotation(getRotation()).setIgnoreEntities(getIgnoreEntities()).setChunk((ChunkPos)null); 
	        	//System.out.println("Placing!");
	        	template.addBlocksToWorld(worldIn, pos.add(getOffset()), placementsettings);
	            //System.out.println("Placed!");
	            return true;
	        }
		}
		
		return false;
	}
}