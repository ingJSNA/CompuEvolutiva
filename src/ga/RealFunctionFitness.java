package ga;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArray;
import function.real.Ackley;

public class RealFunctionFitness extends OptimizationFunction<BitArray> {

	// Number of bits per integer (i.e. per real)
	int BITS_PER_DOUBLE = 8;

	public RealFunctionFitness() {
	}

	@Override
	public Double apply(BitArray x) {
		// TODO Auto-generated method stub
		int DIM = x.dimension() / BITS_PER_DOUBLE;
		double[] min = DoubleArray.create(DIM, -32.768);
		double[] max = DoubleArray.create(DIM, 32.768);

		// CodeDecodeMap

		CodeDecodeMap<BitArray, double[]> map = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);

		double[] decode = map.decode(x);

		double fitness = new Ackley().value(decode);

		return fitness;
	}
}
