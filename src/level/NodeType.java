package level;

import java.util.ArrayList;

public class NodeType {
	public static ArrayList<String> allTypes = new ArrayList<String>();
	
	public static void init()
	{
		String[] temp = {"MIN", "MAX", "TERM", "CHANCE"};
		for (String s: temp)
			allTypes.add(s);
	}
	public static void initNumberMax(int n)
	{
		for (int i = 0; i < n; i++)
			allTypes.add("MAXNUM"+i);
	}
	
	public NodeType()
	{
		
	}
}
