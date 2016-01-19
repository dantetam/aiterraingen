package ai_ml;

public class ProtectedGrid<T> {

	private T[][] grid;
	public int rows, cols;

	public T getItem(int r, int c)
	{
		if (r < 0 || r >= rows || c < 0 || c >= cols)
			return null;
		return grid[r][c];
	}

}
