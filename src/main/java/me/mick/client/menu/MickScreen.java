package me.mick.client.menu;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class MickScreen extends CottonClientScreen {

    public MickScreen(GuiDescription description) {
        super(description);
    }

    public static class MickGui extends LightweightGuiDescription {
        public MickGui() {
            WTabPanel tabs = new WTabPanel();
            setRootPanel(tabs);
            tabs.setSize(128, 128);
            tabs.add(new WGridPanel(), tab -> tab.icon(new ItemIcon(new ItemStack(Items.APPLE))));

            tabs.validate(this);
        }
    }

}
