package imageclonega;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.testbed.MaxOnes;
import unalcol.types.collection.bitarray.BitArray;

public class SimilariyCheck extends OptimizationFunction<BitArray> {

	/**
	 * Evaluate
	 * 
	 * @param x
	 *            Binary Array to be evaluated
	 * @return the OptimizationFunction function over the binary array
	 */
	@Override
	public Double apply(BitArray x) {
		// TODO Auto-generated method stub
		return new MaxOnes().apply(x);
	}

}
