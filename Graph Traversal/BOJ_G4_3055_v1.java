package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 240328
public class BOJ_G4_3055_v1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, hy, hx; // hy, hx : 고슴도치
	static char[][] map;
	static ArrayDeque<int[]> waters = new ArrayDeque<>();

	static int[] dys = {-1, 1, 0, 0};
	static int[] dxs = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException, NumberFormatException {

		init();
		bfs();
	}

	private static void bfs() {

		// 고슴도치가 bfs 로 확산함과 동시에 물이 차오르는데, D 에 도달한 고슴도치가 있으면 시간을 출력한다
		ArrayDeque<int[]> hog = new ArrayDeque<>();
		hog.add(new int[] {hy, hx, 0}); // 시작 지점 세팅

		boolean[][] visited = new boolean[R][C]; // 고슴도치 방문 여부
		visited[hy][hx] = true;

		while (!hog.isEmpty()) {

			// 물 채우기 - 인접한 칸으로 한칸씩 확산
			flowWater();

			// 고슴도치 이동
			int size = hog.size();
			for (int i = 0; i < size; i++) {

				int[] cur = hog.poll();

				// 도착 지점이라면, 소요 시간을 출력 후 return
				if (map[cur[0]][cur[1]] == 'D') {
					System.out.println(cur[2]);
					return;
				}
				
				// 4방으로 이동
				for (int d = 0; d < 4; d++) {
					int ny = cur[0] + dys[d];
					int nx = cur[1] + dxs[d];

					if (!(0 <= ny && ny < R) || !(0 <= nx && nx < C)) continue;
					if (visited[ny][nx] || map[ny][nx] == '*' || map[ny][nx] == 'X') continue;

					// 방문 처리
					visited[ny][nx] = true;
					hog.add(new int[] {ny, nx, cur[2] + 1});
				}
			}
		}
		// 모든 경우를 확인했을 때 'D' 를 못 만나면 실패
		System.out.println("KAKTUS");
	}

	/**
	 * 물이 한 칸 차오른 맵으로 세팅
	 */
	private static void flowWater() {

		ArrayDeque<int[]> nextQueue = new ArrayDeque<>();
		
		while (!waters.isEmpty()) {
			
			int[] cur = waters.poll();
			
			for (int d = 0; d < 4; d++) {
				int ny = cur[0] + dys[d];
				int nx = cur[1] + dxs[d];

				if (!(0 <= ny && ny < R) || !(0 <= nx && nx < C) || map[ny][nx] == 'X' || map[ny][nx] == 'D' || map[ny][nx] == '*') continue;
				
				// S or .
				map[ny][nx] = '*';
				nextQueue.add(new int[] {ny, nx});
			}
		}
		waters = nextQueue;
	}

	private static void init() throws IOException, NumberFormatException {

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				// 고슴도치 초기 위치 저장
				if (map[i][j] == 'S') {
					hy = i;
					hx = j;
				} else if (map[i][j] == '*') {
					waters.add(new int[] {i, j});
				}
			}
		}
	}
}
