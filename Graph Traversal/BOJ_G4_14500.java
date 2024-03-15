package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240315
public class BOJ_G4_14500 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, map[][];
    static int maxResult = Integer.MIN_VALUE;
    static int[] dys = {-1, 1, 0, 0};
    static int[] dxs = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, -1, 0, 0); // ㅗ 모양 제외
                checkOther(i, j); // ㅗ 모양
            }
        }
        System.out.println(maxResult);
    }

    /**
     * ㅗ ㅏ ㅓ ㅜ 모양 체크
     * @param r
     * @param c
     */
    private static void checkOther(int r, int c) {

        // i 가 뻗지 않을 방향
        for (int i = 0; i < 4; i++) {
            boolean flag = true;
            int tempSum = map[r][c];

            for (int j = 0; j < 4; j++) {
                if (j == i) continue; // 뻗지 않을 방향을 제외하고 3방을 모두 선택

                int nextR = r + dys[j];
                int nextC = c + dxs[j];

                // 하나라도 조건에 안맞으면 볼 필요 없음
                if (!(0 <= nextR && nextR < N) || !(0 <= nextC && nextC < M)) {
                    flag = false;
                    break;
                }
                tempSum += map[nextR][nextC];
            }

            if (flag) {
                maxResult = Math.max(maxResult, tempSum);
            }
        }
    }

    private static void dfs(int r, int c, int prevDirection, int sum, int depth) {

        if (depth == 4) {
            maxResult = Math.max(maxResult, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            // 직전방향 정반대의 방향 (직전으로 돌아가는 방향)이면 continue
            if (d == (prevDirection ^ 1)) continue;

            int nextR = r + dys[d];
            int nextC = c + dxs[d];

            if (!(0 <= nextR && nextR < N) || !(0 <= nextC && nextC < M)) continue;
            // 직전 방향으로만 가지 않으면, 4번 중 같은 곳 방문할 경우 없기 때문에 visited 체크 X

            dfs(nextR, nextC, d, sum + map[r][c], depth + 1);
        }
    }

    private static void init() throws IOException, NumberFormatException {

        String[] input = br.readLine().split("\\s+");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
    }
}