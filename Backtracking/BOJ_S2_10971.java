import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240229
public class BOJ_S2_10971 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int totalMinCost = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        map = new int[N][];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
        visited = new boolean[N];

        // 0 번에서 시작해서 0 번으로 돌아오는 경우에서의 최솟값을 찾는다.
        traverse(0, 1, 0);
        visited[0] = true;
        System.out.println(totalMinCost);
    }

    /**
     * city 번호에 연결된, 방문한 적이 없고 연결선이 있는 도시로 비용을 더하면서 넘어간다.
     * 모두 선택된 경우, 시작 지점으로 돌아가는 비용까지 포함하여 값을 업데이트한다.
     * 총 비용이 기존 총 비용보다 커지면 볼 필요가 없는 경로이므로 폐기(return)
     * @param city 이번에 방문한 도시
     * @param selectedCnt 지금까지 방문한 도시 수
     * @param totalCost 지금까지 소요된 총 비용
     */
    private static void traverse(int city, int selectedCnt, int totalCost) {

        // 기존 총 비용보다 현재 총 비용이 크다면 폐기
        if (totalCost >= totalMinCost) return;

        // 모두 선택시 값 갱신. 위 코드로, 무조건 갱신해야 하는 경우이다
        if (selectedCnt == N) {
            totalMinCost = totalCost;
        }

        // 시작 도시 (0) 을 제외한 나머지 도시들을 traverse
        // 마지막 도시를 방문하기 직전 도시에서는, 마지막 도시에서 시작 도시로 이어지는지를 판단하여 가지치기 한다
        for (int i = 1; i < N; i++) {
            if (!visited[i] && map[city][i] != 0) {
                visited[i] = true;
                if (selectedCnt == N - 1) {
                    if (map[i][0] == 0) return;
                    // 시작점으로 돌아가는 선까지 더해 호출한다
                    traverse(i, selectedCnt + 1, totalCost + map[city][i] + map[i][0]);
                } else {
                    traverse(i, selectedCnt + 1, totalCost + map[city][i]);
                }
                visited[i] = false;
            }
        }
    }
}
