package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240208
public class SWEA_D3_5215 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException, NumberFormatException {
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++)
			System.out.printf("#%d %d\n", t, getHambugerScore());
	}

	// knapsack algorithm
	public static int getHambugerScore() throws IOException, NumberFormatException {

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		int[] scores = new int[N];
		int[] calories = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			scores[i] = Integer.parseInt(st.nextToken());
			calories[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][L + 1];

		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < L + 1; j++) {
				if (i == 0 || j == 0) {
					dp[i][j] = 0;
					continue;
				}

				if (calories[i - 1] <= j)
					dp[i][j] = Math.max(dp[i - 1][j], scores[i - 1] + dp[i - 1][j - calories[i - 1]]);
				else
					dp[i][j] = dp[i - 1][j];
			}
		}

		return dp[N][L];
	}
}
