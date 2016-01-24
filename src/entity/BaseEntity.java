package entity;

import java.util.ArrayList;

import game.Civilization;
import level.Intelligence;
import level.Tile;

public class BaseEntity {

	public Tile location = null;
	public Civilization owner;

	public String name;
	public ArrayList<Action> queueAction = new ArrayList<Action>();
	public boolean improvement = false;
	public double action = 2, maxAction = 2;
	//public ArrayList<Tile> queueTiles = new ArrayList<Tile>();

	public BaseEntity(Civilization civ, String name)
	{
		owner = civ;
		civ.units.add(this);
		this.name = name;
	}

	private Action temp = new Action("temp",-9999);
	public void tick()
	{
		do
		{
			if (improvement)
			{
				if (queueAction.size() == 0)
				{
					Intelligence intel = location.grid.intelligence;
					Tile settleBest = location.grid.settlerSpots(owner, location, 10, 3)[0];
					double[] settleData = intel.scoreFromSettlerPerTurn(this, settleBest);
					double settlerScore = settleData[0]/settleData[1];
					double workerScore = intel.scoreFromWorkerPerTurn(this, (int)settleData[1]);
					if (settlerScore >= workerScore)
					{
						queueAction.add(temp.new QueueAction(5*maxAction, "Settler"));
						queueAction.add(temp.new MakeAction(1*maxAction, "Settler"));
					}
					else
					{
						queueAction.add(temp.new QueueAction(5*maxAction, "Worker"));
						queueAction.add(temp.new MakeAction(1*maxAction, "Worker"));
					}
				}
				if (queueAction.size() > 0)
				{
					Action currentAction = queueAction.remove(0);
					if (currentAction instanceof Action.QueueAction)
					{
						action = 0;
						currentAction.costAction -= maxAction;
					}
					else if (currentAction instanceof Action.QueueAction)
					{
						Action.QueueAction queueAction = (Action.QueueAction)currentAction;
						currentAction.costAction -= maxAction;
						BaseEntity en = new BaseEntity(owner, queueAction.unit);
						location.grid.move(en, location.row, location.col);
					}
				}
			}
			else
			{
				if (name.equals("Settler"))
				{
					if (queueAction.isEmpty())
					{
						Tile[] locations = location.grid.settlerSpots(owner, location, 10, 5);
						for (int i = 0; i < locations.length; i++)
						{
							Tile desired = locations[i];
							ArrayList<Tile> path = location.grid.findPath(this, location.row, location.col, desired.row, desired.col);
							if (path != null)
							{
								for (Tile t: path)
									queueAction.add(temp.new MoveAction(1, t));
								break;
							}
						}
					}
					if (!queueAction.isEmpty())
					{
						//if (queueTiles.size() == 0) break;
						Action currentAction = queueAction.remove(0);
						if (currentAction instanceof Action.MoveAction)
						{
							Action.MoveAction moveAction = (Action.MoveAction)currentAction;
							Tile t = moveAction.location;
							location.grid.move(this, t.row, t.col);
						}
					}
					if (queueAction.size() == 0)
					{
						improvement = true;
					}
				}
				else 
				{
					System.err.println("No action for unit named " + name);
				}
			}
		} while (action > 0);
	}

}
