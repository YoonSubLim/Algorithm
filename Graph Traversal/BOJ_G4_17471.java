package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

// 240305
public class BOJ_G4_17471 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, peopleNum[], totalPeopleNum;
	static boolean connect[][], selected[];
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException, NumberFormatException {

		init();

		// 선택하는 조합에 따라, 차이 갱신
		for (int cnt = 1; cnt <= N - 1; cnt++) {

			selected[0] = true;
			comb(0, 1, cnt);
			selected[0] = false;
		}

		if (minDiff == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(minDiff);
	}


	private static void comb(int start, int selectedCnt, int toSelectCnt) {

		if (selectedCnt == toSelectCnt) {

			// selected / non-selected node 들이 전부 연결되어 있는지 확인
			if (!isConnected(true)) return;
			if (!isConnected(false)) return;

			// 모두 연결되어있다면 차이 값 갱신
			int selectedSum = 0;
			for (int i = 0; i < N; i++) {
				if (selected[i])
					selectedSum += peopleNum[i];
			}

			minDiff = Math.min(minDiff, Math.abs(selectedSum - (totalPeopleNum - selectedSum)));

			return;
		}

		// 조합 선택
		for (int i = start + 1; i < N; i++) {
			if (!selected[i]) {
				selected[i] = true;
				comb(i, selectedCnt + 1, toSelectCnt);
				selected[i] = false;
			}
		}
	}

	/**
	 * 조합으로 선택된 그룹 A 와 나머지 그룹 B
	 * option true 는 그룹 A 가 전부 연결되어있는지 확인
	 * option false 는 그룹 B 가 전부 연결되어있는지 확인
	 * 그룹 A 는 true(option) 인 첫 요소를 찾아 bfs 를 돌며 false(!option) 로 변경 후, 전부 false(!option) 로 변경되었는지 확인한다
	 * 그룹 B 는 false(option) 인 첫 요소를 찾아 bfs 를 돌며 true(!option) 로 변경 후, 전부 true(!option) 로 변경되었는지 확인한다
	 * @param option selected 가 true/false 인 원소들 간 전부 연결되어있는지 확인하기 위한, selected 정보
	 * @return
	 */
	private static boolean isConnected(boolean option) {

		boolean[] temp = selected.clone();
		boolean[] checked = new boolean[N];

		ArrayDeque<Integer> deq = new ArrayDeque<>();

		for (int i = 0; i < N; i++) {
			// bfs 시작할 첫 원소 찾기
			if ((option && selected[i]) || (!option && !selected[i])) {
				deq.add(i);
				break;
			}
		}

		while (!deq.isEmpty()) {
			int curIdx = deq.poll();
			temp[curIdx] = !option;
			checked[curIdx] = true;

			for (int i = 0; i < N; i++) {
				if (selected[i] == option && connect[curIdx][i] && !checked[i]) {
					deq.add(i);
				}
			}
		}

		// temp 배열에 하나라도 option 이 남아있다면, 전부 연결되어있지 않은 경우로, false 리턴
		for (int i = 0; i < N; i++) {
			if (temp[i] == option)
				return false;
		}
		return true;
	}

	private static void init() throws IOException, NumberFormatException {

		N = Integer.parseInt(br.readLine());
		peopleNum = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		totalPeopleNum = Arrays.stream(peopleNum).sum();

		connect = new boolean[N][N];
		selected = new boolean[N];

		for (int n = 0; n < N; n++) {
			String[] nums = br.readLine().split("\\s+");
			for (int j = 1; j < nums.length; j++) {
				int num = Integer.parseInt(nums[j]);
				connect[n][num - 1] = connect[num - 1][n] = true;
			}
		}
	}
}