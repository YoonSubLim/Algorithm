package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ_G5_2493 {
	
	static int[] tops;
	static int[] results;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tops = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		results = new int[tops.length];
		
		findTopResults();
	}

	private static void printTopResults() {
		
		StringBuilder sb = new StringBuilder();
		for (int result: results)
			sb.append(result + " ");
		System.out.println(sb);
	}
	
	private static void findTopResults() {
		
		// 탑의 index 를 내림차순으로 스택에 저장한다
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < tops.length; i++) {

			// 스택이 비어있다면, i번째 인덱스 추가 및 결과는 0
			if (stack.isEmpty()) {
				results[i] = 0;
				stack.add(i);
				continue;
			}
			
			// 기존에 stack top에 있던 탑의 Index
			int topIdx = stack.peek();
			// topIdx번째 탑이 i번째 탑보다 높다면 -> i번째 탑이 발사하는 신호는 topIdx 가 수신한다  
			if (tops[topIdx] > tops[i]) {
				results[i] = topIdx + 1;
				stack.add(i);
			} else if (tops[topIdx] < tops[i]) {
				// topIdx번째 탑이 i번째 탑보다 낮다면 -> stack top 에 있는 탑이 i번째보다 클 때까지 pop
				do {
					stack.pop();
				} while (!stack.isEmpty() && tops[stack.peek()] < tops[i]);
				
				if (stack.isEmpty()) {
					results[i] = 0;
					stack.add(i);
				} else {
					results[i] = stack.peek() + 1;
					stack.add(i);
				}
			}
		}
		
		printTopResults();
	}
}
