package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S3_11659 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 숫자의 개수 N
		int N = Integer.parseInt(st.nextToken());
		// 출력 테스트 수
		int M = Integer.parseInt(st.nextToken());

		// 누적 합 배열 setting - 1부터 값 시작
		int[] prefixSum = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			// 이전 인덱스에서의 값 + 새로운 값
			prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
		}

		// 출력
		for (int t = 0; t < M; t++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());

			// i ~ j 값 = j까지의 누적 합 - (i-1) 까지의 누적 합
			System.out.println(prefixSum[j] - prefixSum[i - 1]);
		}
	}
}