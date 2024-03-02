package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

// 이 방법 좀 아닌 거 같은데 추후 업뎃 커밋 찍겠습니다 ..
// 240206
public class BOJ_S1_16926 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		map = new int[N][];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		}

		// 돌아가는 층의 수
		int depth = Math.min(N, M) / 2;

		LinkedList<Integer>[] numsInDepth = new LinkedList[depth];

		for (int d = 0; d < depth; d++) {
			numsInDepth[d] = new LinkedList<>();
			numsInDepth[d].addAll(getNumsByDepth(d));
		}
		printResult(numsInDepth, R);
	}

	private static List<Integer> getNumsByDepth(int depth) {

		int row = depth;
		int col = depth;

		int[] dirR = {0, 1, 0, -1};
		int[] dirC = {1, 0, -1, 0};
		int dirIdx = 0;

		LinkedList<Integer> numsByDepth = new LinkedList<>();

		while (true) {

			numsByDepth.add(map[row][col]);
			int nextRow = row + dirR[dirIdx];
			int nextCol = col + dirC[dirIdx];

			if (nextRow < depth || nextRow > map.length - 1 - depth || nextCol < depth || nextCol > map[0].length - 1 - depth) {
				dirIdx++;
				nextRow = row + dirR[dirIdx];
				nextCol = col + dirC[dirIdx];
			}
			if (nextRow == depth && nextCol == depth)
				break;

			row = nextRow;
			col = nextCol;
		}
		return numsByDepth;
	}

	private static void printResult(LinkedList<Integer>[] numsInDepth, int rotateCount) {

		for (int d = 0; d < numsInDepth.length; d++) {
			rotate(numsInDepth[d], rotateCount);
		}

		int[] dirR = {0, 1, 0, -1};
		int[] dirC = {1, 0, -1, 0};

		for (int d = 0; d < numsInDepth.length; d++) {

			int row = d;
			int col = d;

			int dirIdx = 0;

			for (int i = 0; i < numsInDepth[d].size(); i++) {

				map[row][col] = numsInDepth[d].get(i);

				int nextRow = row + dirR[dirIdx];
				int nextCol = col + dirC[dirIdx];

				if (nextRow < d || nextRow > map.length - 1 - d || nextCol < d || nextCol > map[0].length - 1 - d) {
					dirIdx++;
					nextRow = row + dirR[dirIdx];
					nextCol = col + dirC[dirIdx];
				}

				if (nextRow == d && nextCol == d)
					break;

				row = nextRow;
				col = nextCol;
			}
		}

		for (int nums[]: map) {
			for (int num: nums) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}

	private static void rotate(LinkedList<Integer> list, int rotateCount) {

		for (int r = 0; r < rotateCount; r++) {
			list.add(list.removeFirst());
		}
	}
}
