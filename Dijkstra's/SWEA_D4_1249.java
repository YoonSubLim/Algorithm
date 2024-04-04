package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 240404
public class SWEA_D4_1249 {
	
	static BufferedReader br;
	static int N, map[][], dist[][];
	static int[] dys = {-1, 1, 0, 0};
	static int[] dxs = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			System.out.printf("#%d %d\n", t, dijkstra());
		}
	}
	
	// PriorityQueue 활용 다익스트라
	private static int dijkstra() {
		
		// int[] -> (row, col, dist[row][col])
		PriorityQueue<int[]> pq = new PriorityQueue<>((n1, n2) -> n1[2] - n2[2]);
		dist[0][0] = 0;
		pq.add(new int[] {0, 0, dist[0][0]});
		
		while (!pq.isEmpty()) {
			
			// 비용이 최소인 노드 찾기
			int[] cur = pq.poll();
			visited[cur[0]][cur[1]] = true; // poll 된 노드는 최소 거리를 보장하므로 방문 처리
			
			for (int d = 0; d < 4; d++) {
				
				int nr = cur[0] + dys[d];
				int nc = cur[1] + dxs[d];
				
				if (!(0 <= nr && nr < N) || !(0 <= nc && nc < N)) continue;
				if (visited[nr][nc]) continue;
				
				// 갱신된 노드들은 pq 에 add
				if (dist[nr][nc] > dist[cur[0]][cur[1]] + map[nr][nc]) {
					dist[nr][nc] = dist[cur[0]][cur[1]] + map[nr][nc];
					pq.add(new int[] {nr, nc, dist[nr][nc]});
				}
			}
		}
		
		return dist[N - 1][N - 1];
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][];
		dist = new int[N][N];
		
		// 거리를 나타내는 배열은 무한대 를 의미하는 int max 값으로 초기화
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		visited = new boolean[N][N];
	}
}

/*
 * 30m
 * 최근에 [BOJ 4485 녹색 옷 입은 애가 젤다지?] 문제를 풀었는데,
 * 같은 문제라서 쉽게 풀 수 있었다
 * 
 * 이동 간에 복구 비용이 최소가 되는 경로를 찾는 거라,
 * 최단 경로 알고리즘 다익스트라 이용해서 품 
 */
