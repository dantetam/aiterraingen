package level;

import java.util.ArrayList;

public class MinimaxTree extends Tree {

	public Node first = new Node(NodeType.MIN, 0);

	public static void main(String[] args)
	{
		new MinimaxTree().test();
	}

	public void test()
	{
		int levelsBelow = 5;
		for (int i = 0; i < levelsBelow; i++)
		{
			//Find node of level i
			ArrayList<Node> nodes = findNodesDepth(null, i);
			if (nodes.size() == 0) {System.err.println("Empty list"); break;}
			//Add appropriate nodes
			/*
				   MIN
				   / \
				 MAX MAX
				/ |   | \
			 MIN MIN MIN MIN
			       ...
	       TERM TERM TERM TERM
			 */
			NodeType type = NodeType.MAX; 
			if (i == levelsBelow - 1) type = NodeType.TERM;
			else if (nodes.get(0).type == NodeType.MAX) type = NodeType.MIN;
			for (int j = 0; j < nodes.size(); j++)
				populateNodeChildren(nodes.get(i),type,i*j,i*j*2);
		}
		for (int i = 0; i <= levelsBelow; i++)
		{
			ArrayList<Node> nodes = findNodesDepth(null, i);
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				System.out.print(node.toString() + " ");
			}
			System.out.println();
		}
	}

	public void populateNodeChildren(Node node, NodeType type, int... children)
	{
		for (int i = 0; i < children.length; i++)
		{
			Node newNode = new Node(type, children[i]);
			node.children.add(newNode);
		}
	}

}
