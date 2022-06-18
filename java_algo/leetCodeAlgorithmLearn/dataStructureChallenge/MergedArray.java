package dataStructureChallenge;

class MergeSortedArray {
    public void merge(int[] a, int aLen, int[] b, int bLen) {
        int aIdx = aLen - 1;
        int bIdx = bLen - 1;
        int totalIdx = aLen + bLen - 1;
        while (bIdx >= 0) {
            if (aIdx >= 0 && a[aIdx] >= b[bIdx]) {
                a[totalIdx--] = a[aIdx--];
            } else {
                a[totalIdx--] = b[bIdx--];
            }
        }
    }
}
