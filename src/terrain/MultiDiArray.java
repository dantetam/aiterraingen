package terrain;

import java.util.ArrayList;
import java.util.Iterator;

public class MultiDiArray<T> implements Iterable<T> {

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
	public T get(Object[] coord)
	{
		int[] temp = new int[coord.length]; System.arraycopy(coord, 0, temp, 0, coord.length);
		return get(temp);
	}
	
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
				index += add;
			}
		}
		return data[index];
	}

	public class MultiDiArrayIterator<T> implements Iterator<T>
	{
		public ArrayList<Integer> xyz = new ArrayList<Integer>();
		public MultiDiArrayIterator() {for (int i = 0; i < dimensions.length; i++) xyz.add(new Integer(0));}
		@Override
		public boolean hasNext() {
			int product = 1;
			for (int i: xyz)
				product *= i;
			return product < length;
		}
		@Override
		public T next() {
			T temp = (T) get(xyz.toArray());
			int i = xyz.size() - 1;
			while (true)
			{
				int j = xyz.set(i, xyz.get(i) + 1);
				if (j >= dimensions[i])
				{
					xyz.set(i, 0);
					i--;
				}
				else break;
			}
			if (!hasNext()) return null;
			return temp;
		}
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	public Iterator<T> iterator() {
		return new MultiDiArrayIterator();
	}
	
}
