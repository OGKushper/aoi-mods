package me.mrdaniel.ageofittgard.catalogtypes.nodetype;

import com.google.common.collect.Lists;

import java.util.List;

public final class NodeTypes {

	public static final NodeType BRANCHING = new NodeType("Branching", "branching");
	public static final NodeType LINK = new NodeType("Link", "link");
	public static final NodeType CHOOSE = new NodeType("Choose", "choose");
	public static final NodeType BREAK = new NodeType("Break", "break");
	public static final NodeType GIFT = new NodeType("Gift", "gift");
	public static final NodeType END = new NodeType("End", "end");

	public static final List<NodeType> ALL = Lists.newArrayList(BRANCHING, LINK, CHOOSE, BREAK, GIFT, END);
}
