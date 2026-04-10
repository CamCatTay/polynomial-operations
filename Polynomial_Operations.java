
import java.util.ArrayList;

public class Polynomial_Operations {

    // Returns the degree of polynomial
    public static int get_degree(Polynomial p) {
        ArrayList<Integer> coefficients = p.get_polynomial();
        for (int i = coefficients.size() - 1; i >= 0; i--) {
            if (coefficients.get(i) != 0) return i;
        }
        return -1;
    }

    // Polynomial long division
    public static Polynomial divide_galois(Polynomial a, Polynomial m) {
        ArrayList<Integer> result = new ArrayList<>(a.get_polynomial());
        int mDeg = get_degree(m);

        while (true) {
            int current_degree = -1;
            for (int i = result.size() - 1; i >= 0; i--) {
                if (result.get(i) != 0) { current_degree = i; break; }
            }
            if (current_degree < mDeg) break;

            int shift = current_degree - mDeg;
            ArrayList<Integer> mCoeffs = m.get_polynomial();

            for (int i = 0; i <= mDeg; i++) {
                int index = i + shift;
                while (result.size() <= index) result.add(0);
                result.set(index, (result.get(index) ^ mCoeffs.get(i)) % 2);
            }
        }

        return new Polynomial(result);
    }

    // Polynomial multiplication with reduction of m
    public static Polynomial multiply_galois(Polynomial a, Polynomial b, Polynomial m) {
        ArrayList<Integer> a_coefficients = a.get_polynomial();
        ArrayList<Integer> b_coefficients = b.get_polynomial();

        int max_size = a_coefficients.size() + b_coefficients.size();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < max_size; i++) result.add(0);

        for (int b_index = 0; b_index < b_coefficients.size(); b_index++) {
            if (b_coefficients.get(b_index) == 1) {
                for (int a_index = 0; a_index < a_coefficients.size(); a_index++) {
                    int index = a_index + b_index;
                    result.set(index, (result.get(index) ^ a_coefficients.get(a_index)) % 2);
                }
            }
        }

        return divide_galois(new Polynomial(result), m);
    }
}
