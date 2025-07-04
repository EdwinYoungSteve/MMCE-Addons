package github.alecsio.mmceaddons.common.assembly;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface IMachineAssembly {

    BlockPos getControllerPos();
    EntityPlayer getPlayer();
    void assembleTick();
    boolean isCompleted();
    String getCompletedTranslationKey();
    String getErrorTranslationKey();
    List<String> getUnhandledBlocks();
}
