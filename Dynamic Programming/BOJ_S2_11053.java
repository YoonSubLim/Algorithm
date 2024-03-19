package BOJ;

import java.util.Arrays;
import java.util.Scanner;

// 240319
public class BOJ_S2_11053 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[] cnts = new int[N]; // 자신부터 시작하는 배열 중 가장 긴 증가하는 부분 수열의 길이 (자신 포함)
        cnts[cnts.length - 1] = 1;
        int maxResult = 1;

        for (int i = nums.length - 2; i >= 0; i--) {

            int maxCandidate = 0;

            for (int j = i + 1; j < N; j++) {
                if (nums[j] > nums[i] && maxCandidate < cnts[j]) {
                    maxCandidate = cnts[j];
                }
            }

            cnts[i] = maxCandidate + 1; // 자신 포함

            if (maxResult < cnts[i])
                maxResult = cnts[i];
        }

        System.out.println(maxResult);
    }
}
