public class Polynomial_Operations {

    public static int get_degree(Polynomial p) {
        return get_degree(p.get_bits());
    }

    public static int get_degree(long value) {
        int degree = -1;
        if (value == 0) return degree;
        while (value != 0) {
            degree++;
            value >>= 1;
        }
        return degree;
    }

    public static Polynomial divide_galois(Polynomial a, Polynomial m) {
        long result = a.get_bits();
        int modulus_degree = get_degree(m);
        while (get_degree(result) >= modulus_degree) {
            int shift = get_degree(result) - modulus_degree;
            result ^= (m.get_bits() << shift);
        }
        return new Polynomial(result);
    }

    public static Polynomial multiply_galois(Polynomial a, Polynomial b, Polynomial m) {
        long result = 0;
        long a_value = a.get_bits();
        long b_value = b.get_bits();
        int index = 0;
        while (b_value != 0) {
            if ((b_value & 1) == 1) {
                result ^= (a_value << index);
            }
            b_value >>= 1;
            index++;
        }
        return divide_galois(new Polynomial(result), m);
    }
}
