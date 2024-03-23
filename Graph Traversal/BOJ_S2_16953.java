package BOJ;

import java.util.ArrayDeque;
import java.util.Scanner;

// 240321
public class BOJ_S2_16953 {

    static long A, B;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        A = sc.nextInt();
        B = sc.nextInt();

        int result = 0;

        ArrayDeque<Long> deque = new ArrayDeque<>();
        deque.add(A);

        while (!deque.isEmpty()) {
            int size = deque.size();

            for (int i = 0; i < size; i++) {
                long num = deque.poll();

                if (num == B) {
                    System.out.println(result + 1);
                    return;
                } else if (num > B) {
                    continue;
                }

                deque.add(num * 2);
                deque.add(num * 10 + 1);
            }
            result += 1;
        }
        System.out.println(-1);
    }
}
