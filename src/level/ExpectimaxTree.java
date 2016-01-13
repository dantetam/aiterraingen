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
		}
	}

}
