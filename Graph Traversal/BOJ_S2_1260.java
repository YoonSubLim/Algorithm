package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 240216
public class BOJ_S2_1260 {
	
	static StringBuilder sb;
	static int N;
	static int M;
	static int V;
	static boolean[][] connection;
	static boolean[] visited;

	public static void main(String[] args) throws IOException, NumberFormatException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		connection = new boolean[N][N];
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken()) - 1;
			int from = Integer.parseInt(st.nextToken()) - 1;
			
			connection[to][from] = connection[from][to] = true;
		}
		
		visited = new boolean[N];
		visited[V - 1] = true;
		printByDfs(V - 1);
		System.out.println();
		
		visited = new boolean[N];
		visited[V - 1] = true;
		printByBfs(V - 1);
	}
	
	private static void printByDfs(int idx) {
		
		System.out.print((idx + 1) + " ");
		
		for (int i = 0; i < N; i++) {
			if (connection[idx][i] && !visited[i]) {
				visited[i] = true;
				printByDfs(i);
			}
		}
	}
	
	private static void printByBfs(int idx) {
		
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.add(idx);
		
		while (!deque.isEmpty()) {
			
			int curIdx = deque.removeFirst();
			System.out.print((curIdx + 1) + " ");
			
			for (int i = 0; i < N; i++) {
				if (connection[curIdx][i] && !visited[i]) {
					visited[i] = true;
					deque.addLast(i);
				}
			}
		}
	}
}
