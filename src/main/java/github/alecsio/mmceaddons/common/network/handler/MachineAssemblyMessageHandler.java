package github.alecsio.mmceaddons.common.network.handler;

import github.alecsio.mmceaddons.common.network.MachineAssemblyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class MachineAssemblyMessageHandler implements IMessageHandler<MachineAssemblyMessage, IMessage> {
    @Override
    public IMessage onMessage(MachineAssemblyMessage message, MessageContext ctx) {
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentTranslation(message.getCompletedTranslationKey()));

        List<Tuple<String, Long>> unhandledBlocks = message.getUnhandledBlocks();

        StringBuilder unhandledBlocksText = new StringBuilder().append("\n");
        for (Tuple<String, Long> unhandledBlock : unhandledBlocks) {
            String key = unhandledBlock.getFirst();
            Long value = unhandledBlock.getSecond();

            unhandledBlocksText.append(value).append("x ").append(I18n.format(key)).append("\n");
        }

        player.sendMessage(new TextComponentTranslation(message.getMissingBlocksTranslationKey(), unhandledBlocksText.toString()));

        return null;
    }
}
