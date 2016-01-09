package level;

public class Link {

	public Node node;
	public double linkValue;
	public boolean preferred = false;
	
	public Link(Node n)
	{
		node = n;
		n.parent = this;
	}
	
}
