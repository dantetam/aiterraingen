package level;

import java.util.ArrayList;

public class NodeType {
	public static ArrayList<String> allTypes = new ArrayList<String>();
	public String type = "";
	
	private static boolean init = false; 
	public static void init()
	{
		init = true;
		String[] temp = {"MIN", "MAX", "TERM", "CHANCE", "MULTI"};
		for (String s: temp)
			allTypes.add(s);
	}
	public static void initNumberMax(int n)
	{
		for (String t: allTypes)
			if (t.contains("MAXNUM"))
				allTypes.remove(t);
		for (int i = 0; i < n; i++)
			allTypes.add("MAXNUM"+i);
	}
	
	public NodeType(String t)
	{
		if (!init) init();
		if (!allTypes.contains(t))
		{
			System.err.println("Invalid type: " + t);
			type = null;
		}
		else
			type = t;
	}
	
	
	public boolean equals(String t) {return type.equals(t);}
	public boolean contains(String t) {return type.contains(t);}
	public String toString() {return type;}
	
}
