package level;

import java.util.ArrayList;

import auxil.Color;
import entity.BaseEntity;

public class Tile {

	public int row, col;
	
	public int food, metal;
	public int foodImpr, metalImpr;
	
	public Color color;
	
	public ArrayList<BaseEntity> units = new ArrayList<BaseEntity>();
	
	public Tile(int r, int c)
	{
		row = r;
		col = c;
		float shade = (float)(Math.random()*255f);
		color = new Color(shade,shade,shade);
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
