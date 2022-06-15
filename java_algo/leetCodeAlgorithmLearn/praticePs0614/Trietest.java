package praticePs0614;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Node {
    HashMap<Character, Node> child;
    boolean isTerminal;

    public Node() {
        this.child = new HashMap<>();
        this.isTerminal = false;
    }
}

class Trie {
    Node root;

    public Trie() {
        this.root = new Node();
    }

    public void insert(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            cur = cur.child.computeIfAbsent(c, key -> new Node());
        }

        cur.isTerminal = true;
    }

    public boolean prefix(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 자식 노드에서 문자를 찾고 있으면 현재 노드를 자식 노드로 업데이트하고 없으면 null로 업데이트
            cur = cur.child.getOrDefault(c, null);

            // 찾는 문자가 없으면 같은 prefix가 아니므로 return false
            if (cur == null) {
                return false;
            }

            // 현재 노드가 문자의 끝이고 현재 노드까지의 문자와 parameter로 들어온 str과 길이가 같지 않으면
            // 현재 노드까지의 문자는 parameter str의 prefix 이므로 return true
            // str이 911이면 길이가 같기 때문에 prefix가 아니고
            // str이 91125426이면 911보다 91125426의 길이가 크기 때문에 911은 str의 prefix 임
            if (cur.isTerminal && i + 1 < str.length()) {
                return true;
            }
        }

        return false;
    }
}

class 전화번호목록 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            List<String> list = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                String str = br.readLine();
                list.add(str);
                trie.insert(str);
            }

            boolean prefix_cheek = false;
            for (int j = 0; j < list.size(); j++) {
                if (trie.prefix(list.get(j))) {
                    prefix_cheek = true;
                    break;
                }
            }

            if (prefix_cheek) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}