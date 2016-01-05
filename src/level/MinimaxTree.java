package level;

import java.util.ArrayList;

public class MinimaxTree {

	public Node first = new Node(NodeType.MIN, 0);

	public static void main(String[] args)
	{
		int levelsBelow = 5;
		for (int i = 0; i < levelsBelow; i++)
		{
			//Find node of level i
			ArrayList<Node> nodes = findNodesDepth(0); Remember to commit code on day of 1/5/2016
			populateNodeChildren(i,-i);
		}
	}
	
	//Depth traversal
	public ArrayList<Node> findNodesDepth(ArrayList<Node> nodes, int depth)
	{
		if (nodes == null)
		{
			nodes = new ArrayList<Node>();
			nodes.add(first);
		}
		if (depth == 0)
			return nodes;
		else
		{
			ArrayList<Node> newNodes = new ArrayList<Node>();
			for (Node node: nodes)
				for (Node n: node.children)
					newNodes.add(n);
			return findNodesDepth(newNodes, depth-1); //--depth doesn't look as good
		}
	}
	
	public class Node
	{
		public NodeType type;
		public int value;
		public ArrayList<Node> children = new ArrayList<Node>();
		public Node(NodeType t, int v) {type = t; value = v;}
	}
	public enum NodeType {MIN, MAX, TERM}
	
}
