import java.util.*;

public class P2 {
    int I, B, N, bs = 0, t = 0, p = 0;
    int dt[];
    boolean inCtrl = true, outCtrl = true;

    void read() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter packet size: ");
        I = sc.nextInt();
        System.out.print("Enter bucket size: ");
        B = sc.nextInt();
        System.out.print("Enter number of packets: ");
        N = sc.nextInt();
        dt = new int[N];
        System.out.println("Enter arrival time for each packet:");
        for (int i = 0; i < N; i++)
            dt[i] = sc.nextInt();
    }

    synchronized void insert() {
        if (p < N && dt[p] == t) {
            if (bs + I <= B) {
                bs += I;
                System.out.println("Packet received at t = " + t +
                        ". " + bs + " Bytes still left in Bucket");
            } else {
                System.out.println("Bucket overflow. Packet Lost at t = " + t);
            }
            p++;
        }
        if (p == N)
            inCtrl = false;
        t++;
    }

    synchronized void delete() {
        if (bs > 0) {
            bs--;
            System.out.println("1 Byte Leaked.■■ " +
                    bs + " Bytes still left in Bucket");
        } else if (p == N) {
            outCtrl = false;
        } else {
            System.out.println("Bucket is Empty. Waiting for Incoming Packets");
        }
    }

    public static void main(String[] args) {
        P2 b = new P2();
        b.read();
        new Thread(() -> {
            try {
                while (b.inCtrl) {
                    b.insert();
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
            }
        }).start();
        new Thread(() -> {
            try {
                while (b.outCtrl) {
                    b.delete();
                    Thread.sleep(1000);
                }
                System.out.println("\nAll Packets Have Been Sent");
            } catch (Exception e) {
            }
        }).start();
    }
}