package greedy101;

class ContainerFillWater {
    public int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int minVal = Math.min(height[left], height[right]);
            max = Math.max(minVal * (right - left), max);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        System.out.println(max);
        return max;
    }

    public int maxArea2(int[] height) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp = Math.min(height[i], height[j]) * (j - i);
                max = Math.max(temp, max);
            }
        }
        return max;
    }
}