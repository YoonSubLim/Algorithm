package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SWEA_D3_6808 {
	
	static BufferedReader br;

	public static void main(String[] args) throws IOException, NumberFormatException{
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d %s\n", t, makeResult());
		}
	}
	
	private static String makeResult() throws IOException {
		
		Integer[] myCards = Stream.of(br.readLine().split("\\s+"))
				.map(Integer::valueOf)
				.toArray(Integer[]::new);
		
		Integer[] oppoCards = IntStream.range(1, 19)
				.filter(num -> !Arrays.asList(myCards).contains(num))
				.boxed()
				.toArray(Integer[]::new);
		
		int cardsSum = IntStream.range(1, 19).sum();
		
		int winCount = 0;
		int loseCount = 0;
		
		// nextPermutation 위해 첫 순열로 세팅
		Arrays.sort(oppoCards);

		do {

			int myScore = getScore(myCards, oppoCards);
			// 승리조건
			if (myScore > cardsSum / 2)
				winCount += 1;
			else
				loseCount += 1;
		} while (nextPermutation(oppoCards));

		return winCount + " " + loseCount;
	}
	
	private static boolean nextPermutation(Integer[] p) {
	
		final int N = p.length;
		
		// 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && p[i - 1] >= p[i]) i--;		
		
		// 최대 순열
		if (i == 0)
			return false;
		
		// 교환할 값 찾기
		int j = N - 1;
		while (p[j] <= p[i - 1]) j--;
		
		// 교환
		swap(p, i - 1, j);
		
		// 꼭대기부터 정렬
		int k = N - 1;
		while (i < k) swap(p, i++, k--);
		
		return true;
	}
	
	private static void swap(Integer[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static int getScore(Integer[] my, Integer[] oppo) {
		
		int score = 0;
		
		for (int i = 0; i < my.length; i++) {
			if (my[i] > oppo[i])
				score += (my[i] + oppo[i]);
		}
		
		return score;
	}
}