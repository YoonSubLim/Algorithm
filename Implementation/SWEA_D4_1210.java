package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240130
public class SWEA_D4_1210 {

    private static int[][] map = new int[100][];
    private static int curRow = -1;
    private static int curCol = -1;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {

            // 안쓰는 값
            String T = br.readLine();

            // map setting
            for(int i = 0; i < map.length; i++) {
                map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            }

            // 시작라인 번호 찾고 메인로직 메서드 호출
            for(int i = 0; i < map[map.length - 1].length; i++) {
                if (map[map.length - 1][i] == 2) {
                    printStartLineByEndLine(t, i);
                    break;
                }
            }
        }
    }

    /**
     * @param t 출력할 테스트 번호
     * @param endLineNumber 거슬러 올라가기 위한 첫 시작 라인 번호
     */
    private static void printStartLineByEndLine(int t, int endLineNumber) {
        curRow = map.length - 1;
        curCol = endLineNumber;

        while (true) {
            if (isEnd()) {
                System.out.printf("#%d %d\n", t, curCol);
                return;
            }

            // 왼쪽으로 길이 열려있는 경우 갈 수 있는 만큼 왼쪽 이동
            if (canGoLeft()) {
                while (canGoLeft())
                    goLeft();
                // 오른쪽으로 길이 열려있는 경우 갈 수 있는 만큼 오른쪽 이동
            } else if (canGoRight()) {
                while (canGoRight())
                    goRight();
            }
            goUp();
        }
    }

    // check method
    private static boolean isEnd() {
        if (curRow == -1)
            return true;
        return false;
    }
    private static boolean canGoLeft() {
        if (curCol > 0 && map[curRow][curCol - 1] == 1)
            return true;
        return false;
    }
    private static boolean canGoRight() {
        if (curCol < map.length - 1 && map[curRow][curCol + 1] == 1)
            return true;
        return false;
    }

    // move method
    private static void goUp() {
        curRow -= 1;
    }
    private static void goLeft() {
        curCol -= 1;
    }
    private static void goRight() {
        curCol += 1;
    }
}
