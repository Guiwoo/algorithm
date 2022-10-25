package programmers.codingTest_1016;

public class Solution{
    public static void main(String[] args) {
        Solution s = new Solution();
        double calculate = s.calculate(16);
        System.out.println(calculate);
    }

    private final int DECK = 54;
    private final int CARD = 13;
    private final int JOKER = 2;
    public double sol(int people){
        if(people > 54) throw new RuntimeException("There is no card");
        return calculate(people);
    }
    private double calculate(int n){
        float denominator = 1;
        float molecule = 1;

        for (int i = 1; i < n; i++) {
            denominator *= DECK - i;
            molecule *= (CARD+JOKER) - i;
        }
        return molecule/denominator;
    }
}