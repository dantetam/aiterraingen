package entity;

import game.Civilization;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;

	public String name;
	public boolean improvement = false;
	public double action = 2, maxAction = 2;
	
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
			Tile[] locations = location.grid.settlerSpots(owner, location, 10, 5);
			Tile desired = locations
		}
		else 
		{
			System.err.println("No action for unit named " + name);
		}
	}
	
}
