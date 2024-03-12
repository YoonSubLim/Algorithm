package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 240221
public class BOJ_G5_1759 {

	static BufferedReader br;
	static StringTokenizer st;
	static int L, C;
	static String alphabets[]; // 주어진 알파벳 배열
	static ArrayList<String> allMoums = new ArrayList(List.of("a", "e", "i", "o", "u")); // 전체 모음
	static ArrayList<String> selectedAlphabets = new ArrayList<>(); // 조합에서 선택된 알파벳
	static ArrayList<String> results = new ArrayList<>(); // 가능한 암호들
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphabets = br.readLine().split("\\s+");

		combination(0);

		// 결과 리스트를 정렬(암호 간 정렬)하여 출력
		Collections.sort(results);
		for (String result : results) {
			System.out.println(result);
		}
	}

	private static void combination(int start) {

		if (selectedAlphabets.size() == L) {
			// 조합에서 모음 개수와 자음 개수가 조건에 일치하지 않으면 return
			int moumCount = (int) selectedAlphabets.stream()
				.filter(alphabet -> allMoums.contains(alphabet))
				.count();
			if (moumCount < 1) return;
			else if (L - moumCount < 2) return;

			// 가능한 암호 조합을 정렬(암호 내 정렬)하여 결과 리스트에 저장
			ArrayList<String> temp = new ArrayList<>(selectedAlphabets);
			Collections.sort(temp);
			StringBuilder sb = new StringBuilder();
			for (String alphabet: temp)
				sb.append(alphabet);
			results.add(sb.toString());
			return;
		}

		for (int i = start; i < C; i++) {
			selectedAlphabets.add(alphabets[i]);
			combination(i + 1);
			selectedAlphabets.remove(selectedAlphabets.size() - 1);
		}
	}
}
