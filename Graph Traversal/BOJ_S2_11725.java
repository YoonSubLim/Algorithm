package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 240321
public class BOJ_S2_11725 {

    public static void main(String[] args) throws IOException, NumberFormatException {

        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] adjList = new ArrayList[N];

        for (int i = 0; i < N - 1; i++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (adjList[a - 1] == null)
                adjList[a - 1] = new ArrayList<>();
            if (adjList[b - 1] == null)
                adjList[b - 1] = new ArrayList<>();

            adjList[a - 1].add(b);
            adjList[b - 1].add(a);
        }

        // Main Logic
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        boolean[] isChecked = new boolean[N];
        int[] parent = new int[N];
        deque.add(1);
        isChecked[0] = true;

        while (!deque.isEmpty()) {
            int parentNum = deque.poll();
            for (int childNum: adjList[parentNum - 1]) {
                if (isChecked[childNum - 1]) {
                    continue;
                }
                isChecked[childNum - 1] = true;
                parent[childNum - 1] = parentNum;
                deque.add(childNum);
            }
        }

        for (int i = 1; i < N; i++) {
            System.out.println(parent[i]);
        }
    }
}
