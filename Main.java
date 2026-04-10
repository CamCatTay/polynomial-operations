import java.util.Scanner;

public class Main {

    // Parse out for HEX or polynomial and convert to bit format for operations
    public static Polynomial parse_polynomial(String input) {

        input = input.trim();

        // Check for hex input first
        if (input.startsWith("0x") || input.startsWith("0X")) {
            return new Polynomial(Long.parseUnsignedLong(input.substring(2), 16));
        }

        long bits = 0;
        String[] terms = input.split("\\+");
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
            bits |= (1L << exponent);
        }
        return new Polynomial(bits);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("1.) Get Degree");
            System.out.println("2.) Divide");
            System.out.println("3.) Multiply");
            System.out.println("0.) Exit");
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
                    result.print_hex();
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
                    result.print_hex();
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
