package github.alecsio.mmceaddons.client;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.item.BaseItemAdvancedMachineBuilder;
import github.alecsio.mmceaddons.common.network.MouseScrollMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientTickHandler {

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;

        if (mc == null || player == null) return;

        if (KeyBindings.cycleToolMode.isPressed()) {
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() instanceof BaseItemAdvancedMachineBuilder) {
                boolean up = true;
                ModularMachineryAddons.INSTANCE.sendToServer(new MouseScrollMessage(up));
            }
        }
    }
}
