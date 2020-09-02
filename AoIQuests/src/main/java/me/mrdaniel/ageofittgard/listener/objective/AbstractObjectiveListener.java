package me.mrdaniel.ageofittgard.listener.objective;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.AgeOfIttgard;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.quest.player.ActiveObjective;
import me.mrdaniel.ageofittgard.manager.QuestProgressManager;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class AbstractObjectiveListener {

    protected ObjectiveType type;
    protected final Map<UUID, List<ActiveObjective>> objectives;

    public AbstractObjectiveListener(ObjectiveType type) {
        this.type = type;
        this.objectives = Maps.newHashMap();
    }

    /**
     * Adds the active objective to the listener, if the objective is the proper type for the listener.
     *
     * @param uuid The players UUID
     * @param objective The objective
     */
    public void add(UUID uuid, ActiveObjective objective) {
        if (this.type == objective.getObjective().getType()) {
            this.objectives.computeIfAbsent(uuid, k -> Lists.newArrayList()).add(objective);
        }
    }

    public void unload(UUID uuid) {
        this.objectives.remove(uuid);
    }

    public void unload(UUID uuid, QuestObjective objective) {
        this.objectives.getOrDefault(uuid, Lists.newArrayList()).removeIf(a -> a.getObjective() == objective);
    }

    protected void checkObjectives(Player player, Event e) {
        List<ActiveObjective> objectives = this.objectives.get(player.getUniqueId());

        if (objectives == null || objectives.isEmpty()) {
            return;
        }

        QuestProgressManager manager = AgeOfIttgard.getInstance().getQuestProgressManager();
        List<ActiveObjective> toRemove = Lists.newArrayList();

        for (ActiveObjective active : Lists.newArrayList(objectives)) {
            QuestObjective objective = active.getObjective();

            if (objective.evaluate(player, e) && manager.increaseObjective(player, active.getActiveQuest(), objective)) {
                toRemove.add(active);
            }
        }

        objectives.removeAll(toRemove);
    }
}
