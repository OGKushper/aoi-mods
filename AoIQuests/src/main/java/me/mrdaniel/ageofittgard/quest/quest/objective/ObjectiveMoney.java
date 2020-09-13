package me.mrdaniel.ageofittgard.quest.quest.objective;

import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.service.economy.EconomyService;

public class ObjectiveMoney extends QuestObjective {

    private double money;

    public ObjectiveMoney(int objectiveId) {
        this(objectiveId, 0.0);
    }

    public ObjectiveMoney(int objectiveId, double money) {
        super(objectiveId, ObjectiveTypes.LOCATION);

        this.money = money;
    }

    @Override
    protected boolean evaluateObjective(Player player, Event event) {
        EconomyService econ = Sponge.getServiceManager().provide(EconomyService.class).orElse(null);
        if (econ == null) {
            return false;
        }

        return econ.getOrCreateAccount(player.getUniqueId()).get().getBalance(econ.getDefaultCurrency()).doubleValue() >= this.money;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
