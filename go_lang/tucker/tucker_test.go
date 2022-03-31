package tucker

import (
	"fmt"
	"sync"
	"testing"
	"time"
)

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
	const expected = 500000500000
	arr := make([]int, 1000000)
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

	t.Run("Go Routine", func(t *testing.T) {
		startTime := time.Now()
		answer := goRoutine(&arr)
		elapsed := time.Since(startTime)

		if answer != expected {
			t.Errorf("Want :%d,Got: %d", expected, answer)
		}
		fmt.Printf("%d\n", elapsed)
	})

}
