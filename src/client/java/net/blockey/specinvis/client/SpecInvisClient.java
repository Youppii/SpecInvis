package net.blockey.specinvis.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SpecInvisClient implements ClientModInitializer {
    public static boolean hideHeads = true;
    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        toggleKey = new KeyBinding(
                "Toggle Spec Visibility",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                "SpecInvis"
        );
        net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper.registerKeyBinding(toggleKey);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                hideHeads = !hideHeads;
                client.player.sendMessage(
                        net.minecraft.text.Text.of("Spectator heads: " + (hideHeads ? "hidden" : "visible")),
                        true
                );
            }
        });
    }
}
