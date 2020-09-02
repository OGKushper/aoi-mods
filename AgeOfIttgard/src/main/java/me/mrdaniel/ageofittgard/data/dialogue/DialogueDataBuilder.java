package me.mrdaniel.ageofittgard.data.dialogue;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;

public class DialogueDataBuilder extends AbstractDataBuilder<DialogueData> implements DataManipulatorBuilder<DialogueData, ImmutableDialogueData> {

	public DialogueDataBuilder() {
		super(DialogueData.class, 1);
	}

	@Override
	public DialogueData create() {
		return new DialogueData();
	}

	@Override
	public Optional<DialogueData> createFrom(DataHolder holder) {
		return create().fill(holder);
	}

	@Override
	protected Optional<DialogueData> buildContent(DataView view) throws InvalidDataException {
		return create().from(view);
	}

	public static void register() {
		DataRegistration.builder()
				.id("dialogue-data")
				.name("Dialogue Data")
				.dataClass(DialogueData.class)
				.immutableClass(ImmutableDialogueData.class)
				.builder(new DialogueDataBuilder())
				.build();
	}
}
