package me.mrdaniel.ageofittgard.gui.chat;

import me.mrdaniel.npcs.gui.IMenu;
import org.spongepowered.api.text.Text;

import javax.annotation.Nullable;

public interface IChatMenu extends IMenu {

    void setHeader(@Nullable Text header);
    void setFooter(@Nullable Text footer);
}
