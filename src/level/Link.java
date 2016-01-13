package level;

public class Link {

	public Node node;
	public double linkValue = -9999;
	public boolean preferred = false;
	
	public Link(Node n)
	{
		node = n;
		n.parent = this;
	}
	
}
