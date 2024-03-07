package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 240304
public class BOJ_G5_13023 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static List<Integer>[] isFriend;
	static boolean[] checked;
	static boolean isEnd;

	public static void main(String[] args) throws IOException, NumberFormatException {
		
		// Init
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isFriend = new ArrayList[N];
		for (int n = 0; n < N; n++) {
			isFriend[n] = new ArrayList<>();
		}
		checked = new boolean[N];

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			isFriend[A].add(B);
			isFriend[B].add(A);
		}

		// Main Logic
		for (int num = 0; num < N; num++) {
			checked[num] = true;
			dfs(1, num);
			checked[num] = false;
			
			if (isEnd) {
				System.out.println(1);
				return;
			}
		}

		System.out.println(0);
	}
	
	private static void dfs(int cnt, int num) {
		// 이미 끝난 경우 전부 종료
		if (isEnd)
			return;

		if (cnt == 5)
			isEnd = true;

		for (int oppoNum: isFriend[num]) {
			if (!checked[oppoNum]) {
				checked[oppoNum] = true;
				dfs(cnt + 1, oppoNum);
				checked[oppoNum] = false;
			}
		}
	}
}
