package level;

import java.util.ArrayList;

public class MultipleMaxTree extends Tree {
	
	public static void main(String[] args)
	{
		int competitors = 3;
		NodeType.init();
		NodeType.initNumberMax(competitors);
		new MultipleMaxTree(competitors, 3, 1).test();
	}
	
	public MultipleMaxTree(int people, int branching, int setsLevels)
	{
		first = new Node(new NodeType("TERM"), -9999);
		for (int i = 0; i < setsLevels; i++)
		{
			for (int j = 0; j < people; j++)
			{
				ArrayList<Node> parents = findNodesDepth(i*(people+1) + j);
				int[] children = new int[branching];
				for (int k = 0; k < branching; k++)
				{
					children[i] = (int)(Math.random()*50);
				}
				NodeType type = new NodeType("MAXNUM"+j);
				for (int k = 0; k < parents.size(); k++)
				{
					populateNodeChildren(parents.get(k),type,children);
				}
			}
		}

		clearAllButTerminal();
	}
	
	public void test()
	{
		printDepthTraverse();
	}
	
}
