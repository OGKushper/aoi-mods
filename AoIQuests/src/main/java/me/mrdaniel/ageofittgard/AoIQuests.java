package me.mrdaniel.ageofittgard;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionType;
import me.mrdaniel.ageofittgard.catalogtypes.conditiontype.ConditionTypeRegistryModule;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeType;
import me.mrdaniel.ageofittgard.catalogtypes.nodetype.NodeTypeRegistryModule;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveType;
import me.mrdaniel.ageofittgard.catalogtypes.objectivetype.ObjectiveTypeRegistryModule;
import me.mrdaniel.ageofittgard.catalogtypes.questitem.QuestItem;
import me.mrdaniel.ageofittgard.catalogtypes.questitem.QuestItemRegistryModule;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatus;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatusRegistryModule;
import me.mrdaniel.ageofittgard.command.CommandAoI;
import me.mrdaniel.ageofittgard.command.CommandLogbook;
import me.mrdaniel.ageofittgard.data.AoIKeys;
import me.mrdaniel.ageofittgard.data.dialogue.DialogueDataBuilder;
import me.mrdaniel.ageofittgard.io.hocon.config.DefaultConfig;
import me.mrdaniel.ageofittgard.io.hocon.config.MainConfig;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue.DialogueConditionTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue.DialogueLinkTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue.DialogueNodeTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.dialogue.NPCDialogueTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.player.ActiveQuestTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.player.PlayerDataTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest.QuestObjectiveTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest.QuestStageTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest.QuestTriggerTypeSerializer;
import me.mrdaniel.ageofittgard.io.hocon.typeserializer.quest.QuestTypeSerializer;
import me.mrdaniel.ageofittgard.listener.TestListener;
import me.mrdaniel.ageofittgard.manager.DialogueManager;
import me.mrdaniel.ageofittgard.manager.PlayerDataManager;
import me.mrdaniel.ageofittgard.manager.QuestDataManager;
import me.mrdaniel.ageofittgard.manager.QuestProgressManager;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueCondition;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueNode;
import me.mrdaniel.ageofittgard.quest.dialogue.NPCDialogue;
import me.mrdaniel.ageofittgard.quest.player.ActiveQuest;
import me.mrdaniel.ageofittgard.quest.player.PlayerData;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestObjective;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.ageofittgard.quest.quest.QuestTrigger;
import me.mrdaniel.npcs.io.hocon.typeserializers.CatalogTypeSerializer;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;

import javax.annotation.Nullable;
import java.nio.file.Path;

@Plugin(id = AoIQuests.MOD_ID,
        name = AoIQuests.NAME,
        version = AoIQuests.VERSION,
        description = AoIQuests.DESCRIPTION,
        authors = {"Daniel12321"},
        url = "https://github.com/Daniel12321/AgeOfIttgard",
        dependencies = { @Dependency(id = "npcs", version = "[4,)") })
public class AoIQuests {

    public static final String MOD_ID = "aoi-quests";
    public static final String NAME = "AgeOfIttgard Quests";
    public static final String VERSION = "1.0.0";
    public static final String DESCRIPTION = "The Age Of Ittgard quest plugin.";

    private static AoIQuests instance;
    public static AoIQuests getInstance() {
        return instance;
    }

    @Inject private Game game;
    @Inject private PluginContainer container;
    @Inject @ConfigDir(sharedRoot = false) private Path configDir;

    private final Logger logger;
    private final QuestDataManager questManager;
    private final PlayerDataManager playerDataManager;
    private final DialogueManager dialogueManager;
    private final QuestProgressManager questProgressManager;

    public AoIQuests() {
        instance = this;

        this.logger = LoggerFactory.getLogger("AgeOfIttgard");
        this.questManager = new QuestDataManager();
        this.playerDataManager = new PlayerDataManager();
        this.dialogueManager = new DialogueManager();
        this.questProgressManager = new QuestProgressManager();
    }

