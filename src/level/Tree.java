package level;

import java.util.ArrayList;

public class Tree {

	public Node first = null; // = new Node(NodeType.MIN, 0);

	public static void main(String[] args)
	{
		new Tree().test();
	}

	public void test()
	{

	}

	public void populateNodeChildren(Node node, NodeType type, int... children)
	{
		for (int i = 0; i < children.length; i++)
		{
			Node newNode = new Node(type, children[i]);
			node.children.add(new Link(newNode));
		}
	}

	public void clearAllButTerminal()
	{
		//ALT: A recursive that simply calls children. Preorder traversal
		ArrayList<ArrayList<Node>> nodeSets = new ArrayList<ArrayList<Node>>();
		int depth = 0; //Find depth of lowest member
		while (true)
		{
			ArrayList<Node> nodes = findNodesDepth(null, depth);
			if (nodes.isEmpty()) break;
			nodeSets.add(nodes); //Store sets so we can traverse them later
			depth++;
		}
		for (int i = 0; i < depth-1; i++)
		{
			for (Node node: nodeSets.get(i))
				node.value = -9999;
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
				for (Link link: node.children)
					newNodes.add(link.node);
			return findNodesDepth(newNodes, depth-1); //--depth doesn't look as good
		}
	}

	public void printDepthTraverse()
	{
		int i = 0;
		while (true)
		{
			ArrayList<Node> nodes = findNodesDepth(null, i);
			if (nodes.isEmpty()) break;
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				if (node.parent != null)
				{
					if (node.parent.preferred)
					{
						System.out.print(node.toString() + "< ");
					}
					else
					{
						System.out.print(node.toString() + "  ");
					}
				}
				else
					System.out.print(node.toString() + "  ");
			}
			System.out.println();
			i++;
		}
	}

}
