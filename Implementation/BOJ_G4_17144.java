package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240322
public class BOJ_G4_17144 {

    static BufferedReader br;
    static StringTokenizer st;
    static int R, C, T, map[][], top, bottom;
    static int[] dys = {0, 0, -1, 1};
    static int[] dxs = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        for (int i = 0; i < T; i++) {
            spread();
            rotateTop();
            rotateBottom();
        }
        System.out.println(getTotal());
    }

    private static int getTotal() {
        int total = 0;
        for (int[] row: map) {
            for (int num: row) {
                if (num == -1 || num == 0) continue;
                total += num;
            }
        }
        return total;
    }

    private static void spread() {
        int[][] nextMap = new int[R][C];
        nextMap[top][0] = nextMap[bottom][0] = -1;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0 || map[i][j] == -1)
                    continue;

                int count = 0; // 차감할 개수 (퍼져나갈 수 있는 영역의 수)

                for (int d = 0; d < 4; d++) {
                    int nextR = i + dys[d];
                    int nextC = j + dxs[d];
                    if (!(0 <= nextR && nextR < R) || !(0 <= nextC && nextC < C) || map[nextR][nextC] == -1)
                        continue;

                    count += 1;
                    nextMap[nextR][nextC] += map[i][j] / 5;
                }
                nextMap[i][j] += map[i][j] - (map[i][j] / 5) * count;
            }
        }
        map = nextMap; // 퍼져나간 맵 설정
    }

    // 공기 청정기 반시계 동작
    private static void rotateTop() {
        // 왼쪽 세로선 아래로 복사
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        // 위쪽 가로선 왼쪽으로 복사
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        // 오른쪽 세로선 위로 복사
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        // 아래쪽 가로선 오른쪽으로 복사
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }

        // 깨끗한 공기
        map[top][1] = 0;
    }

    // 공기 청정기 반시계 동작
    private static void rotateBottom() {
        // 왼쪽 세로선 위로 복사
        for (int i = bottom + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        // 아래쪽 가로선 왼쪽으로 복사
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }

        // 오른쪽 세로선 아래로 복사
        for (int i = R - 1; i > bottom; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        // 위쪽 가로선 오른쪽으로 복사
        for (int i = C - 1; i > 1; i--) {
            map[bottom][i] = map[bottom][i - 1];
        }

        // 깨끗한 공기
        map[bottom][1] = 0;
    }

    private static void init() throws IOException, NumberFormatException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                top = i;
                bottom = i+1;
                break;
            }
        }
    }
}

