package level;

public class Tile {

	public int row, col;
	
	public int food, metal;
	public int foodImpr, metalImpr;
	
	public float shade;
	
	public Tile(int r, int c)
	{
		row = r;
		col = c;
		shade = (float)(Math.random()*255f);
	}
	
	public void start(int f, int m)
	{
		food = f;
		metal = m;
	}
	
	public void improve(int f, int m)
	{
		foodImpr = f;
		metalImpr = m;
	}
	
}
