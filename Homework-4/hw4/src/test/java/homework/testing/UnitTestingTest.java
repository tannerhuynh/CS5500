package homework.testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTestingTest {
    private UnitTesting test = new UnitTesting();

    @Test(expected = IllegalArgumentException.class)
    public void sqrBoundaryIllegal() {
        test.sqr(-1);
    }

    @Test
    public void sqrBoundary() {
        assertEquals(0, test.sqr(0), 0);
        assertEquals(1, test.sqr(1), 0);
    }

    @Test
    public void sqrRationalRoot() {
        assertEquals(6.125, test.sqr(37.515625), 0.125);
    }

    @Test
    public void sqrIrrationalRoot() {
        assertEquals(1.414214, test.sqr(2), 0.00001);
    }

    @Test
    public void perfectSqr() {
        assertEquals(2, test.sqr(4), 0.00001);
    }

    @Test
    public void sqrPartitions() {
        assertEquals(256, test.sqr(65536), 0);
        assertEquals(256.0, test.sqr(65537), 0.02);
        assertEquals(46340.950001051984, test.sqr(2147483647), 0);
    }

    @Test
    public void sqrMax() {
        assertEquals(46340.950001051984, test.sqr(Integer.MAX_VALUE), 0.00001);
    }

    @Test
    public void factorialLowerBoundary() {
        assertEquals(1, test.factorial(0));
        assertEquals(1, test.factorial(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        test.factorial(-1);
    }

    @Test
    public void factorialPartitions() {
        assertEquals(2004189184, test.factorial(16));
        assertEquals(2004310016, test.factorial(15));
        assertEquals(40320, test.factorial(8));
        assertEquals(5040, test.factorial(7));
    }

    @Test
    public void multiplierIdentityProperty() {
        assertEquals(2147483647, test.multiplier(Integer.MAX_VALUE, 1));
        assertEquals(-2147483648, test.multiplier(Integer.MIN_VALUE, 1));
        assertEquals(2147483646, test.multiplier(Integer.MAX_VALUE - 1, 1));
        assertEquals(-2147483647, test.multiplier(Integer.MIN_VALUE + 1, 1));
    }

    @Test
    public void multiplierZeroBoundary() {
        assertEquals(0, test.multiplier(0, 0));
        assertEquals(0, test.multiplier(0, 1));
        assertEquals(0, test.multiplier(0, -1));
    }

    @Test
    public void multiplierPartitions() {
        assertEquals(-1758101632, test.multiplier(-110000, 328344));
        assertEquals(1758101632, test.multiplier(110000, 328344));
    }

    @Test
    public void despacer() {
        assertEquals("Ornare Nullam Tellus Mollis Ligula", test.despacer("Ornare     " +
                "Nullam " +
                "Tellus Mollis Ligula"));

        assertEquals("Ornare Nullam Tellus Mollis Ligula", test.despacer("Ornare " +
                "Nullam " +
                "Tellus Mollis Ligula         "));
    }

    @Test
    public void despacerPoorPerformance() {
        assertEquals(" Ornare Nullam Tellus Mollis Ligula", test.despacer("         " +
                "Ornare Nullam Tellus Mollis Ligula"));
        assertEquals("df f f \" f &&*^ *(* ^ f f f f f f f f f f f f2 32 32 23 3",
                test.despacer("df f f \" f &&*^ *(* ^ f f f f f \n f f f f f f f2 32 32 23 3"));
    }
}