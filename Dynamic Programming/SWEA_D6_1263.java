package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240328
public class SWEA_D6_1263 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, dist[][];
	static boolean[][] conMat;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		int T = Integer.parseInt(br.readLine());
				
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d %d\n", t, makeResult());
		}
	}
	
	private static int makeResult() throws IOException, NumberFormatException {
		
		init();
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			result = Math.min(result, Arrays.stream(dist[i]).sum());
		}
		
		return result;
	}
	
	private static void printMap() {
		for (int[] row: dist) {
			for (int num: row) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		dist = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (st.nextToken().equals("1")) {
					dist[i][j] = 1;
				} else if (i == j) {
					dist[i][j] = 0;
				} else {
					// TODO 충분히 큰 값
					dist[i][j] = 10000000;
				}
			}
		}
	}
}
