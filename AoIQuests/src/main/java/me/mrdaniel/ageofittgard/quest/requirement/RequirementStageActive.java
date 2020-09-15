package me.mrdaniel.ageofittgard.quest.requirement;

import me.mrdaniel.ageofittgard.AoIQuests;
import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.Requirement;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import org.spongepowered.api.entity.living.player.Player;

public class RequirementStageActive extends Requirement {

    private int questId;
    private int stageId;

    public RequirementStageActive(int requirementId) {
        super(RequirementTypes.STAGE_ACTIVE, requirementId);
    }

    public RequirementStageActive(int requirementId, int questId, int stageId) {
        this(requirementId);

        this.questId = questId;
        this.stageId = stageId;
    }

    @Override
    public boolean evaluate(Player player) {
        QuestStage stage = AoIQuests.getInstance().getPlayerDataManager().getPlayerData(player.getUniqueId()).getActive(this.questId).map(ActiveQuest::getStage).orElse(null);

        return stage != null && stage.getStageId() == this.stageId;
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

    public int getStageId() {
        return stageId;
    }

    public void setStageId(int stageId) {
        this.stageId = stageId;
    }
}
