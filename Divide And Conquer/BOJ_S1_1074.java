package BOJ;

import java.util.Scanner;

// 240214
public class BOJ_S1_1074 {
	
	static int count = 0;
	static boolean isEnd = false;
	static int r;
	static int c;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		traverseZ(N, 0, 0);
		System.out.println(count - 1);
		
		sc.close();
	}
	
	/**
	 * 2^N * 2^N 크기의 map. 왼쪽 상단의 row, col 을 받음
	 * @param N
	 * @param startR
	 * @param startC
	 */
	private static void traverseZ(int N, int startR, int startC) {

		if (isEnd)
			return;
		
		if (N == 0) {
			
			count++;
			
			if (startR == r && startC == c)
				isEnd = true;

			return;
		}
		
		int len = (int) Math.pow(2, N);
		// 범위 안에 없으면 더하고 넘어감
		if (!((startR <= r && r < startR + len) && (startC <= c && c < startC + len))) {
			count += len * len;
			return;
		}
		
		int interval = (int) Math.pow(2, N - 1);
		traverseZ(N - 1, startR, startC);
		traverseZ(N - 1, startR, startC + interval);
		traverseZ(N - 1, startR + interval, startC);
		traverseZ(N - 1, startR + interval, startC + interval);
	}
}
