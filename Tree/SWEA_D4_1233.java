package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 240206
public class SWEA_D4_1233 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {

		for (int t = 1; t <= 10; t++) {
			
			System.out.printf("#%d %d\n", t, canCalculate());
		}
	}
	
	/**
	 * 연산 트리가 유효한지 결과를 리턴하는 메서드
	 * 연산 트리는 리프 노드는 숫자만 존재하고, 숫자는 리프노드에만 있는 경우가 유효하기 때문에,
	 * 리프노드에 숫자가 아닌 연산자가 존재하는 경우와, non리프노드에 연산자가 아닌 숫자가 존재하는 경우는 fail (return 0)
	 * 그 외의 경우에서는 success (return 1) 이다.
	 * @return 1 (success) / 0 (fail)
	 * @throws Exception
	 */
	private static int canCalculate() throws Exception {
		
		int N = Integer.parseInt(br.readLine()); // 트리가 갖는 정점의 총 수 N (1 ≤ N ≤ 200)
		int result = 1;

		for (int i = 1; i <= N; i++) {
			
			// 이미 실패시 input 값 소모를 위해 N번 readLine 채우기
			if (result == 0) {
				br.readLine();
				continue;
			}
			
			// i 번째 노드 정보를 split 했을 때
			// 1. 길이가 4이면 자식 노드를 갖는 non리프노드 -> 연산자여야 함
			// 2. 길이가 2이면 자식 노드를 갖지않는 리프노드 -> 숫자여야 함
			String[] nodeInfos = br.readLine().split("\\s+");
			
			if (nodeInfos.length == 4) {
				try {
					Integer.parseInt(nodeInfos[1]);
					// 숫자인 경우 (fail)
					result = 0;
//					break; // N번 readLine() 해야 다음 테스트 진행가능하니, break 문 주석처리
				} catch (NumberFormatException e) {
					// 연산자인 경우 (pass)
					continue;
				}
			}
			
			if (nodeInfos.length == 2) {
				try {
					Integer.parseInt(nodeInfos[1]);
					// 숫자인 경우 (pass)
					continue;
				} catch (NumberFormatException e) {
					// 연산자인 경우 (fail)
					result = 0;
//					break; // N번 readLine() 해야 다음 테스트 진행가능하니, break 문 주석처리
				}
			}
		}
		
		return result;
	}
}
