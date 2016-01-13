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
	
	public void calculateExpected(Node node)
	{
		if (node.children.size() == 0) return;
		double sum = 0;
		for (Link link: node.children)
			sum += link.linkValue*link.node.value;
		node.value = sum;
	}

	//Pre-order traversal
	public void determineIntermediates(Node node)
	{
		if (node.type == NodeType.MIN)
		{
			for (Link link: node.children)
				determineIntermediates(link.node);
			calculateExpected(node);
			node.greatest().parent.preferred = true;
		}
		else if (node.type == NodeType.MAX)
		{
			for (Link link: node.children)
				determineIntermediates(link.node);
			calculateExpected(node);
			node.greatest().parent.preferred = true;
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

}
