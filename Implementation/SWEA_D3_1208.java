package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240130
public class SWEA_D3_1208 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {

            int result = 0;
            int dump = Integer.parseInt(br.readLine());

            int[] map = Arrays.stream(br.readLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .sorted()
                .toArray();

            while (dump != 0) {
                if (map[map.length - 1] == map[0]) {
                    break;
                }

                map[map.length - 1] -= 1;
                map[0] += 1;
                Arrays.sort(map);
                dump -= 1;
            }

            result = map[map.length - 1] - map[0];

            System.out.printf("#%d %d\n", t, result);
        }
    }
}
