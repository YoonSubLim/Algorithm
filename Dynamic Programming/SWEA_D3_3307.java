package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240327
public class SWEA_D3_3307 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int T, N, nums[], LIS[];
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d %d\n", t, makeResult());
		}
	}
	
	private static int makeResult() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		LIS = new int[N + 1];
		int result = 0;
		
		// LIS Brute Force 방법 사용
		for (int i = 1; i <= N; i++) {
			LIS[i] = 1;
			
			for (int j = 0; j <= i - 1; j++) {
				if (nums[j] < nums[i])
					LIS[i] = Math.max(LIS[i], LIS[j] + 1);
			}
			
			result = Math.max(result, LIS[i]);
		}
		
		return result;
	}
}
