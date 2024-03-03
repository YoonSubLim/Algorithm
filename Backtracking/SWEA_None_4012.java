package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240303
public class SWEA_None_4012 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, S[][], minDiff;
    static boolean selected[];
    public static void main(String[] args) throws IOException, NumberFormatException {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            System.out.printf("#%d %d\n", t, makeResult());
        }
    }

    private static int makeResult() throws IOException, NumberFormatException {

        init();
        combination(0, 0);

        return minDiff;
    }

    private static void combination(int cnt, int start) {

        if (cnt == N / 2) {
            minDiff = Math.min(minDiff, Math.abs(getScore(true) - getScore(false)));
            return;
        }

        for (int i = start; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                combination(cnt + 1, i + 1);
                selected[i] = false;
            }
        }
    }

    // selected true -> A의 음식 // false -> B의 음식
    private static int getScore(boolean isSelected) {

        int score = 0;

        for (int i = 0; i < N; i++) {
            if (selected[i] != isSelected) continue;

            for (int j = i + 1; j < N; j++) {
                if (selected[j] != isSelected) continue;
                score += S[i][j] + S[j][i];
            }
        }
        return score;
    }

    private static void init() throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        S = new int[N][];
        minDiff = Integer.MAX_VALUE;
        selected = new boolean[N];

        for (int n = 0; n < N; n++) {
            S[n] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
