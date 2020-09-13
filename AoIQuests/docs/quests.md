# Quests

### **Age of Ittgard Quests Plugin**
<br>

---

# Table of content

- [1. Introduction](#1-Introduction)
- [2. File Structure](#2-File-Structure)

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
    objective {}
    preDesc=[
        "&7Pre Description Line 1.",
        "&7Pre Description Line 2."
    ]
    postDesc="&7Post Description Line 1.."
}
```

<br>

# 4. Stages

Every stage consists of a stageId, a list of objectives, a pre-description and a post-description. The stageId must be unique, must also start at 1 and must progressive (stage 3 can't exist without stage 2). 

The pre and post-descriptions are the same as the pre and post-description in the trigger. Both can be either a list or a single text. Both are displayed below. 

The objectives are explained [further below](#41-Objectives).

```
{
    stageId=1
    objectives=[
        {},
        {}
    ]
    preDesc="&7Pre-description Line 1."
    postDesc=[
        "&7Post-description Line 1.",
        "&7Post-description Line 2."
    ]
}
```

## 4.1. Objectives

