package level;

public class Grid {

	private Tile[][] tiles;
	
	public Grid(int rows, int cols)
	{
		tiles = new Tile[rows][cols];
		this.rows = rows;
		this.cols = cols;
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Tile tile = new Tile(r,c);
				tile.start((int)(Math.random()*5), (int)(Math.random()*5));
				tile.improve(0, 0);
				tiles[r][c] = tile;
			}
		}
		colorTilesAverage();
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
				float n = 0;
				for (int rr = r - 2; rr <= r + 2; rr++)
				{
					for (int cc = c - 2; cc <= c + 2; cc++)
					{
						newShades[r][c] += tiles[r][c].shade;
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
