package recursion101;

import java.util.*;

class MergeSortMyway {
    public void main() {
        int[] arr = { 1, 5, 3, 2, 8, 7, 6, 4 };
        mergeSort(arr);
    }

    public void mergeSort(int[] arr) {
        divideArr(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void divideArr(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + (right - left) / 2;
        divideArr(arr, left, mid);
        divideArr(arr, mid + 1, right);
        swap(arr, left, right);
    }

    void swap(int[] arr, int left, int right) {
        int pivot = left;
        while (pivot <= right) {
            for (int i = left; i <= right; i++) {
                if (arr[pivot] < arr[i]) {
                    int tmp = arr[pivot];
                    arr[pivot] = arr[i];
                    arr[i] = tmp;
                }
            }
            pivot++;
        }
    }

}

class MergeSortLeetCode {
    int[] merge_sort(int[] arr) {
        if (arr.length == 1)
            return arr;
        int pivot = arr.length / 2;
        int[] left_list = merge_sort(Arrays.copyOfRange(arr, 0, pivot));
        int[] right_list = merge_sort(Arrays.copyOfRange(arr, pivot, arr.length));
        return merge(left_list, right_list);
    }

    int[] merge(int[] left_list, int[] right_list) {
        int[] ret = new int[left_list.length + right_list.length];
        int left_cursor = 0, right_cursor = 0, ret_cursor = 0;

        while (left_cursor < left_list.length && right_cursor < right_list.length) {
            if (left_list[left_cursor] < right_list[right_cursor]) {
                ret[ret_cursor++] = left_list[left_cursor++];
            } else {
                ret[ret_cursor++] = right_list[right_cursor++];
            }
        }
        while (left_cursor < left_list.length) {
            ret[ret_cursor++] = left_list[left_cursor++];
        }
        while (right_cursor < right_list.length) {
            ret[ret_cursor++] = right_list[right_cursor++];
        }
        return ret;
    }
}

class MergeSortZeroBase {
    void mergeSort(int[] arr, int[] tmp, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, tmp, left, mid);
            mergeSort(arr, tmp, mid + 1, right);
            merge(arr, tmp, left, right, mid);
        }
    }

    void merge(int[] arr, int[] tmp, int left, int right, int mid) {
        int p = left;
        int q = mid + 1;
        int idx = p;
        while (p <= mid || q <= right) {
            if (p <= mid && q <= right) {
                if (arr[p] <= arr[q]) {
                    tmp[idx++] = arr[p++];
                } else {
                    tmp[idx++] = arr[q++];
                }
            } else if (p <= mid && q > right) {
                tmp[idx++] = arr[p++];
            } else {
                tmp[idx++] = arr[q++];
            }
        }

        for (int i = left; i <= right; i++) {
            arr[i] = tmp[i];
        }
    }
}

class QuickSortZeroBase {
    void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = parition(arr, left, right);
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    int parition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (arr[j] > pivot && i < j)
                j--;
            while (arr[i] <= pivot && i < j)
                i++;
            swap(arr, i, j);
        }
        swap(arr, left, i);
        return i;
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}