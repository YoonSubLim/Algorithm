package BOJ;

import java.util.Scanner;

// 240318
public class BOJ_S5_2941 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[] input = sc.nextLine().toCharArray();
        int N = input.length;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (input[i] == 'c' && (i + 1 < N && (input[i + 1] == '=' || input[i + 1] == '-'))) {
                i += 1;
            } else if (input[i] == 'd') {
                if (i + 1 < N && input[i + 1] == '-') {
                    i += 1;
                } else if (i + 2 < N && input[i + 1] == 'z' && input[i + 2] == '=') {
                    i += 2;
                }
            } else if (input[i] == 'l' && (i + 1 < N && (input[i + 1] == 'j'))) {
                i += 1;
            } else if (input[i] == 'n' && (i + 1 < N && (input[i + 1] == 'j'))) {
                i += 1;
            } else if (input[i] == 's' && (i + 1 < N && (input[i + 1] == '='))) {
                i += 1;
            } else if (input[i] == 'z' && (i + 1 < N && (input[i + 1] == '='))) {
                i += 1;
            }

            cnt++;
        }

        System.out.println(cnt);
    }
}
