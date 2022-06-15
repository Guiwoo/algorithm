package praticePs0614;

import java.util.*;
import java.io.*;

class Boj5052 {
    public String solution(String[] arr) {
        Arrays.sort(arr);
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i].startsWith(arr[i - 1])) {
                return "No";
            }
        }
        return "YES";
    }
}

class Boj5051 {
    public void solution(int[][] input, int k) {
        // 방문 처리해줄 예쁜 그리드
        boolean[][] grid = new boolean[k + 1][k + 1];
        for (int i = 0; i < input.length; i++) {
            int row = input[i][0];
            int col = input[i][1];
            // 앞뒤로 즉 뒤집어서 도 다 트루 체크 해주자 상대방도 감염 여부를 파악해야 하니
            grid[row][col] = true;
            grid[col][row] = true;
        }
        // 감연된 컴퓨터 넣어줄 부분 중복된 부분 있으면 처리하기 귀찮으니 셋 을 아예 만들어 주었다.
        Set<Integer> result = new HashSet<>();
        Queue<Integer> q = new LinkedList();
        // 1번 컴퓨터를 이용해 최초 감염 컴퓨터 들을 큐에 넣는 부분
        for (int i = 0; i < grid[1].length; i++) {
            if (grid[1][i]) {
                q.add(i);
                result.add(i);
            }
        }
        // 하나씩 제거해가며 트루인 부분을 찾아 다시 큐에 넣어준다
        while (!q.isEmpty()) {
            int computerNum = q.poll();
            // 1번은 이미 위에서 체크했으니 2번부터 시작하자.
            for (int i = 2; i < grid[computerNum].length; i++) {
                if (grid[computerNum][i] && !result.contains(i)) {
                    result.add(i);
                    q.add(i);
                }
            }
        }
        System.out.println(result.size());
    }
}

class BojExtra {
    static int N, E, cnt;
    static int graph[][];
    static boolean visit[];

    public static void dfs(int i) {
        visit[i] = true;

        for (int j = 1; j <= N; j++) {
            if (graph[i][j] == 1 && visit[j] == false) {
                dfs(j);
                cnt++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        visit = new boolean[N + 1];

        while (E-- != 0) {
            String str[] = br.readLine().split(" ");
            int t1 = Integer.parseInt(str[0]);
            int t2 = Integer.parseInt(str[1]);
            graph[t1][t2] = graph[t2][t1] = 1;
        }

        dfs(1);
        System.out.println(cnt);
    }

}

class BojExtra2 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            q.add(new Node(scoville[i]));
        }
        while (q.peek().val < K) {
            Node low1 = q.poll();
            Node low2 = q.poll();

            q.add(new Node(low1.val + (low2.val * 2)));
            answer++;
            if (q.peek().val >= K) {
                break;
            }
            if (q.peek().val < K && q.size() == 1) {
                answer = -1;
                break;
            }
        }
        return answer;
    }

    class Node implements Comparable<Node> {
        int val;

        Node(int data) {
            this.val = data;
        }

        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }

    }

    public int solution3(int[] scoville, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        int result = 0;

        for (int n : scoville) {
            queue.add(n);
        }
        System.out.println(queue);

        while (queue.peek() < k && queue.size() >= 2) { // 2보다 커야 두개를 poll()할 수 있음
            ++result;
            int s1 = queue.poll(); // PriorityQueue는 가장 작은 값부터 뽑는다.
            int s2 = queue.poll();
            queue.offer(s1 + s2 * 2);
            System.out.println(queue);
        }

        if (queue.peek() < k) { // while문 돌린 후에도 가장 작은 값이 K보다 작으면 -1 리턴
            return -1;
        }
        return result;
    }

    public int solution2(int[] scoville, int k) {
        int answer = 0;
        Queue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            q.add(new Node(scoville[i]));
        }
        while (q.peek().val < k) {
            int a1 = q.poll().val;
            int a2 = q.poll().val;

            int sum = a1 + (a2 * 2);
            q.add(new Node(sum));
            answer++;

            if (q.peek().val >= k) {
                break;
            }
            if (q.peek().val < k && q.size() == 1) {
                answer = -1;
                break;
            }
        }
        return answer;
    }
}
