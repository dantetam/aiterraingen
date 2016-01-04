package level;

import java.util.ArrayList;

public class MinimaxTree {

	public Node first;

	public class Node
	{
		public NodeType type;
		public int value;
		public ArrayList<Node> children = new ArrayList<Node>();
		public Node(NodeType t, int v) {type = t; value = v;}
	}
	public enum NodeType {MIN, MAX, TERM}
	
}
