package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 240331
public class BOJ_G4_4485 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, map[][], dist[][];
    static boolean visited[][];
    static int[] dys = {0, 0, -1, 1};
    static int[] dxs = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException, NumberFormatException {

        int probNum = 0;
        while ((N = Integer.parseInt(br.readLine())) != 0) {

            map = new int[N][];
            dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            visited = new boolean[N][N];

            System.out.printf("Problem %d: %d\n", ++probNum, makeResult());
        }
    }

    private static int makeResult() {

        PriorityQueue<int[]> nodes = new PriorityQueue<>((n1, n2) -> Integer.compare(n1[2], n2[2]));

        dist[0][0] = map[0][0];
        nodes.add(new int[]{0, 0, dist[0][0]});

        while (!nodes.isEmpty()) {

            int[] curNode = nodes.poll();
            int curY = curNode[0];
            int curX = curNode[1];

            for (int i = 0; i < 4; i++) {
                int nextY = curY + dys[i];
                int nextX = curX + dxs[i];

                if (!(0 <= nextY && nextY < N) || !(0 <= nextX && nextX < N) || visited[nextY][nextX]) continue;

                // 연결된 노드의 거리 값 업데이트
                if (dist[nextY][nextX] > dist[curY][curX] + map[nextY][nextX]) {
                    dist[nextY][nextX] = dist[curY][curX] + map[nextY][nextX];
                    nodes.add(new int[] {nextY, nextX, dist[nextY][nextX]});
                }
            }
            // 현재 노드 방문 처리
            visited[curY][curX] = true;
        }

        return dist[N - 1][N - 1];
    }
}
