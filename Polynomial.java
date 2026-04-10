
import java.util.ArrayList;

public class Polynomial {

    // (Index: Degree, Element: Coefficient)
    private ArrayList<Integer> coefficients;

    public Polynomial(ArrayList<Integer> coefficients) {
        if (coefficients != null && !coefficients.isEmpty()) {
            this.coefficients = coefficients;
        } else {
            this.coefficients = new ArrayList<>();
        }
    }

    public void add_coefficient(int num) {
        coefficients.addLast(num);
    }

    // Returns the highest degree (index of last element)
    public int get_degree() {
        if (coefficients.isEmpty()) return -1;
        return coefficients.size() - 1;
    }

    public ArrayList<Integer> get_polynomial() {
        return coefficients;
    }

    public void print() {
        int listSize = coefficients.size();
        if (listSize == 0) {
            System.out.print("0");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = listSize - 1; i >= 0; i--) {
            int coefficient = coefficients.get(i);
            if (coefficient != 0) {
                if (sb.length() > 0) sb.append(" + ");
                if (i == 0) {
                    sb.append(coefficient);
                } else if (i == 1) {
                    if (coefficient != 1) sb.append(coefficient);
                    sb.append("x");
                } else {
                    if (coefficient != 1) sb.append(coefficient);
                    sb.append("x^").append(i);
                }
            }
        }
        if (sb.length() == 0) sb.append("0");
        System.out.print(sb);
    }
}
