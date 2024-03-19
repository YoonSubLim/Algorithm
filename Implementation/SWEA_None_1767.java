package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_None_1767 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N; // 맵 크기
    static int map[][]; // 맵
    static List<Core> cores; // 코어 리스트
    
    static int[] dys = {-1, 1, 0, 0};
    static int[] dxs = {0, 0, -1, 1};
    
    static Line[] drawedLines; // core 들이 그린 선분
    static int maxCnt, minTotalLength; // 최대 선분 수, 최소 선분 길이합
    
    public static void main(String[] args) throws IOException, NumberFormatException {

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            System.out.printf("#%d %d\n", t, makeResult());
        }
    }

    private static int makeResult() throws IOException, NumberFormatException {
        init();
        go(0, 0, 0);
        return minTotalLength;
    }

    /**
     * 특정 코어를 4방 중 가능한 방향으로 연결 후, 다음 코어로 재귀 호출
     * @param coreNum 처리 중인 코어
     * @param selectedCnt 연결된 코어 수
     * @param totalLength 연결된 전선 길의의 합
     */
    private static void go(int coreNum, int selectedCnt, int totalLength) {
    	
    	// 가지치기
    	// 선택된 코어 수 + 앞으로 모두 선택되었을 때의 코어 수 < 현재까지 가능한 코어 최대수
    	if (selectedCnt + (cores.size() - coreNum) < maxCnt)
    		return;
    	
        if (coreNum == cores.size()) {            
        	if (selectedCnt > maxCnt) {
        		// 값 고려없이 갱신
        		maxCnt = selectedCnt;
        		minTotalLength = totalLength;
        	}
        	else if (selectedCnt == maxCnt) {
        		// 최솟값으로 갱신
        		minTotalLength = Math.min(minTotalLength, totalLength);
        	}            
            return;
        }

        Core core = cores.get(coreNum);

        for (int d = 0; d < 4; d++) {
            Line drawedLine = core.drawLineIfPossible(d);
            
            if (drawedLine != null) {
                drawedLines[coreNum] = drawedLine; // 연결된 라인 추가
                go(coreNum + 1, selectedCnt + 1, totalLength + drawedLine.getLength());
                drawedLines[coreNum] = null; // null : 선택 해제
            } else {
                go(coreNum + 1, selectedCnt, totalLength);
            }
        }
    }

    private static void init() throws IOException, NumberFormatException {

        N = Integer.parseInt(br.readLine());
        cores = new ArrayList<>();

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 고려되는 위치에 코어(1)가 존재한다면 리스트에 추가
                if (0 < r && r < N - 1 && 0 < c && c < N - 1 && map[r][c] == 1) {
                    cores.add(new Core(new Point(r, c)));
                }
            }
        }
        drawedLines = new Line[cores.size()];
        maxCnt = Integer.MIN_VALUE;
        minTotalLength = Integer.MAX_VALUE;
    }

    static class Core {
        Point location;

        public Core(Point location) {
            this.location = location;
        }

        Line drawLineIfPossible(int direction) {

            int r = location.r;
            int c = location.c;

            while (true) {
                r += dys[direction];
                c += dxs[direction];

                if (map[r][c] == 1)
                    return null; // 선 그을 수 없음
                if (!(0 < r && r < N-1) || !(0 < c && c < N-1)) // 가장자리 도착하면
                    break;
            }
            
            // 해당 Point 와 Core 의 Point 를 끝점으로 하는 선 생성
            Line newLine = new Line(this.location, new Point(r, c));
            // 생성된 선이 이미 선택된 다른 선들과 교차하는지 판단
            for (int i = 0; i < drawedLines.length; i++) {
            	// 교차하는 경우 선 그을 수 없음
            	if (drawedLines[i] != null && drawedLines[i].isCrossing(newLine)) return null;
            }            
            
            return newLine;
        }
    }

    static class Line {
    	
        Point start, end;
        
        public Line(Point start, Point end) {
        	this.start = start;
        	this.end = end;
        }
        // 수평 수직 선의 길이 구하기
        int getLength() {
            return Math.abs(start.r - end.r + start.c - end.c);
        }
        
        // 두 선분의 교차 판별
        boolean isCrossing(Line other) {
        	
        	// CCW 알고리즘
        	int x1 = this.start.r;
        	int y1 = this.start.c;
        	int x2 = this.end.r;
        	int y2 = this.end.c;
        	int x3 = other.start.r;
        	int y3 = other.start.c;
        	int x4 = other.end.r;
        	int y4 = other.end.c;
        	
        	//ABC
        	int abc = CCW(x1, y1, x2, y2, x3, y3);
        	//ABD
        	int abd = CCW(x1, y1, x2, y2, x4, y4);
        	//CDA
        	int cda = CCW(x3, y3, x4, y4, x1, y1);
        	//CDB
        	int cdb = CCW(x3, y3, x4, y4, x2, y2);

        	if (abc * abd <= 0 && cda * cdb <= 0)
        		return true;
        	else
        		return false;
        }
        
        private static int CCW(int x1, int y1, int x2, int y2, int x3, int y3) {
        	int a = x1 * y2 + x2 * y3 + x3 * y1;
        	int b = y1 * x2 + y2 * x3 + y3 * x1;
        	if (a - b < 0)
        		return 1;
        	else
        		return -1;
        }
    }
    
    static class Point {
    	
        int r, c;
        
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}