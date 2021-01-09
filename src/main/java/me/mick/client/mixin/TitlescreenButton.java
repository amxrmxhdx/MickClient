package me.mick.client.mixin;

import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.options.ServerList;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

@Mixin(TitleScreen.class)
public class TitlescreenButton extends Screen {

	protected TitlescreenButton(Text title) {
		super(title);
	}

	/**
	 * @author MickMMars
	 */
	@Overwrite
	private void initWidgetsNormal(int y, int spacingY) {
		this.addButton(new ButtonWidget(this.width / 2 - 100, y, 200, 20, new TranslatableText("menu.singleplayer"), (buttonWidget) -> {
			this.client.openScreen(new SelectWorldScreen(this));
		}));
		this.addButton(new ButtonWidget(this.width / 2 - 100, y + spacingY, 200, 20, new LiteralText("Join §bPixlies"), (buttonWidget) -> {
			this.client.openScreen(new MultiplayerScreen(this));
			MultiplayerScreen multiplayerScreen = (MultiplayerScreen) this.client.currentScreen;
			ServerList serverList = multiplayerScreen.getServerList();
			ServerInfo serverInfo = null;
			for (int i = 0; i < serverList.size(); i++) {
				ServerInfo serverInfo2 = serverList.get(i);
				if (serverInfo2.address.equals("pixlies.net")) {
					serverInfo = serverInfo2;
					serverList.swapEntries(0, i);
				}
			}
			if (serverInfo==null) {
				serverInfo = new ServerInfo("§bPixlies §7- §fThink different, think new", "pixlies.net", false);
				serverList.add(serverInfo);
				for (int i = 0; i < serverList.size(); i++) {
					ServerInfo serverInfo2 = serverList.get(i);
					if (serverInfo2.address.equals("pixlies.net")) {
						serverInfo = serverInfo2;
						serverList.swapEntries(0, i);
					}
				}
			}
			serverList.saveFile();
			this.client.openScreen(new ConnectScreen(this, this.client, serverInfo));
		}));
		this.addButton(new ButtonWidget(this.width / 2 - 100, y + spacingY * 2, 200, 20, new TranslatableText("menu.multiplayer"), (buttonWidget) -> {
			if (this.client.options.skipMultiplayerWarning) {
				this.client.openScreen(new MultiplayerScreen(this));
			} else {
				this.client.openScreen(new MultiplayerWarningScreen(this));
			}

		}));
	}

}
