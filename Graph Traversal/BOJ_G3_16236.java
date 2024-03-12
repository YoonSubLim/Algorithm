package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// 240308
public class BOJ_G3_16236 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, fishCount, map[][], curR, curC, sharkSize, totalDistance;
    static int[] dys = {-1, 1, 0, 0};
    static int[] dxs = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        int toEatCnt = sharkSize;
        while(true) {
            int distance = bfs();

            if (distance == 0)
                break;
            toEatCnt -= 1;
            if (toEatCnt == 0) {
                toEatCnt = sharkSize += 1;
            }
            totalDistance += distance;
        }
        System.out.println(totalDistance);
    }

    static private int bfs() {

        boolean[][] visited = new boolean[N][N];
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        List<int[]> candidates = new ArrayList<>();

        deque.add(new int[]{curR, curC});
        visited[curR][curC] = true;

        int dist = 0;
        while(!deque.isEmpty()) {
            int cnt = deque.size();
            dist += 1;
            for (int d = 0; d < cnt; d++) {
                int[] current = deque.poll();

                for (int i = 0; i < 4; i++) {
                    int nextR = current[0] + dys[i];
                    int nextC = current[1] + dxs[i];

                    if (!(0 <= nextR && nextR < N))
                        continue;
                    if (!(0 <= nextC && nextC < N))
                        continue;
                    if (visited[nextR][nextC])
                        continue;

                    if (map[nextR][nextC] <= sharkSize) {
                        visited[nextR][nextC] = true;
                        deque.add(new int[]{nextR, nextC});

                        if (0 < map[nextR][nextC] && map[nextR][nextC] < sharkSize)
                            candidates.add(new int[]{nextR, nextC});
                    }
                }
            }

            if (!candidates.isEmpty())
                break;
        }

        if (candidates.isEmpty())
            return 0;

        candidates.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        eatAndMove(candidates.get(0));

        return dist;
    }

    private static void eatAndMove(int[] toEat) {
        map[curR][curC] = 0;
        curR = toEat[0];
        curC = toEat[1];
        map[curR][curC] = 9;
    }

    static private void init() throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        sharkSize = 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num != 0 && num != 9) {
                    fishCount += 1;
                } else if (num == 9) {
                    curR = i;
                    curC = j;
                }

                map[i][j] = num;
            }
        }
    }
}
