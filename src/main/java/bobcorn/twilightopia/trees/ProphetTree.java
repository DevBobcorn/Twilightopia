package bobcorn.twilightopia.trees;

import java.util.Random;

import javax.annotation.Nullable;

import bobcorn.twilightopia.world.gen.feature.ProphetTreeFeature;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ProphetTree extends Tree {
    @Nullable
    protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
       return new ProphetTreeFeature(NoFeatureConfig::deserialize, true, false);
    }
}
