package me.mrdaniel.ageofittgard.quest.dialogue.condition;

import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionTypes;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueCondition;
import me.mrdaniel.ageofittgard.util.CauseUtils;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.service.economy.transaction.ResultType;

import java.math.BigDecimal;

public class MoneyDialogueCondition extends DialogueCondition {

    private double money;
    private boolean take;

    public MoneyDialogueCondition(int conditionId) {
        super(ConditionTypes.MONEY, conditionId);
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
        if (!take) {
            return account.getBalance(econ.getDefaultCurrency()).doubleValue() >= this.money;
        }

        return account.withdraw(econ.getDefaultCurrency(), new BigDecimal(this.money), CauseUtils.getPluginCause()).getResult() == ResultType.SUCCESS;
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
