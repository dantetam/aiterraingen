package system;

import game.Game;
import level.Tile;

public class RenderSystem extends BaseSystem {

	public RenderSystem(Game main) {
		super(main);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		float widthR = main.width/(float)main.grid.rows;
		float widthC = main.height/(float)main.grid.cols;
		for (int r = 0; r < main.grid.rows; r++)
		{
			for (int c = 0; c < main.grid.cols; c++)
			{
				Tile t = main.grid.getTile(r,c);
				main.fill(t.shade);
				main.rect(r*widthR, c*widthC, widthR, widthC);
			}
		}
	}

}
