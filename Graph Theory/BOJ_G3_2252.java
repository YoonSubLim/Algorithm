package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 240220
public class BOJ_G3_2252 {

    public static void main(String[] args) throws IOException, NumberFormatException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegrees = new int[N];
        List<Integer>[] fromTo = new ArrayList[N];

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // 진입 차수 세팅
        for (int m = 0; m < M; m++) {

            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            inDegrees[to] += 1;

            if (fromTo[from] == null)
                fromTo[from] = new ArrayList<>();
            fromTo[from].add(to);
        }

        // 진입 차수 0이면 deque 추가
        for (int n = 0; n < N; n++) {
            if (inDegrees[n] == 0)
                deque.add(n);
        }

        // 위상정렬
        while (!deque.isEmpty()) {

            int from = deque.poll();

            if (fromTo[from] != null) {
                for (int to: fromTo[from]) {
                    inDegrees[to] -= 1;

                    if (inDegrees[to] == 0)
                        deque.add(to);
                }
            }

            System.out.print((from + 1) + " ");
        }
    }
}
