package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240303
public class BOJ_G2_3109 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int R, C;
    static char[][] map;
    static int[] dys = {-1, 0, 1}; // 최대한 위에서부터 파이프 연결

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        System.out.println(makeResult());
    }

    private static int makeResult() {

        for (int r = 0; r < R; r++) {
            backtracking(r, 0);
        }

        // 마지막 열에 x 표시되어있으면 연결된 파이프의 경우임
        int count = 0;
        for (int r = 0; r < R; r++) {
            if (map[r][C - 1] == 'x')
                count += 1;
        }
        return count;
    }

    /**
     * 최대한 위에 밀착되도록 파이프를 연결한다.
     * 파이프가 지나가는 길에는 x 표시를 하고,
     * 끝까지 연결되지 않았더라도 어차피 못 가는 길이니, x 표시를 둔다.
     * 마지막 열에 도달되었다면, x 표시 됨
     */
    private static boolean backtracking(int r, int c) {

        map[r][c] = 'x';

        if (c == C - 1)
            return true;

        for (int i = 0; i < dys.length; i++) {
            int nextR = r + dys[i];
            if (!(0 <= nextR && nextR < R)) continue;
            if (map[nextR][c + 1] != '.') continue;

            // 경로 Greedy 하게 찾았으면, 다른 경로 더 찾지 않고 리턴
            if (backtracking(nextR, c + 1))
                return true;
        }

        return false;
    }

    private static void init() throws IOException, NumberFormatException {
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][];
        for (int r = 0; r < R; r++) {
            map[r] = String.join("", br.readLine().split("\\s+")).toCharArray();
        }
    }
}
