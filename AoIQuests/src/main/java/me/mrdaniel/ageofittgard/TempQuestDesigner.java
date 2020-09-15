package me.mrdaniel.ageofittgard;

import me.mrdaniel.ageofittgard.catalogtypes.questitem.QuestItems;
import me.mrdaniel.ageofittgard.catalogtypes.queststatus.QuestStatusus;
import me.mrdaniel.ageofittgard.quest.dialogue.DialogueLink;
import me.mrdaniel.ageofittgard.quest.dialogue.node.*;
import me.mrdaniel.ageofittgard.quest.quest.Quest;
import me.mrdaniel.ageofittgard.quest.quest.QuestStage;
import me.mrdaniel.ageofittgard.quest.quest.QuestTrigger;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveCollect;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveKill;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveLocation;
import me.mrdaniel.ageofittgard.quest.quest.objective.ObjectiveNPCDialogue;
import me.mrdaniel.ageofittgard.quest.requirement.RequirementItem;
import me.mrdaniel.ageofittgard.quest.requirement.RequirementQuestStatus;
import me.mrdaniel.ageofittgard.quest.requirement.RequirementStageActive;
import me.mrdaniel.ageofittgard.quest.requirement.RequirementTime;
import me.mrdaniel.npcs.utils.Position;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class TempQuestDesigner {

    public TempQuestDesigner createMainDialogues() {

        // Dialogue Youngster

        AoIQuests.getInstance().getDialogueManager().getOrCreateDialogue(1)
                .setFirstNode(1)
                .addNode(new BranchingDialogueNode(1).addLinks(1, 2))

                // Quest 1 Trigger path
                .addRequirement(new RequirementQuestStatus(1, 1, QuestStatusus.AVAILABLE))
                .addLink(new DialogueLink(1, 2)
                        .addRequirement(1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Youngster", TextColors.GRAY, ": Hey there adventurer!"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Youngster", TextColors.GRAY, ": Did you know there is a haunted house near that cliff?"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Youngster", TextColors.GRAY, ": Anyways, nice meeting you!")))
                .addNode(new EndDialogueNode(2))

                // Post-quest path
                .addLink(new DialogueLink(2, -1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Youngster", TextColors.GRAY, ": Did you check out that house yet?")))
                .save();

        // Dialogue Old Man

        AoIQuests.getInstance().getDialogueManager().getOrCreateDialogue(2)
                .setFirstNode(1)
                .addNode(new BranchingDialogueNode(1).addLinks(1, 4, 5))

                // Quest 1 Stage 2 path
                .addRequirement(new RequirementStageActive(1, 1, 2))
                .addLink(new DialogueLink(1, 2)
                        .addRequirement(1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Hey there youngster."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": You came here to learn the history of the abandoned house near the cliff?"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Well I could tell you all about it!")))
                .addNode(new ChooseDialogueNode(2).addLinks(2, 3))
                .addLink(new DialogueLink(2,3)
                        .setChoiceLine(Text.of(TextColors.GRAY, "I would love to hear it."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Almost 50 years ago, an entire family was murdered in that house."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Some people believe that it was the spirit of the full moon that killed them."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": I know that that is nonsense ofcourse."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": However some people still report paranormal activity when the full moon shines bright.")))
                .addLink(new DialogueLink(3, 3)
                        .setChoiceLine(Text.of(TextColors.GRAY, "No, I dont have time for that old man!"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Well alright then."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": You dont have to be so rude about it...")))
                .addNode(new EndDialogueNode(3))

                // Pre quest path
                .addLink(new DialogueLink(4, -1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Hey there youngster."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": I wish I could still go on adventures like you.")))

                // Post quest path
                .addLink(new DialogueLink(5, -1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": Hey there youngster."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Old Man", TextColors.GRAY, ": I hope you found my information usefull.")))
                .save();

        return this;
    }

    public TempQuestDesigner createMainQuest() {
        AoIQuests.getInstance().getQuestManager().createQuest()
                .setName(Text.of(TextColors.GOLD, TextStyles.BOLD, "The legend of the ", TextColors.DARK_RED, TextStyles.RESET, "50", TextColors.GOLD, TextStyles.BOLD, " spirits"))
                .setTrigger(new QuestTrigger()
                        .setObjective(new ObjectiveNPCDialogue(1, 1))
                        .addPreDesc(Text.of(TextColors.GRAY, "You hear of a story about a house, haunted by spirits."))
                        .addPreDesc(Text.of(TextColors.GRAY, "It might be worth looking into."))
                        .addPostDesc(Text.of(TextColors.GRAY, "I heard a story about a haunted house.")))
                .addStage(new QuestStage(1)
                        .addPreDesc(Text.of(TextColors.GRAY, "I should find the abandoned house near the cliff."))
                        .addObjective(new ObjectiveLocation(1, new Position("world", 0, 70, 0, 0, 0), 5.0 * 5.0))
                        .addPostDesc(Text.of(TextColors.GRAY, "I found the abandoned building, but found no sign of anything abnormal.")))
                .addStage(new QuestStage(2)
                        .addPreDesc(Text.of(TextColors.GRAY, "Maybe the local villagers know something."))
                        .addObjective(new ObjectiveNPCDialogue(1, 2))
                        .addPostDesc(Text.of(TextColors.GRAY, "The villagers told me that strange things only happen during a full moon.")))
                .addStage(new QuestStage(3)
                        .addPreDesc(Text.of(TextColors.GRAY, "I should come back then."))
                        .addObjective(new ObjectiveLocation(1, new Position("world", 0, 70, 0, 0, 0), 5.0 * 5.0)
                                .addRequirement(new RequirementTime(1, 13_000, 22_000)))
                        .addObjective(new ObjectiveKill(2, EntityTypes.GHAST, 3)
                                .addRequirement(new RequirementTime(1, 13_000, 22_000)))
                        .addPostDesc(Text.of(TextColors.GRAY, "I came back during the next full moon, only to find the place crawling with angry ghosts.")))
                .save();

        return this;
    }

    public TempQuestDesigner createSideDialogues() {

        // Dialogue Billy

        AoIQuests.getInstance().getDialogueManager().getOrCreateDialogue(3)
                .setFirstNode(1)
                .addNode(new BranchingDialogueNode(1).addLinks(1, 2))

                // Quest 2 Trigger path
                .addRequirement(new RequirementQuestStatus(1, 2, QuestStatusus.AVAILABLE))
                .addLink(new DialogueLink(1, 2)
                        .addRequirement(1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": I hate bullies, one stole my favorite childhood toy yesterday."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": I don't think I'll ever see it again.")))
                .addNode(new EndDialogueNode(2))

                // Quest 2 Stage 4 path
                .addLink(new DialogueLink(2, 3)
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": Did you find my toy?")))
                .addNode(new BranchingDialogueNode(3).addLinks(3, -1))
                .addRequirement(new RequirementStageActive(2, 2, 4))
                .addLink(new DialogueLink(3, 4).addRequirement(2))
                .addNode(new ChooseDialogueNode(4).addLinks(4, 5))
                .addLink(new DialogueLink(4, -1)
                        .setChoiceLine(Text.of(TextColors.GRAY, "Not yet kiddo."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": I don't think I'll ever get it back.")))
                .addLink(new DialogueLink(5, 5)
                        .setChoiceLine(Text.of(TextColors.GRAY, "Ofcourse I did, here it is!"))
                        .addRequirement(3)
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": Wow, thank you so much sir!"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Billy", TextColors.GRAY, ": I'll never let anyone take it away from me again!")))
                .addRequirement(new RequirementItem(3, QuestItems.BILLY_TOY.build(), 1, true))
                .addNode(new EndDialogueNode(5))
                .save();

        // Dialogue Bully

        AoIQuests.getInstance().getDialogueManager().getOrCreateDialogue(4)
                .setFirstNode(1)
                .addNode(new BranchingDialogueNode(1).addLinks(1, 4))

                // Quest 2 Stage 1 path
                .addRequirement(new RequirementStageActive(1, 2, 1))
                .addLink(new DialogueLink(1, 2)
                        .addRequirement(1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Bully", TextColors.GRAY, ": Hey asshole, what do you want!")))
                .addNode(new ChooseDialogueNode(2).addLinks(2, 3))
                .addLink(new DialogueLink(2, -1)
                        .setChoiceLine(Text.of(TextColors.GRAY, "Nothing, I'm sorry kid."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Bully", TextColors.GRAY, ": That's what I thought!")))
                .addLink(new DialogueLink(3, 4)
                        .setChoiceLine(Text.of(TextColors.GRAY, "I am going to need you to give me Billy's toy!"))
                        .addNpcLine(Text.of(TextColors.AQUA, "Bully", TextColors.GRAY, ": Haha, you'll never get it. I hid it in a place nobody will ever find it!"))
                )
                .addNode(new EndDialogueNode(4))

                // Pre/post quest path
                .addLink(new DialogueLink(4, -1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Bully", TextColors.GRAY, ": Go away you asshole!")))
                .save();
        
        // Dialogue Seamstress

        AoIQuests.getInstance().getDialogueManager().getOrCreateDialogue(5)
                .setFirstNode(1)
                .addNode(new LinkDialogueNode(1).setLinkId(1))
                .addLink(new DialogueLink(1, 2)
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": Hello there young man."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": Do you need any sewing done?")))
                .addNode(new BranchingDialogueNode(2).addLinks(2, 10))

                // Quest 2 Stage 3 path
                .addRequirement(new RequirementStageActive(1, 2, 3))
                .addLink(new DialogueLink(2, 3)
                        .addRequirement(1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": I'll always help a good fellow like you!")))
                .addNode(new ChooseDialogueNode(3).addLinks(3, 4))
                .addLink(new DialogueLink(3, -1)
                        .setChoiceLine(Text.of(TextColors.GRAY, "Oh no thank you, I'm good."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": Alright then."))
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": I will see you around then!")))
                .addRequirement(new RequirementItem(2, QuestItems.BILLY_TOY_PART.build(), 3, true))
                .addLink(new DialogueLink(4, 4)
                        .setChoiceLine(Text.of(TextColors.GRAY, "Actually, do you think you could fix this toy for me?"))
                        .addRequirement(2)
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": That is no problem at all!")))
                .addNode(new GiftDialogueNode(4, 5).setItem(QuestItems.BILLY_TOY.build()))
                .addLink(new DialogueLink(5, 5)
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": There you go young man, good as new.")))
                .addNode(new EndDialogueNode(5))

                // Pre/post quest path
                .addLink(new DialogueLink(10, -1)
                        .addNpcLine(Text.of(TextColors.AQUA, "Seamstress", TextColors.GRAY, ": If you ever do, dont hesitate to ask!")))
                .save();

        return this;
    }

    public TempQuestDesigner createSideQuest() {
        AoIQuests.getInstance().getQuestManager().createQuest()
                .setName(Text.of(TextColors.GOLD, "Billy"))
                .setTrigger(new QuestTrigger()
                        .setObjective(new ObjectiveNPCDialogue(1, 3))
                        .addPreDesc(Text.of(TextColors.GRAY, "A bully stole Billy's childhood toy. You should get it back for him!"))
                        .addPreDesc(Text.of(TextColors.GRAY, "The bully can usually be found on the beach."))
                        .addPostDesc(Text.of(TextColors.GRAY, "I found a kid named Billy who's favorite toy was stolen by a bully.")))
                .addStage(new QuestStage(1)
                        .addPreDesc(Text.of(TextColors.GRAY, "I should find the bully that stole Billy's toy on the beach."))
                        .addObjective(new ObjectiveNPCDialogue(1, 4))
                        .addPostDesc(Text.of(TextColors.GRAY, "I found the bully, but he hid the toy.")))
                .addStage(new QuestStage(2)
                        .addPreDesc(Text.of(TextColors.GRAY, "I should search the bully's room."))
                        .addObjective(new ObjectiveCollect(1, QuestItems.BILLY_TOY_PART.build(), 3))
                        .addPostDesc(Text.of(TextColors.GRAY, "I found Billy's toy, but the bully ripped it into 3 pieces.")))
                .addStage(new QuestStage(3)
                        .addPreDesc(Text.of(TextColors.GRAY, "Maybe the seamstress in town can fix it?"))
                        .addObjective(new ObjectiveNPCDialogue(1, 5))
                        .addObjective(new ObjectiveCollect(2, QuestItems.BILLY_TOY.build(), 1))
                        .addPostDesc(Text.of(TextColors.GRAY, "The seamstress in town was very nice, and fixed the toy up nicely.")))
                .addStage(new QuestStage(4)
                        .addPreDesc(Text.of(TextColors.GRAY, "Lets find Billy."))
                        .addObjective(new ObjectiveNPCDialogue(1, 3))
                        .addPostDesc(Text.of(TextColors.GRAY, "Billy is happy to have his toy back.")))
                .save();

        return this;
    }

    public TempQuestDesigner createManyQuests(int count) {
        for (int i = 0; i < count; i++) {
            Quest quest = AoIQuests.getInstance().getQuestManager().createQuest();

            quest.setName(Text.of(TextColors.GOLD, "Quest " + quest.getQuestId()))
                    .setTrigger(new QuestTrigger()
                            .setObjective(new ObjectiveNPCDialogue(1, 999))
                            .addPreDesc(Text.of(" "))
                            .addPostDesc(Text.of(" ")))
                    .addStage(new QuestStage(1)
                            .addPreDesc(Text.of(" "))
                            .addObjective(new ObjectiveNPCDialogue(1, 999))
                            .addPostDesc(Text.of(" ")))
                    .save();
        }

        return this;
    }
}
