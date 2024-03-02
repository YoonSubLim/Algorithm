package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240201
public class BOJ_S2_2961 {
	
	static int N;
	static int[] arrS;
	static int[] arrB;
	static int minimum = Integer.MAX_VALUE;
	static int[] numbers; 

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		N = Integer.parseInt(br.readLine());
		// N 개 재료에 대한 신맛 / 쓴맛 배열
		arrS = new int[N];
		arrB = new int[N];
		
		// 맛 배열 값 저장
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			arrS[n] = Integer.parseInt(st.nextToken());
			arrB[n] = Integer.parseInt(st.nextToken());
		}
		
		makeResult();
	}
	
	private static void makeResult() {
		// N 개 중 r 개를 선택하는 경우의 결과를 각각 뽑아 minimum 값에 반영한다.
		for (int r = 1; r <= N; r++) {
			numbers = new int[r];
			comb(0, 0);
		}
		System.out.println(minimum);
	}
	
	private static void comb(int cnt, int start) {
		// numbers.length => R
		if (cnt == numbers.length) {
			minimum = Math.min(minimum, calculateDifference());
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}
	
	private static int calculateDifference() {
		
		int totalS = 1;
		int totalB = 0;
		
		for (int i = 0; i < numbers.length; i++) {
			totalS *= arrS[numbers[i]];
			totalB += arrB[numbers[i]];
		}
		return Math.abs(totalS - totalB);
	}
}
