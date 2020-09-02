package me.mrdaniel.ageofittgard.event;

import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import org.spongepowered.api.entity.living.player.Player;

public class CompleteQuestStageEvent extends AbstractQuestEvent {

    public CompleteQuestStageEvent(Player player, ActiveQuest quest) {
        super(player, quest);
    }
}
