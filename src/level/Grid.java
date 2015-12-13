package level;

import entity.BaseEntity;
import game.Civilization;

public class Grid {

	private Tile[][] tiles;
	public Civilization[] civs;
	
	public Grid(int rows, int cols, int numCivs)
	{
		tiles = new Tile[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile tile = new Tile(r,c);
				tile.start((int)(Math.random()*6), (int)(Math.random()*6));
				tile.improve(0, 0);
				tiles[r][c] = tile;
			}
		}
		colorTilesAverage();
		
		civs = new Civilization[numCivs];
		for (int i = 0; i < numCivs; i++)
		{
			Civilization civ = new Civilization();
			civs[i] = civ;
		}
	}
	
	public void move(BaseEntity en, int r, int c)
	{
		if (en.location != null)
			en.location.units.remove(en);
		Tile t = getTile(r,c);
		en.location = t; //Could possibly be null
		if (t != null)
		{
			t.units.add(en);
		}
	}
	
	public Tile getTile(int r, int c)
	{
		if (r >= 0 && r < tiles.length && c >= 0 && c < tiles[0].length)
			return tiles[r][c];
		return null;
	}
	public int rows;
	public int cols;
	
	private void colorTilesAverage()
	{
		float[][] newShades = new float[rows][cols];
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				float n = 0; int sight = 1;
				for (int rr = r - sight; rr <= r + sight; rr++)
				{
					for (int cc = c - sight; cc <= c + sight; cc++)
					{
						if (getTile(rr,cc) == null) continue;
						newShades[r][c] += tiles[rr][cc].shade;
						n++;
					}
				}
				newShades[r][c] /= n;
			}
		}
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				tiles[r][c].shade = newShades[r][c];
			}
		}
	}
	
}
