import java.util.*;

public class P1 {

    static int xor(int a, int b) {
        return a == b ? 0 : 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m, i, j;

        System.out.println("Enter no. of bits in Dataword(d) : ");
        n = sc.nextInt();

        System.out.println("Enter no. of generator bits : ");
        m = sc.nextInt();

        int[] d = new int[n + m];
        int[] g = new int[m];
        int[] r = new int[n + m];

        System.out.println("Enter Dataword bits(one bit per line):");
        for (i = 0; i < n; i++)
            d[i] = sc.nextInt();

        System.out.println("Enter generator bits(one bit per line):");
        for (i = 0; i < m; i++)
            g[i] = sc.nextInt();

        // Append m-1 zeros
        for (i = n; i < n + m - 1; i++)
            d[i] = 0;

        // Copy data to r
        for (i = 0; i < n + m; i++)
            r[i] = d[i];

        // CRC division
        for (i = 0; i < n; i++) {
            if (r[i] == 1) {
                for (j = 0; j < m; j++)
                    r[i + j] = xor(r[i + j], g[j]);
            }
        }

        // Append remainder to dataword
        System.out.println("The redundant (r) bits added are : ");
        for (i = n; i < n + m - 1; i++) {
            d[i] = r[i];
            System.out.println(d[i]);
        }

        // Print codeword
        System.out.println("\nThe codeword (c = d + r) is :");
        for (i = 0; i < n + m - 1; i++)
            System.out.println(d[i]);

        // ---------------- ERROR / NO ERROR PART ----------------

        System.out.println("\nEnter received codeword bits one by one:");
        for (i = 0; i < n + m - 1; i++)
            r[i] = sc.nextInt();

        // CRC check at receiver
        for (i = 0; i < n; i++) {
            if (r[i] == 1) {
                for (j = 0; j < m; j++)
                    r[i + j] = xor(r[i + j], g[j]);
            }
        }

        // Check if remainder is zero
        boolean error = false;
        for (i = 0; i < n + m - 1; i++) {
            if (r[i] == 1) {
                error = true;
                break;
            }
        }

        if (error)
            System.out.println("\nError detected in received data ❌");
        else
            System.out.println("\nNo error detected in received data ✅");
    }
}
