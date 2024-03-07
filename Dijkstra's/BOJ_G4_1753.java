package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 240227
public class BOJ_G4_1753 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, E, K;

    static int[] distance;
    static boolean[] visited;

    static class Node {
        int weight, to;
        Node(int weight, int to) {
            this.weight = weight;
            this.to = to;
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

        distance[start] = 0;

        for (int v = 0; v < V; v++) {
            int nodeValue = Integer.MAX_VALUE;
            int nodeIdx = -1;

            // 방문한 적이 없는, 거리 비용이 최소값인 노드 찾기
            for (int i = 0; i < V; i++) {
                if (!visited[i] && distance[i] < nodeValue) {
                    nodeValue = distance[i];
                    nodeIdx = i;
                }
            }

            if (nodeIdx == -1)
                continue;

            visited[nodeIdx] = true;

            // 해당 노드에서 연결된 노드들의 거리값 갱신
            for (int i = 0; i < nodes.get(nodeIdx).size(); i++) {
                // 연결 정보가 존재하고, 방문한 적이 없으면
                Node adjNode = nodes.get(nodeIdx).get(i);
                // 값 비교 후 갱신
                // (현재까지의 i 로의 거리)와 (선택된 노드를 거쳐 i 로 가는 거리)
                distance[adjNode.to] = Math.min(distance[adjNode.to], distance[nodeIdx] + adjNode.weight);
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
        visited = new boolean[V];
    }
}
