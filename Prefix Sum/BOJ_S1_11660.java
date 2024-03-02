package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_S1_11660 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// total prefix map setting
		// prefixMap[i][j] 는 (i, j) 까지의 전체 누적합
		int[][] prefixMap = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				prefixMap[r][c] = Integer.parseInt(st.nextToken()) + prefixMap[r - 1][c] + prefixMap[r][c - 1] - prefixMap[r - 1][c - 1];
			}
		}

		// main logic
		for (int m = 0; m < M; m++) {
			// array of {x1 y1 x2 y2}
			int[] xys = Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt)
				.toArray();

			int result = prefixMap[xys[2]][xys[3]] - prefixMap[xys[0] - 1][xys[3]] - prefixMap[xys[2]][xys[1] - 1] + prefixMap[xys[0] - 1][xys[1] - 1];

			bw.write(Integer.toString(result));
			bw.write("\n");
		}
		bw.flush();

		bw.close();
		br.close();
	}
}

