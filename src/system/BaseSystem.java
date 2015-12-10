package system;

import game.Game;

public abstract class BaseSystem {

	protected Game main;
	
	public BaseSystem(Game game)
	{
		main = game;
	}
	
	public abstract void tick();
	
}
