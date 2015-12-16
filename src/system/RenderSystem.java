package system;

import entity.BaseEntity;
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
				main.fill(t.color);
				main.rect(r*widthR, c*widthC, widthR, widthC);
			}
		}
		for (int r = 0; r < main.grid.rows; r++)
		{
			for (int c = 0; c < main.grid.cols; c++)
			{
				Tile t = main.grid.getTile(r,c);
				main.fill(0,255,0);
				main.text(t.food, r*widthR + main.textSize/2, c*widthC + main.textSize);
				main.fill(255,100,50);
				main.text(t.metal, (r+1)*widthR - main.textSize, c*widthC + main.textSize);
				if (t.units.size() > 0)
				{
					for (int i = 0; i < t.units.size(); i++) //Render tile improvements before mobile units
					{
						BaseEntity en = t.units.get(i);
						if (en.improvement)
						{
							main.fill(en.owner.color);
							main.rect(r*widthR + widthR/5f, c*widthC + widthC/5f, widthR*0.6f, widthC*0.6f);
						}
					}
					for (int i = 0; i < t.units.size(); i++)
					{
						BaseEntity en = t.units.get(i);
						if (!en.improvement)
						{
							main.fill(en.owner.color);
							main.ellipse(r*widthR + widthR/2f, c*widthC + widthC/2f, widthR/3f, widthC/3f);
						}
					}
				}
			}
		}
	}

}
