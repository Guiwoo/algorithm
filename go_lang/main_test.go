package main

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

// func TestCarpet(t *testing.T) {
// 	insertNuminArrBySlicing := func(arr []int, index int, insert []int) ([]int, error) {
// 		if index > len(arr) {
// 			return nil, errors.New("index is out of range")
// 		}
// 		for i := 0; i < len(insert); i++ {
// 			arr = append(arr, insert[i])
// 		}
// 		copy(arr[index+len(insert):], arr[index:])
// 		for i := 0; i < len(insert); i++ {
// 			arr[index+i] = insert[i]
// 		}
// 		return arr, nil
// 	}
// 	arr := []int{1, 2, 3, 4, 5, 6, 7, 8, 9}
// 	insert := []int{10000, 2000}

// 	got, _ := insertNuminArrBySlicing(arr, 5, insert)
// 	want := []int{1, 2, 3, 4, 5, 10000, 2000, 6, 7, 8, 9}

// 	for i := range got {
// 		if got[i] != want[i] {
// 			t.Errorf("Want %q,but Got %q", want[i], got[i])
// 		}
// 	}
// }

var wg sync.WaitGroup

func forLoopSum(arr *[]int) (result int) {
	result = 0
	for _, v := range *arr {
		result += v
	}
	return result
}

func goRoutine(arr *[]int) int {
	new := *arr
	wg.Add(4)

	firstHalf := new[:len(new)/4]
	secondHalf := new[len(new)/4 : len(new)/4*2]
	third := new[len(new)/4*2 : len(new)/4*3]
	fourth := new[len(new)/4*3:]
	total := 0

	go func() {
		new := forLoopSum(&firstHalf)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&secondHalf)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&third)
		total += new
		wg.Done()
	}()
	go func() {
		new := forLoopSum(&fourth)
		total += new
		wg.Done()
	}()

	wg.Wait()
	return total
}

func TestList(t *testing.T) {
	const expected = 50000005000000
	arr := make([]int, 10000000)
	for i, _ := range arr {
		arr[i] = i + 1
	}
	t.Run("For loop", func(t *testing.T) {
		startTime := time.Now()
		answer := forLoopSum(&arr)
		elapsed := time.Since(startTime)

		if answer != expected {
			t.Errorf("Want :%d,Got: %d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})

	t.Run("Go Routine twice", func(t *testing.T) {
		startTime := time.Now()
		answer := goRoutine(&arr)
		elapsed := time.Since(startTime)

		if answer != expected {
			t.Errorf("Want :%d,Got: %d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})

}
