package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240306
public class BOJ_G4_17281 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, scores[][];
    static boolean selected[];
    static int selectedPlayer[];
    static int maxScore = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        perm(0);
        System.out.println(maxScore);
    }

    private static void perm(int cnt) {

        if (cnt == 9) {
            updateMaxScore();
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                if (cnt != 3)
                    selectedPlayer[cnt] = i;
                else
                    selectedPlayer[++cnt] = i;
                perm(cnt + 1);
                selected[i] = false;
            }
        }
    }

    private static void updateMaxScore() {

        int caseResult = 0;
        int curPlayerIdx = 0; // 시작 플레이어 타순

        // N 이닝 동안 반복
        for (int e = 0; e < N; e++) {
            int outCount = 0;
            boolean base[] = new boolean[3];

            while (outCount != 3) {
                int curPlayerNum = selectedPlayer[curPlayerIdx]; // 타순의 선수 번호
                curPlayerIdx = (curPlayerIdx + 1) % 9; // 다음을 위해 타순 업데이트
                int playerResult = scores[e][curPlayerNum]; // 선수 번호의 결과

                // 아웃
                if (playerResult == 0) {
                    outCount++;
                    continue;
                }

                switch (playerResult) {
                    case 1:
                        if (base[2])
                            caseResult += 1;
                        base[2] = base[1];
                        base[1] = base[0];
                        base[0] = true;
                        break;
                    case 2:
                        for (int i = 1; i < 3; i++) {
                            if (base[i])
                                caseResult += 1;
                        }
                        base[2] = base[0];
                        base[1] = true;
                        base[0] = false;
                        break;
                    case 3:
                        for (int i = 0; i < 3; i++) {
                            if (base[i])
                                caseResult += 1;
                        }
                        base[0] = base[1] = false;
                        base[2] = true;
                        break;
                    case 4:
                        for (int i = 0; i < 3; i++) {
                            if (base[i])
                                caseResult += 1;
                            base[i] = false;
                        }
                        caseResult += 1;
                        break;
                }

                // 시간 초과

//                // 그 외 진루
//                bases.add(1);
//                while (playerResult - 1 > 0) {
//                    bases.add(0);
//                    playerResult -= 1;
//                }
//
//                // 홈인
////                caseResult = bases.subList(3, bases.size()).stream().mapToInt(Integer::intValue).sum();
//                while (bases.size() > 3) {
//                    caseResult += bases.remove(0);
//                }
            }
        }

        maxScore = Math.max(maxScore, caseResult);
    }

    private static void init() throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        scores = new int[N][9];
        for (int i = 0; i < N; i++) {
            scores[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
        selected = new boolean[9];
        selectedPlayer = new int[9];

        // 4번 타자 세팅
        selected[0] = true;
        selectedPlayer[3] = 0;
    }
}
