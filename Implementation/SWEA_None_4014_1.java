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
		return checkVertical() + checkHorizontal();
	}
	
	private static int checkVertical() {
		
		int cnt = 0;
		int[] col = new int[N];
		
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				col[r] = map[r][c];
			}
			if (installable(col)) cnt++;
		}
		return cnt;
	}
	
	private static int checkHorizontal() {
		
		int cnt = 0;
		
		for (int r = 0; r < N; r++) {
			if (installable(map[r])) cnt++;
		}
		return cnt;
	}
	
	private static boolean installable(int[] arr) {
		
		boolean[] installed = new boolean[arr.length];
		
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] != arr[i-1]) {
				if (Math.abs(arr[i] - arr[i-1]) > 1) return false;
				
				if(arr[i] > arr[i-1]) {
					if (i-X < 0) return false;
					for (int k = i-1; k >= i-X; k--) {
						if (arr[k] != arr[i-1] || installed[k]) return false;
						installed[k] = true;
					}
				} else if (arr[i] < arr[i-1]) {
					if (i-1+X >= N) return false;
					for (int k = i; k <= i-1+X; k++) {
						if (arr[k] != arr[i] || installed[k]) return false;
						installed[k] = true;
					}
				}
			}
		}
		return true;
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
 * => 2번 방식으로 리팩토링
 * 
 * */
