package game;

import java.util.ArrayList;

import auxil.Color;
import entity.BaseEntity;

public class Civilization {

	public ArrayList<BaseEntity> units = new ArrayList<BaseEntity>();
	
	public Color color;
	
	public Civilization()
	{
		/*r = (float)(Math.random()*255f);
		g = (float)(Math.random()*255f);
		b = (float)(Math.random()*255f);*/
		color = new Color((float)(Math.random()*255f),(float)(Math.random()*255f),(float)(Math.random()*255f));
	}
	
}
