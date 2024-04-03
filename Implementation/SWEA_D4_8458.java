package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 240401
// k번 움직였을 때, 전체 움직임 횟수 k(k+1)/2
// '모든 점에서 원점까지의 거리'가 짝수나 홀수로 통일되어 있어야 해결 가능하며
// 전체 움직임 횟수가 가장 먼 점의 거리보다 크면서, 모든 점들과 짝/홀이 일치해야 한다
public class SWEA_D4_8458 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());
			int firstX = Integer.parseInt(st.nextToken());
			int firstY = Integer.parseInt(st.nextToken());
			int firstDist = Math.abs(firstX) + Math.abs(firstY);
			int maxDist = firstDist;
			
			boolean isFirstEven = firstDist % 2 == 0; // even 이면 true odd 면 false
			boolean solvable = true;
			
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				int curDist = Math.abs(x) + Math.abs(y); 
				maxDist = Math.max(maxDist, curDist);

				if ((curDist % 2 == 0) != isFirstEven) {
					solvable = false;
				}
			}
			
			if (!solvable) {
				System.out.printf("#%d %d\n", t, -1);
				continue;
			}
			
			int k = 0;
			int totalMove = 0;
			
			while (true) {
				
				totalMove += k;
				
				// totalMove 와 모든 점들의 거리가 짝수나 홀수로 일치해있어야 한다
				if (maxDist <= totalMove && Math.abs(totalMove - maxDist) % 2 == 0) {
					System.out.printf("#%d %d\n", t, k);
					break;
				} else {
					k++;
					continue;
				}
			}
		}
	}
}
