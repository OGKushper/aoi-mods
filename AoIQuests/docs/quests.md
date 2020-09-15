# Quests

### **Age of Ittgard Quests Plugin**
<br>

---

# Table of content

- [1. Introduction](#1-Introduction)
- [2. File Structure](#2-File-Structure)
- [3. Trigger](#3-Trigger)
- [4. Stages](#4-Stages)
  - [4.1. Objectives]($41-Objectives)
    - [4.1.1. NPC Dialogue](#411-NPC-Dialogue)
    - [4.1.2. Location](#412-Location)
    - [4.1.3. Kill](#413-Kill)
    - [4.1.4. Collect](#414-Collect)
    - [4.1.5. Poem](#415-Poem)
    - [4.1.6. Money](#416-Money)
    - [4.1.7. Quest](#417-Quest)
  - [4.2. Requirements](#42-Requirements)
    - [4.2.1. Item](#421-Item)
    - [4.2.2. Location](#422-Location)
    - [4.2.3. Money](#423-Money)
    - [4.2.4. Time](#424-Time)
    - [4.2.5. Quest Status](#425-Quest-Status)
    - [4.2.6. Active Stage](#426-Active-Stage)

---

<br>

# 1. Introduction  

The general idea of quests is to have multiple stages, which each contain a number of objectives. Stages must be completed successively, but objectives in a stage can be completed in any order, or even at the same time. After the last stage is completed, the quest is completed and the player is rewarded items and/or money.

<br>

# 2. File structure

All quest files are placed in the 'config/aoi-quests/quests' folder. The file name does not matter, as long as the file prefix is `.conf`.

A quest must contain the following attributes.

| Attribute   | Explanation |
|-------------|-------------|
| questId     | The ID of the quest. Must be unique. |
| name        | The name of the quest, displayed in the logbook. |
| trigger     | The objective to complete in order to start the quest. Explained further below. |
| stages      | A list of stages, each containing a list of objectives to complete and more. Explained further below. |
| rewardMoney | The money given to a player when he completes the quest. Can be 0. |
| reward      | A list of items that are given to the player when he completes the quest. The item format is the standard sponge item format. |

The general file structure of dialogue files goes as following. Keep in mind that these files are written in HOCON, not JSON.

```
questId=1
name="&6Quest Name"
trigger {}
stages=[
    {},
    {}
]
rewardMoney=0.0
rewards=[
    {},
    {}
]
```

<br>

# 3. Trigger

The trigger must contain three thing: an objective, the pre-description and the post-description. The objective is the same format as used in the stages. The objective format is explained [here](#41-Objectives). Instead of it being a list, it is only one objective.

The pre and post descriptions are used in the logbook to tell the story during or after completing the quest. In case of the trigger, the pre-description is only used in the clue menu (the menu that opens when you complete a trigger). Both can be either a list or a single text. Both are displayed below.

```
trigger {
    preDesc=[
        "&7Pre Description Line 1.",
        "&7Pre Description Line 2."
    ]
    postDesc="&7Post Description Line 1.."
    objective {}
}
```

<br>

# 4. Stages

Every stage consists of a stageId, a list of objectives, a pre-description and a post-description. The stageId must be unique in the quest, must start at 1 and must be progressive (stage 3 can't exist without stage 2). 

The pre and post-descriptions are the same as the pre and post-description in the trigger. Both can be either a list or a single text. Both are displayed below. 

The objectives are explained [further below](#41-Objectives).

```
{
    stageId=1
    preDesc="&7Pre-description Line 1."
    postDesc=[
        "&7Post-description Line 1.",
        "&7Post-description Line 2."
    ]
    objectives=[
        {},
        {}
    ]
}
```

## 4.1. Objectives

Each stage has a list of objectives that must be completed before the player can progress to the next stage. A trigger also has an objective, which once completed starts the quest (If the player decides to write down the clue).

Each objectives has at least 3 values: the objectiveId and the type. The objectiveId must be unique in the stage. It is used to keep track of a player's progress. The type specifies what type ob objective it is.

There are multiple types of objectives. Here are all the objective types. 

| Type         | Description |
|--------------|-------------|
| NPC Dialogue | The player has to complete a specific dialogue with a specific NPC. |
| Location | The player has to go to a location. |
| Kill | The player has to kill a certain amount of a specific mob. |
| Collect | The player has to gather a certain amount of a specific item. |
| Poem | Unimplemented |
| Money | The player has to have a certain amount of money. |
| Quest | The player has to complete a different quest. |

Each objective is described further below.

### 4.1.1. NPC Dialogue

The NPC Dialogue objective requires two extra values: the dialogueId and the npcId. The dialogueId must refer to a valid dialogue and the npcId must refer to a valid npc.

```
{
    objectiveId=1
    type="npc_dialogue"
    npcId=2
}
```

### 4.1.2. Location

The Location objective requires 2 extra values: the distance and the target location. The distance is the range in blocks that the player must be in to complete the objective.

```
{
    objectiveId=1
    type=location
    distance=5.0
    target {
        world=world
        x=0.0
        y=70.0
        z=0.0
    }
}
```

### 4.1.3. Kill

The Kill objective requires 2 extra values: the entity type and the amount. Additional values may be added in the future to support bosses.

```
{
    objectiveId=1
    type=kill
    entityType="minecraft:ghast"
    amount=3
}
```

### 4.1.4. Collect

The Collect objective requires 2 extra values: the item amount and the item. The item format is the standard sponge item format.

```
{
    objectiveId=1
    type=collect
    itemAmount=3
    item {
        ContentVersion=1
        Count=1
        ItemType="minecraft:gold_nugget"
        UnsafeDamage=0
        UnsafeData {
            display {
                Name="§6Item Name"
                Lore=[
                    "§7Item Lore Line 1."
                ]
            }
        }
    }
}
```

### 4.1.5. Poem

Unimplemented

```
{
    objectiveId=1
    type=poem
}
```

### 4.1.6. Money

The Money objective requires 1 extra value, the amount of money.

```
{
    objectiveId=1
    type=money
    money=500.0
}
```

### 4.1.7. Quest

The Quest objective requires 1 extra value, the questId. The questId must refer to a valid quest.

```
{
    objectiveId=1
    type=money
    questId=3
}
```

## 4.2. Requirements

Each objective can also have additional requirements. If an objective is completed but not all the requirements are met, the objective will not be completed. These are all the requirement types:

| Type         | Description |
|--------------|-------------|
| Item | Requires the player to have a certain amount of a specific item in his inventory. |
| Location | Requires the player to be near a certain location. |
| Money | Requires the player to have a certain amount of money. |
| Time | Requires the time to be between 2 values. |
| Quest Status | Requires the player be at a certain status of a quest. |
| Active Stage | Requires the player to be in a certain stage of a quest. |

Here is an example of how requirements should be added to objectives.

```
{
    objectiveId=1
    type=location
    distance=5.0
    target {
        world=world
        x=0.0
        y=70.0
        z=0.0
    }
    requirements=[
        {},
        {}
    ]
}
```

Each objective is described further below.

### 4.2.1. Item

The Item requirement requires 3 extra values: the item amount, whether or not the items are taken away and the item itself.

```
{
    requirementId=1
    requirementType=item
    itemAmount=3
    take=true
    item {
        ContentVersion=1
        Count=1
        ItemType="minecraft:gold_nugget"
        UnsafeDamage=0
        UnsafeData {
            display {
                Name="§6Item Name"
                Lore=[
                    "§7Item Lore Line 1."
                ]
            }
        }
    }
}
```

### 4.2.2. Location

The Location requirement requires 2 extra values: the distance and the target. The distance is the range in blocks that the player must be in to meet the requirement. 

```
{
    requirementId=1
    requirementType=location
    distance=5.0
    target {
        world=world
        x=0.0
        y=70.0
        z=0.0
    }
}
```

### 4.2.3. Money

The Money requirement requires 2 extra values: the amount of money the player needs and whether to take the money on completion or not.

```
{
    requirementId=1
    requirementType=money
    money=500.0
    take=true
}
```

### 4.2.4. Time

The Time requirement requires 2 extra values, both are times in ticks. The requirement is met when the time is between fromTicks and toTicks. Will also work with values that cross midnight (e.q. from=23000, to=1000).

```
{
    requirementId=1
    requirementType=time
    fromTicks=13000
    toTicks=22000
}
```

### 4.2.5. Quest Status

The Quest requirement requires 2 extra values: the questId and the status of the quest. This requirement is most usefull when adding quest triggers to dialogue. This can be done by creating a path with a quest status requirement with status=available. 

```
{
    requirementId=1
    requirementType=quest
    questId=3
    status=available
}
```

### 4.2.6. Active Stage

The Quest requirement requires 2 extra values, the questId and the stageId. This requirement is most usefull when adding quest objectives to dialogue. This can be done by creating a path with a active stage requirement with the correspending questId and stageId. The objectiveId is not required.

```
{
    requirementId=1
    requirementType=stage
    questId=3
    stageId=2
}
```
