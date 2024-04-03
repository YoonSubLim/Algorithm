package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240330
public class BOJ_G5_1717 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        makeSet();

        for (int i = 0; i < m; i++) {

            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op == 1) {
                // Same Set ?
                if (find(a) == find(b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            } else if (op == 0) {
                // Union
                union(a, b);
            }
        }
    }

    private static void makeSet() {
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
    }

    private static boolean union(int x, int y) {
        if (find(x) == find(y)) return false;
        parents[find(y)] = find(x);
        return true;
    }

    private static int find(int x) {
        if (x == parents[x])
            return x;
        return parents[x] = find(parents[x]);
    }
}