package entity;

import java.util.ArrayList;

import game.Civilization;
import level.Intelligence;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;

	public String name;
	public ArrayList<String> queueAction = new ArrayList<String>();
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
		if (improvement)
		{
			if (queueAction.size() > 0)
			{
				Intelligence intel = location.grid.intelligence;
				Tile settleBest = location.grid.settlerSpots(owner, location, 10, 3)[0];
				double[] settleData = intel.scoreFromSettlerPerTurn(this, settleBest);
				double settlerScore = settleData[0]/settleData[1];
				double workerScore = intel.scoreFromWorkerPerTurn(this, (int)settleData[1]);
				if (settlerScore >= workerScore)
				{
					for (int i = 0; i < 5; i++)
						queueAction.add("QueueSettler");
					queueAction.add("MakeSettler");
				}
				else
				{
					for (int i = 0; i < 5; i++)
						queueAction.add("QueueWorker");
					queueAction.add("MakeWorker");
				}
			}
		}
		else
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
					do
					{
						if (queueTiles.size() == 0) break;
						Tile t = queueTiles.remove(0);
						location.grid.move(this, t.row, t.col);
					} while (action > 0);
				}
				if (queueTiles.size() == 0)
				{
					improvement = true;
				}
			}
			else 
			{
				System.err.println("No action for unit named " + name);
			}
		}
	}

}
