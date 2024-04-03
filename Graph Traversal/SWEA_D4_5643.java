package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_D4_5643 {

	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, cnt[];
	static ArrayList<Integer>[] big;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			System.out.printf("#%d %d\n", t, makeResult());
		}
	}
	
	private static int makeResult() {
		
		// 모든 노드에 대해 cnt 값 업데이트
		for (int i = 0; i < N; i++) {
			bfs(i);
		}
		
		int result = 0;

		// cnt 값이 자신 제외 N-1 이라면
		for (int i = 0; i < N; i++) {
			if (cnt[i] == N - 1) {
				result += 1;
			}
		}
		
		return result;
	}
	
	// num 번 노드에서 시작해서 연결된 모든 노드를 탐색한다.
	private static void bfs(int num) {
				
		ArrayDeque<Integer> deq = new ArrayDeque<>();
		deq.add(num);
		boolean[] visited = new boolean[N];
		
		while (!deq.isEmpty()) {
			
			int from = deq.poll();
			
			// 연결된 노드를 bfs 로 돌며,
			// cnt[num] 에 1 을 더해, num 뒤에 한 개 노드가 더 있음을 추가하고
			// cnt[to] 에 1 을 더해, to 전에 한 개 노드가 더 있음을 추가한다
			for (int i = 0; i < big[from].size(); i++) {
				int to = big[from].get(i);
				if (visited[to]) continue;
			
				visited[to] = true;
				cnt[num] += 1;
				cnt[to] += 1;
				deq.add(to);
			}
		}
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		cnt = new int[N];

		big = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			big[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			big[from].add(to);
		}		
	}
}
