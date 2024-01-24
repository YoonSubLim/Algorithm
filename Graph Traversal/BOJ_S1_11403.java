import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

public class BOJ_S1_11403 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] matrix = new int[N][];
        for (int i = 0; i < N; i++) {
            int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[i] = nums;
        }

        int[][] reachable = new int[N][];

        // i : start node
        for (int i = 0; i < N; i++) {
            LinkedList<Integer> deque = new LinkedList<>();
            int[] visited = new int[N];

            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 1) {
                    deque.addLast(j);
                }
            }

            while (deque.size() != 0) {
                int curIdx = deque.pollFirst();

                visited[curIdx] = 1;
                for (int k = 0; k < N; k++) {
                    if (matrix[curIdx][k] == 1 && visited[k] != 1) {
                        deque.addLast(k);
                    }
                }
            }
            reachable[i] = visited;
        }

        for (int[] row: reachable) {
            for (int num: row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
