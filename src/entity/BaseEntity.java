package entity;

import game.Civilization;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;
	
	public BaseEntity(Civilization civ)
	{
		owner = civ;
	}
	
}
