package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240302
public class BOJ_G4_6987 {

	static final int COUNTRY_COUNT = 6;
	static BufferedReader br;
	static StringTokenizer st;
	static int wins[] = new int[COUNTRY_COUNT], draws[] = new int[COUNTRY_COUNT], loses[] = new int[COUNTRY_COUNT];
	static int[][] pairs; // i 번째 경기에서의 두 나라
	static int totalGames; // 전체 경기수
	static int result; // 1 / 0

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0 ; i < 4; i++) {
			init();
			backtracking(0, totalGames);
			System.out.print(result + " ");
		}
		System.out.println();
	}

	private static void backtracking(int matchCnt, int totalGames) {

		// 모두 도달했을 때, win draws loses 카운트가 남아있는 게 없으면 성공
		if (matchCnt == totalGames) {
			for (int i = 0; i < COUNTRY_COUNT; i++) {
				if (wins[i] > 0 || draws[i] > 0 || loses[i] > 0)
					return;
			}
			result = 1;
			return;
		}

		int me = pairs[matchCnt][0];
		int enemy = pairs[matchCnt][1];

		// 이기는 경우
		if (wins[me] > 0 && loses[enemy] > 0) {
			wins[me] -= 1;
			loses[enemy] -= 1;
			backtracking(matchCnt + 1, totalGames);
			wins[me] += 1;
			loses[enemy] += 1;
		}

		// 비기는 경우
		if (draws[me] > 0 && draws[enemy] > 0) {
			draws[me] -= 1;
			draws[enemy] -= 1;
			backtracking(matchCnt + 1, totalGames);
			draws[me] += 1;
			draws[enemy] += 1;
		}

		// 지는 경우
		if (loses[me] > 0 && wins[enemy] > 0) {
			loses[me] -= 1;
			wins[enemy] -= 1;
			backtracking(matchCnt + 1, totalGames);
			loses[me] += 1;
			wins[enemy] += 1;
		}
	}

	private static void init() throws IOException, NumberFormatException {

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < COUNTRY_COUNT; i++) {
			wins[i] = Integer.parseInt(st.nextToken());
			draws[i] = Integer.parseInt(st.nextToken());
			loses[i] = Integer.parseInt(st.nextToken());
		}

		totalGames = (COUNTRY_COUNT * (COUNTRY_COUNT - 1)) / 2;
		pairs = new int[totalGames][];
		int idx = 0;
		for (int i = 0; i < COUNTRY_COUNT - 1; i++) {
			for (int j = i + 1; j < COUNTRY_COUNT; j++) {
				pairs[idx++] = new int[]{i, j};
			}
		}

		result = 0;
	}
}
