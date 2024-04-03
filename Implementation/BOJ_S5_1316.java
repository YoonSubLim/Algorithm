package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 240325
public class BOJ_S5_1316 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            boolean isGroupWord = true;

            boolean[] checked = new boolean[26];
            checked[word.charAt(0) - 'a'] = true; // 첫번째 알파벳 체크

            for (int j = 1; j < word.length(); j++) {
                if (word.charAt(j) != word.charAt(j - 1)) {
                    if (checked[word.charAt(j) - 'a']) {
                        isGroupWord = false;
                        break;
                    } else {
                        checked[word.charAt(j) - 'a'] = true;
                    }
                }
            }

            if (isGroupWord) cnt++;
        }

        System.out.println(cnt);
    }
}
