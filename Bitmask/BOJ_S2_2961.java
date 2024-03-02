package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240201
public class BOJ_S2_2961 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		int N = Integer.parseInt(br.readLine());
		// N 개 재료에 대한 신맛 / 쓴맛 배열
		int[] arrS = new int[N];
		int[] arrB = new int[N];
		
		int minimum = Integer.MAX_VALUE;
		
		// 맛 배열 값 저장
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			arrS[n] = Integer.parseInt(st.nextToken());
			arrB[n] = Integer.parseInt(st.nextToken());
		}
		
		if (N == 1) {
			System.out.println(Math.abs(arrS[0] - arrB[0]));
			return;
		}
		
		// N 개 재료가 각각 있을 때 없을 때를 비트 로 나타내기 위해
		// 1 ~ 2^N-1 까지의 경우를 비교한다. 1개 이상의 재료 선택하라는 조건 때문.
		for (int i = 1; i < (1 << N); i++) {
			int sour = 1;
			int bitter = 0;
			
			// 각 자리의 비트 값이 존재하는지 = 특정 재료가 존재하는지 여부에 따라 값 처리
			for (int j = 0; j < N; j++) {
				// i(조합 케이스) 의 j 번째(재료)에 값이 존재 (!= 0, 선택된 상태)
				if ((i & (1 << j)) != 0) {
					sour *= arrS[j];
					bitter += arrB[j];
				}
			}
			
			// 이때의 값 차이 반영
			minimum = Math.min(minimum, Math.abs(sour - bitter));
			
			// 더 비교할 필요가 없는 경우 break
			if (minimum == 0)
				break;
		}

		System.out.println(minimum);
	}
}
