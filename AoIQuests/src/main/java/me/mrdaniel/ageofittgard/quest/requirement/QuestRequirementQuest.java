package me.mrdaniel.ageofittgard.quest.requirement;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.QuestRequirement;
import org.spongepowered.api.entity.living.player.Player;

public class QuestRequirementQuest extends QuestRequirement {

    private int questId;

    public QuestRequirementQuest(int requirementId) {
        super(RequirementTypes.QUEST, requirementId);
    }

    public QuestRequirementQuest(int requirementId, int questId) {
        this(requirementId);

        this.questId = questId;
    }

    @Override
    public boolean evaluate(Player player) {
        return AoIQuests.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId()).isCompleted(this.questId);
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
}
