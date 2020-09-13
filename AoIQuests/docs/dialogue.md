# Dialogue

### **Age of Ittgard Quests Plugin**
<br>

---

# Table of content

- [1. Introduction](#1-Introduction)
- [2. File Structure](#2-File-Structure)
- [3. Nodes](#3-Nodes)
  - [3.1. Link Node](#31-Link-Node)
  - [3.2. Choose Node](#32-Choose-Node)
  - [3.3. Break Node](#33-Break-Node)
  - [3.4. Gift Node](#34-Gift-Node)
  - [3.5. End Node](#35-End-Node)
- [4. Links](#4-Links)
  - [4.1. NPC Lines](#41-NPC-Lines)
  - [4.2. Choice Line](#42-Choice-Line)
  - [4.3. Requirements](#43-Requirements)

---

<br>

# 1. Introduction  

The general idea of the dialogue system is to have a network of nodes, connected by links. The player start at the starting node and based on what type of node is it, will be refered by a link to another node. This will continue until the player reaches an end node. It is also possible to create loops, by linking back to a node the player has already visited.

<br>

# 2. File structure

All dialogue files are placed in the 'config/aoi-quests/dialogue' folder. The file name does not matter, as long as the file prefix is `.conf`.

A dialogue file consists of a dialogueId, the firstNodeId, a list of nodes, a list of links and a list of requirements.
The dialogueId is used to refer to a specific dialogue by quests. This id must be unique. The firstNodeId is required so that the plugin knows where to start the dialogue. The nodes, links and requirements are explained further below.

The general file structure of dialogue files goes as following. Keep in mind that these files are written in HOCON, not JSON.

```
dialogueId=1
firstNodeId=1
nodes=[
    {},
    {}
]
links=[
    {},
    {}
]
requirements=[
    {},
    {}
]
```

<br>

# 3. Nodes

There are multiple types of nodes. Each node requires different data values. Below are all node types with the data they require.

## 3.1. Link Node

The link node is the most basic node type. It simply runs one link, sending the dialogue towards the next node. It is also the most logical starting node for any dialogue.

```
{
    nodeId=1
    nodeType=link
    linkId=1
}
```

## 3.2. Choose Node

The choose node is the most complex node type. It mark a choice for a player. The player chooses which link to run. Keep in mind that to choose a link that link must have a `choiceLine`. The links can also have requirement. These are also specified in the link. When a player meets the requirements, he can choose the option. When he doesnt, the option can not be selected.

```
{
    nodeId=2
    nodeType=choose
    linkIds=[
        2,
        3
    ]
}
```

## 3.3. Break Node

The break node is used to stop the active dialogue. However it doesnt mean the end of the dialogue. The player can reinitiate the dialogue at any time. When he does, the dialogue will restart at whatever node was choosen by this node.

```
{
    nodeId=3
    nodeType=break
    nextNodeId=1
}
```

## 3.4. Gift Node

The gift node is used to give players quest items in dialogue. Other than it giving the player an item, it acts like a normal link node. The item format is the standard sponge item format.

```
{
    nodeId=4
    nodeType=gift
    linkId=4
    item {
        ContentVersion=1
        Count=1
        ItemType="minecraft:totem_of_undying"
        UnsafeDamage=0
        UnsafeData {
            display {
                Lore=[
                    "§7Lore Line 1",
                    "§7Lore Line 2"
                ]
                Name="§6Item Name"
            }
        }
    }
}
```

## 3.5. End Node

The end node marks the end of a dialogue. It is used to tell a quest that the player completed the dialogue. If the player choose a path that should not result in the quest objective being completed, you can use a break node instead.

```
{
    nodeId=5
    nodeType=end
}
```

<br>

# 4. Links

Links do not have different types. However depending on how they are used, they do sometimes require other data. The only essential values are the linkId and the nextNodeId. The linkId must be unique in one dialogue. It is used to refer to the link by nodes. The nextNodeId is the node that the link will run next.

This is the structure of the most basic link, with only has the essential data. 

```
{
    linkId=1
    nextNodeId=2
}
```

However in most cases, other data should be added. There are three types of other data: npcLines, choiseLine and requirements. 

## 4.1. NPC Lines

NPC lines are the lines an NPC will tell a player. Any link can have NPC lines. All NPC dialogue will occur here. These lines will be displayed one after another with a short interval. It can be either a list of texts or a single text. Both options are displayed below. 

```
{
    linkId=1
    nextNodeId=2
    npcLines=[
        "&bNPC Name&7: NPC Line 1.",
        "&bNPC Name&7: NPC Line 2.",
        "&bNPC Name&7: NPC Line 3."
    ]
}
```
```
{
    linkId=1
    nextNodeId=2
    npcLines="&bNPC Name&7: NPC Line 1."
}

```

## 4.2. Choice Line

A choice line is the text displayed in choice menu's for choose nodes. Every link used in a choice menu should have a choice line.

```
{
    linkId=2
    nextNodeId=3
    npcLines=""
    choiceLine="&7No thank you, I'm good."
}
```

## 4.3. Requirements

Requirements are used to check whether the player is allowed to run this link. Currently it is only allowed in combination with choose nodes. This means any link with requirements should also contain a choise line. The requirement format is explained further in [the quests documentation](quests.md#42-Requirements). The requirements are not placed directly in the link for the sake of reusability.

This is an example of how requirements should be applied to links.

```
{
    linkId=2
    nextNodeId=3
    npcLines=""
    choiceLine="&7No thank you, I'm good."
    requirements=[
        1,
        2
    ]
}
```
