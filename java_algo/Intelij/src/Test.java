import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args){
        System.out.println(draw(2,0,0));
    }
    public static int draw (int n, int x, int y) {
        // n이 1이면 1
        if (n == 1) return 1;
        else if (y < n/2 && x < n/2) return (int) (Math.pow((n/2), 2) + draw(n/2, x%(n/2), y%(n/2)));
        else if (y < n/2 && x >= n/2) return draw(n/2, x%(n/2), y%(n/2));
        else if (y >= n/2 && x < n/2) return (int) ((Math.pow((n/2), 2) * 2) + draw(n/2, x%(n/2), y%(n/2)));
        else return (int) ((Math.pow((n/2), 2) * 3) + draw(n/2, x%(n/2), y%(n/2)));
    }
    static public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for(int i =0; i<count.length; i++){
            System.out.println(Character.toString((char)('a'+i))+" : 개수 " + count[i]);
        }
        if (allZero(count)) return true;

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }

        return false;
    }

    static private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }
}


