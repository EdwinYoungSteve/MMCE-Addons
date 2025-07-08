package github.alecsio.mmceaddons.common.network.handler;

import github.alecsio.mmceaddons.common.item.BaseItemAdvancedMachineBuilder;
import github.alecsio.mmceaddons.common.item.ScrollDirection;
import github.alecsio.mmceaddons.common.network.MouseScrollMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MouseScrollMessageHandler implements IMessageHandler<MouseScrollMessage, IMessage> {
    @Override
    public IMessage onMessage(MouseScrollMessage message, MessageContext ctx) {
        ScrollDirection scrollDirection = ScrollDirection.getFrom(message.isDirection());
        EntityPlayerMP player = ctx.getServerHandler().player;

        player.getServerWorld().addScheduledTask(() -> {
            ItemStack stack = player.getHeldItem(EnumHand.MAIN_HAND);
            if (stack.getItem() instanceof BaseItemAdvancedMachineBuilder) {
                BaseItemAdvancedMachineBuilder.onMouseScroll(stack, player, scrollDirection);
            }
        });

        return null;
    }
}
