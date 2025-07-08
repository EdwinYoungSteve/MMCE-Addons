package github.alecsio.mmceaddons.common.mixin;

import hellfirepvp.modularmachinery.common.util.BlockArray;
import hellfirepvp.modularmachinery.common.util.IBlockStateDescriptor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(BlockArray.BlockInformation.class)
public interface IBlockInformationAccessor {
    @Accessor(value = "matchingStates", remap = false)
    List<IBlockStateDescriptor> getMatchingStates();
}
