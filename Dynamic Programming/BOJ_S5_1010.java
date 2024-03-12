package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240228
public class BOJ_S5_1010 {

    static int[][] C;

    public static void main(String[] args) throws IOException, NumberFormatException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            R = Math.min(R, N - R); // 메모리 절감용 코드. nCr 와 nCn-r 중 배열의 크기를 더 적게 쓰는 경우로 계산한다.

            System.out.println(comb(N, R));
        }
    }

    private static int comb(int N, int R) {

        C = new int[N + 1][R + 1];

        for (int i = 0; i <= N; i++) {
            // j 의 시작점도 고려함
            // 불필요한 시작점 제외

            // 방법 1
//			for (int j = (i <= (N - R)? 0 : i - (N - R)), end = Math.min(i, R); j <= end; j++) {
            // 방법 2
            for (int j = Math.max(i - (N - R), 0), end = Math.min(i, R); j <= end; j++) {
                if (j == 0 || j == i) {
                    C[i][j] = 1;
                } else {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[N][R];
    }
}
