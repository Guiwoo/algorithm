package algoCrackMid;

public class FindFirstAndLastPosition {
    class Solution {
        public int[] searchRange(int[] arr, int target) {
            int left = -1;
            int right = -1;

            int nLeft = 0;
            int nRight = arr.length - 1;
            int idx = -1;
            while (nLeft <= nRight) {
                int mid = nLeft + (nRight - nLeft) / 2;
                if (arr[mid] < target) {
                    nLeft = mid + 1;
                } else if (arr[mid] == target) {
                    idx = mid;
                    break;
                } else {
                    nRight = mid - 1;
                }
            }

            if (idx == -1)
                return new int[] { left, right };

            left = idx;
            right = idx;

            boolean leftMove = true;
            boolean rightMove = true;

            while (leftMove || rightMove) {
                while (leftMove) {
                    if (left - 1 >= 0 && arr[left - 1] == target) {
                        left--;
                    } else {
                        leftMove = false;
                    }
                }
                while (rightMove) {
                    if (right + 1 < arr.length && arr[right + 1] == target) {
                        right++;
                    } else {
                        rightMove = false;
                    }
                }
            }
            System.out.println(left + " " + right);
            return new int[] { left, right };
        }
    }
}
