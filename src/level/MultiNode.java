package level;

public class MultiNode extends Node {

	public double[] values;
	
	public MultiNode(double[] vs) 
	{
		super(new NodeType("MULTI"), -9999);
	}
	
	public MultiNode(NodeType t, double[] vs) 
	{
		super(t, -9999);
		values = vs;
	}
	
	public String toString()
	{
		String temp = "";
		if (values == null) return "T:X";
		for (double d: values)
			temp += (int)d + ",";
		temp = "T:" + temp;
		if (temp.equals("T")) return "T:X";
		return temp;
	}

}
