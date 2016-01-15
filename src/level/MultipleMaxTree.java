package level;

import java.util.ArrayList;

public class MultipleMaxTree extends Tree {

	public static void main(String[] args)
	{
		int competitors = 3;
		
		new MultipleMaxTree(competitors, 3, 2).test();
	}
	
	public MultipleMaxTree(int people, int branching, int setsLevels)
	{
		
	}
	
	public void test()
	{
		printDepthTraverse();
	}
	
}
