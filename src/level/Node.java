package level;

import java.util.ArrayList;

public class Node
{
	public NodeType type;
	public int value;
	public ArrayList<Node> children = new ArrayList<Node>();
	public String toString()
	{
		String temp = "m";
		if (type == NodeType.MAX) temp = "M";
		else if (type == NodeType.TERM) temp = "T";
		return temp + ":" + value;
	}
	public Node(NodeType t, int v) {type = t; value = v;}
}
