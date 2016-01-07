package level;

import java.util.ArrayList;
import java.util.Comparator;

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
		if (value == -9999)
			return temp + ":X";
		return temp + ":" + value;
	}
	
	public int compareTo(Node node)
	{
		return new Integer(value).compareTo(node.value);
	}
	
	public Node(NodeType t, int v) {type = t; value = v;}
	
	public Node least() {return leastOrGreatest(-1);}
	public Node greatest() {return leastOrGreatest(1);}
	private Node leastOrGreatest(int value) //>= 1 for greatest, <= -1 for least
	{
		value = (int)Math.signum(value);
		if (children.isEmpty())
			return null;
		Node candidate = children.get(0);
		if (children.size() == 1) return candidate;
		for (int i = 1; i < children.size(); i++)
		{
			if (Math.signum(children.get(i).compareTo(candidate)) == value) candidate = children.get(i);
		}
		return candidate;
	}
}
