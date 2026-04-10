import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Converts string into polynomial for manipulation
    public static Polynomial parse_polynomial(String input) {
        String[] terms = input.split("\\+");
        int max_degree = 0;

        ArrayList<Integer> exponents = new ArrayList<>();
        for (String term : terms) {
            term = term.trim();
            if (term.isEmpty()) continue;

            int exponent;
            if (term.equals("1") || term.equals("x^0")) {
                exponent = 0;
            } else if (term.equals("x") || term.equals("x^1")) {
                exponent = 1;
            } else if (term.startsWith("x^")) {
                exponent = Integer.parseInt(term.substring(2).trim());
            } else {
                continue;
            }
            exponents.add(exponent);
            if (exponent > max_degree) max_degree = exponent;
        }

        // Create the list
        ArrayList<Integer> coefficients = new ArrayList<>();
        for (int i = 0; i <= max_degree; i++)
            coefficients.add(0);
        for (int exponent : exponents)
            coefficients.set(exponent, 1);

        return new Polynomial(coefficients);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1. get_degree");
            System.out.println("2. divide_galois");
            System.out.println("3. multiply_galois");
            System.out.println("0. exit");
            System.out.print("Input choice: ");
            String choice = input.nextLine().trim();

            switch (choice) {
                case "1": {
                    System.out.print("Enter A(x): ");
                    Polynomial a = parse_polynomial(input.nextLine().trim());
                    System.out.println("degree = " + Polynomial_Operations.get_degree(a));
                    break;
                }
                case "2": {
                    System.out.print("Enter A(x): ");
                    Polynomial a = parse_polynomial(input.nextLine().trim());
                    System.out.print("Enter m(x): ");
                    Polynomial m = parse_polynomial(input.nextLine().trim());
                    Polynomial result = Polynomial_Operations.divide_galois(a, m);
                    System.out.print("A(x) mod m(x) = ");
                    result.print();
                    System.out.println();
                    break;
                }
                case "3": {
                    System.out.print("Enter A(x): ");
                    Polynomial a = parse_polynomial(input.nextLine().trim());
                    System.out.print("Enter B(x): ");
                    Polynomial b = parse_polynomial(input.nextLine().trim());
                    System.out.print("Enter m(x): ");
                    Polynomial m = parse_polynomial(input.nextLine().trim());
                    Polynomial result = Polynomial_Operations.multiply_galois(a, b, m);
                    System.out.print("A(x) * B(x) mod m(x) = ");
                    result.print();
                    System.out.println();
                    break;
                }
                case "0":
                    input.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
            System.out.println();
        }
    }
}
