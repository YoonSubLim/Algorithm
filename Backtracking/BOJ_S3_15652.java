package BOJ;

import java.util.Scanner;

// 240316
public class BOJ_S3_15652 {

    static int N, M;
    static int[] selected;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        selected = new int[M];

        dfs(0, 0);
    }

    private static void dfs(int start, int cnt) {

        if (cnt == M) {

            for (int num: selected) {
                System.out.print(num + 1 + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {

            selected[cnt] = i;
            dfs(i, cnt + 1);
        }
    }
}
