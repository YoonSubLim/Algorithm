package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240201
public class BOJ_S2_12891 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int lenS = Integer.parseInt(st.nextToken());
		int lenP = Integer.parseInt(st.nextToken());
		String S = br.readLine();
		int[] counts = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		int result = 0;
		
		for (int i = 0; i < lenS; i++) {
			
			// 새로 추가되는 위치의 알파벳의 index 찾아서, count 값 차감
			int newAlphabetIdx = getAlphabetIdx(S.charAt(i));
			counts[newAlphabetIdx]--;
			
			// 제외되는 위치의 알파벳의 index 찾아서, count 값 추가
			if (i - lenP >= 0) {
				int endAlphabetIdx = getAlphabetIdx(S.charAt(i - lenP));
				counts[endAlphabetIdx]++;
			}

			if (i >= lenP - 1) {
				// 가능한 경우라면 result 추가
				// counts 의 모든 값이 0 이하여야 가능한 경우이다.
				boolean isPossibleAnswer = true;
				for (int j = 0; j < 4; j++) {
					if (counts[j] > 0) {
						isPossibleAnswer = false;
						break;
					}
				}

				if (isPossibleAnswer)
					result++;
			}
		}
		System.out.println(result);
	}
	
	private static int getAlphabetIdx(char ch) {
		switch (ch) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		}
		return -1;
	}
}
