package entity;

import level.Tile;

public abstract class Action {

	public double costAction; 
	public String type;
	
	public Action(String t, double c)
	{
		type = t;
		costAction = c;
	}
	
	public class MoveAction extends Action {public Tile location; public MoveAction(double c, Tile tile) {super("MoveAction",c); location = tile;}}
	public class MakeAction extends Action {public String unit; public MakeAction(double c, String u) {super("MakeAction",c); unit = u;}}
	public class QueueAction extends Action {public String unit; public QueueAction(double c, String u) {super("QueueAction",c); unit = u;}}
	
}
