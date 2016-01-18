package level;

import java.util.ArrayList;

public class MultipleMaxTree extends Tree {

	public static void main(String[] args)
	{
		int competitors = 3;
		//NodeType.init();
		NodeType.initNumberMax(competitors);
		new MultipleMaxTree(competitors, 2, 1).test();
	}

	public MultipleMaxTree(int people, int branching, int setsLevels)
	{
		first = new Node(new NodeType("TERM"), -9999);
		for (int i = 0; i < setsLevels; i++)
		{
			for (int j = 0; j < people; j++)
			{
				ArrayList<Node> parents = findNodesDepth(i*(people+1) + j);
				NodeType type = new NodeType("MAXNUM"+j);
				for (int k = 0; k < parents.size(); k++)
				{
					int[] children = new int[branching];
					for (int l = 0; l < branching; l++)
					{
						children[l] = (int)(Math.random()*50); //These values only generate the end results
					}
					populateNodeChildren(parents.get(k),type,children);
				} 
				/*Remember to choose nodes based of dynamic cooperation and competition. 
				Have weights set in certain personalities for:
				cooperation, competition, willingness to help others, and willingness to return gifts, 
				loyalty, forgiveness (both for competition and treachery.), etc.*/
			}
			ArrayList<Node> last = findNodesDepth(i*(people+1) + people);
			for (Node node: last)
			{
				Node current = node;
				double[] values = new double[people];
				for (int j = 0; j < people; j++) //Go up three levels
				{
					values[j] = current.value;
					current = current.parent.upperNode;
					//System.out.println(values[j]);
				}
				Node parent = node.parent.upperNode;
				populateNodeMulti(parent, values);
				clearNodeChild(parent, node);
			}
		}

		//clearAllButTerminal();
	}

	public void printDepthTraverse()
	{
		int i = 0;
		while (true)
		{
			ArrayList<ArrayList<Node>> allNodes = findNodesDepthSeparated(i);
			if (allNodes.isEmpty()) break;
			for (ArrayList<Node> set: allNodes)
			{
				for (int j = 0; j < set.size(); j++)
				{
					Node node = set.get(j);
					if (node.parent != null)
					{
						String temp = node.toString();
						temp += node.parent.preferred ? "<" : " ";
						temp += j == set.size() - 1 ? "  " : " -";
						System.out.print(temp);
					}
					else
						System.out.print(node.toString() + "  ");
					/*if (j == nodes.size() - 1)
					System.out.print(" ");
				else
					System.out.print("-");*/
				}
				System.out.print(" ");
			}
			System.out.println();
			i++;
		}
	}

	public void populateNodeMulti(Node node, double[] values)
	{
		MultiNode newNode = new MultiNode(values);
		node.children.add(new Link(node,newNode));
	}

	public void test()
	{
		printDepthTraverse();
	}

}
