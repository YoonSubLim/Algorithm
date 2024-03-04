package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240215
public class SWEA_None_5644 {

	static BufferedReader br;
	static StringTokenizer st;
	static Person personA;
	static int[] movesA;
	static Person personB;
	static int[] movesB;
	static int M; // 총 이동시간
	static int A; // BC 의 개수
	static Charger[] chargers;

	static class Person {
		private int x;
		private int y;
		private ArrayList<Charger> usableChargers;
		
		Person(int x, int y) {
			this.x = x;
			this.y = y;
			this.usableChargers = updateUsableChargers();
		}

		public ArrayList<Charger> getUsableChargers() {
			return usableChargers;
		}
		
		public void move(int direction) {
			if (direction == 0)
				return;
			
			if (direction == 1 && this.y > 0)
				y -= 1;
			if (direction == 2 && this.x < 9)
				x += 1;
			if (direction == 3 && this.y < 9)
				y += 1;
			if (direction == 4 && this.x > 0)
				x -= 1;
			
			// 이동 후 사용가능한 충전기 새로 찾기
			this.usableChargers = updateUsableChargers();
		}
		
		private ArrayList<Charger> updateUsableChargers() {
			
			ArrayList<Charger> newChargers = new ArrayList<>();
			
			for (Charger charger: chargers) {
				if (charger.canCover(this.x, this.y))
					newChargers.add(charger);
			}
			
			return newChargers;
		}
	}
	
	static class Charger {
		private int x;
		private int y;
		private int coverage;
		private int performance;
		
		public Charger(int x, int y, int coverage, int performance) {
			super();
			this.x = x - 1; // index
			this.y = y - 1; // index
			this.coverage = coverage;
			this.performance = performance;
		};

		/**
		 * 특정 좌표를 이 충전기가 커버하는지
		 * @param personX
		 * @param personY
		 * @return 커버 여부
		 */
		public boolean canCover(int personX, int personY) {
			return (Math.abs(personX - this.x) + Math.abs(personY - this.y) <= this.coverage); 
		}
		
		public int getPerformance() {
			return this.performance;
		}
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			System.out.printf("#%d %d\n", t, getMaxTotalChargeAmount());
		}
	}

	private static int getMaxTotalChargeAmount() throws IOException, NumberFormatException {
		
		caseInit();

		// case 별 최대 충전량 -> 정답 변수
		int maxTotalChargeAmount = 0;

		// 이동 전 초기 상태에서 최댓값 합산
		maxTotalChargeAmount += getMaxChargeAmountInPosition();

		// 이동 후 위치 상태에서 최댓값 합산
		for (int m = 0; m < M; m++) {
			personA.move(movesA[m]);
			personB.move(movesB[m]);
			maxTotalChargeAmount += getMaxChargeAmountInPosition();
		}
		
		return maxTotalChargeAmount;
	}

	/**
	 * PersonA, PersonB 의 특정 위치 상태에서, 가능한 충전기 정보를 모두 파악한 후 최댓값을 갱신하여 리턴한다
	 * @return 특정 위치 상태에서의 최대 충전량
	 */
	private static int getMaxChargeAmountInPosition() {

		ArrayList<Charger> pABCs = personA.getUsableChargers();
		ArrayList<Charger> pBBCs = personB.getUsableChargers();

		// 현재 위치에서 가능한 Charger 들의 조합을 비교한다
		int maxChargeAmountInPosition = 0;
		if (!pABCs.isEmpty() && !pBBCs.isEmpty()) {
			for (Charger chA: pABCs) {
				for (Charger chB: pBBCs) {
					// 겹치는 경우
					if (chA.equals(chB)) {
						// 겹치게 계산할 수밖에 없는 경우. chA/2 + chB/2 == chA == chB
						if (pABCs.size() == 1 && pBBCs.size() == 1) {
							maxChargeAmountInPosition = Math.max(maxChargeAmountInPosition, chA.getPerformance());
						}
					} else {
						// 그 외는 무조건 다른 조합으로 선택하는 것이 크다
						maxChargeAmountInPosition = Math.max(maxChargeAmountInPosition, chA.getPerformance() + chB.getPerformance());
					}
				}
			}
		} else if (!pABCs.isEmpty()) {
			for (Charger chA: pABCs)
				maxChargeAmountInPosition = Math.max(maxChargeAmountInPosition, chA.getPerformance());
		} else if (!pBBCs.isEmpty()) {
			for (Charger chB: pBBCs)
				maxChargeAmountInPosition = Math.max(maxChargeAmountInPosition, chB.getPerformance());
		}

		return maxChargeAmountInPosition;
	}
	
	private static void caseInit() throws IOException, NumberFormatException {
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 총 이동시간
		A = Integer.parseInt(st.nextToken()); // BC 의 개수
		
		movesA = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		movesB = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
		
		chargers = new Charger[A];
		for (int i = 0; i < A; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int coverage = Integer.parseInt(st.nextToken());
			int performance = Integer.parseInt(st.nextToken());
			chargers[i] = new Charger(x, y, coverage, performance);
		}
		
		// 초기 상태에서 접근 가능한 충전기를 찾기 위해
		// charger 먼저 세팅 후 person 객체 생성.
		// 생성자 내부에서 charger 를 통해 접근가능한 충전기 리스트 초기화
		personA = new Person(0, 0);
		personB = new Person(9, 9);
	}
}