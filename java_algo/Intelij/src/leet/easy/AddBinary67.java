package leet.easy;

public class AddBinary67 {
    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();

        int A = a.length()-1;
        int B = b.length()-1;
        int carry = 0;

        while (A >= 0 || B >= 0 || carry == 1){
            int digitA = A < 0 ? 0 : a.charAt(A--) - '0';
            int digitB = B < 0 ? 0 : b.charAt(B--) - '0';
            sum.insert(0, (digitA + digitB + carry) % 2);
            carry = (digitA + digitB + carry) / 2;
        }
        return sum.toString();
    }
}
