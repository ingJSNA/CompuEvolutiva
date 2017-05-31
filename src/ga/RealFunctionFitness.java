package ga;

import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.real.BinaryToRealVector;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.tracer.Tracer;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArray;
import function.real.Ackley;

public class RealFunctionFitness extends OptimizationFunction<BitArray> {

	// Number of bits per integer (i.e. per real)
	int BITS_PER_DOUBLE = 8;
	private int dIM;
	private CodeDecodeMap<BitArray, double[]> encoder;
	private Ackley ackley;

	public RealFunctionFitness(int bitArraySize) {
		dIM = bitArraySize / BITS_PER_DOUBLE;
		double[] min = DoubleArray.create(dIM, -32.768);
		double[] max = DoubleArray.create(dIM, 32.768);

		// CodeDecodeMap
		encoder = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);

		ackley = new Ackley();

	}

	@Override
	public Double apply(BitArray x) {

		double[] decode = encoder.decode(x);

		double fitness = ackley.value(decode);

		return fitness;
	}
}
