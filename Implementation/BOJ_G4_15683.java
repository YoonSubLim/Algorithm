package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 240221
public class BOJ_G4_15683 {

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int M;
    static char[][] map;

    static ArrayList<CCTV> cctvs = new ArrayList<>();

    static int minResult = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        combination(0);
        System.out.println(minResult);
    }

    private static void combination(int cctvIdx) {

        if (cctvIdx == cctvs.size()) {
            updateMinResult();
            return;
        }

        // CCTV 의 방향을 선택
        for (int i = 0; i < cctvs.get(cctvIdx).possibleWayCount; i++) {
            cctvs.get(cctvIdx).direction = Direction.values()[i];
            combination(cctvIdx + 1);
        }
    }

    private static void updateMinResult() {

        // MAP 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '#')
                    map[i][j] = '0';
            }
        }

        // CCTV 객체가 방향에 맞게 맵을 커버한다
        for (CCTV cctv: cctvs)
            cctv.coverMap();

        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    count += 1;
                    if (count >= minResult)
                        return;
                }
            }
        }

        minResult = count;
    }

    private static void init() throws IOException, NumberFormatException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if ('1' <= map[i][j] && map[i][j] <= '5') {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
    }

    static class CCTV {
        int r;
        int c;
        char type;
        int possibleWayCount;
        Direction direction;

        CCTV(int r, int c, char type) {
            this.r = r;
            this.c = c;
            this.type = type;

            if (type == 2) possibleWayCount = 2;
            else if (type == 5) possibleWayCount = 1;
            else possibleWayCount = 4;
        }

        public void coverMap() {

            switch (type) {
                case '1':
                    if (direction == Direction.UP || direction == Direction.DOWN) coverMapVertical(direction);
                    else if (direction == Direction.LEFT || direction == Direction.RIGHT) coverMapLinear(direction);
                    break;
                case '2':
                    if (direction == Direction.UP || direction == Direction.DOWN) {
                        coverMapVertical(direction);
                        coverMapVertical(direction.getOpposite());
                    } else if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                        coverMapLinear(direction);
                        coverMapLinear(direction.getOpposite());
                    }
                    break;
                case '3':
                    if (direction == Direction.UP || direction == Direction.DOWN) {
                        coverMapVertical(direction);
                        coverMapLinear(direction.getNext());
                    } else if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                        coverMapLinear(direction);
                        coverMapVertical(direction.getNext());
                    }
                    break;
                case '4':
                    if (direction == Direction.UP || direction == Direction.DOWN) {
                        coverMapLinear(direction.getPrev());
                        coverMapVertical(direction);
                        coverMapLinear(direction.getNext());
                    } else if (direction == Direction.LEFT || direction == Direction.RIGHT) {
                        coverMapVertical(direction.getPrev());
                        coverMapLinear(direction);
                        coverMapVertical(direction.getNext());
                    }
                    break;
                case '5':
                    coverMapLinear(Direction.LEFT);
                    coverMapLinear(Direction.RIGHT);
                    coverMapVertical(Direction.UP);
                    coverMapVertical(Direction.DOWN);
                    break;
                default:
                    break;
            }
        }

        private void coverMapLinear(Direction direction) {

            int dir;
            if (direction == Direction.LEFT) dir = -1;
            else if (direction == Direction.RIGHT) dir = 1;
            else return;

            int curC = c + dir;

            while (0 <= curC && curC < M) {
                if (map[this.r][curC] == '6') break;
                if (map[this.r][curC] == '0') map[this.r][curC] = '#';
                curC += dir;
            }
        }

        private void coverMapVertical(Direction direction) {

            int dir;
            if (direction == Direction.UP) dir = -1;
            else if (direction == Direction.DOWN) dir = 1;
            else return;

            int curR = r + dir;

            while (0 <= curR && curR < N) {
                if (map[curR][this.c] == '6') break;
                if (map[curR][this.c] == '0') map[curR][this.c] = '#';
                curR += dir;
            }
        }
    }

    static enum Direction {
        UP, LEFT, DOWN, RIGHT;

        public Direction getOpposite() {
            switch (this) {
                case UP:
                    return DOWN;
                case DOWN:
                    return DOWN;
                case LEFT:
                    return RIGHT;
                case RIGHT:
                    return LEFT;

                default:
                    return null;
            }
        }
        public Direction getNext() {
            switch (this) {
                case UP:
                    return LEFT;
                case LEFT:
                    return DOWN;
                case DOWN:
                    return RIGHT;
                case RIGHT:
                    return UP;

                default:
                    return null;
            }
        }

        public Direction getPrev() {
            switch (this) {
                case UP:
                    return RIGHT;
                case RIGHT:
                    return DOWN;
                case DOWN:
                    return LEFT;
                case LEFT:
                    return UP;

                default:
                    return null;
            }
        }
    }
}
