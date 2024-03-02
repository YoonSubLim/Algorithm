package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_S1_11286 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (Math.abs(o1) == Math.abs(o2))
					return o1 - o2;

				return Math.abs(o1) - Math.abs(o2);
			}
		});

		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {

			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				Integer polledNum = pq.poll();
				if (polledNum == null)
					System.out.println(0);
				else
					System.out.println(polledNum);
				continue;
			}

			pq.offer(num);
		}
	}
}
