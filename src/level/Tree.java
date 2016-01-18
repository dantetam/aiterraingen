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

	public void populateNodeChildren(Node node, NodeType type, int[] children)
	{
		for (int i = 0; i < children.length; i++)
		{
			Node newNode = new Node(type, children[i]);
			node.children.add(new Link(node, newNode));
		}
	}

	public void clearNodeChild(Node node, Node child)
	{
		Link link = child.parent;
		clearNodeLink(node,link);
	}
	public void clearNodeLink(Node node, Link link) 
	{
		link.lowerNode.value = -9999;
		link.lowerNode.parent = null;
		link.lowerNode = null;
		node.children.remove(link);
		link.upperNode = null;
		link.preferred = false;
		link.linkValue = -9999;
	}
	public void clearAllNodeChildren(Node node)
	{
		for (int i = 0; i < node.children.size(); i++)
		{
			clearNodeLink(node, node.children.get(i));
		}
		node.children.clear();
	}

	public void clearAllButTerminal()
	{
		//ALT: A recursive method that simply calls children. Preorder traversal
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
	public ArrayList<Node> findNodesDepth(int depth)
	{
		return findNodesDepth(null, depth);
	}
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
					newNodes.add(link.lowerNode);
			return findNodesDepth(newNodes, depth-1); //--depth doesn't look as good
		}
	}
	public ArrayList<ArrayList<Node>> findNodesDepthSeparated(int depth)
	{
		/*if (nodes == null)
		{
			nodes = new ArrayList<ArrayList<Node>>();
			ArrayList<Node> temp = new ArrayList<Node>(); //Make creating a new arraylist a utility?
			temp.add(first);
			nodes.add(temp);
		}
		if (depth == 0)
			return nodes;
		else
		{
			ArrayList<ArrayList<Node>> newNodes = new ArrayList<ArrayList<Node>>();
			for (ArrayList<Node> parentNodes: nodes)
			{
				for (Node node: parentNodes)
				{
					ArrayList<Node> lowerNodes = new ArrayList<Node>();
					for (Link link: node.children)
						lowerNodes.add(link.lowerNode);
					newNodes.add(lowerNodes);
				}
			}
			return findNodesDepth(newNodes, depth-1); //--depth doesn't look as good
		}*/
		//Seems a little unnecessarily complex...
		ArrayList<Node> nodes = findNodesDepth(depth);
		ArrayList<ArrayList<Node>> separatedNodes = new ArrayList<ArrayList<Node>>();
		if (nodes.contains(first))
		{
			ArrayList<Node> temp = new ArrayList<Node>();
			temp.add(first);
			separatedNodes.add(temp);
			return separatedNodes;
		}
		if (nodes.isEmpty()) return separatedNodes;
		int[] parentIndex = new int[nodes.size()];
		ArrayList<Node> foundParents = new ArrayList<Node>();
		for (int i = 0; i < nodes.size(); i++)
		{
			Node n = nodes.get(i);
			Node parent = n.parent.upperNode;
			int index = foundParents.indexOf(parent);
			if (index == -1) //Add it to the list of parents if it wasn't already found
			{
				foundParents.add(parent);
				index = foundParents.size() - 1;
			}
			parentIndex[i] = index;
		}
		for (int i = 0; i < foundParents.size(); i++)
			separatedNodes.add(new ArrayList<Node>());
		for (int i = 0; i < nodes.size(); i++)
			separatedNodes.get(parentIndex[i]).add(nodes.get(i));
		return separatedNodes;
	}

	private ArrayList<Node> findPathOfPreference(ArrayList<Node> chosen, Node node)
	{
		if (chosen == null) chosen = new ArrayList<Node>();
		chosen.add(node);
		if (node.children.isEmpty()) 
			return chosen;
		for (Link link: node.children)
			if (link.preferred)
			{
				/*ArrayList<Node> otherNodes = findPathOfPreference(link.node);
				for (Node n: otherNodes)
					nodes.add(n);*/
				return findPathOfPreference(chosen, link.lowerNode);
			}
		return chosen; 
	}
	public ArrayList<Node> fixPreference(Node n)
	{
		ArrayList<Node> chosen = findPathOfPreference(null, first);
		/*for (Node node: chosen)
			System.out.print(">>> " + node.toString() + " ");
		System.out.println();*/
		int i = 0;
		while (true)
		{
			ArrayList<Node> nodes = findNodesDepth(null, i);
			if (nodes.isEmpty()) break;
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				if (!chosen.contains(node) && node.parent != null)
					node.parent.preferred = false;
			}
			i++;
		}
		return chosen;
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
						System.out.print(node.toString() + "<");
					}
					else
					{
						System.out.print(node.toString() + " ");
					}
				}
				else
					System.out.print(node.toString() + " ");
				/*if (j == nodes.size() - 1)
					System.out.print(" ");
				else
					System.out.print("-");*/
			}
			System.out.println();
			i++;
		}
	}

}
