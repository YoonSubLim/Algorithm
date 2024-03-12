package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_G5_17070 {

    static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Pipe {
        Position head, tail;
        int diagonal;

        public Pipe(Position head, Position tail, int diagonal) {
            this.head = head;
            this.tail = tail;
            this.diagonal = diagonal;
        }
    }
    static final int DIAGONAL = 0;
    static final int HORIZONTAL = 1;
    static final int VERTICAL = 2;
    static int[][][] visited; // (r, c) 의 가로,세로,대각선 으로 Pipe 의 Head 가 위치했을 때의 dp 값

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new int[N][N][3]; // 0 : diagonal 1 : horizontal 2 : vertical // head 기준 방문여부 체크
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        Pipe pipe = new Pipe(new Position(0, 1), new Position(0, 0), HORIZONTAL); // 가로 기준으로 시작
        System.out.println(getTotalRouteCount(pipe));
    }

    public static int getTotalRouteCount(Pipe startPipe) {

        Position head = startPipe.head;

        if (visited[head.r][head.c][startPipe.diagonal] != 0)
            return visited[head.r][head.c][startPipe.diagonal];

        // 끝에 도달해 있는 위치라면 1 리턴
        if (head.r == N - 1 && head.c == N - 1)
            return 1;

        int routeCnt = 0; // 현재 위치에 저장할 dp 값

        // 대각선 방향 진행
        if (head.r + 1 < N && head.c + 1 < N) {
            if (map[head.r + 1][head.c] != 1 && map[head.r][head.c + 1] != 1 && map[head.r + 1][head.c + 1] != 1) {
                routeCnt += getTotalRouteCount(new Pipe(new Position(head.r + 1, head.c + 1), head, DIAGONAL));
            }
        }
        // 가로가 아닐 때(세로 or 대각선 일 때), 세로 방향 진행
        if (startPipe.diagonal != HORIZONTAL && head.r + 1 < N && map[head.r + 1][head.c] != 1) {
            routeCnt += getTotalRouteCount(new Pipe(new Position(head.r + 1, head.c), head, VERTICAL));
        }
        // 세로가 아닐 때(가로 or 대각선 일 때), 가로 방향 진행
        if (startPipe.diagonal != VERTICAL && head.c + 1 < N && map[head.r][head.c + 1] != 1) { // 세로
            routeCnt += getTotalRouteCount(new Pipe(new Position(head.r, head.c + 1), head, HORIZONTAL));
        }
        return visited[head.r][head.c][startPipe.diagonal] = routeCnt;
    }
}
