package level;

public class Link {

	public Node upperNode, lowerNode;
	public double linkValue = -9999;
	public boolean preferred = false;
	
	public Link(Node upper, Node lower)
	{
		upperNode = upper;
		lowerNode = lower;
		lowerNode.parent = this;
	}
	
}
