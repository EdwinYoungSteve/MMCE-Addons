package github.alecsio.mmceaddons.common.mixin;

import hellfirepvp.modularmachinery.common.util.IBlockStateDescriptor;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(IBlockStateDescriptor.class)
public interface BlockStateDescriptorMixin {

    @Invoker("<init>")
    static IBlockStateDescriptor invokeConstructor(List<IBlockState> applicable) {
        throw new AssertionError(); // mixins my beloved
    }
}
