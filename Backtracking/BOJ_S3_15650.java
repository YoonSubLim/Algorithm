package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240131
public class BOJ_S3_15650 {

	static int N;
	static int R;
	static String[] numbers;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		numbers = new String[R];

		comb(0, 1);
	}

	private static void comb(int cnt, int start) {
		if (cnt == R) {
			System.out.println(String.join(" ", numbers));
			return;
		}

		for (int i = start; i <= N; i++) {
			numbers[cnt] = String.valueOf(i);
			comb(cnt + 1, i + 1);
		}
	}
}
