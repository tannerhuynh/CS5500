package homework.testing;

public class UnitTesting implements UnitTestingHomework {
    // Illegal constructor
    public UnitTesting() {
        // Nothing
    }

    @Override
    public double sqr(double n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot be negative number.");
        } else if (n == 0) {
            return 0;
        }
        double t;

        double squareRoot = n / 2;

        do {
            t = squareRoot;
            squareRoot = (t + (n / t)) / 2;
        } while ((t - squareRoot) != 0);

        return squareRoot;
    }

    @Override
    public int factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException("Cannot be negative number.");
        }
        int mul = 1;

        for(int i = 1; i <= n; i++)
        {
            mul = mul * i;
        }
        return mul;
    }

    @Override
    public int multiplier(int x, int y) {
        return x * y;
    }

    @Override
    public String despacer(String s) {
        StringBuilder builder = new StringBuilder(s.length());
        boolean foundSpace = false;

        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                foundSpace = true;
            } else {
                if (foundSpace) {
                    builder.append(' ');
                    foundSpace = false;
                }
                builder.append(s.charAt(i));
            }
        }
        return builder.toString();
    }
}
