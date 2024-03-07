package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_G4_2636 {

    static class Point {
        int r;
        int c;
        Point (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static BufferedReader br;
    static StringTokenizer st;

    static int[][] map;
    static boolean[][] visited;

    static int R;
    static int C;

    static int[] dys = {0, 0, -1, 1};
    static int[] dxs = {-1, 1, 0, 0};

    static int beforeCount;

    static List<Point> meltingPoints;

    public static void main(String[] args) throws IOException, NumberFormatException {

        br = new BufferedReader(new InputStreamReader(System.in));
        mapInit();
        makeResult();
    }

    /**
     * 가장자리에 속하는 0,0 에서 0만을 dfs 로 탐색하며, 인접한 모든 치즈의 좌표를 '녹을 치즈 리스트'에 저장함
     * 녹을 치즈 리스트의 좌표값을 0으로 수정하여 녹이고, 시간 += 1 && 방문 배열, 치즈 리스트 초기화하여 녹을 치즈가 없을 때까지 반복한다
     */
    private static void makeResult() {

        int time = 0;
        int beforeDeleteCount = 0;

        while (true) {
            meltingPoints = new ArrayList<Point>();
            visited = new boolean[R][C];

            dfs(0, 0);

            if(meltingPoints.isEmpty())
                break;

            for (Point point: meltingPoints)
                map[point.r][point.c] = 0;

            beforeCount -= beforeDeleteCount;
            beforeDeleteCount = meltingPoints.size();

            time += 1;
        }

        System.out.println(time);
        System.out.println(beforeCount);
    }

    // 공기 중을 dfs 로 탐색하며 인접한 치즈의 좌표는 리스트로 저장한다.
    private static void dfs(int r, int c) {

        for (int i = 0; i < dys.length; i++) {
            int nextR = r + dys[i];
            int nextC = c + dxs[i];

            if (!(0 <= nextR && nextR < R)) continue;
            if (!(0 <= nextC && nextC < C)) continue;
            if (visited[nextR][nextC]) continue;

            visited[nextR][nextC] = true;

            if (map[nextR][nextC] == 1) {
                meltingPoints.add(new Point(nextR, nextC));
                continue;
            }

            dfs(nextR, nextC);
        }
    }

    private static void mapInit() throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int cheeseCount = 0;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1)
                    cheeseCount += 1;
            }
        }
        beforeCount = cheeseCount;
    }
}
