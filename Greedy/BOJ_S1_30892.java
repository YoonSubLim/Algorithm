package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// 240202
public class BOJ_S1_30892 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int sharksNum_ = Integer.parseInt(st.nextToken()); // N, 사용하지 않음
		int eatCount = Integer.parseInt(st.nextToken()); // K
		long size = Long.parseLong(st.nextToken()); // T

		List<Integer> sharks = Arrays.stream(br.readLine().split("\\s+"))
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());

		Stack<Integer> canEat = new Stack<>();
		ArrayDeque<Integer> cannotEat = new ArrayDeque<>();

		// 초기 세팅
		// 먹을 수 있는 상어는 큰 거부터 먹을 거니, 나중에 들어온 상어가 먼저 나가는 LIFO -> Stack
		// 먹을 수 없는 상어는 작은 거부터 비교 후 먹을 수 있는 상어 스택에 추가할 거라, 먼저 들어온 상어가 먼저 나가는 FIFO -> Stack
		for (int shark : sharks) {
			if (shark < size) {
				canEat.push(shark);
			} else {
				cannotEat.add(shark);
			}
		}

		while (eatCount > 0) {

			// 먹을 수 있는 상어의 최상단이 제일 큰 상어가 되도록 재조정
			while (!cannotEat.isEmpty() && cannotEat.peek() < size)
				canEat.push(cannotEat.poll());

			// 먹을 수 있는 게 없으면 종료
			if (canEat.isEmpty())
				break;

			// 먹을 수 있는 상어 중 가장 큰 상어 먹기
			size += canEat.pop();
			eatCount--;
		}

		System.out.println(size);
	}
}
