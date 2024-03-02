package BOJ;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 240205
public class BOJ_S4_1158 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		printJosephus(N, K);
		sc.close();
	}
	
	private static void printJosephus(int N, int K) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		
		List<Integer> nums = IntStream.range(1, N + 1)
				.boxed()
				.collect(Collectors.toList());
		
		int curIdx = 0;
		int interval = K;
		while (!nums.isEmpty()) {

			// 빠져야 할 위치라면, 제거 후 interval 원복 후 continue. -> curIdx 는 그대로 그 위치에서부터 시작한다
			if (interval == 1) {
				sb.append(nums.remove(curIdx) + ", ");
				interval = K;
			} else {
				curIdx++;
				interval--;				
			}
			
			if (curIdx >= nums.size())
				curIdx = 0;
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append(">");
		
		System.out.println(sb);
	}
}
