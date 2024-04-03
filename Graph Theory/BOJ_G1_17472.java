package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240329
public class BOJ_G1_17472 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, map[][], islandCnt, parents[];
	static boolean connected[];
	static ArrayList<int[]> edges;

	static int[] dys = {-1, 1, 0, 0};
	static int[] dxs = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		init();
		kruskal();
	}

	/**
	 * 모두 연결될 수 있는 상황에서, 최소신장트리를 구성한다
	 */
	private static void kruskal() {

		// 연결되지 않은 섬이 있다면
		for (int i = 1; i <= islandCnt; i++) {
			if (!connected[i]) {
				System.out.println(-1);
				return;
			}
		}

		makeSet();
		edges.sort((o1, o2) -> Integer.compare(o1[2], o2[2]));

		int cnt = 0;
		int weight = 0;
		for (int[] edge: edges) {
			// from-to union
			if (!union(edge[0], edge[1])) continue;
			weight += edge[2];

			if (++cnt == islandCnt - 1) break;
		}

		if (cnt == islandCnt - 1)
			System.out.println(weight);
		else
			System.out.println(-1);
	}

	private static void makeSet() {
		parents = new int[islandCnt + 1];
		for (int i = 1; i <= islandCnt; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		if (find(a) == find(b)) return false;

		parents[find(b)] = find(a); // a 쪽에 b 붙임
		return true;
	}

	private static int find(int x) {
		if (parents[x] == x) return x;
		else return parents[x] = find(parents[x]);
	}

	private static void init() throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		}

		islandsInit();

		edges = new ArrayList<>();
		connected = new boolean[islandCnt + 1];

		bridgeInit();
	}
	
	private static void bridgeInit() {
		
		int[][] minBridge = new int[islandCnt + 1][islandCnt + 1];
		for (int i = 0; i <= islandCnt; i++) {
			Arrays.fill(minBridge[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) continue;
				
				int from = map[i][j];
				// 위
				for (int r = i - 1; r >= 0; r--) {
					// 다른 섬 만나면 다리 갱신
					if (map[r][j] == 0) continue;
					if (map[r][j] == from) break;

					// 다른 섬인데 거리 미달
					if (Math.abs(i - r) <= 2) break;

					int to = map[r][j];
					minBridge[from][to] = minBridge[to][from] = Math.min(minBridge[from][to],
						Math.abs(r - i) - 1);
					break;
				}

				// 아래
				for (int r = i + 1; r < N; r++) {
					// 다른 섬 만나면 다리 갱신
					if (map[r][j] == 0) continue;
					if (map[r][j] == from) break;

					// 다른 섬인데 거리 미달
					if (Math.abs(i - r) <= 2) break;

					int to = map[r][j];
					minBridge[from][to] = minBridge[to][from] = Math.min(minBridge[from][to],
						Math.abs(r - i) - 1);
					break;
				}

				// 왼
				for (int c = j - 1; c >= 0; c--) {
					// 다른 섬 만나면 다리 갱신
					if (map[i][c] == 0) continue;
					if (map[i][c] == from) break;

					// 다른 섬인데 거리 미달
					if (Math.abs(j - c) <= 2) break;

					int to = map[i][c];
					minBridge[from][to] = minBridge[to][from] = Math.min(minBridge[from][to],
						Math.abs(c - j) - 1);
					break;
				}

				// 오
				for (int c = j + 1; c < M; c--) {
					// 다른 섬 만나면 다리 갱신
					if (map[i][c] == 0) continue;
					if (map[i][c] == from) break;

					// 다른 섬인데 거리 미달
					if (Math.abs(j - c) <= 2) break;

					int to = map[i][c];
					minBridge[from][to] = minBridge[to][from] = Math.min(minBridge[from][to],
						Math.abs(c - j) - 1);
					break;
				}
			}
		}

		for (int i = 1; i <= islandCnt; i++) {
			for (int j = i + 1; j <= islandCnt; j++) {
				if (minBridge[i][j] != Integer.MAX_VALUE) {
					edges.add(new int[] {i, j, minBridge[i][j]});
					connected[i] = connected[j] = true;
				}
			}
		}
	}
	
	private static void islandsInit() {
		// 섬 추가
		boolean[][] visited = new boolean[N][M];
		int islandNum = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					ArrayDeque<Point> deq = new ArrayDeque<>();
					deq.add(new Point(i, j));

					while (!deq.isEmpty()) {
						Point cur = deq.poll();
						map[cur.y][cur.x] = islandNum;

						for (int d = 0; d < 4; d++) {
							int ny = cur.y + dys[d];
							int nx = cur.x + dxs[d];
							if (!(0 <= ny && ny < N) || !(0 <= nx && nx < M) || visited[ny][nx] || map[ny][nx] == 0)
								continue;
							deq.add(new Point(ny, nx));
							visited[ny][nx] = true;
						}
					}
					islandNum += 1;
				}
				visited[i][j] = true;	
			}
		}
		islandCnt = islandNum - 1;
	}
	
	static class Point {
		int y, x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
