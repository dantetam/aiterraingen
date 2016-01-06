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
			node.children.add(newNode);
		}
	}
	
	public void setValuesChildren(Node node, int value)
	{
		for (Node n: node.children)
		{
			n.value = value;
		}
	}
	
	public void clearAllButTerminal()
	{
		ArrayList<ArrayList<Node>> nodeSets = new ArrayList<ArrayList<Node>>();
		int depth = 0; //Find depth of lowest member
		while (true)
		{
			ArrayList<Node> nodes = findNodesDepth(null, depth);
			if (nodes.isEmpty()) break;
			nodeSets.add(nodes); //Store sets so we can traverse them later
			depth++;
		}
		for (ArrayList<Node> nodes: nodeSets)
			for (Node node: nodes)
				node.value = -9999;
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

}
