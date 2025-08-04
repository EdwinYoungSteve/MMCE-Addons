package github.alecsio.mmceaddons.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.ArrayList;
import java.util.List;

// Sent from server -> client
public class MachineAssemblyMessage implements IMessage {

    private String completedTranslationKey;
    private String missingBlocksTranslationKey;
    private List<Tuple<String, Long>> unhandledBlocks;

    public MachineAssemblyMessage() {
    }

    public MachineAssemblyMessage(String completedTranslationKey, String missingBlocksTranslationKey, List<Tuple<String, Long>> unhandledBlocks) {
        this.completedTranslationKey = completedTranslationKey;
        this.missingBlocksTranslationKey = missingBlocksTranslationKey;
        this.unhandledBlocks = unhandledBlocks;
    }

    public String getCompletedTranslationKey() {
        return completedTranslationKey;
    }

    public String getMissingBlocksTranslationKey() {
        return missingBlocksTranslationKey;
    }

    public List<Tuple<String, Long>> getUnhandledBlocks() {
        return unhandledBlocks;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        completedTranslationKey = ByteBufUtils.readUTF8String(buf);
        missingBlocksTranslationKey = ByteBufUtils.readUTF8String(buf);
        this.unhandledBlocks = new ArrayList<>();
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            String key = ByteBufUtils.readUTF8String(buf);
            long count = buf.readLong();
            this.unhandledBlocks.add(new Tuple<>(key, count));
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, completedTranslationKey);
        ByteBufUtils.writeUTF8String(buf, missingBlocksTranslationKey);
        buf.writeInt(this.unhandledBlocks.size());
        unhandledBlocks.forEach(tuple -> {
            ByteBufUtils.writeUTF8String(buf, tuple.getFirst());
            buf.writeLong(tuple.getSecond());
        });
    }
}
