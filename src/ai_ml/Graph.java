package ai_ml;

public class Graph extends ProtectedGridP<Position> {
	
	public Graph(int rows, int cols)
	{
		tiles = new Position[rows][cols];
		init(rows,cols);
		int count = 100;
		for (int i = 0; i < count; i++)
		{
			Position pos = null;
			do
			{
				pos = getTile((int)(Math.random()*rows), (int)(Math.random()*cols));
			} while (pos.occupant == null);
			GraphEntityType type = i >= count/2 ? GraphEntityType.BLUE : GraphEntityType.ORANGE;
			GraphEntity en = new GraphEntity(type);
			move(en, pos);
		}
		determineTileBias();
	}
	
	public void determineTileBias()
	{
		for (int r = 0; r < rows; r++)
		{
			for (int c = 0; c < cols; c++)
			{
				Position pos = getTile(r,c);
				Implement k-NN here
			}
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
