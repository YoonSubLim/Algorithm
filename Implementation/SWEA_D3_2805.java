package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240130
public class SWEA_D3_2805 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(br.readLine());
            int middle = N / 2;
            int total = 0;

            int[][] map = new int[N][];

            // map setting
            for (int n = 0; n < N; n++) {
                map[n] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            }

            // main logic
            for (int i = 0; i < N; i++) {
                // 왼쪽 값 더하기 down top
                for (int l = Math.abs(middle - i); l < middle; l++) {
                    total += map[i][l];
                }
                // 가운데 값 더하기
                total += map[i][middle];
                // 오른쪽 값 더하기 top down
                for (int r = 2 * middle - Math.abs(i - middle); r > middle; r--) {
                    total += map[i][r];
                }
            }

            System.out.printf("#%d %d\n", t, total);
        }
    }

}
