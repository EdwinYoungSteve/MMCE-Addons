package github.alecsio.mmceaddons.common.mixin;

import hellfirepvp.modularmachinery.common.util.BlockArray;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(value = BlockArray.class, remap = false)
public interface IBlockArrayInvoker {

    @Invoker
    void invokeUpdateSize(BlockPos addedPos);
}
