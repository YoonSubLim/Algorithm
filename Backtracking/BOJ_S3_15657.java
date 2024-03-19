package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240319
public class BOJ_S3_15657 {

    static int N, M, nums[], selected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        String[] input = br.readLine().split("\\s+");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        nums = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(nums);
        selected = new int[M];

        dfs(0, 0);
    }

    private static void dfs(int cnt, int start) {

        if (cnt == M) {
            for (int num: selected) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < N; i++) {
            selected[cnt] = nums[i];
            dfs(cnt + 1, i);
        }
    }
}
