package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 240129
public class SWEA_D3_1289 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            int count = 0;
            char[] bits = br.readLine().toCharArray();

            for (int i = 0; i < bits.length; i++) {

                if (bits[i] == '1') {

                    count += 1;

                    for (int j = i; j < bits.length; j++) {
                        if (bits[j] == '1')
                            bits[j] = '0';
                        else
                            bits[j] = '1';
                    }
                }
            }
            System.out.printf("#%d %d\n", t, count);
        }
    }
}