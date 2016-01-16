package level;

import java.util.ArrayList;
import java.util.Comparator;

public class Node
{
	public NodeType type;
	public double value;
	public Link parent;
	public ArrayList<Link> children = new ArrayList<Link>();

	public String toString()
	{
		String temp = "m";
		if (type.equals("MAX")) temp = "M";
		else if (type.equals("TERM")) temp = "T";
		else if (type.contains("MAXNUM")) temp = type.toString().substring(6);
		if (parent != null && parent.linkValue != -9999)
		if (value == -9999)
			return temp + ":X";
		return temp + ":" + (int)value;
	}

	public int compareTo(Node node)
	{
		return new Double(value).compareTo(node.value);
	}

	public Node(NodeType t, int v) {type = t; value = v;}

	public Node least() {return leastOrGreatest(-1);}
	public Node greatest() {return leastOrGreatest(1);}
	protected Node leastOrGreatest(int value) //>= 1 for greatest, <= -1 for least
	{
		value = (int)Math.signum(value);
		if (children.isEmpty())
		{
			System.err.println("No children for node " + toString());
			return null;
		}
		Node candidate = children.get(0).node;
		if (children.size() == 1) return candidate;
		for (int i = 1; i < children.size(); i++)
		{
			if (Math.signum(children.get(i).node.compareTo(candidate)) == value) candidate = children.get(i).node;
		}
		return candidate;
	}
}
