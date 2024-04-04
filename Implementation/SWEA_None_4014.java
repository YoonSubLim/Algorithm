package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_None_4014 {

	static BufferedReader br;
	static StringTokenizer st;
	static int N, X, map[][];
	
	public static void main(String[] args) throws IOException, NumberFormatException {

		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			init();
			System.out.printf("#%d %d\n", t, makeResult());
		}
	}
	
	private static int makeResult() {
		
		int cnt = 0;
		
		// 가로
		for (int i = 0; i < N; i++) {
			
			boolean flag = true;
			boolean[] installed = new boolean[N];
			for (int j = 1; j < N; j++) {
				if (map[i][j] != map[i][j-1]) {
					// 높이 차 1 초과
					if (Math.abs(map[i][j] - map[i][j-1]) > 1) {
						flag = false;
						break;
					}
					if (map[i][j] > map[i][j-1]) {
						// 앞쪽으로 설치하는 경우에서 불가능하면
						if (j - X < 0) {
							flag = false;
							break;
						}
						for (int k = j-1; k >= j-X; k--) {
							if (map[i][k] != map[i][j-1] || installed[k]) {
								flag = false;
								break;
							}
							installed[k] = true;
						}
					} else {
						// 뒤쪽으로 설치하는 경우에서 불가능하면
						if (j - 1 + X >= N) {
							flag = false;
							break;
						}
						for (int k = j + 1; k <= j-1+X; k++) {
							if (map[i][k] != map[i][j] || installed[k]) {
								flag = false;
								break;
							}
							installed[k] = true;
						}
					}
				}
			}
			if (flag) {
				System.out.println("가로 : " + i +" 에서 가능");
				cnt++;
			}
		}
		
		// 세로
		for (int j = 0; j < N; j++) {

			boolean flag = true;
			boolean[] installed = new boolean[N];
			for (int i = 1; i < N; i++) {
				if (map[i][j] != map[i-1][j]) {
					// 높이 차 1 초과
					if (Math.abs(map[i][j] - map[i-1][j]) > 1) {
						flag = false;
						break;
					}
					if (map[i][j] > map[i-1][j]) {
						// 앞쪽으로 설치하는 경우에서 불가능하면
						if (i - X < 0) {
							flag = false;
							break;
						}
						for (int k = i-1; k >= i-X; k--) {
							if (map[k][j] != map[i-1][j] || installed[k]) {
								flag = false;
								break;
							}
							installed[k] = true;
						}
					} else {
						// 뒤쪽으로 설치하는 경우에서 불가능하면
						if (i - 1 + X >= N) {
							flag = false;
							break;
						}
						for (int k = i + 1; k <= i-1+X; k++) {
							if (map[k][j] != map[i][j] || installed[k]) {
								flag = false;
								break;
							}
							installed[k] = true;
						}
					}
				}
			}
			if (flag) {
				System.out.println("세로 : " + j +" 에서 가능");
				cnt++;
			}
		}
		
		return cnt;
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		map = new int[N][];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		}
	}
}


/*
 * 약 50분
 * 
 * 가로 세로에 활주로를 놓을 수 있는지 확인하는 로직은 같기 때문에,
 * 각각 확인하는 코드가 상당히 많이 중복된다.
 * 
 * 1. 가로 세로 를 뒤바꾼 2차원 배열을 하나 더 생성해서,
 * 메서드에 인자로 넘겨주는 식으로 구성하면 중복 코드를 줄일 수 있다.
 * 
 * 2. 혹은 1차원 배열 (가로 혹은 세로) 한 줄에 대해 판별하는 메서드를 짜고,
 * 케이스에 맞게 세로 열이나 가로 행을 넘겨주는 방식
 * 
 * */
