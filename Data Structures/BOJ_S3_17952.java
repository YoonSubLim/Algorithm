package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 240226
public class BOJ_S3_17952 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException, NumberFormatException {

        int result = 0;

        int N = Integer.parseInt(br.readLine()); // 이번 학기의 분

        Stack<Task> tasks = new Stack<>();
        Task curTask = null;

        for (int t = 0; t < N; t++) {

            String input = br.readLine();

            if (input.equals("0")) {
                if (curTask == null) {
                    continue;
                }

                if (curTask.isFinishedAfterDoTask()) {
                    result += curTask.score;

                    if (!tasks.isEmpty()) {
                        curTask = tasks.pop();
                    } else {
                        curTask = null;
                    }
                }
                continue;
            }

            // 새로운 과제가 주어진 경우
            st = new StringTokenizer(input);
            int N_ = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            // 하던 과제 있으면 스택에 저장
            if (curTask != null) {
                tasks.add(curTask);
            }
            // 새로 할 과제 생성
            curTask = new Task(A, T);

            if (curTask.isFinishedAfterDoTask()) {
                result += curTask.score;

                if (!tasks.isEmpty()) {
                    curTask = tasks.pop();
                } else {
                    curTask = null;
                }
            }
        }

        System.out.println(result);
    }

    static class Task {

        int score;
        int leftMinutes;

        Task(int score, int leftMinutes) {
            this.score = score;
            this.leftMinutes = leftMinutes;
        }

        public boolean isFinishedAfterDoTask() {
            this.leftMinutes -= 1;
            if (leftMinutes == 0) {
                return true;
            }
            return false;
        }
    }
}
