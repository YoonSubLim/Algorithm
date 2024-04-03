package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240324
public class BOJ_S2_15663 {

    static int N, M, nums[], selected[];
    static boolean[] checked;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        checked = new boolean[N];
        selected = new int[M];

        Arrays.sort(nums);
        dfs(0);
    }

    private static void dfs(int cnt) {
        if (cnt == M) {

            StringBuilder sb = new StringBuilder();
            for (int num: selected) {
                sb.append(num).append(' ');
            }
            System.out.println(sb.toString());
            return;
        }

        int before = 0;
        for (int i = 0; i < N; i++) {
            if (!checked[i] && before != nums[i]) {
                checked[i] = true;
                selected[cnt] = nums[i];
                before = nums[i];
                dfs(cnt + 1);
                checked[i] = false;
            }
        }
    }
}
