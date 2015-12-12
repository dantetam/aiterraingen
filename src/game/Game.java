package game;

import java.util.ArrayList;

import level.Grid;
import processing.core.PApplet;
import system.*;

public class Game extends PApplet {

	public ArrayList<BaseSystem> systems = new ArrayList<BaseSystem>();
	public InputSystem iSystem = new InputSystem(this);
	public CivSystem civSystem = new CivSystem(this);
	public RenderSystem rSystem = new RenderSystem(this);

	public Grid grid;

	public float width = 1000, height = 1000, textSize = 16;

	public static void main(String[] args)
	{
		PApplet.main(new String[]{Game.class.getName()});
	}

	public Game()
	{
		//Perform system calculations in this order
		systems.add(iSystem);
		systems.add(civSystem);
		systems.add(rSystem);

		grid = new Grid(20,20);
	}

	public void setup()
	{
		size(1000,1000);
		/*if (super.width != width || super.height != height)
		{
			System.out.println("Please manually set width and height variables in game class");
			System.exit(0);
		}*/
		textSize(textSize);
	}

	public void draw()
	{
		for (int i = 0; i < systems.size(); i++)
		{
			systems.get(i).tick();
		}
	}

}
