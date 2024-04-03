package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240403
public class SWEA_None_4008 {
	
	static BufferedReader br;
	static int T, N, maxResult, minResult, ops[], nums[], selectedOps[];
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			dfs(0, 0);
			System.out.printf("#%d %d\n", t, maxResult - minResult);
		}
	}
	
	private static void dfs(int cnt, int start) {
		
		if (cnt == nums.length - 1) {
						
			int result = nums[0];
			for (int i = 0; i < selectedOps.length; i++) {
				if (selectedOps[i] == 0) {
					result += nums[i + 1];
				} else if (selectedOps[i] == 1) {
					result -= nums[i + 1];
				} else if (selectedOps[i] == 2) {
					result *= nums[i + 1];
				} else if (selectedOps[i] == 3) {
					result /= nums[i + 1];
				}
			}
			
			maxResult = Integer.max(maxResult, result);
			minResult = Integer.min(minResult, result);
			
			return;
		}
		
		for (int i = 0; i < ops.length; i++) {
			
			if (ops[i] == 0) continue;
			selectedOps[cnt] = i;
			ops[i] -= 1;
			dfs(cnt + 1, i);
			ops[i] += 1;
		}
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine());
		maxResult = Integer.MIN_VALUE;
		minResult = Integer.MAX_VALUE;
		ops = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		nums = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		selectedOps = new int [nums.length - 1];
	}
}
