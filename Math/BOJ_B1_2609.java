package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 240401
public class BOJ_B1_2609 {

	public static void main(String[] args) throws IOException, NumberFormatException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nums = br.readLine().split("\\s+");
		
		int a = Integer.parseInt(nums[0]);
		int b = Integer.parseInt(nums[1]);
		
		System.out.println(gcd(a, b));
		System.out.println(lcm(a, b));
	}
	
	private static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
	
	private static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}
