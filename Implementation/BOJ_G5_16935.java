package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240206
public class BOJ_G5_16935 {

	static String[][] map;
	static int N;
	static int M;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		map = new String[N][]; // N * M
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().split("\\s+");
		}

		// 연산 수행
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {

			switch(st.nextToken()) {
				case "1":
					operation1();
					break;
				case "2":
					operation2();
					break;
				case "3":
					operation3();
					break;
				case "4":
					operation4();
					break;
				case "5":
					operation5();
					break;
				case "6":
					operation6();
					break;
			}
		}

		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (String[] row: map) {
			for (String num: row) {
				sb.append(num + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	// 연산자 1 : 상하 반전
	private static void operation1() {

		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				String temp = map[i][j];
				map[i][j] = map[N - 1 - i][j];
				map[N - 1 - i][j] = temp;
			}
		}
	}

	// 연산자 2 : 좌우 반전
	private static void operation2() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				String temp = map[i][j];
				map[i][j] = map[i][M - 1 -j];
				map[i][M - 1 - j] = temp;
			}
		}
	}

	// 연산자 3 : 90도 오른쪽 회전
	private static void operation3() {
		String[][] result = new String[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[j][N - 1 - i] = map[i][j];
			}
		}

		map = result;
		int temp = M;
		M = N;
		N = temp;
	}

	// 연산자 4 : 90도 왼쪽 회전
	private static void operation4() {

		String[][] result = new String[M][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result[M - 1 - j][i] = map[i][j];
			}
		}

		map = result;
		int temp = M;
		M = N;
		N = temp;
	}

	// 연산자 5 : 부분 배열 오른쪽 회전
	private static void operation5() {
		String[][] result = new String[N][M];

		// #1 -> 2
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				result[i][j + M/2] = map[i][j];
			}
		}
		// #2 -> 3
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				result[i + N/2][j] = map[i][j];
			}
		}
		// #3 -> 4
		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				result[i][j - M/2] = map[i][j];
			}
		}
		// #4 -> 1
		for (int i = N/2; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				result[i - N/2][j] = map[i][j];
			}
		}

		map = result;
	}

	// 연산자 6 : 부분 배열 왼쪽 회전
	private static void operation6() {
		String[][] result = new String[N][M];

		// #1 -> 4
		for (int i = 0; i < N/2; i++) {
			for (int j = 0; j < M/2; j++) {
				result[i + N/2][j] = map[i][j];
			}
		}
		// #2 -> 1
		for (int i = 0; i < N/2; i++) {
			for (int j = M/2; j < M; j++) {
				result[i][j - M/2] = map[i][j];
			}
		}
		// #3 -> 2
		for (int i = N/2; i < N; i++) {
			for (int j = M/2; j < M; j++) {
				result[i - N/2][j] = map[i][j];
			}
		}
		// #4 -> 3
		for (int i = N/2; i < N; i++) {
			for (int j = 0; j < M/2; j++) {
				result[i][j + M/2] = map[i][j];
			}
		}

		map = result;
	}
}
