package level;

import java.util.ArrayList;

import auxil.Color;
import entity.BaseEntity;
import game.Civilization;

public class Tile {

	public Grid grid;
	public Civilization owner;
	public int row, col;
	
	public int biome;
	public int food, metal;
	public int foodImpr, metalImpr;
	
	public Color color;
	
	public ArrayList<BaseEntity> units = new ArrayList<BaseEntity>();
	
	public Tile(Grid g, int r, int c)
	{
		grid = g;
		biome = (int)(Math.random()*7);
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
	
	public void improveBiome() //Possibly convert biome?
	{
		if (biome >= 0 || biome <= 2)
		{
			metalImpr = 2;
		}
		else if (biome >= 4 || biome <= 6)
		{
			foodImpr = 2;
		}
		else
		{
			foodImpr = 1;
			metalImpr = 1;
		}
	}
	
	public double dist(Tile t)
	{
		return Math.sqrt(Math.pow(t.row - row, 2) + Math.pow(t.col - col, 2));
	}
	
}
