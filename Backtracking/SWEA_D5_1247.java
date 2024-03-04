package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 240215
public class SWEA_D5_1247 {

	static BufferedReader br;
	
	static int N;
	static boolean[] isSelected;
	static Stack<Integer> visitOrders;
	
	static Point[] points;
	
	static int minDist;
	static int totalDist;
	
	public static void main(String[] args) throws IOException, NumberFormatException {

		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			getMinDist();
			System.out.printf("#%d %d\n", t, minDist);
		}
	}
	
	static class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getDistance(Point p) {
			return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
		}
	}
	
	private static void getMinDist() throws IOException, NumberFormatException {
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		points = new Point[N + 2];
		points[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		points[N + 1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for (int i = 1; i <= N; i++)
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		isSelected = new boolean[N];
		
		visitOrders = new Stack<Integer>();
		visitOrders.add(0);
		
		minDist = Integer.MAX_VALUE;
		totalDist = 0;
		
		perm(0);
	}
		
	private static void perm(int count) {
		
		if(count == N) {
			// 집으로 돌아가는 경로의 값까지 합산하여 값 설정
			minDist = Math.min(totalDist + points[visitOrders.peek()].getDistance(points[N + 1]), minDist);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			
			if (!isSelected[i]) {
				
				// i 번째 선택 -> 그 전에 선택된 좌표와 거리 계산 하여 더하기
				int dist = points[visitOrders.peek()].getDistance(points[i + 1]);

				// 회사 ~ i 번째 고객까지의 거리가 이미 최소 거리를 초과했으면 넘어감
				if (totalDist + dist >= minDist)
					continue;
				
				// i 번째 고객 선택
				isSelected[i] = true;
				totalDist += dist;
				visitOrders.add(i + 1);
				
				perm(count + 1);
				
				// i 번째 고객 선택 해제
				visitOrders.pop();
				totalDist -= dist;
				isSelected[i] = false;
			}
		}
	}
}