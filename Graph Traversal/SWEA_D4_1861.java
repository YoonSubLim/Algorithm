package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240207
public class SWEA_D4_1861 {

	static BufferedReader br;

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int[] result = makeResult(); 
			System.out.printf("#%d %d %d\n", t, result[0], result[1]);
		}
	}

	static int[] makeResult() throws IOException {

		int[] result = new int[2];
		int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][];
		int[][] counts = new int[N][N];

		for (int i = 0; i < N; i++)
			map[i] = Arrays.stream(br.readLine().split("\\s+"))
			.mapToInt(Integer::parseInt)
			.toArray();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (counts[i][j] == 0)
					dfs(map, counts, result, i, j);
			}
		}

		return result;
	}

	// 상 하 좌 우
	static int[] dirX = {-1, 1, 0, 0};
	static int[] dirY = {0, 0, -1, 1};

	// startPoint map[i][j] 에서 dfs 를 돌며, 최대로 이동할 수 있는 방의 수를, 경로에 있는 모든 방에 저장한다
	static int dfs(int[][] map, int[][] counts, int[] result, int row, int col) {
		
		for (int d = 0; d < 4; d++) {
			int nextRow = row + dirX[d];
			int nextCol = col + dirY[d];

			if (nextRow < 0 || nextRow >= map.length || nextCol < 0 || nextCol >= map.length)
				continue;

			// 다음 방이 1만큼 더 큰 수라면 (연결되어 있다면)
			if (map[nextRow][nextCol] == map[row][col] + 1) {
				// 서로 다른 수 중 연속된 수를 비교하고 + dfs 메서드를 count 값이 없는 경우에 진입하므로
				// 해당 노드에 연결된 모든 노드들은 처음 방문하는 상태 -> 모두 dfs 해야 함
				// 연결된 방이 있다면 그 방의 count 수 + 1 => 현재 방의 이동 가능 수
				int roomCount = dfs(map, counts, result, nextRow, nextCol) + 1;
				counts[row][col] = roomCount;
				
				// 최댓값 결과 바뀌었으면
				if (result[1] < roomCount) {
					result[0] = map[row][col];
					result[1] = roomCount;
				} else if (result[1] == roomCount && map[row][col] < result[0]) {
					// 최대인 방이 여럿이라면 적힌 수가 가장 작은 것으로 대체
					result[0] = map[row][col];
				}
				return roomCount;
			}
		}

		// 연결된 방이 없다면, 
		counts[row][col] = 1;
		return 1;
	}
}
