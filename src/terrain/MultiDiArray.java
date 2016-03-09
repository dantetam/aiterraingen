package terrain;

public class MultiDiArray<T> {

	public int[] dimensions;
	public int length;
	private T[] data;
	
	public MultiDiArray(int size, int... dimensions)
	{
		length = 1;
		for (int dim: dimensions)
			length *= dim;
		data = (T[])new Object[length];
		this.dimensions = dimensions;
	}
	
	/**
	 * n = 1, i = x
	 * n = 2, i = y*len(x) + x
	 * n = 3, i = z*len(y)*len(x) + y*len(x) + x
	 */
	public T get(int... coord)
	{
		if (coord.length != dimensions.length)
			throw new ArrayIndexOutOfBoundsException("Dimension mismatch");
		int index = 0;
		for (int i = 0; i < coord.length; i++)
		{
			if (i == 0) index += coord[0];
			else
			{
				int add = coord[i];
				for (int j = 0; j < i; j++)
					add *= dimensions[i];
			}
		}
	}
	
}
