package me.mrdaniel.ageofittgard.gui.chat;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import javax.annotation.Nullable;
import java.util.List;

public abstract class AbstractChatMenu implements IChatMenu {

    protected Player player;
    protected Text title;
    protected Text header;
    protected Text footer;

    @Override
    public void open() {
        this.update();
    }

    @Override
    public void update() {
        this.player.sendMessage(Text.of(" "));

        PaginationList.builder()
                .title(this.title)
                .padding(Text.of(TextColors.YELLOW, "-"))
                .header(this.header)
                .contents(this.getContents())
                .footer(this.footer)
                .build()
                .sendTo(this.player);
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void setTitle(Text title) {
        this.title = title;
    }

    @Override
    public void setHeader(@Nullable Text header) {
        this.header = header;
    }

    @Override
    public void setFooter(@Nullable Text footer) {
        this.footer = footer;
    }

    protected abstract List<Text> getContents();
}