    @Listener
    public void onPreInit(@Nullable GamePreInitializationEvent e) {
        AoIKeys.init();
        DialogueDataBuilder.register();

        this.game.getRegistry().registerModule(ConditionType.class, new ConditionTypeRegistryModule());
        this.game.getRegistry().registerModule(NodeType.class, new NodeTypeRegistryModule());
        this.game.getRegistry().registerModule(ObjectiveType.class, new ObjectiveTypeRegistryModule());
        this.game.getRegistry().registerModule(QuestItem.class, new QuestItemRegistryModule());
        this.game.getRegistry().registerModule(QuestStatus.class, new QuestStatusRegistryModule());

        CatalogTypeSerializer.register(ConditionType.class);
        CatalogTypeSerializer.register(NodeType.class);
        CatalogTypeSerializer.register(ObjectiveType.class);
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(Quest.class), new QuestTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(QuestTrigger.class), new QuestTriggerTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(QuestStage.class), new QuestStageTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(QuestObjective.class), new QuestObjectiveTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(PlayerData.class), new PlayerDataTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(ActiveQuest.class), new ActiveQuestTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(NPCDialogue.class), new NPCDialogueTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(DialogueNode.class), new DialogueNodeTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(DialogueLink.class), new DialogueLinkTypeSerializer());
        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(DialogueCondition.class), new DialogueConditionTypeSerializer());
    }

    @Listener
    public void onInit(@Nullable GameInitializationEvent e) {
        this.logger.info("Loading plugin...");

        MainConfig config = new DefaultConfig<>(MainConfig.class, this.configDir, "config.conf").get();

        this.questManager.load(config);
        this.playerDataManager.load(config);
        this.dialogueManager.load(config);
        this.questProgressManager.load();

//        new TempQuestDesigner().createMainDialogues().createMainQuest().createSideDialogues().createSideQuest(); // TODO: Remove

        this.game.getEventManager().registerListeners(this, new TestListener()); // TODO: Remove
        this.game.getCommandManager().register(this, new CommandAoI().build(), "aoi", "ageofittgard");
        this.game.getCommandManager().register(this, new CommandLogbook().build(), "log", "logbook", "quest", "quests", "questbook", "journal");

        this.logger.info("Loaded plugin successfully.");
    }

    @Listener
    public void onStopping(@Nullable GameStoppingServerEvent e) {
        this.logger.info("Unloading Plugin...");

        this.game.getEventManager().unregisterPluginListeners(this);
        this.game.getScheduler().getScheduledTasks().forEach(Task::cancel);
        this.game.getCommandManager().getOwnedBy(this).forEach(this.game.getCommandManager()::removeMapping);

        this.logger.info("Unloaded plugin successfully.");
    }

    @Listener
    public void onReload(@Nullable GameReloadEvent e) {
        this.onStopping(null);
        this.onInit(null);

        // Reloads all the active quest objectives for all players
        Sponge.getServer().getOnlinePlayers().forEach(p -> this.questProgressManager.load(p.getUniqueId(), this.playerDataManager.load(p.getUniqueId())));
    }

    @Listener
    public void onJoin(ClientConnectionEvent.Join e) {

        // Loads all the active quest objectives for the player
        this.questProgressManager.load(e.getTargetEntity().getUniqueId(), this.playerDataManager.load(e.getTargetEntity().getUniqueId()));
    }

    @Listener
    public void onQuit(ClientConnectionEvent.Disconnect e) {

        // Unloads active quest objective quests for the player
        this.questProgressManager.unload(e.getTargetEntity().getUniqueId());

        // Unloads player data
        this.playerDataManager.unload(e.getTargetEntity().getUniqueId());
    }

    public Logger getLogger() {
        return this.logger;
    }

    public Game getGame() {
        return this.game;
    }

    public PluginContainer getContainer() {
        return this.container;
    }

    public Path getConfigDir() {
        return this.configDir;
    }

    public QuestDataManager getQuestManager() {
        return this.questManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return this.playerDataManager;
    }

    public DialogueManager getDialogueManager() {
        return this.dialogueManager;
    }

    public QuestProgressManager getQuestProgressManager() {
        return this.questProgressManager;
    }
}
