
public class Polynomial {

    private long bits;

    public Polynomial(long bits) {
        this.bits = bits;
    }

    public long get_bits() {
        return bits;
    }

    public void print_hex() {
        System.out.print("0x" + Long.toHexString(bits).toUpperCase());
    }

    public void print() {
        if (bits == 0) {
            System.out.print("0");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 63; i >= 0; i--) {
            if ((bits & (1L << i)) != 0) {
                if (sb.length() > 0) sb.append(" + ");
                if (i == 0) sb.append("1");
                else if (i == 1) sb.append("x");
                else sb.append("x^").append(i);
            }
        }
        System.out.print(sb);
    }
}
