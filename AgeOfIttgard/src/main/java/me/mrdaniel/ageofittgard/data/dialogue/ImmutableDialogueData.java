package me.mrdaniel.ageofittgard.data.dialogue;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.data.AoIKeys;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;

import java.util.Map;

public class ImmutableDialogueData extends AbstractImmutableData<ImmutableDialogueData, DialogueData> {

    private final Map<Integer, Integer> progress;

    public ImmutableDialogueData() {
        this(Maps.newHashMap());
    }

    public ImmutableDialogueData(Map<Integer, Integer> progress) {
        this.progress = progress;
    }

    public Map<Integer, Integer> getProgress() {
        return this.progress;
    }

    @Override
    protected void registerGetters() {
        registerKeyValue(AoIKeys.DIALOGUE_PROGRESS, () -> Sponge.getRegistry().getValueFactory().createMapValue(AoIKeys.DIALOGUE_PROGRESS, this.progress).asImmutable());
        registerFieldGetter(AoIKeys.DIALOGUE_PROGRESS, this::getProgress);
    }

    @Override
    public DialogueData asMutable() {
        return new DialogueData(this.progress);
    }

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    protected DataContainer fillContainer(DataContainer container) {
        return this.asMutable().fillContainer(container);
    }
}
