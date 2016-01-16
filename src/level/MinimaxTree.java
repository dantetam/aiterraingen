package level;

import java.util.ArrayList;
import java.util.Comparator;

public class MinimaxTree extends Tree {

	int levelsBelow;

	public static void main(String[] args)
	{
		NodeType.init();
		new MinimaxTree(5, 3).test();
	}

	public MinimaxTree(int levels, int branching)
	{
		levelsBelow = levels;
		first = new Node(new NodeType("MIN"), 0);
		if (levelsBelow > 1) 
			for (int i = 0; i < levelsBelow - 1; i++)
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
				NodeType type = new NodeType("MAX");
				if (i == levelsBelow - 2) type = new NodeType("TERM");
				else if (nodes.get(0).type.equals("MAX")) type = new NodeType("MIN");
				for (int j = 0; j < nodes.size(); j++)
				{
					int[] children = new int[branching];
					for (int k = 0; k < branching; k++)
						children[k] = i*j*k + (int)(Math.random()*50);
					populateNodeChildren(nodes.get(j),type,children);
				}
			}
		clearAllButTerminal();
	}

	//Pre-order traversal
	public void determineIntermediates(Node node)
	{
		if (node.type.equals("MIN"))
		{
			for (Link link: node.children)
				determineIntermediates(link.node);
			Node least = node.least();
			node.value = least.value;
			least.parent.preferred = true;
		}
		else if (node.type.equals("MAX"))
		{
			for (Link link: node.children)
				determineIntermediates(link.node);
			Node greatest = node.greatest();
			node.value = greatest.value;
			greatest.parent.preferred = true;
		}
		else if (node.type.equals("TERM"))
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
		fixPreference(first);
		printDepthTraverse();
	}

}
