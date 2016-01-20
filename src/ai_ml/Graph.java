package ai_ml;

public class Graph extends ProtectedGridP<Position> {
	
	public Graph(int rows, int cols)
	{
		tiles = new Position[rows][cols];
		init(rows,cols);
		for (int i = 0; i < 50; i++)
		{
			Position pos = null;
			do
			{
				pos = getTile((int)(Math.random()*rows), (int)(Math.random()*cols));
			} while (pos.occupant == null);
			GraphEntity en = new GraphEntity(GraphEntityType.BLUE);
			move(en, pos);
		}
	}
	
	public void move(GraphEntity en, Position p)
	{
		if (p.occupant != null) return;
		en.pos = p;
		p.occupant = en;
	}

	public void init(int rows, int cols) 
	{
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				tiles[r][c] = new Position(r,c);
			}
		}
	}
	
}
