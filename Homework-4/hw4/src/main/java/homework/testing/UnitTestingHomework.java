package homework.testing;

/**
 * This interface specifies a number of methods for which you need to create a sufficient test
 * suite.
 */
public interface UnitTestingHomework {
    /**
     * Sqr.
     *
     * @param n the factor
     * @return the product of the factor times itself
     */
    double sqr(double n);

    /**
     * Factorial.
     *
     * @param n the (non-negative) factor to consider
     * @return n!
     */
    int factorial(int n);

    /**
     * Multiplier
     *
     * @param x one factor
     * @param y second factor
     * @return (x * y)
     */
    int multiplier(int x, int y);

    /**
     * Despacer. Replace multiple contiguous spaces with a single space
     *
     * @param inputText the input text
     * @return the string such that there is no substring containing only blanks whose length is
     * greater than one (1). A blank is defined as the char whose ASCII value is 32.
     */
    String despacer(String inputText);
}
