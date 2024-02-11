import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240211
public class BOJ_G5_7569 {

    static int M;
    static int N;
    static int H;
    static int[][][] tomatos;

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomatos = new int[H][N][];

        // map setting
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                tomatos[h][n] = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
            }
        }

        // main logic
        int result = getDayForAffectAllTomatos();

        // 토마토가 모두 익지 못하는 상황이라면 -1 출력
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatos[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(result);
    }

    // 토마토 객체
    static class Tomato {
        int M;
        int N;
        int H;
        int day; // 감염된 일수

        Tomato(int M, int N, int H, int day) {
            this.M = M;
            this.N = N;
            this.H = H;
            this.day = day;
        }
    }

    public static int getDayForAffectAllTomatos() {

        ArrayDeque<Tomato> tomatoDeq = new ArrayDeque<>();
        int result = -1;

        // 초기 세팅. 1인 경우의 토마토를 해당 좌표와 소요일자 0 으로 추가한다
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatos[h][n][m] == 1) {
                        tomatoDeq.add(new Tomato(m, n, h, 0));
                    }
                }
            }
        }

        // 덱이 비어있을 때까지, 토마토를 꺼내어 날짜를 결과에 세팅하고, 해당 토마토에 인접한 토마토를 익게한다
        while (!tomatoDeq.isEmpty()) {
            Tomato curTomato = tomatoDeq.removeFirst();
            result = curTomato.day;

            affectOtherTomatos(tomatoDeq, curTomato); // 인접한 토마토 익게 만들기
        }

        // 모든 토마토가 익어있었다면 0, 모든 토마토가 익어있었다면 -1을 리턴한다
        return result;
    }

    // 인접한 토마토가 익을 수 있으면 소요일자를 1 추가하여 토마토 덱에 추가한다
    static void affectOtherTomatos(ArrayDeque<Tomato> tomatoDeq, Tomato curTomato) {

        int[] delH = {0, 0, 0, 0, 1, -1};
        int[] delN = {0, 0, 1, -1, 0, 0};
        int[] delM = {1, -1, 0, 0, 0, 0};

        for (int i = 0; i < delH.length; i++) {

            int nextH = curTomato.H + delH[i];
            int nextN = curTomato.N + delN[i];
            int nextM = curTomato.M + delM[i];

            if (nextH < 0 || nextH > H - 1) continue;
            if (nextN < 0 || nextN > N - 1) continue;
            if (nextM < 0 || nextM > M - 1) continue;

            if (tomatos[nextH][nextN][nextM] != 0) continue;

            tomatos[nextH][nextN][nextM] = curTomato.day + 1; // 모든 토마토가 익지는 않은 경우를 체크하기 위해, 배열값도 변경
            tomatoDeq.add(new Tomato(nextM, nextN, nextH, curTomato.day + 1));
        }
    }
}

        /*
        int result = -1;

        int day = 1;
        // 익어있는 상태 1을 day 1 (시작 날짜)라 가정하고, 1인 경우 인접한 모든 토마토를 익게하고 그 토마토는 다음 day 인 2로 저장한다
        // day 2 에서는 2인 토마토들만을 대상으로 이 과정을 반복하고, 해당 날짜를 result 에 저장한다.
        // 아무 토마토도 익지 않은 경우 -1 을 리턴받고, while 문 break
        while (true) {
            int nextDay = getNextDay(day++);

            if (nextDay == -1)
                break;
            result = nextDay;
        }

        // 익지 않은 토마토가 존재하는 경우 -1 리턴
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatos[h][n][m] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        // 모두 익었지만, 저장되어있을 때부터 익어있는 경우
        if (result == -1) {
            System.out.println(0);
            return;
        }

        // 그 외 정상적으로 토마토가 모두 익은 경우, 시작날짜가 1이었으므로 -1 하여 소요 날짜를 출력
        System.out.println(result - 1);
    }

    // 영향을 미칠 토마토의 숫자 (day) 를 입력받아, 해당되는 토마토의 인접한 토마토들을 익게 한다.
    private static int getNextDay(int startDay) {

        int nextDay = -1;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomatos[h][n][m] == startDay && affectOtherTomatos(h, n, m))
                        nextDay = startDay + 1;
                }
            }
        }

        return nextDay;
    }

    // 인접한 토마토들을 익게 만들고, 영향을 끼친 토마토가 있는지 여부를 boolean 으로 리턴한다.
    private static boolean affectOtherTomatos(int h, int n, int m) {

        int[] delH = {0, 0, 0, 0, 1, -1};
        int[] delN = {0, 0, 1, -1, 0, 0};
        int[] delM = {1, -1, 0, 0, 0, 0};

        int nextDay = tomatos[h][n][m] + 1;
        boolean isAffected = false;

        for (int i = 0; i < delM.length; i++) {

            int nextH = h + delH[i];
            int nextN = n + delN[i];
            int nextM = m + delM[i];

            if (nextH < 0 || nextH > H - 1) continue;
            if (nextN < 0 || nextN > N - 1) continue;
            if (nextM < 0 || nextM > M - 1) continue;

            if (tomatos[nextH][nextN][nextM] != 0) continue;

            tomatos[nextH][nextN][nextM] = nextDay;
            isAffected = true;
        }

        return isAffected;
    }
}
*/