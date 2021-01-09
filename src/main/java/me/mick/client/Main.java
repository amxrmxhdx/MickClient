package me.mick.client;
import lombok.Getter;
import lombok.Setter;
import me.mick.client.menu.MickScreen;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class Main implements ModInitializer {

	private static @Getter Main instance;

	private static final @Getter double version = 0.1;
	private static final @Getter boolean devBuild = true;
	private static final @Getter String clientName = "MickClient";

	private static @Getter @Setter int screenTextColor = 0;

	public static String formatClientVersion() {
		return ((isDevBuild()) ? "development build v" : "v")+getVersion();
	}

	@Override
	public void onInitialize() {
		instance = this;

		KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.mick.menu",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_R,
				"category.mick"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				if (client.isInSingleplayer() || client.getServer() != null && !(client.currentScreen instanceof MickScreen)) {
					client.openScreen(new MickScreen(new MickScreen.MickGui()));
				}
			}
		});

		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
		System.out.println("MickClient loaded. ");
		System.out.println("Name: "+Main.getClientName());
		System.out.println("Version: "+((Main.isDevBuild()) ? " development build " : " v")+Main.getVersion());
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}
}
