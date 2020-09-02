package me.mrdaniel.ageofittgard.data.dialogue;

import com.google.common.collect.Maps;
import me.mrdaniel.ageofittgard.data.AoIKeys;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;

import java.util.Map;
import java.util.Optional;

public class DialogueData extends AbstractData<DialogueData, ImmutableDialogueData> {

	private Map<Integer, Integer> progress;

	public DialogueData() {
		this(Maps.newHashMap());
	}

	public DialogueData(Map<Integer, Integer> progress) {
		this.progress = progress;
	}

	public Map<Integer, Integer> getProgress() {
		return progress;
	}

	public int getProgress(Integer dialogueId) {
		return this.progress.getOrDefault(dialogueId, 0);
	}

	public void setProgress(Map<Integer, Integer> progress) {
		this.progress = progress;
	}

	public void setProgress(Integer dialogueId, Integer progress) { this.progress.put(dialogueId, progress); }

	public void deleteProgress(Integer dialogueId) { this.progress.remove(dialogueId); }

	@Override
	protected void registerGettersAndSetters() {
		registerKeyValue(AoIKeys.DIALOGUE_PROGRESS, () -> Sponge.getRegistry().getValueFactory().createMapValue(AoIKeys.DIALOGUE_PROGRESS, this.progress));
		registerFieldGetter(AoIKeys.DIALOGUE_PROGRESS, this::getProgress);
		registerFieldSetter(AoIKeys.DIALOGUE_PROGRESS, this::setProgress);
	}

	@Override
	public Optional<DialogueData> fill(DataHolder holder, MergeFunction overlap) {
		holder.get(AoIKeys.DIALOGUE_PROGRESS).ifPresent(progress -> this.progress = progress);

		return Optional.of(this);
	}

	public Optional<DialogueData> from(DataView view) {
		Optional<? extends Map<?, ?>> progress = view.getMap(AoIKeys.DIALOGUE_PROGRESS.getQuery());

		if (!progress.isPresent()) {
			return Optional.empty();
		}

		this.progress.putAll((Map<Integer, Integer>) progress.get());

		return Optional.of(this);
	}

	@Override
	public Optional<DialogueData> from(DataContainer container) {
		return from((DataView) container);
	}

	@Override
	public DialogueData copy() {
		return new DialogueData(this.progress);
	}

	@Override
	public ImmutableDialogueData asImmutable() {
		return new ImmutableDialogueData(this.progress);
	}

	@Override
	public int getContentVersion() {
		return 1;
	}

	@Override
	protected DataContainer fillContainer(DataContainer container) {
		return container.set(AoIKeys.DIALOGUE_PROGRESS, this.progress);
	}
}
