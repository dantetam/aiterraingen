package system;

import entity.BaseEntity;
import game.Civilization;
import game.Game;

public class CivSystem extends BaseSystem {

	public CivSystem(Game main) {
		super(main);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		for (int i = 0; i < main.grid.civs.length; i++)
		{
			Civilization civ = main.grid.civs[i];
			for (int j = 0; j < civ.units.size(); j++)
			{
				BaseEntity en = civ.units.get(j);
				en.tick();
			}
		}
	}
	
}
