package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 240311
public class BOJ_G5_16928 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, map[] = new int[101], start = 1;
    static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            map[from] = to;
        }

        bfs();
    }

    private static void bfs() {

        ArrayDeque<Integer> deq = new ArrayDeque<>();
        deq.add(start);
        visited[start] = true;

        int count = 1;

        while (!deq.isEmpty()) {
            int size = deq.size();

            for (int i = 0; i < size; i++) {

                int now = deq.poll();

                for (int next = now + 1; next <= now + 6; next++) {
                    if (next == 100 || map[next] == 100) {
                        System.out.println(count);
                        return;
                    }

                    if (!visited[next]) {
                        visited[next] = true;

                        if (map[next] != 0) {
                            deq.add(map[next]);
                            visited[map[next]] = true;
                        } else {
                            deq.add(next);
                        }
                    }
                }
            }
            count += 1;
        }
    }
}
