package github.alecsio.mmceaddons.common.assembly.handler;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import github.alecsio.mmceaddons.common.assembly.IMachineAssembly;
import github.alecsio.mmceaddons.common.assembly.MachineAssemblyManager;
import github.alecsio.mmceaddons.common.assembly.handler.exception.MultiblockBuilderNotFoundException;
import github.alecsio.mmceaddons.common.config.MMCEAConfig;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.tiles.base.TileMultiblockMachineController;
import ink.ikx.mmce.core.AssemblyConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;


public class MachineAssemblyEventHandler {

    public static final Cache<EntityPlayer, Boolean> ASSEMBLY_ACCESS_TOKEN = CacheBuilder.newBuilder()
            .expireAfterWrite(MMCEAConfig.cooldown, TimeUnit.SECONDS)
            .build();

    @SubscribeEvent()
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        World world = player.world;
        if (event.phase == TickEvent.Phase.START || world.isRemote || world.getTotalWorldTime() % AssemblyConfig.tickBlock != 0) {
            return;
        }

        Collection<IMachineAssembly> machineAssemblies = MachineAssemblyManager.getMachineAssemblyListFromPlayer(player);
        if (machineAssemblies == null || machineAssemblies.isEmpty()) {
            return;
        }

        ModularMachinery.EXECUTE_MANAGER.addSyncTask(() -> {
            for (final IMachineAssembly assembly : machineAssemblies) {
                if (!(world.getTileEntity(assembly.getControllerPos()) instanceof TileMultiblockMachineController)) {
                    MachineAssemblyManager.removeMachineAssembly(assembly.getControllerPos());
                    player.sendMessage(new TextComponentTranslation(assembly.getErrorTranslationKey()));
                    return;
                }
                try {
                    assembly.assembleTick();
                } catch (MultiblockBuilderNotFoundException e) {
                    player.sendMessage(new TextComponentTranslation("error.assembler.not.found"));
                    MachineAssemblyManager.removeMachineAssembly(assembly.getControllerPos());
                }

                if (assembly.isCompleted()) {
                    MachineAssemblyManager.removeMachineAssembly(assembly.getControllerPos());
                    player.sendMessage(new TextComponentTranslation(assembly.getCompletedTranslationKey()));
                    List<String> unhandledBlocks = assembly.getUnhandledBlocks();
                    if (unhandledBlocks != null && !unhandledBlocks.isEmpty()) {
                        Map<String, Long> blockCounts = unhandledBlocks.stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

                        StringBuilder unhandledBlocksBuilder = new StringBuilder().append("\n");
                        blockCounts.forEach((block, count) ->
                                unhandledBlocksBuilder.append(count).append("x ").append(block).append("\n")
                        );

                        player.sendMessage(new TextComponentTranslation(
                                assembly.getMissingBlocksTranslationKey(),
                                unhandledBlocksBuilder.toString()
                        ));
                    }
                }
            }
        });
    }

    @SubscribeEvent
    public void onPlayerLogOut(PlayerEvent.PlayerLoggedOutEvent event) {
        MachineAssemblyManager.removeMachineAssembly(event.player);
    }

}
