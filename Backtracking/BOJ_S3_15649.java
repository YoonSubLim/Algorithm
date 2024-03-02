package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240130
public class BOJ_S3_15649 {

    static boolean[] visited;
    static int[] result;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int M;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] NM = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        N = NM[0];
        M = NM[1];
        visited = new boolean[N + 1];
        result = new int[M + 1];
        permutation(N, M);
    }

    private static void permutation(int n, int m) {

        if (m == 0) {
            // 결과값 출력
            for (int i = 0; i < M; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            // 방문한 적 없으면
            if (!visited[i]) {
                // 방문하고,
                visited[i] = true;
                // 결과값 반영
                result[M - m] = i;
                // N 개 중에 M개 골라야 하는데 1개 골랐으니, nPm-1
                permutation(N, m - 1);
                visited[i] = false;
            }
        }
    }
}
