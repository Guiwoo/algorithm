package main

import (
	"bufio"
	"fmt"
	"os"
	"sort"
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

func permutation_practice() {
	s := []int{1, 2, 3}
	visit := make([]bool, 4)
	checking := func(start int) {}

	result := []int{}

	checking = func(start int) {
		if start == 3 {
			fmt.Println(result)
		}
		for i, _ := range s {
			if !visit[i] {
				visit[i] = true
				result = append(result, i+1)
				checking(start + 1)
				visit[start] = false
				result = result[:len(result)-1]
			}
		}
	}

	checking(0)
}
func bj2751() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscan(reader, &n)

	arr := make([]int, n)
	for i := range arr {
		fmt.Fscan(reader, &arr[i])
	}

	sort.Ints(arr)
	for _, v := range arr {
		writer.Write([]byte(fmt.Sprint(v) + "\n"))
	}
}
func bj10989() {
	var n int
	reader := bufio.NewReader(os.Stdin)
	writer := bufio.NewWriter(os.Stdout)
	defer writer.Flush()

	fmt.Fscan(reader, &n)
	arr := make([]int, 10001)

	for i := 0; i < n; i++ {
		var new int
		fmt.Fscan(reader, &new)
		arr[new] = arr[new] + 1
	}

	for j := 0; j < 10001; j++ {
		if arr[j] != 0 {
			for k := 0; k < arr[j]; k++ {
				fmt.Fprintln(writer, j)
			}
		}
	}
}
func main() {
	bj10989()
}
