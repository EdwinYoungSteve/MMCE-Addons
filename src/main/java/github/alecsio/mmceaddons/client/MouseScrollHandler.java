package github.alecsio.mmceaddons.client;

import github.alecsio.mmceaddons.ModularMachineryAddons;
import github.alecsio.mmceaddons.common.item.BaseItemAdvancedMachineBuilder;
import github.alecsio.mmceaddons.common.network.MouseScrollMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class MouseScrollHandler {

    @SubscribeEvent
    public void onMouseScroll(MouseEvent event) {
        int scrollDelta = event.getDwheel();
        if (scrollDelta == 0) {return;}

        boolean isShiftDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);
        if (!isShiftDown) {return;}

        EntityPlayer player = Minecraft.getMinecraft().player;
        if (player == null) {return;}

        if (player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof BaseItemAdvancedMachineBuilder) {
            boolean up = scrollDelta > 0;
            ModularMachineryAddons.INSTANCE.sendToServer(new MouseScrollMessage(up));

            event.setResult(Event.Result.ALLOW);
            event.setCanceled(true);
        }
    }
}
