package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240317
public class BOJ_S3_15654 {

    static int N, M, numbers[], selectedNums[];
    static boolean selected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        selectedNums = new int[M];
        selected = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(numbers);

        dfs(0);
    }

    private static void dfs(int cnt) {

        if (cnt == M) {
            for (int num: selectedNums) {
                System.out.print(num + " ");
            }
            System.out.println();

            return;
        }

        for (int i = 0; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                selectedNums[cnt] = numbers[i];
                dfs(cnt + 1);
                selected[i] = false;
            }
        }
    }
}
