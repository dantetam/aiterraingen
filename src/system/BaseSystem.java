package system;

import game.Game;

public abstract class BaseSystem {

	private Game game;
	
	public BaseSystem(Game main)
	{
		game = main;
	}
	
	public abstract void tick();
	
}
