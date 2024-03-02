package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240208
public class BOJ_G4_17406 {

	static BufferedReader br;
	static StringTokenizer st;

	static int[][] map;
	static int[][] resultMap;
	static int N, M, K;
	static int[][] operations;
	static boolean[] isSelected;
	static int[][] selectedOps;

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {

		init(); // 값 초기화
		makeResultByPerm(0); // 순열 조합에 따라 값을 만들어내고, 세팅
		System.out.println(result); // 결과 출력
	}

	static void init() throws NumberFormatException, IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // Row
		M = Integer.parseInt(st.nextToken()); // Col
		K = Integer.parseInt(st.nextToken()); // 연산 개수

		isSelected = new boolean[K];
		selectedOps = new int[K][];

		// 기본 배열 저장
		map = new int[N][];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		}

		// 연산자 정보를 저장할 배열.
		operations = new int[K][];
		for (int i = 0; i < K; i++) {
			operations[i] = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		}
	}

	static void makeResultByPerm(int count) {

		if (count == K) {
			setResult();
			return;
		}

		for (int i = 0; i < K; i++) {

			if (isSelected[i])
				continue;

			isSelected[i] = true;
			selectedOps[i] = operations[count];
			makeResultByPerm(count + 1);
			isSelected[i] = false;
		}
	}

	static void setResult() {

		// 모든 연산자 경우마다 연산 결과를 저장할 배열 초기화
		resultMap = Arrays.copyOf(map, map.length);
		for (int j = 0; j < resultMap.length; j++)
			resultMap[j] = Arrays.copyOf(map[j], map[j].length);

		// 연산자별 rotate 수행
		for (int[] opInfo: selectedOps)
			rotate(opInfo[0] - 1, opInfo[1] - 1, opInfo[2]); // r, c, s. r과 c는 줄 번호 -> index

		// rotate 로 돌아간 경우의 최솟값과 비교하여 결과 세팅
		setMinRowValueResult();
	}

	static void rotate(int r, int c, int s) {

		// 이전 배열을 저장하고, 이동시 이 배열에서 값을 찾아온다
		int[][] tempMap = Arrays.copyOf(resultMap, resultMap.length);
		for (int j = 0; j < resultMap.length; j++)
			tempMap[j] = Arrays.copyOf(resultMap[j], resultMap[j].length);

		// depth 별 rotate. s == 0 일 때는 제자리라 의미 없음
		for (int d = s; d >= 1; d--) {
			// 상단
			for (int j = c - d; j < c + d; j++)
				resultMap[r - d][j + 1] = tempMap[r - d][j];
			// 우측
			for (int i = r - d; i < r + d; i++)
				resultMap[i + 1][c + d] = tempMap[i][c + d];
			// 하단
			for (int j = c + d; j > c - d; j--)
				resultMap[r + d][j - 1] = tempMap[r + d][j];
			// 좌측
			for (int i = r + d; i > r - d; i--)
				resultMap[i - 1][c - d] = tempMap[i][c - d];
		}
	}

	static void setMinRowValueResult() {
		for (int[] row: resultMap)
			result = Math.min(result, Arrays.stream(row).sum());
	}
}
