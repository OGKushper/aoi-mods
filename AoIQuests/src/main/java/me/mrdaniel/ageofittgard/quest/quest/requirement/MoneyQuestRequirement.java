package me.mrdaniel.ageofittgard.quest.quest.requirement;

import me.mrdaniel.ageofittgard.catalogtypes.requirementtype.RequirementTypes;
import me.mrdaniel.ageofittgard.quest.quest.QuestRequirement;
import me.mrdaniel.ageofittgard.util.CauseUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;

import java.math.BigDecimal;

public class MoneyQuestRequirement extends QuestRequirement {

    private double money;
    private boolean take;

    public MoneyQuestRequirement(int requirementId) {
        super(RequirementTypes.MONEY, requirementId);
    }

    @Override
    public boolean evaluate(Player player) {
        return this.checkMoney(player, false);
    }

    @Override
    public boolean apply(Player player) {
        return this.checkMoney(player, this.take);
    }

    private boolean checkMoney(Player player, boolean take) {
        EconomyService econ = Sponge.getServiceManager().provide(EconomyService.class).orElse(null);
        if (econ == null) {
            return false;
        }

        UniqueAccount account = econ.getOrCreateAccount(player.getUniqueId()).get();
        if (take) {
            return account.withdraw(econ.getDefaultCurrency(), new BigDecimal(this.money), CauseUtils.getPluginCause()).getResult() == ResultType.SUCCESS;
        } else {
            return account.getBalance(econ.getDefaultCurrency()).doubleValue() >= this.money;
        }
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public boolean isTake() {
        return this.take;
    }

    public void setTake(boolean take) {
        this.take = take;
    }
}
