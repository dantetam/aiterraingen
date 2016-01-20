package ai_ml;

public abstract class ProtectedGridP<T> {

	protected T[][] tiles;
	public int rows, cols;

	public T getTile(int r, int c)
	{
		if (r < 0 || r >= rows || c < 0 || c >= cols)
			return null;
		return tiles[r][c];
	}
	
	public abstract void init(int rows, int cols);

}
