package ShortestPass;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Boj1865 {
    static class Edge {
        int from;
        int to;
        int time;

        public Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int repeat = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeat; i++) {
            // 0 번 점 개수, 1번 도로개수, 2번 웜홀개수
            String[] inputs = bf.readLine().split(" ");
            int V = Integer.parseInt(inputs[0]);
            int E = Integer.parseInt(inputs[1]);
            int W = Integer.parseInt(inputs[2]);
            // Graph Initalized
            ArrayList<Edge> graph = new ArrayList<>();
            for (int e = 0; e < E; e++) {
                String[] input = bf.readLine().split(" ");
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                int time = Integer.parseInt(input[2]);
                graph.add(new Edge(from, to, time));
                graph.add(new Edge(to, from, time));
            }
            for (int w = 0; w < W; w++) {
                String[] input = bf.readLine().split(" ");
                int from = Integer.parseInt(input[0]);
                int to = Integer.parseInt(input[1]);
                int time = -1 * Integer.parseInt(input[2]);
                graph.add(new Edge(from, to, time));
            }

            boolean cycle = false;
            for (int x = 1; x <= V; x++) {
                if (bellmanFord(V, graph, x)) {
                    cycle = true;
                    sb.append("YES\n");
                    break;
                }
            }
            if (!cycle) {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
        bf.close();
    }

    static boolean bellmanFord(int v, ArrayList<Edge> graph, int start) {
        int[] dp = new int[v + 1];
        Arrays.fill(dp, 0);
        dp[start] = 0;

        boolean relaxedAnEdge = true;
        for (int i = 0; i < v - 1 && relaxedAnEdge; i++) {
            relaxedAnEdge = false;
            for (Edge edge : graph) {
                if (dp[edge.from] + edge.time < dp[edge.to]) {
                    dp[edge.to] = dp[edge.from] + edge.time;
                    relaxedAnEdge = true;
                }
            }
        }

        relaxedAnEdge = true;
        for (int i = 0; i < v - 1 && relaxedAnEdge; i++) {
            relaxedAnEdge = false;
            for (Edge edge : graph) {
                if (dp[edge.from] + edge.time < dp[edge.to]) {
                    dp[edge.to] = Integer.MIN_VALUE;
                    relaxedAnEdge = true;
                }
            }
        }
        for (int d : dp) {
            if (d < 0)
                return false;
        }
        return true;
    }

    static boolean belmanFord2(int v, ArrayList<Edge> graph, int start) {
        int[] dist = new int[v + 1];
        Arrays.fill(dist, 1, dist.length, 1 << 30);
        dist[start] = 0;
        boolean isMinusCycle = false;
        for (int i = 0; i < v + 1; i++) {
            for (int j = 0; j < graph.size(); j++) {
                Edge cur = graph.get(j);
                if (dist[cur.from] == Integer.MAX_VALUE)
                    continue;
                if (dist[cur.to] > dist[cur.from] + cur.time) {
                    dist[cur.to] = dist[cur.from] + cur.time;

                    if (i == v) {
                        isMinusCycle = true;
                    }
                }
            }
        }
        return isMinusCycle;
    }
}
