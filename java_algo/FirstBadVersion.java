public class FirstBadVersion {
    public boolean isBadVersion(int n) {
        return n < 1 << 31;
    }

    public int myVer(int n) {
        int low = 1;
        int high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public int fasterWay(int n) {
        return bs(1, n);
    }

    public int bs(long start, long end) {
        if (end == 1) {
            return (int) end;
        }
        long mid = (start + end) / 2;
        int answer = 0;
        if (!isBadVersion((int) mid)) {
            if (isBadVersion((int) mid - 1)) {
                answer = (int) mid + 1;
            } else {
                return bs(mid + 1, end);
            }
        } else {
            if (!isBadVersion((int) mid - 1)) {
                answer = (int) mid;
            } else {
                return bs(start, mid - 1);
            }

        }
        return answer;
    }
}