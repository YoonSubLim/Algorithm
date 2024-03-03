package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

// 240213
public class BOJ_S5_16435 {

	public static void main(String[] args) throws IOException, NumberFormatException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N_ = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int[] fruitHeights = Stream.of(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();
		int startRangeValue = 0;
				
		while(true) {
			
			int eatableCount = 0;
			
			for (int i = 0; i < fruitHeights.length; i++) {
				if (startRangeValue < fruitHeights[i] && fruitHeights[i] <= L)
					eatableCount += 1;
			}
			
			if (eatableCount == 0)
				break;
			
			startRangeValue = L;
			L += eatableCount;
		}
		
		System.out.println(L);
	}
}
