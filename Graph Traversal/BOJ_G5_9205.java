package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 240328
public class BOJ_G5_9205 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int T, N;
	static ArrayList<Point> points;
	static boolean[][] reachable;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException, NumberFormatException {		
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			makeResult();
		}
	}
	
	private static void makeResult() throws IOException, NumberFormatException {
		
		init();
		
		// 그래프 구성
		for (int i = 0; i < N; i++) {
			Point p1 = points.get(i);
			
			for (int j = 0; j < N; j++) {
				Point p2 = points.get(j);
				
				if (p1.canReach(p2)) {
					reachable[i][j] = reachable[j][i] = true;
				}
			}
		}
		
		// 그래프 탐색 후 연결 여부에 따라 결과 출력
		boolean isConnected = dfs(0);
		if (isConnected) {
			System.out.println("happy");
		} else {
			System.out.println("sad");
		}
	}
	
	private static boolean dfs(int idx) {
		
		// 마지막 인덱스라면 펜타포트 도착
		if (idx == N - 1) {
			return true;
		}
		
		Point point = points.get(idx);
		boolean result = false;
		
		for (int j = 0; j < N; j++) {
			if (reachable[idx][j] && !visited[j]) {
				visited[j] = true;
				result = result || dfs(j);
				
				if (result) {
					return result;
				}
			}
		}
		
		return result;
	}
	
	private static void init() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine()) + 2; // 편의점 개수에, 집과 펜타포트 를 포함한 개수 로 N을 재정의
		points = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			points.add(new Point(y, x));
		}
		
		reachable = new boolean[N][N];
		visited = new boolean[N];
	}
	
	static class Point {
		int y, x;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
		public boolean canReach(Point p) {
			if (this.equals(p)) return false;
			
			int dist = Math.abs(this.y - p.y) + Math.abs(this.x - p.x);
			if (dist <= 50 * 20) {
				return true;
			}
			return false;
		}
	}
}
