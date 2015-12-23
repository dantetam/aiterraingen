package level;

import java.util.ArrayList;

import entity.BaseEntity;

public class Intelligence {

	//Measure permanent gain over number of turns. Almost like a E/P ratio instead of a P/E. A ROI?
	public int scoreFromSettlerToTile(BaseEntity en, Tile t) 
	{
		double score = 0;
		for (int rr = t.row - 2; rr <= t.row + 2; rr++)
			for (int cc = t.col - 2; cc <= t.col + 2; cc++)
				if (en.location.grid.getTile(rr,cc) != null)
					score += en.location.grid.evalTile(en.owner, rr, cc);
		return (int)(score/turnsSettlerToTile(en,t));
	}
	
	public double turnsSettlerToTile(BaseEntity en, Tile t)
	{
		ArrayList<Tile> path = en.location.grid.findPath(en, en.location.row, en.location.col, t.row, t.col);
		return Math.ceil((double)path.size()/(double)en.maxAction);
	}
	
	public double scoreFromWorkerInTurns(BaseEntity en, int turns)
	{
		while (true)
		{
			if (turns <= 0) break;
			for (int rr = t.row - 2; rr <= t.row + 2; rr++)
				for (int cc = t.col - 2; cc <= t.col + 2; cc++)
					if (en.location.grid.getTile(rr,cc) != null)
		}
	}
	
}
