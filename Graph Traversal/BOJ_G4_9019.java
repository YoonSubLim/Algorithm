package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

// 240313
public class BOJ_G4_9019 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int D = 0;
    static final int S = 1;
    static final int L = 2;
    static final int R = 3;
    static boolean[] isChecked;

    public static void main(String[] args) throws IOException, NumberFormatException {

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            makeResult();
        }

        br.close();
    }

    private static void makeResult() throws IOException, NumberFormatException {

        isChecked = new boolean[10000];

        String[] input = br.readLine().split("\\s+");
        Register register = new Register(Integer.parseInt(input[0]), "");
        bfs(register, Integer.parseInt(input[1]));
    }

    private static void bfs(Register register, int target) {

        ArrayDeque<Register> deq = new ArrayDeque<>();
        deq.add(register);
        isChecked[register.storedValue] = true;

        while (!deq.isEmpty()) {

            Register curReg = deq.poll();

            for (int cmd = 0; cmd < 4; cmd++) {

                int operateResult = curReg.operate(cmd);
                String operateCommand = curReg.commands + getCharByNum(cmd);

                if (!isChecked[operateResult]) {

                    // 종료 조건
                    if (operateResult == target) {
                        System.out.println(operateCommand);
                        return;
                    }

                    isChecked[operateResult] = true;
                    deq.add(new Register(operateResult, operateCommand));
                }
            }
        }
    }

    static class Register {
        int storedValue;
        String commands;

        public Register(int storedValue, String commands) {
            super();
            this.storedValue = storedValue;
            this.commands = commands;
        }

        public int operate(int command) {

            int num = storedValue;

            switch (command) {

                case D:
                    return num * 2 % 10000;
                case S:
                    return --num < 0 ? 9999 : num;
                case L:
                    return (num % 1000) * 10 + num / 1000;
                case R:
                    return (num % 10) * 1000 + num / 10;
            }
            return -1;
        }
    }

    public static String getCharByNum(int num) {
        switch (num) {
            case 0: return "D";
            case 1: return "S";
            case 2: return "L";
            case 3: return "R";
        }
        return null;
    }
}