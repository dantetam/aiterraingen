package entity;

import java.util.ArrayList;

import game.Civilization;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;

	public String name;
	public boolean improvement = false;
	public double action = 2, maxAction = 2;
	public ArrayList<Tile> queueTiles = new ArrayList<Tile>();

	public BaseEntity(Civilization civ, String name)
	{
		owner = civ;
		civ.units.add(this);
		this.name = name;
	}

	public void tick()
	{
		if (name.equals("Settler"))
		{
			if (queueTiles.size() == 0)
			{
				Tile[] locations = location.grid.settlerSpots(owner, location, 10, 5);
				for (int i = 0; i < locations.length; i++)
				{
					Tile desired = locations[i];
					ArrayList<Tile> path = location.grid.findPath(this, location.row, location.col, desired.row, desired.col);
					if (path != null)
					{
						queueTiles = path;
						break;
					}
				}
			}
			if (queueTiles.size() > 0)
			{
				
			}
		}
		else 
		{
			System.err.println("No action for unit named " + name);
		}
	}

}
