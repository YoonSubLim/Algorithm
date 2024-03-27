package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240326
public class BOJ_G5_12865 {

	static int N, K, W[], V[];
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		init();
		System.out.println(makeResult());
	}
	
	private static int makeResult() {
		
		int[][] dp = new int[N + 1][K + 1];
		
		for (int i = 0; i <= N; i++) dp[i][0] = 0;
		for (int i = 0; i <= K; i++) dp[0][i] = 0;
		
		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				
				if (W[i] > w) {
					dp[i][w] = dp[i-1][w];
				} else {
					dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-W[i]] + V[i]);
				}
			}
		}

		return dp[N][K];
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		W = new int[N + 1];
		V = new int[N + 1];
		
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			W[n] = Integer.parseInt(st.nextToken());
			V[n] = Integer.parseInt(st.nextToken());
		}
	}
}
