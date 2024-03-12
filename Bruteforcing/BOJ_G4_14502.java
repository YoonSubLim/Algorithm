package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 240301
public class BOJ_G4_14502 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static short[][] map;
    static int maxSafeArea = Integer.MIN_VALUE;
    static short[] dys = {-1, 1, 0, 0};
    static short[] dxs = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new short[N][M];

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Short.parseShort(st.nextToken());
            }
        }

        combination(0);
        System.out.println(maxSafeArea);
    }

    private static void combination(int cnt) {

        if(cnt == 3) {
            // 3개의 Point 에 벽을 세웠을 때의, 안전구역 구하기
            maxSafeArea = Math.max(maxSafeArea, getSafeArea());
            return;
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) {
                    map[r][c] = 1;
                    combination(cnt + 1);
                    map[r][c] = 0;
                }
            }
        }
    }

    private static int getSafeArea() {

        short[][] copyMap = new short[N][];
        for (int i = 0; i < N; i++) {
            copyMap[i] = map[i].clone();
        }

        // 바이러스
        visited = new boolean[N][M];
        for (short n = 0; n < N; n++) {
            for (short m = 0; m < M; m++) {
                if (!visited[n][m] && map[n][m] == 2) {
                    bfs(copyMap, new short[]{n, m});
                }
                visited[n][m] = true;
            }
        }

        int safeAreaCnt = 0;
        for (int n = 0; n < N; n++) {
            for (int m = 0; m < M; m++) {
                if (copyMap[n][m] == 0) {
                    safeAreaCnt += 1;
                }
            }
        }

        return safeAreaCnt;
    }

    private static void bfs(short[][] map, short[] location) {

        ArrayDeque<short[]> locations = new ArrayDeque<>();
        locations.add(location);

        while(!locations.isEmpty()) {
            short[] curLocation = locations.poll();
            short r = curLocation[0];
            short c = curLocation[1];

            for (short i = 0; i < 4; i++) {
                short nextR = (short) (r + dys[i]);
                short nextC = (short) (c + dxs[i]);

                if (!(0 <= nextR && nextR < N)) continue;
                if (!(0 <= nextC && nextC < M)) continue;
                if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = 2;
                    visited[nextR][nextC] = true;
                    locations.add(new short[]{nextR, nextC});
                }
            }
        }
    }
}
