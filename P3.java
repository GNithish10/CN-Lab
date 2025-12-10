import java.util.*;

public class P3 {
    static final int INF = 999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();
        int[][] A = new int[V + 1][V + 1];
        int[] d = new int[V + 1];
        System.out.println("Enter adjacency matrix:");
        for (int i = 1; i <= V; i++)
            for (int j = 1; j <= V; j++) {
                A[i][j] = sc.nextInt();
                if (i == j)
                    A[i][j] = 0;
                else if (A[i][j] == 0)
                    A[i][j] = INF;
            }
        System.out.print("Enter source vertex: ");
        int src = sc.nextInt();
        Arrays.fill(d, INF);
        d[src] = 0;
        for (int i = 1; i < V; i++)
            for (int u = 1; u <= V; u++)
                for (int v = 1; v <= V; v++)
                    if (A[u][v] != INF && d[v] > d[u] + A[u][v])
                        d[v] = d[u] + A[u][v];
        for (int i = 1; i <= V; i++)
            System.out.println("Distance from " + src + " to " + i + " = " + d[i]);
    }
}