package level;

import java.util.ArrayList;

import auxil.Color;
import entity.BaseEntity;
import game.Civilization;

public class Tile {

	public Grid grid;
	public Civilization owner;
	public int row, col;
	
	public int food, metal;
	public int foodImpr, metalImpr;
	
	public Color color;
	
	public ArrayList<BaseEntity> units = new ArrayList<BaseEntity>();
	
	public Tile(Grid g, int r, int c)
	{
		grid = g;
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
	
	public double dist(Tile t)
	{
		return Math.sqrt(Math.pow(t.row - row, 2) + Math.pow(t.col - col, 2));
	}
	
}
