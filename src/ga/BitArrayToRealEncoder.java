package ga;

import unalcol.optimization.real.BinaryToRealVector;
import unalcol.search.multilevel.CodeDecodeMap;
import unalcol.types.collection.bitarray.BitArray;
import unalcol.types.real.array.DoubleArray;

public class BitArrayToRealEncoder {

	// Number of bits per integer (i.e. per real)
	private int BITS_PER_DOUBLE = 8;
	private int dIM;
	private CodeDecodeMap<BitArray, double[]> encoder;

	public BitArrayToRealEncoder(int bitArraySize, double minValue, double maxValue) {

		init(bitArraySize, minValue, maxValue);
	}

	public BitArrayToRealEncoder(int bitArraySize, double minValue, double maxValue,
			int bitsPerDouble) {
		this.BITS_PER_DOUBLE = bitsPerDouble;
		init(bitArraySize, minValue, maxValue);
	}

	/**
	 * @param bitArraySize
	 * @param minValue
	 * @param maxValue
	 */
	private void init(int bitArraySize, double minValue, double maxValue) {
		dIM = bitArraySize / BITS_PER_DOUBLE;
		double[] min = DoubleArray.create(dIM, minValue);
		double[] max = DoubleArray.create(dIM, maxValue);

		// CodeDecodeMap
		encoder = new BinaryToRealVector(BITS_PER_DOUBLE, min, max);
	}

	public BitArray encode(double[] thing) {
		return encoder.code(thing);
	}

	public double[] decode(BitArray bitArray) {
		return encoder.decode(bitArray);
	}
}
