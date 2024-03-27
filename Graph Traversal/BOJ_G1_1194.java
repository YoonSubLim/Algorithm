package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 240327
public class BOJ_G1_1194 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, startY, startX;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dys = {-1, 1, 0, 0}; 
	static int[] dxs = {0, 0, -1, 1}; 
	
	public static void main(String[] args) throws IOException, NumberFormatException {

		init();
		bfs(startY, startX);
	}
	
	private static void bfs(int startY, int startX) {

		ArrayDeque<Point> deq = new ArrayDeque<>();
		deq.add(new Point(startY, startX, 0, 0));
		visited[startY][startX][0] = true;

		while (!deq.isEmpty()) {
			
			Point cur = deq.poll();

			for (int i = 0; i < 4; i++) {
				int nextY = cur.y + dys[i];
				int nextX = cur.x + dxs[i];
				
				if (!(0 <= nextY && nextY < N) || !(0 <= nextX && nextX < M)) continue;
				else if (visited[nextY][nextX][cur.keys]) continue;
				else if (map[nextY][nextX] == '#') continue;

				// 정답 케이스
				if (map[nextY][nextX] == '1') {
					System.out.println(cur.move + 1);
					return;
				}

				if ('a' <= map[nextY][nextX] && map[nextY][nextX] <= 'f') {
					// key 업데이트 // 없으면 추가
					int keys = cur.keys | (1 << (map[nextY][nextX] - 'a'));
					deq.add(new Point(nextY, nextX, cur.move + 1, keys));
					visited[nextY][nextX][keys] = true;
				} else if ('A' <= map[nextY][nextX] && map[nextY][nextX] <= 'F') {
					if ((cur.keys & (1 << map[nextY][nextX] - 'A')) > 0) {
						deq.add(new Point(nextY, nextX, cur.move + 1, cur.keys));
						visited[nextY][nextX][cur.keys] = true;
					}
				} else {
					deq.add(new Point(nextY, nextX, cur.move + 1, cur.keys));
					visited[nextY][nextX][cur.keys] = true;
				}
			}
		}
		System.out.println(-1);
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == '0') {
					startY = i;
					startX = j;
					map[i][j] = '.';
				}
			}
		}
		
		visited = new boolean[N][M][1 << 6];
	}

	static class Point {
		int y, x, move, keys;

		public Point(int y, int x, int move, int keys) {
			this.y = y;
			this.x = x;
			this.move = move;
			this.keys = keys;
		}
	}
}
