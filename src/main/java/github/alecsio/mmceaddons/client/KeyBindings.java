package github.alecsio.mmceaddons.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBindings {

    public static KeyBinding cycleToolMode;

    public static void init() {
        cycleToolMode = new KeyBinding("key.cycle", Keyboard.KEY_X, "key.categories.mmcea");

        ClientRegistry.registerKeyBinding(cycleToolMode);
    }
}
