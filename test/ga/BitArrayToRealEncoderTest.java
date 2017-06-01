/**
 * 
 */
package ga;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import unalcol.types.collection.bitarray.BitArray;

/**
 * @author juan
 *
 */
public class BitArrayToRealEncoderTest {

	private static final Logger LOG = LoggerFactory.getLogger(BitArrayToRealEncoderTest.class);
	private BitArrayToRealEncoder encoder;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		encoder = new BitArrayToRealEncoder(24, -32.768, 32.768);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ga.BitArrayToRealEncoder#encode(double[])}.
	 */
	@Test
	public void testEncode() {
		double[] realArray = new double[] { 0, 0, 0 };
		try {
			BitArray bitArray = encoder.encode(realArray);
			assertNotNull(bitArray);
			System.out.println(bitArray.toString());
		} catch (Exception e) {
			LOG.error("Exception", e);
			fail("Not yet implemented");
		}
	}

	/**
	 * Test method for
	 * {@link ga.BitArrayToRealEncoder#decode(unalcol.types.collection.bitarray.BitArray)}.
	 */
	@Test
	public void testDecode() {
		BitArray bitArray = new BitArray("100000001000000010000000");
		try {
			double[] values = encoder.decode(bitArray);
			assertNotNull(values);
			System.out.println(Arrays.toString(values));
		} catch (Exception e) {
			LOG.error("Exception", e);
			fail("Not yet implemented");
		}

	}

	@Test
	public void testFullDecode() {
		encoder = new BitArrayToRealEncoder(8 * 100, -32.768, 32.768);
		BitArray bitArray = new BitArray(
				"10000000100000001000001101111111100000111000001101111111011111110111111110000000011111111000000001111100100000000111110001111100100000111000000001111100100000111000000001111111011111000111111101111111011111000111110001111111100000111000000010000011011111001000001110000011100000111000000001111111011111111000000001111100100000110111110010000000100000111000001101111111011111000111111110000011011111111000000010000011100000000111110001111111011111111000000010000011100000000111111101111100100000000111110010000000100000000111111101111111011111111000001110000000011111111000001101111100011111111000000010000011100000111000001101111100011111111000000001111100011111110111111110000011011111111000000010000011100000001000000001111100011111111000000010000011100000111000000010000000100000000111110001111111");
		try {
			double[] values = encoder.decode(bitArray);
			assertNotNull(values);
			System.out.println(Arrays.toString(values));
		} catch (Exception e) {
			LOG.error("Exception", e);
			fail("Not yet implemented");
		}

	}

}
