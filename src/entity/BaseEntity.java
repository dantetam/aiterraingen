package entity;

import game.Civilization;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;
	
	public boolean improvement = false;
	public double action = 2, maxAction = 2;
	
	public BaseEntity(Civilization civ)
	{
		owner = civ;
		civ.units.add(this);
	}
	
	public void tick()
	{
		
	}
	
}
