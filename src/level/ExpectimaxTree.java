package level;

import java.util.ArrayList;

public class ExpectimaxTree extends MinimaxTree {

	public static void main(String[] args)
	{
		new ExpectimaxTree(4).test();
	}
	
	public ExpectimaxTree(int levels) {
		super(levels);
		for (int i = 0; i < levels; i++)
		{
			ArrayList<Node> nodes = super.findNodesDepth(null, i);
			for (Node node: nodes)
			{
				int size = node.children.size();
				if (size == 0) continue;
				double sum = 0;
				for (Link link: node.children)
				{
					link.linkValue = Math.random();
					sum += link.linkValue;
				}
				for (Link link: node.children)
					link.linkValue /= sum; //Normalize values so the sum adds up to 1
			}
		}
	}

}
