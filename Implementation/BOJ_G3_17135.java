package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ_G3_17135 {

	static class Enemy {

		int y;
		int x;

		public Enemy(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public void move() {
			this.y += 1;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Enemy)
				return (this.y == ((Enemy) obj).y && this.x == ((Enemy) obj).x);
			return super.equals(obj);
		}
	}

	static class Archor {

		int y;
		int x;
		int distance;

		public Archor(int y, int x, int distance) {
			this.y = y;
			this.x = x;
			this.distance = distance;
		}

		public Enemy getAttackEnemy() {

			// 가장 가까운 거리의 적을 공격해야하므로, d = 1 ~ D 까지 돌며 적이 존재하는 경우의 적들을 반환한다.
			for (int d = 1; d <= D; d++) {
//				for (int row = tempMap.size() - 1; row >= tempMap.size() - d; row--) {
//					for (int col = this.x - d + 1; col <= this.x + d - 1; col++) {
//
//						if (!(0 <= col && col < M))
//							continue;
//
//						if (Math.abs(row - this.y) + Math.abs(col - this.x) != d)
//							continue;
//
//						Enemy enemy = tempMap.get(row)[col];
//						if (enemy != null) {
//							return enemy;
//						}
//					}
//				}

				for (int col = this.x - (d - 1); col <= this.x + (d - 1); col++) {

					if (!(0 <= col && col < M))
						continue;

					int yIndex = tempMap.size() - Math.abs(Math.abs(col - this.x) - d);
//					System.out.printf("현재 위치 : [%d %d] \n", yIndex, col);
//					printMap();
					if (yIndex < 0)
						continue;

					Enemy enemy = tempMap.get(yIndex)[col];
					if (enemy != null) {
//						System.out.println(d);
						return enemy;
					}
				}
			}

			return null;
		}
	}

	static int N;
	static int M;
	static int D;
	static List<Enemy> enemies;
	static List<Archor> archors;
	static int[] selectedIdx;
	static int maxResult;
	static ArrayList<Enemy[]> map;
	static ArrayList<Enemy[]> tempMap;

	public static void main(String[] args) throws IOException, NumberFormatException {

		init(); // 초기화
		comb(0, 0); // 궁수의 위치 조합 세팅 후 최대 결과값 업데이트
		System.out.println(maxResult);
	}

	/**
	 * 초기화 함수
	 * 적들이 위치해있는 정보를 표시하는 map 을 한 줄 단위로 빼고 넣기 위해,
	 * ArrayList<Enemy[]> 로 세팅하며 Enemy[] 에 null 이면 적이 없고, 객체가 있다면 적이 있음
	 * @throws IOException, NumberFormatException
	 */
	private static void init() throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		enemies = new ArrayList<>();
		selectedIdx = new int[3];
		maxResult = Integer.MIN_VALUE;
		map = new ArrayList<Enemy[]>();

		// 1이면 배열에 Enemy 객체를 넣어 라인 단위(Enemy 배열)로 리스트에 추가한다.
		for (int n = 0; n < N; n++) {

			Enemy[] rowEnemies = new Enemy[M];

			char[] row = br.readLine().replaceAll(" ", "").toCharArray();
			for (int m = 0; m < M; m++) {
				if (row[m] == '1')
					rowEnemies[m] = new Enemy(n, m);
			}
			map.add(rowEnemies);
		}
	}

	private static void comb(int count, int startIdx) {

		// 조합이 선택된 상황
		if (count == 3) {
			// 조합에 따라 궁수의 좌표를 설정하여 리스트에 추가
			mapInit();
			archors = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				archors.add(new Archor(N, selectedIdx[i], D));
			}

			// 주어진 상황에서 결과를 구해 반영
			updateResult();
			return;
		}

		for (int i = startIdx; i < M; i++) {
			selectedIdx[count] = i;
			comb(count + 1, i + 1);
		}
	}

	private static void mapInit() {
		tempMap = new ArrayList<>();
		for (int i = 0; i < map.size(); i++) {

			Enemy[] newEnemies = new Enemy[M];
			Enemy[] originalEnemies = map.get(i);

			for (int j = 0; j < M; j++) {
				if (originalEnemies[j] != null)
					newEnemies[j] = new Enemy(originalEnemies[j].y, originalEnemies[j].x);
			}
			tempMap.add(newEnemies);
		}
	}

	private static void updateResult() {

		// 현 궁수 조합으로 잡을 수 있는 최대 결과값
		int attackedEnemyCount = 0;

		for (int n = 0; n < N; n++) {
//			printMap();
			attackedEnemyCount += attackEnemy();
			moveAllEnemies(); // 적 이동
		}

		maxResult = Math.max(maxResult, attackedEnemyCount);
	}

	private static void moveAllEnemies() {

		tempMap.remove(tempMap.size() - 1); // 마지막 열은 삭제 (맵 밖으로 이동)

		for (int n = 0; n < tempMap.size(); n++) {
			for (int m = 0; m < M; m++) {
				Enemy enemy = tempMap.get(n)[m];
				if (enemy != null)
					enemy.move();
			}
		}

		tempMap.add(0, new Enemy[M]); // 첫째 줄 추가 (적이 없는 null 배열)
	}

	// 최대한 겹치지 않도록 적을 제거해야 함 -> 어쩔 수 없는 경우에만 같은 적 공격하도록
	private static int attackEnemy() {

		List<Enemy> attackEnemies = new ArrayList<>();

		int count = 0;
		// 공격할 수 있는 최소 거리의 적 받아오기
		for (Archor archor: archors) {

			Enemy enemy = archor.getAttackEnemy();

			if (enemy != null) {
				attackEnemies.add(enemy);
			}
		}

		List<Enemy> distinctEnemies = attackEnemies.stream().distinct().collect(Collectors.toList());

		distinctEnemies.forEach(enemy -> tempMap.get(enemy.y)[enemy.x] = null);

		count += distinctEnemies.size();

		return count;
	}

	private static void printMap() {

		for (Enemy[] row: tempMap) {
			for (Enemy enemy: row) {
				if (enemy == null) {
					System.out.print("O");
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}