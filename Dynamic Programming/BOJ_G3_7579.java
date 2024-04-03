package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240326
public class BOJ_G3_7579 {

	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, mem[], cost[], C; // mem : 사용 중인 메모리
	static int dp[][];
	
	public static void main(String[] args) throws IOException, NumberFormatException {

		init();
		System.out.println(makeResult());
	}
	
	private static int makeResult() {
		
		int result = -1;
		
		for (int i = 1; i <= N; i++) {
			for (int c = 0; c <= C; c++) {
				if (cost[i] > c) {
					dp[i][c] = dp[i-1][c];
				} else {
					dp[i][c] = Math.max(dp[i-1][c], dp[i-1][c-cost[i]] + mem[i]);
				}
			}
		}

		for (int c = 0; c <= C; c++) {
			for (int i = 1; i <= N; i++) {
				
				if (dp[i][c] >= M) {
					return c;
				}
			}
		}
        return result;
    }
	
	private static void init() throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		mem = new int[N + 1];
		cost = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		C = Arrays.stream(cost).sum();
		dp = new int[N + 1][C + 1];
	}
}
