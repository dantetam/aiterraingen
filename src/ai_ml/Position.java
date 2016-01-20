package ai_ml;

public class Position {

	public double x,y;
	public GraphEntity occupant;
	public GraphEntityType bias;
	
	public Position(double a, double b)
	{
		x = a; y = b;
	}
	
	public double dist(Position p)
	{
		return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
	}
	
}
