package entity;

import level.Tile;

public abstract class Action {

	public double cost; 
	public String type;
	
	public Action(String t, double c)
	{
		type = t;
		cost = c;
	}
	
	public class MoveAction extends Action {public Tile location; public MoveAction(String t, double c, Tile tile) {super(t,c); location = tile;}}
	public class MakeAction extends Action {public String unit; public MakeAction(String t, double c, String u) {super(t,c); unit = u;}}
	public class QueueAction extends Action {public String unit; public QueueAction(String t, double c, String u) {super(t,c); unit = u;}}
	
}
