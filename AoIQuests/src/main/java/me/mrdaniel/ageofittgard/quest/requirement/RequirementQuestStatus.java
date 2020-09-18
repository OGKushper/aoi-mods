package me.mrdaniel.ageofittgard.quest.requirement;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatus;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import org.spongepowered.api.entity.living.player.Player;

public class RequirementQuestStatus extends Requirement {

    private int questId;
    private QuestStatus status;

    public RequirementQuestStatus() {
        super(RequirementTypes.QUEST_STATUS);
    }

    public RequirementQuestStatus(int questId, QuestStatus status) {
        this();

        this.questId = questId;
        this.status = status;
    }

    @Override
    public boolean evaluate(Player player) {
        return AoIQuests.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId()).getStatus(this.questId) == this.status;
    }

    @Override
    public boolean apply(Player player) {
        return this.evaluate(player);
    }

    public int getQuestId() {
        return questId;
    }

    public void setQuestId(int questId) {
        this.questId = questId;
    }

    public QuestStatus getStatus() {
        return status;
    }

    public void setStatus(QuestStatus status) {
        this.status = status;
    }
}
