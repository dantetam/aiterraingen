package level;

import java.util.ArrayList;
import java.util.Comparator;

public class MinimaxTree extends Tree {

	int levelsBelow;

	public static void main(String[] args)
	{
		new MinimaxTree(4).test();
	}

	public MinimaxTree(int levels)
	{
		levelsBelow = levels;
		first = new Node(NodeType.MIN, 0);
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
				populateNodeChildren(nodes.get(j),type,i*j + (int)(Math.random()*50),i*j*2 + (int)(Math.random()*50));
		}
		clearAllButTerminal();
	}

	//Pre-order traversal
	public void determineIntermediates(Node node)
	{
		if (node.type == NodeType.MIN)
		{
			for (Node child: node.children)
				determineIntermediates(child);
			node.value = node.least().value;
		}
		else if (node.type == NodeType.MAX)
		{
			for (Node child: node.children)
				determineIntermediates(child);
			node.value = node.greatest().value;
		}
		else if (node.type == NodeType.TERM)
		{
			return;
		}
		else
			System.err.println("Invalid node type of " + node.toString() + " for minimax tree");
		/*for (Node child: node.children)
			determineIntermediates(child);*/
	}

	public void test()
	{
		printDepthTraverse();
		determineIntermediates(first);
		printDepthTraverse();
	}

}
