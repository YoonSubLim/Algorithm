package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 240226
public class BOJ_S3_2606 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static boolean[][] adjMat;
    static boolean[] reached;

    public static void main(String[] args) throws IOException, NumberFormatException {

        int result = 0;

        int N = Integer.parseInt(br.readLine());
        adjMat = new boolean[N][N];
        reached = new boolean[N];

        int P = Integer.parseInt(br.readLine());

        for (int p = 0; p < P; p++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            adjMat[a][b] = adjMat[b][a] = true;
        }

        Queue<Integer> deque = new ArrayDeque<>();

        deque.add(0);
        reached[0] = true;

        while (!deque.isEmpty()) {

            int nextPersonIdx = deque.poll();

            for (int i = 0; i < N; i++) {
                if (adjMat[nextPersonIdx][i] && !reached[i]) {
                    result += 1;
                    deque.add(i);
                    reached[i] = true;
                }
            }
        }

        System.out.println(result);
    }
}

