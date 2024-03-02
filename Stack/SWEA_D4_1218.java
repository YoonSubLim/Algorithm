package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

// 240202
public class SWEA_D4_1218 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Character> open = new ArrayList<>(Arrays.asList('(', '[', '{', '<'));
	static ArrayList<Character> close = new ArrayList<>(Arrays.asList(')', ']', '}', '>'));
	
	public static void main(String[] args) throws Exception {

		for (int t = 1; t <= 10; t++) {
			System.out.printf("#%d %d\n", t, makeResult());
		}
	}

	private static int makeResult() throws Exception {
		
		int result = 1;

		int len = Integer.parseInt(br.readLine());
		String brakets = br.readLine();
		
		Stack<Character> st = new Stack<>(); 
		
		for (int i = 0; i < len; i++) {
			Character ch = brakets.charAt(i);
			
			// 열리는 괄호라면
			if (open.contains(ch)) {
				st.add(ch);
				continue;
			}
		
			// 닫히는 괄호라면, stack 의 top 을 pop 하여 같은 종류의 괄호인지 비교
			Character top = st.pop();
			// 비교할 닫히는 괄호를 찾기 위한 index 찾기
			int idx = open.indexOf(top);
			// 같은 종류의 괄호가 아니라면 break
			if (ch != close.get(idx)) {
				result = 0;
				break;
			}
		}
		
		return result;
	}
}
