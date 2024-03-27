package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240327
public class BOJ_G4_2239 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int sudoku[][] = new int[9][];
    static boolean isEnd;

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 9; i++) {
            sudoku[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt)
                .toArray();
        }

        dfs(0, 0);

        br.close();
    }

    private static void dfs(int sy, int sx) {

        int ny = -1;
        int nx = -1;

        // TODO 안봐도 되는, sy 라인의 sx 의 이전 값들을 확인하고 있음
        for (int i = sy; i < 9 && ny == -1; i++) {
            for (int j = 0; j < 9 && nx == -1; j++) {
                if (sudoku[i][j] == 0) {
                    ny = i;
                    nx = j;
                }
            }
        }

        // 다음으로 확인해볼 값이 없으면 => 다 찾음
        if (ny == -1) {
            printSudoku();
            isEnd = true;
            return;
        }

        for (int num = 1; num <= 9 && !isEnd; num++) {
            if (validate(ny, nx, num)) {
                sudoku[ny][nx] = num;
                dfs(ny, nx);
                sudoku[ny][nx] = 0;
            }
        }
    }

    private static void printSudoku() {
        for (int[] row: sudoku) {
            for (int num: row) {
                System.out.print(num);
            }
            System.out.println();
        }
    }

    private static boolean validate(int y, int x, int num) {
        return validateVertical(y, x, num) && validateHorizontal(y, x, num) && validateSection(y, x, num);
    }

    private static boolean validateVertical(int y, int x, int num) {
        for (int i = 0; i < 9; i++) {
            if (i != y && sudoku[i][x] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateHorizontal(int y, int x, int num) {
        for (int i = 0; i < 9; i++) {
            if (i != x && sudoku[y][i] == num) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateSection(int y, int x, int num) {

        for (int i = y - y % 3; i < y - y % 3 + 3; i++) {
            for (int j = x - x % 3; j < x - x % 3 + 3; j++) {
                if (i == y && j == x) continue;
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
