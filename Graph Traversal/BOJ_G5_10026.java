package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 240212
public class BOJ_G5_10026 {

    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException, NumberFormatException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][];

        for (int i = 0; i < N; i++)
            map[i] = br.readLine().toCharArray();

        System.out.println(bfs(false) + " " + bfs(true));
    }

    static class Point {
        int r;
        int c;
        char color;

        Point(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }

    static int[] delR = {0, 0, 1, -1};
    static int[] delC = {1, -1, 0, 0};

    static int bfs(boolean isColorBlind) {
        int result = 0;
        boolean[][] visited = new boolean[N][N];

        ArrayDeque deque = new ArrayDeque();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!visited[i][j]) {
                    deque.add(new Point(i, j, map[i][j]));
                    result++;
                }

                while (!deque.isEmpty()) {

                    Point curPoint = (Point) deque.removeFirst();

                    for (int d = 0; d < 4; d++) {

                        int nextR = curPoint.r + delR[d];
                        int nextC = curPoint.c + delC[d];

                        if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N || visited[nextR][nextC]) continue;

                        if (isColorBlind) {
                            if ((curPoint.color == 'R' || curPoint.color == 'G') && (map[nextR][nextC] == 'R' || map[nextR][nextC] == 'G')) {
                                deque.addLast(new Point(nextR, nextC, map[nextR][nextC]));
                                visited[nextR][nextC] = true;
                            } else if (curPoint.color == map[nextR][nextC]) {
                                deque.addLast(new Point(nextR, nextC, map[nextR][nextC]));
                                visited[nextR][nextC] = true;
                            }
                        } else if (curPoint.color == map[nextR][nextC]) {
                            deque.addLast(new Point(nextR, nextC, map[nextR][nextC]));
                            visited[nextR][nextC] = true;
                        }
                    }
                }
            }
        }

        return result;
    }
}
