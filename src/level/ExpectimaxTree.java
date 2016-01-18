package level;

import java.util.ArrayList;

public class ExpectimaxTree extends MinimaxTree {

	public static void main(String[] args)
	{
		new ExpectimaxTree(3,3).test();
	}

	public ExpectimaxTree(int levels, int branching) {
		super(levels,branching);
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
			sum += link.linkValue*link.lowerNode.value;
		node.value = sum;
	}

	//Pre-order traversal
	public void determineIntermediates(Node node)
	{
		if (node.type.equals("MIN"))
		{
			for (Link link: node.children)
				determineIntermediates(link.lowerNode);
			calculateExpected(node);
			node.least().parent.preferred = true;
		}
		else if (node.type.equals("MAX"))
		{
			for (Link link: node.children)
				determineIntermediates(link.lowerNode);
			calculateExpected(node);
			node.greatest().parent.preferred = true;
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
	
	public void printDepthTraverse()
	{
		int i = 0;
		while (true)
		{
			String stringy = "";
			ArrayList<Node> nodes = findNodesDepth(null, i);
			if (nodes.isEmpty()) break;
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				stringy = node.toString();
				if (node.parent != null)
				{
					if (node.parent.linkValue == -9999)
						stringy += " V:X";
					else
						stringy += " " + (int)(node.parent.linkValue*10)/10D;
					if (node.parent.preferred)
					{
						System.out.print(stringy + "< ");
					}
					else
					{
						System.out.print(stringy + "  ");
					}
				}
				else
					System.out.print(stringy + "  ");
			}
			System.out.println();
			i++;
		}
	}

}
