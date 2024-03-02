package BOJ;

import java.util.Scanner;

public class BOJ_S5_17478 {

    // depth 별로 앞에 붙을 접두어
    private static final String prefix = "____";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int depth = 0;

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        printResponse(N, depth);
        scanner.close();
    }

    private static void printResponse(int N, int depth) {

        StringBuilder sb = new StringBuilder();
        for (int d = 0; d < depth; d++) {
            sb.append(prefix);
        }
        String prefixOfDepth = sb.toString();

        System.out.print(prefixOfDepth + "\"재귀함수가 뭔가요?\"\n");

        if (N == 0) {
            sb = new StringBuilder();
            sb.append(prefixOfDepth + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
            System.out.print(sb);
        } else {
            sb = new StringBuilder();
            sb.append(prefixOfDepth + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
            sb.append(prefixOfDepth + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
            sb.append(prefixOfDepth + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
            System.out.print(sb);

            printResponse(N - 1, depth + 1);
        }

        System.out.print(prefixOfDepth + "라고 답변하였지.\n");
    }
}
