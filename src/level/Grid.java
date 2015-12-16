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
				Tile tile = new Tile(this,r,c);
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
			for (int j = 0; j < 1; j++)
			{
				BaseEntity en = new BaseEntity(civ);
				Tile t = null;
				do
				{
					t = getTile((int)(Math.random()*tiles.length), (int)(Math.random()*tiles[0].length));
				} while (t.units.size() != 0);
				move(en, t.row, t.col);
			}
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
	
	public Tile[] settlerSpots(Tile t, double dist)
	{
		ArrayList<>
		for (int r = 0; r < rows; r++)
		{
			if (Math.abs(t.row - r) > dist)
				continue;
			for (int c = 0; c < cols; c++)
			{
				if (Math.abs(t.col - c) > dist)
					continue;
				Tile candidate = getTile(r,c); double candidateDist = candidate.dist(t);
				if (candidateDist <= dist)
				{
					
				}
			}
		}
	}
	//Returns the score of a city 5x5 area, ignoring foreign tiles owned by others
	private int[][] returnCityScores(Civilization civ) 
	{
		int[][] temp = new int[rows][cols];
		int[][] tileScores = new int[rows][cols];
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile t = getTile(r,c);
				if (t.owner == null || t.owner.equals(civ))
					tileScores[r][c] = t.food + t.foodImpr + t.metal + t.metalImpr;
				else 
					tileScores[r][c] = 0;
			}
		}
		//I feel like I've written this code before
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
				for (int rr = r - 2; rr <= r + 2; rr++)
					for (int cc = c - 2; cc <= c + 2; cc++)
					{
						Tile t = getTile(r,c);
						if (t != null)
							temp[r][c] += t.food + t.foodImpr + t.metal + t.metalImpr;
					}
		return temp;
	}
	
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
						newShades[r][c] += tiles[rr][cc].color.r;
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
				tiles[r][c].color.set(newShades[r][c]);
			}
		}
	}
	
}
