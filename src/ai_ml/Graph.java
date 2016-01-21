package ai_ml;

import java.util.ArrayList;

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
		ArrayList<GraphEntity> entities = new ArrayList<GraphEntity>();
		ArrayList<EntityDistLink> distances = new ArrayList<EntityDistLink>();
		int limit = 10;
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++)
			{
				Position pos = getTile(r,c);
				if (pos.occupant != null)
					entities.add(pos.occupant);
			}
		for (int row = 0; row < rows; row++)
		{
			for (int col = 0; col < cols; col++)
			{
				Position center = getTile(row,col);
				int blue = 0, orange = 0;
				/*for (int r = 0; r < rows; r++)
				{
					//Implement k-NN here
					int i = 0;
					if (distances.get(i) < )	
				}*/
				for (GraphEntity en: entities)
				{
					double dist = en.pos.dist(center);
					for (int i = 0; i < limit; i++)
					{
						if (i >= distances.size() || dist < distances.get(i).dist)
						{
							distances.add(i, new EntityDistLink(en, dist));
							break;
						}
					}
				}
				for (EntityDistLink link: distances)
				{
					if (link.en.type == GraphEntityType.BLUE)
						blue++;
					else if (link.en.type == GraphEntityType.ORANGE)
						orange++;
				}
				if (blue > orange) center.bias = GraphEntityType.BLUE;
				else center.bias = GraphEntityType.ORANGE;
			}
		}
	}
	public class EntityDistLink 
	{
		public GraphEntity en;
		public double dist;
		public EntityDistLink(GraphEntity e, double d) {en = e; dist = d;}
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
