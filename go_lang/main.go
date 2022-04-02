package main

import (
	"bufio"
	"fmt"
	"os"
)

/**
reader := bufio.NewReader(os.Stdin) 라인 하나 다받아오기
text, _ := reader.ReadString('\n')
split := strings.Fields(text)
*/

func bj2750() {
	var n int
	quickSort := func(arr []int, start, end int) {}
	quickSort = func(arr []int, start, end int) {
		if start >= end {
			return
		}
		pivot := start
		left := start + 1
		right := end

		for left <= right {
			for left <= end && arr[left] <= arr[pivot] {
				left++
			}
			for right > start && arr[right] >= arr[pivot] {
				right--
			}
			if left > right {
				arr[right], arr[pivot] = arr[pivot], arr[right]
			} else {
				arr[left], arr[right] = arr[right], arr[left]
			}
		}
		quickSort(arr, start, right-1)
		quickSort(arr, right+1, end)
	}

	reader := bufio.NewReader(os.Stdin)
	fmt.Fscanln(reader, &n)

	arr := make([]int, n)
	for i, _ := range arr {
		var num int
		fmt.Fscanln(reader, &num)
		arr[i] = num
	}
	// sort.Ints(arr)
	quickSort(arr, 0, len(arr)-1)
	for _, v := range arr {
		fmt.Println(v)
	}
}

func main() {
	bj2750()
}
