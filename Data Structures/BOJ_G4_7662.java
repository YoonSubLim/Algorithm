package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

// 240312
public class BOJ_G4_7662 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			printTestResult();
		}
	}

	private static void printTestResult() throws IOException {

		int k = Integer.parseInt(br.readLine());
		TreeMap<Integer, Integer> tMap = new TreeMap<>();

		for (int i = 0; i < k; i++) {

			String[] cmds = br.readLine().split("\\s+");

			if (cmds[0].equals("I")) {
				int num = Integer.parseInt(cmds[1]);
				tMap.merge(num, 1, Integer::sum);
				continue;
			}

			// cmds[0] == "D"
			if (tMap.isEmpty()) // lastKey / firstKey Exception 처리
				continue;

			int deleteNum = cmds[1].equals("1") ? tMap.lastKey() : tMap.firstKey();
			tMap.merge(deleteNum, -1, Integer::sum);
			if (tMap.get(deleteNum) == 0)
				tMap.remove(deleteNum);
		}

		System.out.println(tMap.isEmpty() ? "EMPTY" : tMap.lastKey() + " " + tMap.firstKey());
	}
}