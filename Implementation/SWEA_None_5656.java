package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240329
public class SWEA_None_5656 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int N, W, H, startMap[][], remainBricks;
	
	public static void main(String[] args) throws IOException, NumberFormatException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			dfs(0, startMap);
			System.out.printf("#%d %d\n", t, remainBricks);
		}
	}
	
	private static void dfs(int cnt, int[][] map) {
		
		if (cnt == N) {
			remainBricks = Math.min(remainBricks, countBricks(map));
			return;
		}
		
		boolean shooted = false;
		
		for (int c = 0; c < W && remainBricks > 0; c++) { // 남은 벽돌 수가 0이면 더 이상 볼 필요 없음
			
			// 벽돌이 없는 경우는 제외하여 불필요한 연산 최소화
			if (!isThereBrick(c, map)) continue;
			
			// 다음 케이스로 넘겨줄 맵 복사
			int[][] tempMap = map.clone();
			for (int j = 0; j < H; j++) {
				tempMap[j] = map[j].clone();
			}
			
			// 복사한 맵에 구슬을 쏜 후 다음으로 이동
			tempMap = shoot(c, tempMap);
			shooted = true;
			dfs(cnt + 1, tempMap);
		}
		
		if (!shooted) {
			// 쏠 게 하나도 없었던 경우 (e.g. input case #5)
			remainBricks = 0;
			return;
		}
	}
	
	private static boolean isThereBrick(int idx, int[][] map) {
		
		for (int i = 0; i < H; i++) {
			if (map[i][idx] != 0) {
				return true;
			}
		}
		return false;
	}
	
	private static int[][] shoot(int idx, int[][] map) {
		
		ArrayDeque<int[]> points = new ArrayDeque<>(); // 폭발해야 할 포인트들
		for (int r = 0; r < H; r++) {
			if (map[r][idx] != 0) {
				points.add(new int[] {r, idx, map[r][idx]});
				break;
			}
		}
		
		// 연쇄 폭발
		while (!points.isEmpty()) {
			
			int[] cur = points.poll();
			int curY = cur[0];
			int curX = cur[1];
			int range = cur[2];
			
			// 가로로 터지는데, 주변에 영향을 주는 폭발 (1 이상인 블럭)인 경우 덱에 넣어 추후 폭발
			for (int x = (curX - range + 1 >= 0 ? curX - range + 1 : 0); x <= (curX + range - 1 < W ? curX + range - 1 : W - 1); x++) {
				if (map[curY][x] > 1) {
					points.add(new int[] {curY, x, map[curY][x]});
				}
				map[curY][x] = 0; // 폭발
			}
			
			// 세로
			for (int y = (curY - range + 1 >= 0 ? curY - range + 1 : 0); y <= (curY + range - 1 < H ? curY + range - 1 : H - 1); y++) {
				if (map[y][curX] > 1) {
					points.add(new int[] {y, curX, map[y][curX]});
				}
				map[y][curX] = 0; // 폭발
			}
		}
		
		// 폭발 이후 맵 업데이트
		return updateMapAfterShoot(map);
	}
	
	private static int[][] updateMapAfterShoot(int[][] map) {
						
		// 열  별로
		for (int c = 0; c < W; c++) {
			// 행 (위아래) 별로
			for (int r = H - 1; r > 0; r--) {
				// 아래서부터 올라갈 때, 0 을 만나면
				if (map[r][c] == 0) {
					boolean flag = false; // 떨어진 게 있는지
					// 그 위의 숫자를 찾아서 떨어뜨림
					for (int k = r - 1; k >= 0; k--) {
						if (map[k][c] != 0) {
							map[r][c] = map[k][c];
							map[k][c] = 0;
							flag = true;
							break;
						}
					}
					if (!flag) break; // 떨어진 거 없으면 더이상 안 봄
				}
			}
		}
		
		return map;
	}
	
	// 남은 벽돌 세기
	private static int countBricks(int[][] map) {
		
		int cnt = 0;
		
		for (int i = H - 1; i >= 0; i--) {
			boolean containBrick = false;
			for (int j = 0; j < W; j++) {
				if (map[i][j] != 0) {
					cnt += 1;
					containBrick = true;
				}
			}
			if (!containBrick) break; // row 에 벽돌을 하나라도 포함 안하면 종료
		}
		return cnt;
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		startMap = new int[H][];
		for (int i = 0; i < H; i++) {
			startMap[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		}
		
		remainBricks = Integer.MAX_VALUE;
	}
}
