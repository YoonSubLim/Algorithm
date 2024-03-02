package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 240205
public class BOJ_S1_13335 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int truckNum = Integer.parseInt(st.nextToken()); // n (1 ≤ n ≤ 1,000)
        int bridgeLen = Integer.parseInt(st.nextToken()); // w (1 ≤ w ≤ 100)
        int bridgeWeight = Integer.parseInt(st.nextToken()); // L (10 ≤ L ≤ 1,000)

        int[] truckWeight = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        int currentTruckIdx = 0;
        int result = 0;

        while (true) {
            if (deque.size() == bridgeLen) {
                deque.pollFirst();
            }

            if (currentTruckIdx < truckNum && deque.stream().mapToInt(Integer::intValue).sum() + truckWeight[currentTruckIdx] <= bridgeWeight) {
                deque.addLast(truckWeight[currentTruckIdx++]);
            } else {
                deque.addLast(0);
            }

            result++;

            if (deque.stream().mapToInt(Integer::intValue).sum() == 0)
                break;
        }

        System.out.println(result);
    }
}
