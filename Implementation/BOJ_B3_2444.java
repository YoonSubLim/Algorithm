package BOJ;

import java.util.Scanner;

// 240323
public class BOJ_B3_2444 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        for (int i = 0; i < 2 * N - 1; i++) {

            int start = 0 + Math.abs((2 * N - 1) / 2 - i - 0); // 시작점 (0)부터 오른쪽으로 이동하는데, 중앙에서 i 만큼 왼쪽으로 떨어진 것과 시작점과의 차이만큼 이동한다
            int end = (2 * N - 2) - Math.abs((2 * N - 1) / 2 + i - (2 * N - 2)); // 끝점 (2N - 2)부터 왼쪽으로 이동하는데, 중앙에서 i 만큼 오른쪽으로 떨어진 것과 끝점과의 차이만큼 이동한다

            StringBuilder sb = new StringBuilder();
            sb.append(" ".repeat(start));
            sb.append("*".repeat(end - start + 1));

            System.out.println(sb.toString());
        }
    }
}
