package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240309
public class BOJ_G3_1600 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, W, H, map[][];
    static boolean[][][] visited;
    static int[] dys = {0, 1, 0, -1, 1, 1, 2, 2, -1, -1, -2, -2};
    static int[] dxs = {1, 0, -1, 0, 2, -2, 1, -1, 2, -2, 1, -1};

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        bfs();
    }

    private static void bfs() {

        if (W == 1 && H == 1) {
            System.out.println(0);
            return;
        }

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{0, 0, K});
        visited[0][0][K] = true;

        int time = 1;

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                int[] curPoint = deque.poll();

                for (int d = 0; d < dys.length; d++) {
                    int nextR = curPoint[0] + dys[d];
                    int nextC = curPoint[1] + dxs[d];
                    int curK = curPoint[2];

                    if (d >= 4 && curK == 0)
                        break;

                    if (!(0 <= nextR && nextR < H) || !(0 <= nextC && nextC < W) || map[nextR][nextC] == 1)
                        continue;

                    if (nextR == H - 1 && nextC == W - 1) {
                        System.out.println(time);
                        return;
                    }

                    if(d >= 4 && !visited[nextR][nextC][curK - 1]) {
                        deque.add(new int[]{nextR, nextC, curK - 1});
                        visited[nextR][nextC][curK - 1] = true;
                    } else if (d < 4 && !visited[nextR][nextC][curK]){
                        deque.add(new int[]{nextR, nextC, curK});
                        visited[nextR][nextC][curK] = true;
                    }
                }
            }
            time += 1;
        }

        System.out.println(-1);
    }

    private static void init() throws IOException, NumberFormatException {

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][];
        for (int i = 0; i < H; i++) {
            map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[H][W][K + 1];
    }
}
