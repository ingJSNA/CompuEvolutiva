package imageclonega;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestImageCloneGA {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBinary() {
		fail("Not yet implemented");
	}

	@Test
	public void testBinaryImageClone() {
		ImageCloneGA ga = new ImageCloneGA();
		try {
			ga.binaryImageClone();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Exception");
		}

	}

}
