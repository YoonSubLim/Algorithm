package BOJ;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

// 240201
public class BOJ_G5_2023 {

	public static void main(String[] args) {

		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		LinkedList<String> nums = new LinkedList<>(Arrays.asList("2", "3", "5", "7"));
		String[] suffixes = new String[] {"1", "3", "7", "9"};
		
		// N 자리 수 구성
		for (int i = 0; i < N - 1; i++) {
			int len = nums.size();
			
			for (int j = 0; j < len; j++) {
				String prefix = nums.pollFirst();
				
				for (int k = 0; k < suffixes.length; k++) {
					String nextNum = prefix + suffixes[k];
					if (isPrime(Integer.parseInt(nextNum)))
						nums.add(nextNum);
				}
			}
		}
		
		for (String num: nums) {
			sb.append(num + "\n");
		}
		
		System.out.println(sb);
		
		sc.close();
	}
	
	private static boolean isPrime(int N) {
		
		for (int i = 2; i * i <= N; i++) {
			if (N % i == 0)
				return false;		
		}
		
		return true;
	}
}
