package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 240213
public class BOJ_G5_2138 {

	static int bitLen;
	static char[] myBits;
	static char[] targetBits;

	public static void main(String[] args) throws IOException, NumberFormatException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		bitLen = Integer.parseInt(br.readLine());
		myBits = br.readLine().toCharArray();
		targetBits = br.readLine().toCharArray();

		int result = -1;

		int resultA = getResult(true);
		int resultB = getResult(false);

		if (resultA != -1 && resultB != -1)
			result = Math.min(resultA, resultB);
		else if (resultA != -1)
			result = resultA;
		else if (resultB != -1)
			result = resultB;

		System.out.println(result);
	}

	private static int getResult(boolean firstIsClicked) {

		int clickCount = 0;
		char[] myTempBits = Arrays.copyOf(myBits, myBits.length);

		if (firstIsClicked) {
			clickCount += 1;
			turnOnOffSwitches(myTempBits, 0);
		}

		for (int i = 1; i < bitLen; i++) {
			if (myTempBits[i - 1] == targetBits[i - 1])
				continue;

			clickCount += 1;
			turnOnOffSwitches(myTempBits, i);
		}

		// 가능 여부 판단
		for (int i = 0; i < bitLen; i++) {
			if (myTempBits[i] != targetBits[i])
				return -1;
		}

		return clickCount;
	}

	// 위치에 맞게 스위치 turn and off
	private static void turnOnOffSwitches(char[] curBit, int switchIdx) {

		if (switchIdx == 0)
			for (int i = switchIdx; i <= switchIdx + 1; i++)
				turnOnOff(curBit, i);
		else if (switchIdx == bitLen - 1)
			for (int i = bitLen - 1; i >= bitLen- 2; i--)
				turnOnOff(curBit, i);
		else
			for (int i = switchIdx - 1; i <= switchIdx + 1; i++)
				turnOnOff(curBit, i);
	}

	private static void turnOnOff(char[] curBit, int switchIdx) {
		if (curBit[switchIdx] == '1')
			curBit[switchIdx] = '0';
		else if (curBit[switchIdx] == '0')
			curBit[switchIdx] = '1';
	}
}