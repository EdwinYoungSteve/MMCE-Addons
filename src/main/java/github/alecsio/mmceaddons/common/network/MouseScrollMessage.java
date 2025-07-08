package github.alecsio.mmceaddons.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class MouseScrollMessage implements IMessage {

    // true -> UP
    // false -> DOWN
    // This is probably cursed, but using any other type for something that will essentially only ever have two values felt
    // wasteful xd
    private boolean direction;

    public MouseScrollMessage() {
    }

    public MouseScrollMessage(boolean direction) {
        this.direction = direction;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        direction = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(direction);
    }

    public boolean isDirection() {
        return direction;
    }
}
