package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 240227
public class BOJ_G4_1753_PriorityQueue {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, E, K;

    static int[] distance;

    // 현재 노드 번호와, 현재 노드까지의 도달 비용을 나타낸다
    static class Node {
        int weight, num;
        Node(int weight, int num) {
            this.weight = weight;
            this.num = num;
        }
    }

    static List<ArrayList<Node>> nodes;

    public static void main(String[] args) throws IOException, NumberFormatException {

        init();
        dijkstra(K - 1);

        for (int dist: distance) {
            if (dist == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(dist);
        }
    }

    private static void dijkstra(int start) {

        PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.weight, o2.weight));

        // start 번 노드까지의 비용은 0
        q.offer(new Node(0, start));

        distance[start] = 0;

        while (!q.isEmpty()) {

            // 현재 최소 비용을 갖는 노드
            Node curNode = q.poll();

            // '현재 노드 번호에 저장된 비용' 값이 '현재 경로에서의 비용'보다 적으면,
            // 이전에 이미 최저 비용으로 갱신된 것이므로, 굳이 비교해볼 필요 없음
            // 방문한 노드를 재방문하는 것을 방지한다
            if (distance[curNode.num] < curNode.weight) {
                continue;
            }

            // 현재 노드 번호에 연결된 다음 노드 탐색
            for (int i = 0; i < nodes.get(curNode.num).size(); i++) {
                Node nextNode = nodes.get(curNode.num).get(i);

                // 다음 노드로, 현재 노드를 경유해 가는 경우의 비용이 더 적은 경우, 거리 갱신
                // 이때의 curNode 는 우선순위 큐에서 꺼낸 값으로, curNode.num 까지의 총 거리
                // 이때의 nextNode 는 node list 에서 꺼낸 값으로, curNode.num - nextNode.num 까지의 거리
                if (distance[nextNode.num] > curNode.weight + nextNode.weight) {
                    distance[nextNode.num] = curNode.weight + nextNode.weight;
                    // 갱신된 노드에 대해서만 우선순위 큐로 관리한다
                    q.offer(new Node(distance[nextNode.num], nextNode.num));
                }
            }
        }
    }

    private static void init() throws IOException, NumberFormatException {

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        nodes = new ArrayList<ArrayList<Node>>(V);
        for (int v = 0; v < V; v++) {
            nodes.add(new ArrayList<Node>());
        }

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            nodes.get(u).add(new Node(w, v));
        }

        distance = new int[V];
        for (int v = 0; v < V; v++) {
            distance[v] = Integer.MAX_VALUE;
        }
    }
}
