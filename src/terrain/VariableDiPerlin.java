package terrain;

public class VariableDiPerlin {

	/**
	 * For n = 2
	 * input: width, height, levels
	 * 
	 * Generate levels of random noise of width*height (2D)
	 * Average levels using weights (3D array)
	 * Return noise of 2d
	 * 
	 * General case
	 * input: levels, otherParameters, width1, width2..., widthN
	 * 
	 * width1*...*widthN
	 * average by levels
	 * Return noise of n dimensions
	 * 
	 */
	
	private int dim = 0;
	public double[] generateNoise(int levels, double persistence, int... dimensions)
	{
		dim = dimensions.length;
		double[][] noises = new double[levels][];
		for (int i = 0; i < levels; i++)
		{
			
		}
	}
	
}
