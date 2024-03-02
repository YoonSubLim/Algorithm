package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// 240202
public class SWEA_D3_1225 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= 10; t++) {
			
			// 버리는 값
			int t_ = Integer.parseInt(br.readLine());
			
			// 받은 처음 숫자 리스트
			List<Integer> nums = Arrays.stream(br.readLine().split("\\s+"))
					.mapToInt(Integer::parseInt)
					.boxed()
					.collect(Collectors.toList());
			// 숫자 리스트를 큐로 구성
			ArrayDeque<Integer> deque = new ArrayDeque<>(nums);

			// 반복하며 pollFirst and addLast
			int minusNum = 1;
			while (true) {
				int num = deque.pollFirst() - minusNum;
				
				// 다음 값이 0 이하라면, 0을 삽입 후 종료
				if (num <= 0) {
					deque.addLast(0);
					break;
				}
				
				minusNum = (minusNum % 5) + 1;
				deque.addLast(num);
			}
			
			// 출력
			sb.setLength(0);
			sb.append("#" + t + " ");
			for (int num: deque) {
				sb.append(num + " ");
			}			
			System.out.println(sb);
		}
	}
}
